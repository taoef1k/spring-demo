package com.example.spring.demo.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class CustomerUtil {

    public static String saveFile(String folder, MultipartFile file) throws IOException {

    	String prefix = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
    	StringBuilder fullPath= new StringBuilder(folder)
    			.append(prefix)
    			.append("_")
    			.append(file.getOriginalFilename());

        byte[] bytes = file.getBytes();
        Path path = Paths.get(fullPath.toString());
        Files.write(path, bytes);

        return path.getFileName().toString();

    }

    public static InputStream getStreamFile(String folder, String filename) throws FileNotFoundException {

    	String path= new StringBuilder(folder).append(filename).toString();
    	File file = new File(path);
        return new FileInputStream(file);

    }

    public static boolean deleteFile(String folder, String filename) {
    	String path= new StringBuilder(folder).append(filename).toString();
    	File fileToDelete = new File(path);
        return fileToDelete.delete();
    }

}
