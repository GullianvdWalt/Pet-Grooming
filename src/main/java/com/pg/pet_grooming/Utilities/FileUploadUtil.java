/*
 * Created By Gullian Van Der Walt - 2020/10/05, 13:46

   Utility Class for saving files
 */
package com.pg.pet_grooming.Utilities;

import java.io.*;
import java.nio.file.*;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {
    
    public static void saveFile(String uploadDir, String fileName,
        MultipartFile multipartFile)throws IOException{
        // Get File path
        Path uploadPath = Paths.get(uploadDir);
        
        // Create Directory if it does not exist
        if(!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }
        
        try(InputStream inputStream = multipartFile.getInputStream()){
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }catch(IOException ioe){
            throw new IOException("Could not save image file: " + fileName, ioe);
        }
    }
}
