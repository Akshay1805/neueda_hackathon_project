package com.BankApp.controller;



import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/user")
public class UserController
{

    @Operation(summary = "Create a new User")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Found the book",
//                    content = { @Content(mediaType = "application/json",
//                            schema = @Schema(implementation = Book.class)) }),
//            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
//                    content = @Content),
//            @ApiResponse(responseCode = "404", description = "Book not found",
//                    content = @Content) })
    @PostMapping("/create")
    boolean createUser(String userID,String FirstName,String LastName,String Email,String UpiID){

        return true;
    }
    @Operation(summary = "Verify a User")
    @PostMapping("/verify")
    boolean verifyUser(String userID,String Password){
        return true;
    }
}
