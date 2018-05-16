package classpath;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class WildcardEntry extends CompositeEntry {
    private String path;

    public WildcardEntry(String path) {
        this.path = path;
        File file = new File(path);
        if(file.isDirectory()){
            entryList = Arrays.stream(Objects.requireNonNull(file.listFiles()))
                    .filter(f -> f.getName().endsWith(".jar") || f.getName().endsWith(".JAR"))
                    .map(e -> new ZipEntry(e.getAbsolutePath())).collect(Collectors.toList());
        }
    }
    private void walkFn(File path,List<Entry> list){
        if(!path.isDirectory()
                && (path.getName().endsWith(".jar") || path.getName().endsWith(".JAR"))){
            ZipEntry ze = new ZipEntry(path.getAbsolutePath());
            list.add(ze);
        }
    }


}
