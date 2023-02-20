package com.ironhack.BankingSystem.controller.interfaces;



import com.ironhack.BankingSystem.model.users.User;

import java.util.List;

/**
 * Interface for UserController. Contains methods for handling user related operations
 */
public interface UserControllerInterface {
    /**
     * Retrieves a list of all users
     *
     * @return list of all users
     */
    List<User> getUsers();

    /**
     * Saves a new user
     *
     * @param user the user to be saved
     * @return the saved user
     */
    User saveUser(User user);
}
