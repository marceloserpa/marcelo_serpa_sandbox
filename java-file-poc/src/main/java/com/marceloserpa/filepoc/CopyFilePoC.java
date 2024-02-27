package com.marceloserpa.filepoc;

import java.io.*;
import java.nio.channels.FileChannel;

public class CopyFilePoC {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Hello world!");

        String from = args[0];
        String to = args[1];

        System.out.println("from: " + from);
        System.out.println("to: " + to);

        copyFile(from, to);

        Thread.sleep(2000);

        zeroCopyFile(from, to);

    }

    private static void zeroCopyFile(String from, String to) throws FileNotFoundException {
        long start = System.currentTimeMillis();

        try(FileChannel origin = new FileInputStream(from).getChannel();
            FileChannel dest = new FileOutputStream(to).getChannel()) {
            origin.transferTo(0, origin.size(), dest);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;

        System.out.println("zero copy" + timeElapsed + " ms");
    }

    private static void copyFile(String from, String to) throws IOException {
        long start = System.currentTimeMillis();

        byte[] data = new byte[8 * 1024];

        long bytesToCopy = new File(from).length();
        long bytesCopied = 0;

        try (FileInputStream fis = new FileInputStream(from);
             FileOutputStream fos = new FileOutputStream(to)) {

            while (bytesCopied < bytesToCopy) {
                fis.read(data);
                fos.write(data);
                bytesCopied += data.length;
            }
            fos.flush();
        }

        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;

        System.out.println("copy" + timeElapsed + " ms");
    }
}