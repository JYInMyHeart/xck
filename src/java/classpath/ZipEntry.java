package classpath;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
