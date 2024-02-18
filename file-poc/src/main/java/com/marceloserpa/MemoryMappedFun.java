package com.marceloserpa;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

public class MemoryMappedFun {
    private static final String FILE = "./metrics.txt";

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        RandomAccessFile file = new RandomAccessFile(FILE, "rw");
        FileChannel channel = file.getChannel();

        // Read file into mapped buffer
        MappedByteBuffer mbb =
                channel.map(FileChannel.MapMode.READ_WRITE,
                        0,          // position
                        channel.size());

        System.out.println("Reading content and printing ... ");

        char a;
        List<String> lines = new ArrayList<>();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < channel.size(); i++) {
            a = (char) mbb.get();
            if( a == '\n') {
                lines.add(sb.toString());

                sb =  new StringBuffer();
            }

            sb.append(a);
            System.out.print(a);
        }

        System.out.println("--------------");

        System.out.println(lines);


        channel.close();
        file.close();
        System.out.println("Total read and print time: " + (System.currentTimeMillis() - startTime));
    }
}
