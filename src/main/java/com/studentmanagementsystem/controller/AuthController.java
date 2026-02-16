package com.studentmanagementsystem.controller;

import com.studentmanagementsystem.data.UserData;
import com.studentmanagementsystem.exceptions.EmailAlreadyExistsException;
import com.studentmanagementsystem.exceptions.UserAlreadyExistsException;
import com.studentmanagementsystem.servicefacade.UserServiceFacade;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class AuthController {

    private final UserServiceFacade userServiceFacade;

    public AuthController(UserServiceFacade userServiceFacade) {

        this.userServiceFacade = userServiceFacade;
    }

    @GetMapping
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }


    @GetMapping("/signup")
    public String signupPage(@ModelAttribute("userData") UserData userData) {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@Valid UserData userData,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        // Check if validation errors exist
        //bindingResults successfully binds form data to java object
        //If any valid rules are violated then bindingResult already has errors stored
        //and hence error message is shown instead of throwing exception
        if (bindingResult.hasErrors()) {
            // Return the signup page and show errors
            return "signup";
        }
        //Using Redirect attribute
        try {
            userServiceFacade.signup(userData);
        } catch (UserAlreadyExistsException e) {
            redirectAttributes.addFlashAttribute(
                    "message",
                    "Username Already Exists");
            return "redirect:/signup";
        } catch (EmailAlreadyExistsException e){
            redirectAttributes.addFlashAttribute(
                    "message",
                    "Email Already Exists");
            return "redirect:/signup";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(
                    "message",
                    "Unexpected Exception");
            return "redirect:/signup";
        }
        redirectAttributes.addFlashAttribute("message", "Signup successful!");
        return "redirect:/login";
        //Using Binding Result
//         try{
//             userServiceFacade.signup(userData);
//         }
//         catch (UserAlreadyExistsException e){
//            // This adds a **field error** to 'username' in BindingResult
//            bindingResult.rejectValue(
//                    "username",       // field name in UserData
//                    "error.userData", // error code
//                    "Username already exists" // Error message to show
//            );
//
//
//            return "signup";
//        }
    }

}