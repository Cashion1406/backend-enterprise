package com.enterprise.backend.DTO;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUploadUtil {

    public static String savefile(String filename, MultipartFile multipartFile) throws IOException {

        Path uploadDir = Paths.get("uploaded-file");

        try (InputStream inputStream = multipartFile.getInputStream()) {


            Path filepath = uploadDir.resolve(filename);

            Files.copy(inputStream, filepath, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            throw new IOException("Cant upload " + filename, null);
        }

        return filename;
    }
}
