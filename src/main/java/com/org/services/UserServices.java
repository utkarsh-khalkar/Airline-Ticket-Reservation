package com.org.services;

import com.org.model.User;
import com.org.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServices {

    @Autowired
    private UserRepo userRepo;

    /*
     * Retrieves a user by their ID
     * Id of the user to retrieve
     *
     * Optional containing the user if found or empty if not
     */
    public Optional<User> getUserById(int id) {
        return  userRepo.findById(id);
    }
    /*
     * Saves a user
     * User to save
     * add or update user
     */
    public void addOrUpdateUser(User user) {
        userRepo.save(user);
    }

    /*
     * Retrieves all users
     *
     */
    public List<User> getUser() {
        return userRepo.findAll();
    }
    /*
     * Deletes a user
     * Id of the user to delete
     */
    public void deleteUserByID(int id)
    {
        User user=userRepo.findById(id).get();
        userRepo.deleteById(id);
    }


}
