package org.launcode.Code.Food.controller;

import org.launcode.Code.Food.models.Cuisine;
import org.launcode.Code.Food.models.User;
import org.launcode.Code.Food.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public String viewHomePage(Model model) {
        List<User> listUsers = userRepository.findAll();
        model.addAttribute("listUsers", listUsers);

        return "user/index";
    }

    @GetMapping("/register")
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new User());

        return "user/register";
    }

    @PostMapping("/register_success")
    public String processUserRegistration(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);
        return "user/register_success";
    }

//    @GetMapping("add")
////    @PreAuthorize("hasAuthority('recipe:write')")
//    public String displayAddUserForm(Model model) {
//        model.addAttribute(new User());
//        return "user/add";
//    }
//
//    @PostMapping("add")
////    @PreAuthorize("hasAuthority('recipe:write')")
//    public String processAddUserForm(@ModelAttribute @Valid User newUser,
//                                        Errors errors, Model model) {
//
//        if (errors.hasErrors()) {
//            return "user/add";
//        }
//        userRepository.save(newUser);
//        return "redirect:";
//    }
//
//    @GetMapping("delete")
//    @PreAuthorize("hasAuthority('recipe:write')")
//    public String displayDeleteUserForm(Model model){
//        model.addAttribute("title","Delete User");
//        model.addAttribute("cuisines",userRepository.findAll());
//        return "user/delete";
//    }

//    @GetMapping("view/{userId}")
//    public String displayViewUser(Model model, @PathVariable int userId) {
//
//        Optional<User> optUser = userRepository.findById(userId);
//        if (optUser.isPresent()) {
//            User user = (User) optUser.get();
//            model.addAttribute("user", user);
//            return "user/view";
//        } else {
//            return "redirect:../";
//        }
//    }

//    @PostMapping("delete")
//    @PreAuthorize("hasAuthority('recipe:write')")
//    public String deleteUsers(@RequestParam(required = false) int[] userIds){
//        if(userIds!=null) {
//            for (int id : userIds) {
//                userRepository.deleteById(id);
//            }
//        }
//        return "redirect:";
//    }

//    private static final List<User> USER = Arrays.asList(
//            new User(1, "James Bond"),
//            new User(2, "Maria Jones"),
//            new User(3, "Anna Smith")
//    );
//    //Manually create a list of user profiles
//
//    @GetMapping(path="/{userId}")
//    public User getUser(@PathVariable("userId") Integer userId) {
//        return User.stream()
//                .filter(user -> userId.equals(user.getUserId()))
//                .findFirst()
//                .orElseThrow(() -> new IllegalStateException("User" + userId + " does not exist"));
//    }
    //Look for a user in the above list via path -> /{userId}
}
