package com.jnep.backend.web;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MainController {
    
    //Slf4j와 같음
    //Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/")
    public String hello(Model model){
        log.info("main Controller");
        return "main";
    }

    @GetMapping("/display")
    public ResponseEntity<Resource> display(@RequestParam("filename") String filename) {
        String path = "C:\\JNEP_FILE_UPLOAD\\";
        
        System.out.println("path + folder + filename" + path + filename);
        
        Resource resource = new FileSystemResource(path + filename);
        HttpHeaders header = new HttpHeaders();
        
        try{
            if(!resource.exists()){
                return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
            }
                Path filePath = Paths.get(path + filename);
                header.add("Content-type", Files.probeContentType(filePath));
        }catch(IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
    }
}