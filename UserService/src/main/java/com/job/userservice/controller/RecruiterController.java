package com.job.userservice.controller;

import com.job.userservice.dto.AuthRequest;
import com.job.userservice.dto.RegisterRecruiterRequest;
import com.job.userservice.dto.UpdateRecruiterProfile;
import com.job.userservice.dto.UpdateRequest;
import com.job.userservice.exception.ResourceNotFoundException;
import com.job.userservice.exception.UserAlreadyExistsException;
import com.job.userservice.exception.UserNotFoundException;
import com.job.userservice.service.RecruiterService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/recruiter")
@Tag(name = "Recruiter APIs", description = "Operations for Recruiters")
public class RecruiterController {

    private static final Logger logger = LoggerFactory.getLogger(RecruiterController.class);

    @Autowired
    private RecruiterService recruiterService;

    
    /*
     * Registers a new recruiter.
     * Returns a token and user role if the operation is successful.
     */
    @Operation(
            summary = "Register a recruiter",
            description = "Registers a new recruiter by providing details like name, email, password,phone number, location and company details.",
            tags = {"Recruiter APIs"}
        )
        @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Recruiter registered successfully"),
            @ApiResponse(responseCode = "400", description = "if user already exist"),
            @ApiResponse(responseCode = "500", description = "if any  internal server issues occur")
        })
    @PostMapping("/register")
    public ResponseEntity<?> registerRecruiter(@RequestBody RegisterRecruiterRequest registerRequest) {
        try {
            return ResponseEntity.ok(recruiterService.registerRecruiter(registerRequest));
        } catch (UserAlreadyExistsException ex) {
            logger.error("User already exists: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        } catch (Exception ex) {
            logger.error("Error during recruiter registration", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong. Please try again.");
        }
    }
    
    
    /*
     * Login existing recruiter.
     * Returns a token and user role if the operation is successful.
     */
    @Operation(
    		summary ="Login a recruiter",
            description = "Login recruiter by providing details like email, password.",
            tags = {"Recruiter APIs"}
            )
    @ApiResponses({
    	 @ApiResponse(responseCode = "200", description = "Recruiter login successfully"),
         @ApiResponse(responseCode = "401", description = "if a not registered user tries to login"),
         @ApiResponse(responseCode = "500", description = "if any  internal server issues occur")
    })
    @PostMapping("/login")
    public ResponseEntity<?> loginRecruiter(@RequestBody AuthRequest authRequest) {
        try {
            return ResponseEntity.ok(recruiterService.loginRecruiter(authRequest));
        } catch (UserNotFoundException ex) {
            logger.error("Login failed: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found Re-login / Register");
        }
        catch(Exception e) {
        	logger.error("Error during recruiterlogin", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong. Please try again.");
        }
    }
    
    
    /*
     * Getting existing recruiter profile details.
     * Returns recruiter complete details  if the operation is successful.
     */
    @Operation(
    		summary =" get recruiter profile",
            description = "get recruiter profile by providing details recruiterid through path variable.",
            tags = {"Recruiter APIs"}
            )
    @ApiResponses({
    	 @ApiResponse(responseCode = "200", description = "Recruiter profile displayed sucessully"),
         @ApiResponse(responseCode = "401", description = "if the recruiter id profile is not found"),
         @ApiResponse(responseCode = "500", description = "if any  internal server issues occur")
    })
    @GetMapping("/profile/{userId}")
    @PreAuthorize("hasAuthority('ROLE_RECRUITER')")
    public ResponseEntity<?> getRecruiterProfile(@PathVariable Long userId) {
        try {
            return ResponseEntity.ok(recruiterService.getRecruiterProfile(userId));
        } catch (ResourceNotFoundException ex) {
            logger.error("Profile not found: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            logger.error("Error fetching profile", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving profile");
        }
    }
    
    
    /*
     * updating existing recruiter profile details.
     * Returns recruiter complete details  if the operation is successful.
     */
    @Operation(
    		summary ="update recruiter profile",
            description = "updates recruiter profile by providing the updated details of recruiter through requestParam.",
            tags = {"Recruiter APIs"}
            )
    @ApiResponses({
    	 @ApiResponse(responseCode = "200", description = "Recruiter profile updated sucessully"),
         @ApiResponse(responseCode = "401", description = "if the recruiter profile is not found"),
         @ApiResponse(responseCode = "500", description = "if any  internal server issues occur")
    })
    @PutMapping("/profile/update")
    @PreAuthorize("hasAuthority('ROLE_RECRUITER')")
    public ResponseEntity<?> updateRecruiterProfile(@RequestBody UpdateRecruiterProfile updateRequest) {
        try {
            return ResponseEntity.ok(recruiterService.updateRecruiterProfile(updateRequest));
        } catch (ResourceNotFoundException ex) {
            logger.error("Profile update failed: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            logger.error("Error updating profile", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating profile");
        }
    }
    
    /*
     * getting  recruiter details by providing Jwt Token.
     * Returns recruiter complete details  if the operation is successful.
     */
    @Operation(
    		summary ="getting  recruiter details by providing Jwt Token.",
            description = "getting recruiter details by providing the jwt token.",
            tags = {"Recruiter APIs"}
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
            return recruiterService.getUserDetails(extractedToken);
        }catch (Exception e) {
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
    		return ResponseEntity.ok(recruiterService.deleteUserDetails(authRequest));
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
