package jawa.classpath;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;


public class WildcardEntry extends CompositeEntry {
    private String path;

    public WildcardEntry(String path) {
        this.path = path;
        File file = new File(path);
        if (file.isDirectory()) {
            entryList = Arrays.stream(Objects.requireNonNull(file.listFiles()))
                    .filter(f -> f.getName().endsWith(".jar") || f.getName().endsWith(".JAR"))
                    .map(e -> new ZipEntry(e.getAbsolutePath())).collect(Collectors.toList());
        }
    }
}
