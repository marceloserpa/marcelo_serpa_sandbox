package com.mserpa.fileshare;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Storage {

    private final Path blobFolder;

    private final Map<String, String> registry = new HashMap<>();

    public Storage(Path blobFolder) throws IOException {
        this.blobFolder = blobFolder;
        Files.createDirectories(blobFolder);
    }

    public String save(Path fullPath) throws IOException {
        if (!Files.exists(fullPath)) {
            throw new IllegalArgumentException("File does not exist: " + fullPath);
        }

        String originalName = fullPath.getFileName().toString();
        String uuid = UUID.randomUUID().toString();

        Path destination = blobFolder.resolve(uuid);
        Files.copy(fullPath, destination, StandardCopyOption.COPY_ATTRIBUTES);

        registry.put(uuid, originalName);
        return uuid;
    }

    public String getOriginalName(String uuid) {
        return registry.get(uuid);
    }
}
