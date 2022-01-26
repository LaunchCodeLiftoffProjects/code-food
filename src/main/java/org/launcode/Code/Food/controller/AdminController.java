package org.launcode.Code.Food.controller;

import org.launcode.Code.Food.models.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("admin/api/v1/users")
public class AdminController {

    private static final List<User> USER = Arrays.asList(
            new User(1, "James Bond"),
            new User(2, "Maria Jones"),
            new User(3, "Anna Smith")
    );
    //Manually create a list of user profiles

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')") //Replaces the antMatcher for GET
    public List<User> getAllUsers() {
        System.out.println("getAllUsers");
        return USER;
    }
    //View users

    @PostMapping
    @PreAuthorize("hasAuthority('user:write')") //Replaces the antMatcher for POST
    public void registerNewUser(@RequestBody User user) {
        System.out.println("registerNewUser");
        System.out.println(user);
    }
    //Create new user

    @DeleteMapping(path = "{userId}")
    @PreAuthorize("hasAuthority('user:write')") //Replaces the antMatcher for DELETE
    public void deleteUser(@PathVariable("userId") Integer userId) {
        System.out.println("deleteUser");
        System.out.println(userId);
    }
    //Delete user

    @PutMapping(path = "{userId}")
    @PreAuthorize("hasAuthority('user:write')") //Replaces the antMatcher for PUT
    public void updateUser(@PathVariable("userId") Integer userId, @RequestBody User user) {
        System.out.println("updateUser");
        System.out.println(String.format("%s %s", userId, user));
    }
    //Update user
}
