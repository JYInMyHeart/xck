package classpath;

public interface Entry {
    byte[] readClass(String className);
    static Entry newEntry(String path){
        if(path.endsWith("*")){
            return new WildcardEntry(path.substring(0,path.length() - 2));
        }else if(path.endsWith("jar")
                || path.endsWith("JAR")
                || path.endsWith("zip")
                || path.endsWith("ZIP")){
            return new ZipEntry(path);
        } else if (path.contains(",")) {
            return new CompositeEntry(path);
        }else{
            return new DirEntry(path);
        }
    }
}
