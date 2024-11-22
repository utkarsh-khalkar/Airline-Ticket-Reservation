package com.org.controller;

import com.org.Exceptions.UserAlreadyExistsException;
import com.org.model.User;
import com.org.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/flight/users")
public class UserController {

    @Autowired
    private UserServices userServices;
    // http://localhost:8080/api/flight/users
    @GetMapping("/users")
    public List<User> getUser() {
        return userServices.getUser();
    }

    //http://localhost:8080/api/flight/users/register
    @PostMapping("/register")
    public ResponseEntity<?> addUser(@Valid @RequestBody User user)
    {
        try{
            userServices.registerUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "message","User registered successfully","userID",user.getUserId()));
        }catch (UserAlreadyExistsException e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error",e.getMessage()));

        }catch (Exception e)
        {
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error","An Expected error occurred"));
        }

    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserByID(@PathVariable int id)
    {
        try
        {
            if(id<0)
            {
                return ResponseEntity.status(400).body("Invalid ID");
            }else {
                return ResponseEntity.ok(userServices.getUserById(id));
            }
        }catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }



    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUserByID(@PathVariable int id)
    {
        try
        {
            if(id<0)
            {
                return ResponseEntity.status(400).body("Invalid ID");
            }else {
                userServices.deleteUserByID(id);
                return ResponseEntity.ok("User deleted successfully");
            }
        }catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
