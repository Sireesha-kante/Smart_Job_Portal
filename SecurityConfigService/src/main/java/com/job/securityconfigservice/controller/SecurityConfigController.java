package com.job.securityconfigservice.controller;


import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/config")
public class SecurityConfigController {
    
//    @Autowired
//    private ConfigFileWriter configFileWriter;
//    
//    @Value("${spring.cloud.config.server.git.uri}")
//    private String configRepoPath;
//    
    
    @GetMapping("/")
    public String welcome() {
    	return "here is the config server";
    }

//    @PostMapping(value = "/update-key", 
//                consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<String> updateJwtKey(@RequestBody Map<String, String> configData) {
//        try {
//            System.out.println("Received data: " + configData);
//            
//            if (!configData.containsKey("jwt.secret")) {
//                return ResponseEntity.badRequest()
//                    .body("{\"error\": \"Missing jwt.secret in payload\"}");
//            }
//            
//            // 1. Write to file
//            configFileWriter.writeConfig("UserService", configData);
//            
//            // 2. Manual Git operations
//            String gitResult = executeGitCommands(configData.get("jwt.secret"));
//            
//            return ResponseEntity.ok()
//                .body("{\"status\": \"success\", \"git\": \"" + gitResult + "\"}");
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError()
//                .body("{\"error\": \"" + e.getMessage().replace("\"", "'") + "\"}");
//        }
//    }
//    
//    private String executeGitCommands(String secret) throws Exception {
//        File repoDir = new File(configRepoPath.replace("file:///", ""));
//        System.out.println("Working directory: " + repoDir.getAbsolutePath());
//        
//        // Execute commands separately to ensure each completes
//        String[] commands = {
//            "git add .",
//            "git commit -m \"Updated JWT secret: " + secret + "\"",
//            "git status"
//        };
//        
//        StringBuilder result = new StringBuilder();
//        for (String cmd : commands) {
//            Process p = new ProcessBuilder()
//                .command("cmd.exe", "/c", cmd)
//                .directory(repoDir)
//                .start();
//            
//            // Consume both streams to prevent deadlock
//            String output = readStream(p.getInputStream());
//            String error = readStream(p.getErrorStream());
//            
//            int exitCode = p.waitFor();
//            result.append(cmd).append(": ")
//                  .append(exitCode == 0 ? "SUCCESS" : "FAILED")
//                  .append("\nOutput: ").append(output)
//                  .append("\nErrors: ").append(error)
//                  .append("\n\n");
//        }
//        return result.toString();
//    }
//    
//    private String readStream(InputStream inputStream) throws IOException {
//        try (BufferedReader reader = new BufferedReader(
//            new InputStreamReader(inputStream))) {
//            return reader.lines().collect(Collectors.joining("\n"));
//        }
//    }
}