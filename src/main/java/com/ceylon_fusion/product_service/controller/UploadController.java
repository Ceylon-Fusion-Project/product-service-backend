package com.ceylon_fusion.product_service.controller;

import com.ceylon_fusion.product_service.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/upload")
@CrossOrigin
public class UploadController {
//    @PostMapping(value = "/upload-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<StandardResponse> uploadFile(
//            @RequestParam("file") MultipartFile file,
//            @RequestParam("type") String type
//    ) {
//        try {
//            String directory = type.equalsIgnoreCase("image") ?
//                    "D:/Final-Year-Project/Images/product_images/" :
//                    "D:/Final-Year-Project/Images/product_files/";
//
////            String fileName = file.getOriginalFilename();
////            Path filePath = Paths.get(directory + fileName);
////            Files.write(filePath, file.getBytes());
//
////            String originalFilename = file.getOriginalFilename();
////            Path filePath = Paths.get(directory, originalFilename);
////            Files.write(filePath, file.getBytes());
//
//            String originalFilename = file.getOriginalFilename();
//            int dotIndex = originalFilename.lastIndexOf('.');
//            if (dotIndex == -1) {
//                return new ResponseEntity<>(
//                        new StandardResponse(400, "Invalid file: no extension found", null),
//                        HttpStatus.BAD_REQUEST
//                );
//            }
//
//            String extension = originalFilename.substring(dotIndex);;
//            String newFileName = UUID.randomUUID().toString() + extension;
//            Path filePath = Paths.get(directory, newFileName);
//            Files.write(filePath, file.getBytes());
//
//
//            //String fileURL = (type.equalsIgnoreCase("image") ? "/images/" : "/files/") + fileName;
//            //String fileURL = (type.equalsIgnoreCase("image") ? "/images/" : "/files/") + originalFilename;
//            String fileURL = (type.equalsIgnoreCase("image") ? "/images/" : "/files/") + newFileName;
//
//
//            return new ResponseEntity<>(
//                    new StandardResponse(200, "File uploaded successfully", fileURL),
//                    HttpStatus.OK
//            );
//        } catch (IOException e) {
//            e.printStackTrace();// for debugging purposes
//            return new ResponseEntity<>(
//                    new StandardResponse(500, "Upload failed", null),
//                    HttpStatus.INTERNAL_SERVER_ERROR
//            );
//        }
//    }

@PostMapping(value = "/upload-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
public ResponseEntity<StandardResponse> uploadFile(
        @RequestParam("file") MultipartFile file,
        @RequestParam("type") String type
) {
    try {
        // Save all certificate files (images or not) in product_files
        String directory = type.equalsIgnoreCase("image") ?
                "D:/Final-Year-Project/Images/product_images/" :
                "D:/Final-Year-Project/Images/product_files/";

        String originalFilename = file.getOriginalFilename();

        System.out.println("Uploaded filename: " + originalFilename);


        if (originalFilename == null || !originalFilename.matches(".*\\.[a-zA-Z0-9]{2,6}$")) {
            return new ResponseEntity<>(
                    new StandardResponse(400, "Invalid file: no extension found", null),
                    HttpStatus.BAD_REQUEST
            );
        }

        String extension = originalFilename.substring(originalFilename.lastIndexOf('.'));
        String newFileName = UUID.randomUUID().toString() + extension;
        Path filePath = Paths.get(directory, newFileName);
        Files.write(filePath, file.getBytes());

        String fileURL = (type.equalsIgnoreCase("image") ? "/images/" : "/files/") + newFileName;

        return new ResponseEntity<>(
                new StandardResponse(200, "File uploaded successfully", fileURL),
                HttpStatus.OK
        );
    } catch (IOException e) {
        e.printStackTrace();
        return new ResponseEntity<>(
                new StandardResponse(500, "Upload failed", null),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}



}
