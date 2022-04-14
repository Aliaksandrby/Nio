package by.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ReadMeFile {


    public static void saveToReadMeFile(String s) throws IOException {
        Path path = Paths.get("readme.txt");
        SeekableByteChannel fileChannel = Files.newByteChannel(path,StandardOpenOption.WRITE,StandardOpenOption.CREATE);
        ByteBuffer byteBuffer = ByteBuffer.allocate(s.length()).put(s.getBytes());
        byteBuffer.rewind();
        fileChannel.write(byteBuffer);
        fileChannel.close();
    }

    public static void saveToReadMeFile2(String s) throws IOException {
        Files.newByteChannel(
                        Paths.get("readme.txt"),
                        StandardOpenOption.WRITE,
                        StandardOpenOption.CREATE
                )
                .write(ByteBuffer.allocate(s.length()).put(s.getBytes()).rewind()); // added rewind();
    }

    public static String readFromReadMeFile(int sizeBuff) throws IOException {
        String s;
        ByteBuffer byteBuffer = ByteBuffer.allocate(sizeBuff);
        int size = Files.newByteChannel(Paths.get("readme.txt")).read(byteBuffer);
        byteBuffer.rewind(); // added rewind();
        byte[] bytes = new byte[size];
        byteBuffer.get(bytes);
        s = new String(bytes);
        return s;
    }

    public static void main(String[] args) throws IOException {
        String s = "Hello World";
        saveToReadMeFile2(s);
        String newStr = readFromReadMeFile(s.length());
        System.out.println(newStr);
    }

}