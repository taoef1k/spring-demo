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
	
    private static final String UPLOAD_FOLDER= "/Users/mohdtaufik/Documents/upload/";
	
    public static String saveFile(MultipartFile file) throws IOException {
    	
    	String prefix = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
    	StringBuilder fullPath= new StringBuilder(UPLOAD_FOLDER)
    			.append(prefix)
    			.append("_")
    			.append(file.getOriginalFilename());
    	
        byte[] bytes = file.getBytes();
        Path path = Paths.get(fullPath.toString());
        Files.write(path, bytes);
        
        return path.getFileName().toString();

    }
    
    public static InputStream getStreamFile(String filename) throws FileNotFoundException {

    	String path= new StringBuilder(UPLOAD_FOLDER).append(filename).toString();
    	File file = new File(path);
        return new FileInputStream(file);

    }
    
    public static boolean deleteFile(String filename) {
    	String path= new StringBuilder(UPLOAD_FOLDER).append(filename).toString();
    	File fileToDelete = new File(path);
        return fileToDelete.delete();
    }
    
}
