package jawa.classpath;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Optional;

public class DirEntry implements Entry {
    private String absDir;

    public DirEntry(String absDir) {
        if (!absDir.endsWith("\\"))
            absDir += "\\";
        this.absDir = absDir;
    }

    @Override
    public byte[] readClass(String className) {
        String fileName = absDir + className;
        File file = new File(fileName);
        ByteBuffer byteBuffer = null;
        try (InputStream in = new FileInputStream(file)) {
            try (FileChannel channel = ((FileInputStream) in).getChannel()) {
                byteBuffer = ByteBuffer.allocate((int) channel.size());
                while (channel.read(byteBuffer) > 0)
                    System.out.println("reading");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(byteBuffer).get().array();
    }

    @Override
    public String toString() {
        return "DirEntry{" +
                "absDir='" + absDir + '\'' +
                '}';
    }
}
