package com.project01.project01.Controller;

import com.project01.project01.entity.UserDocument;
import com.project01.project01.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    @Autowired
    UserRepository repo;

    @RequestMapping ("/")
    public String homepage(){

        return "index.html";
    }


    @PostMapping("/profile")
    public String profile(@RequestParam String password, @RequestParam String username, Model m, RedirectAttributes ra){

        UserDocument ud = repo.findByusername(username);

        if( ud == null){
            ra.addFlashAttribute("warning","Username or Password was Wrong");
            return "redirect:/";
        }

        String name = ud.getUsername();
        String pass = ud.getPassword();

        if(!username.equals(name) || !password.equals(pass)){
            ra.addFlashAttribute("warning","Username or Password was Wrong");
            return "redirect:/";
        }

        m.addAttribute("aa",ud);


        return "profile.html";
    }

    @RequestMapping("/register")
    public String register(@ModelAttribute UserDocument d, RedirectAttributes ra){


        if(d.getUsername()!=null && d.getPassword() != null && d.getEmail() != null){

            UserDocument ud = repo.findByusername(d.getUsername());
            if(ud != null){
                ra.addFlashAttribute("warning","Username is already exists. Try a new username");
                return "redirect:/register";
            }

            repo.save(d);
            ra.addFlashAttribute("msg","Registration Successful");
            return "redirect:/register";
        }

        return "registration.html";
    }


}
