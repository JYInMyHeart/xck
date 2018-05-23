package jawa.classpath;

import java.io.File;
import java.util.Optional;

public class Classpath {
    private Entry bootClasspath;
    private Entry extClasspath;
    private Entry userClasspath;

    public Classpath() {
    }

    public static Classpath parse(String jreOption, String cpOption) throws Exception {
        Classpath cp = new Classpath();
        cp.parseBootAndExtClasspath(jreOption);
        cp.parseUserClasspath(cpOption);
        return cp;
    }

    private void parseBootAndExtClasspath(String jreOption) throws Exception {
        String jreDir = getJreDir(jreOption);
        this.bootClasspath = new WildcardEntry(jreDir + "/lib");
        this.extClasspath = new WildcardEntry(jreDir + "/lib/ext");
    }

    private String getJreDir(String jreOption) throws Exception {
        if (jreOption != null && new File(jreOption).exists())
            return jreOption;
        String jh = System.getenv("JAVA_HOME");
        if (jh != null)
            return jh.endsWith("/") ? jh + "jre" : jh + "/jre";
        throw new Exception("cant find jre folder!");
    }

    private void parseUserClasspath(String cpOption) {
        if (cpOption == null || cpOption.equals(""))
            cpOption = ".";
        this.userClasspath = Entry.newEntry(cpOption);
    }

    public byte[] readClass(String className) {
        if (className.startsWith("'")) {
            className = className.replace("'", "");
        }
        className += ".class";
        byte[] bootData = bootClasspath.readClass(className);
        if (Optional.ofNullable(bootData).isPresent()) {
            return bootData;
        }
        byte[] extData = extClasspath.readClass(className);
        if (Optional.ofNullable(extData).isPresent()) {
            return extData;
        }
        return userClasspath.readClass(className);


    }

    public String toString() {
        return "Classpath{" +
                "bootClasspath=" + bootClasspath + "\n" +
                ", extClasspath=" + extClasspath + "\n" +
                ", userClasspath=" + userClasspath +
                '}';
    }
}
