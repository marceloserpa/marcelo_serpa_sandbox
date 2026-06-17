package com.mserpa.fileshare;

import java.io.IOException;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) throws IOException {
        Path blobStorage = Path.of("blob-storage");
        Path sampleFile = Path.of("samples-files", "my-poc.txt");

        Storage storage = new Storage(blobStorage);

        String uuid = storage.save(sampleFile);

        System.out.println("Uploaded: " + sampleFile);
        System.out.println("Stored as UUID: " + uuid);
        System.out.println("Original name: " + storage.getOriginalName(uuid));
    }
}
