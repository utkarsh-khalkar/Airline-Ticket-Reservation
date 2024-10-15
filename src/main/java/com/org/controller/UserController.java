package com.org.controller;

import com.org.model.User;
import com.org.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flight")
public class UserController {

    @Autowired
    private UserServices userServices;
    @GetMapping("/users")
    public List<User> getUser() {
        return userServices.getUser();
    }

    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        userServices.addOrUpdateUser(user);
        return ResponseEntity.ok("User added successfully");
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
