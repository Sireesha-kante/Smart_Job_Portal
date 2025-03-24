package com.job.userservice.controller;

import org.slf4j.Logger;


import org.springframework.web.bind.annotation.RequestBody;


import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.job.userservice.dto.AuthRequest;
import com.job.userservice.dto.RegisterJobSeekerRequest;
import com.job.userservice.dto.UpdateRequest;
import com.job.userservice.dto.UpdateUserProfile;
import com.job.userservice.exception.ResourceNotFoundException;
import com.job.userservice.exception.UserAlreadyExistsException;
import com.job.userservice.exception.UserNotFoundException;
import com.job.userservice.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/jobseeker")
@Tag(name = "JobSeeker APIs", description = "Operations for JobSeeker")
public class UserController {
    
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    
    /*
    * Registers a new recruiter.
    * Returns a token and user role if the operation is successful.
    */
   @Operation(
           summary = "Register a Jobseeker",
           description = "Registers a new jobseeker by providing details like name, email, password,phone number, location and resume soon details.",
           tags = {"JobSeeker APIs"}
       )
       @ApiResponses({
           @ApiResponse(responseCode = "200", description = "JobSeeker registered successfully"),
           @ApiResponse(responseCode = "400", description = "if user already exist"),
           @ApiResponse(responseCode = "500", description = "if any  internal server issues occur")
       })
    @PostMapping("/register")
    public ResponseEntity<?> registerJobSeeker(@RequestBody RegisterJobSeekerRequest registerRequest){
    	try {
    		 System.out.println("Received Request: " + registerRequest.getEmail());
    		System.out.println("Received Password: " + registerRequest.getPassword());
    		return ResponseEntity.ok(userService.registerUser(registerRequest));
    	}
    	catch (UserAlreadyExistsException ex) {
			logger.error("User already exists: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		} 
    	catch (Exception ex) {
    		if (registerRequest.getPassword() == null || registerRequest.getPassword().isEmpty()) {
    		    throw new IllegalArgumentException("Password cannot be null or empty");
    		}
            logger.error("Error during JobSeeker registration", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong. Please try again.");
        }
    }
   /*
   * Login a JobSeeker.
   * Returns a token and user role if the operation is successful.
   */
  @Operation(
          summary = "Login a jobseeker",
          description = "Login jobseeker by providing details like email, password.",
          tags = {"JobSeeker APIs"}
      )
      @ApiResponses({
          @ApiResponse(responseCode = "200", description = "Jobseeker login successfully"),
          @ApiResponse(responseCode = "401", description = "if user doesnt exist /invalid credentials"),
          @ApiResponse(responseCode = "500", description = "if any  internal server issues occur")
      })
    
    @PostMapping("/login")
    public ResponseEntity<?> loginJobSeeker(@RequestBody AuthRequest authRequest) {
        try {
            System.out.println(authRequest.getEmail());
            return ResponseEntity.ok(userService.loginUser(authRequest));
        } 
        catch (UserNotFoundException ex) {
            logger.error("Login failed: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found Re-login / Register");
        }
        catch(Exception e) {
        	logger.error("Error during recruiterlogin", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong. Please try again.");
        }
    }
  
 
    @PatchMapping("/update")
    @PreAuthorize("hasAuthority('ROLE_JOB_SEEKER')")
    public ResponseEntity<?> updateUserDetails(@RequestBody UpdateRequest updateRequest ){
    	try {
    		return ResponseEntity.ok(userService.updateUser(updateRequest));
    	}
    	 catch (ResourceNotFoundException ex) {
             logger.error("Profile not found: {}", ex.getMessage());
             return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
         } catch (Exception ex) {
             logger.error("Error fetching profile", ex);
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving profile");
         }
    	
    }
    
    
    /*
     * Getting existing jobseeker profile details.
     * Returns jobseeker complete details  if the operation is successful.
     */
    @Operation(
    		summary =" get jobseeker profile",
            description = "get jobseeker  profile by providing details jobseekerId through path variable.",
            tags = {"JobSeeker APIs"}
            )
    @ApiResponses({
    	 @ApiResponse(responseCode = "200", description = "jobseeker profile displayed sucessully"),
         @ApiResponse(responseCode = "401", description = "if the jobseeker id profile is not found"),
         @ApiResponse(responseCode = "500", description = "if any  internal server issues occur")
    })
    @GetMapping("/profile/{userId}")
    @PreAuthorize("hasAuthority('ROLE_JOB_SEEKER')")
    public ResponseEntity<?> getJobSeekerProfile(@PathVariable Long userId) {
        try {
            return ResponseEntity.ok(userService.getUserProfile(userId));
        } catch (ResourceNotFoundException ex) {
            logger.error("Profile not found: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            logger.error("Error fetching profile", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving profile");
        }
    }
    
    /*
     * updating existing Jobseeker profile details.
     * Returns Jobseeker complete updated details  if the operation is successful.
     */
    @Operation(
    		summary ="update Jobseeker profile",
            description = "updates Jobseeker profile by providing the updated details of Jobseeker through requestParam.",
            tags = {"JobSeeker APIs"}
            )
    @ApiResponses({
    	 @ApiResponse(responseCode = "200", description = "Jobseeker profile updated sucessully"),
         @ApiResponse(responseCode = "401", description = "if the Jobseeker profile is not found"),
         @ApiResponse(responseCode = "500", description = "if any  internal server issues occur")
    })
    @PutMapping("/profile/update")
    @PreAuthorize("hasAuthority('ROLE_JOB_SEEKER')")
    public ResponseEntity<?> updateJobSeekerProfile(@RequestBody UpdateUserProfile updateRequest) {
        try {
            return ResponseEntity.ok(userService.updateUserProfile(updateRequest));
        } catch (ResourceNotFoundException ex) {
            logger.error("Profile update failed: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            logger.error("Error updating profile", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating profile");
        }
    }
    
    
    /*
     * getting  jobseeker details by providing Jwt Token.
     * Returns recruiter complete details  if the operation is successful.
     */
    @Operation(
    		summary ="getting  jobseeker details by providing Jwt Token.",
            description = "getting jobseeker details by providing the jwt token.",
            tags = {"JobSeeker APIs"}
            )
    @ApiResponses({
    	 @ApiResponse(responseCode = "200", description = "Jwt token sucessful"),
         @ApiResponse(responseCode = "401", description = "if any authorization token is missing or invalid"),
         @ApiResponse(responseCode = "500", description = "if any  internal server issues occur")
    })
    @GetMapping("/getUserDetails")
    public ResponseEntity<?> getUserDetails(@RequestHeader(value = "Authorization", required = false) String token) {
        try {
        	System.out.println("Received Token: " + token); // Debugging

            if (token == null || !token.startsWith("Bearer ")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authorization header missing or invalid");
            }

            // Remove "Bearer " prefix
            String extractedToken = token.substring(7);
            return userService.getUserDetails(extractedToken);
        }
        catch (Exception e) {
			// TODO: handle exception
        	logger.error("Error getting  profile details", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching updating profile");
        	
		}
    }
    
    /*
     * deleting  jobseeker from db by providing email an d password.
     * Returns deletion sucess message  if the operation is successful.
     */
    @Operation(
    		summary ="deleting  jobseeker from db.",
            description = " deleting jobseeker details  providing email an d password.",
            tags = {"JobSeeker APIs"}
            )
    @ApiResponses({
    	 @ApiResponse(responseCode = "200", description = "user deletion sucessful"),
         @ApiResponse(responseCode = "401", description = "if deleting user is missing or invalid"),
         @ApiResponse(responseCode = "500", description = "if any  internal server issues occur")
    })
    @DeleteMapping
    @PreAuthorize("hasAuthority('ROLE_JOB_SEEKER')")
    public ResponseEntity<String> deleteUser(@RequestBody AuthRequest authRequest){
    	try {
    		return ResponseEntity.ok(userService.deleteUserDetails(authRequest));
    	}catch(UserNotFoundException ex) {
    		logger.error("User deletion failed: {}", ex.getMessage());
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    	}catch (Exception e) {
			// TODO: handle exception
    		logger.error("invalid error during deletion", e);
    		return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("server error");
		}
    }
    
    
    
    
}
