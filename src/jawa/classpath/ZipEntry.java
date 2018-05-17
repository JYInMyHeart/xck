package jawa.classpath;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class ZipEntry implements Entry {
    private String absPath;

    public ZipEntry(String absPath) {
        this.absPath = absPath;
    }

    @Override
    public byte[] readClass(String className) {
        try {
            ZipFile zipFile = new ZipFile(absPath);
            InputStream inputstream = new ZipInputStream(new FileInputStream(new File(absPath)));
            while (inputstream.available() == 1) {
                java.util.zip.ZipEntry ze = ((ZipInputStream) inputstream).getNextEntry();
                if (Optional.ofNullable(ze).isPresent() && Optional.ofNullable(ze).get().getName().equals(className)) {

//                    List<Byte> list = new ArrayList<>();
//                    InputStream in = zipFile.getInputStream(ze);
//                    int b;
//                    while( (b = in.read()) != -1){
//                        list.add((byte)b);
//                    }
//                    byte[] bytes = new byte[list.size()];
//                    for (int i = 0;i < list.size();i++) {
//                        bytes[i] = list.get(i);
//                    }
//                    System.out.println(Arrays.toString(bytes));
                    return zipFile.getInputStream(ze).readAllBytes();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    @Override
    public String toString() {
        return "ZipEntry{" +
                "absPath='" + absPath + '\'' +
                '}';
    }
}
