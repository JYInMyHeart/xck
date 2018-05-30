package jawa.classpath;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
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
            ZipInputStream inputstream = new ZipInputStream(new FileInputStream(new File(absPath)));
            while (inputstream.available() == 1) {
                java.util.zip.ZipEntry ze = inputstream.getNextEntry();
                assert ze != null;
                if (Optional.ofNullable(ze).isPresent() && Optional.ofNullable(ze).get().getName().equals(className)) {
                    return readAllBytes(zipFile.getInputStream(ze));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    public static byte[] readAllBytes(InputStream inputStream) throws IOException {
        final int DEFAULT_BUFFER_SIZE = 2048;
        final int MAX_BUFFER_SIZE = 8192;
        byte[] buf = new byte[DEFAULT_BUFFER_SIZE];
        int capacity = buf.length;
        int nread = 0;
        int n;
        for (;;) {
            // read to EOF which may read more or less than initial buffer size
            while ((n = inputStream.read(buf, nread, capacity - nread)) > 0)
                nread += n;

            // if the last call to read returned -1, then we're done
            if (n < 0)
                break;

            // need to allocate a larger buffer
            if (capacity <= MAX_BUFFER_SIZE - capacity) {
                capacity = capacity << 1;
            } else {
                if (capacity == MAX_BUFFER_SIZE)
                    throw new OutOfMemoryError("Required array size too large");
                capacity = MAX_BUFFER_SIZE;
            }
            buf = Arrays.copyOf(buf, capacity);
        }
        return (capacity == nread) ? buf : Arrays.copyOf(buf, nread);
    }

    @Override
    public String toString() {
        return "ZipEntry{" +
                "absPath='" + absPath + '\'' +
                '}';
    }
}
