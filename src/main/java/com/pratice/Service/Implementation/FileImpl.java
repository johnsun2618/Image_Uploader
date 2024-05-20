package com.pratice.Service.Implementation;

import com.pratice.Service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileImpl implements FileService {

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {

        //File Name
        String name = file.getOriginalFilename();

//        Random name generator file
        String randomID = UUID.randomUUID().toString();
        String fileName1 = randomID.concat(name.substring(name.lastIndexOf(".")));

        //Full Path
        String filePath = path + File.separator+fileName1;

        //Create folder if not created
        File f = new File(path);
        if (!f.exists()){
            f.mkdir();
        }

        // File copy
        Files.copy(file.getInputStream(), Paths.get(filePath));


        return name;
    }

}
