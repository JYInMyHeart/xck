package classpath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CompositeEntry implements Entry {
    private String pathList;
    List<Entry> entryList;

    public CompositeEntry(String pathList) {
        this.pathList = pathList;
        String[] paths = pathList.split(",");
        entryList = Arrays.stream(paths)
                .map(Entry::newEntry)
                .collect(Collectors.toList());
    }

    public CompositeEntry() {
    }

    @Override
    public byte[] readClass(String className) {
        List<byte[]> result = entryList.stream()
                .map(e -> e.readClass(className))
                .collect(Collectors.toList());
        return result.stream().filter(e -> e != null && e.length > 0).collect(Collectors.toList()).get(0);
    }

    @Override
    public String toString() {
        return "CompositeEntry{" +
                "pathList='" + pathList + '\'' +
                ", entryList=" + entryList +
                '}';
    }
}
