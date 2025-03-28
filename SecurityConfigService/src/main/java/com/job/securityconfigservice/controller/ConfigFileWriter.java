//package com.job.securityconfigservice.controller;
//
//import java.nio.file.*;
//import java.io.IOException;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//@Component
//public class ConfigFileWriter {
//    
//    @Value("${spring.cloud.config.server.git.uri}")
//    private String gitUri;
//
//    public void writeConfig(String appName, Map<String,String> configData) throws IOException {
//        // Convert URI to Windows path
//        String winPath = gitUri.replace("file:///", "").replace("/", "\\");
//        Path repoPath = Paths.get(winPath);
//        
//        // Debugging output
//        System.out.println("Repo path: " + repoPath);
//        System.out.println("Directory exists: " + Files.exists(repoPath));
//        System.out.println("Is writable: " + Files.isWritable(repoPath));
//        
//        Path filePath = repoPath.resolve(appName + ".properties");
//        
//        // Create parent directories if needed
//        Files.createDirectories(filePath.getParent());
//        
//        // Write content
//        StringBuilder content = new StringBuilder();
//        configData.forEach((k,v) -> content.append(k).append("=").append(v).append("\n"));
//        
//        System.out.println("Writing to: " + filePath);
//        System.out.println("Content: " + content);
//        
//        Files.write(filePath, 
//                  content.toString().getBytes(),
//                  StandardOpenOption.CREATE,
//                  StandardOpenOption.TRUNCATE_EXISTING);
//        
//        System.out.println("Write successful");
//    }
//}