package com.company.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.app.model.User;
import com.company.app.repository.UsersRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



@Controller
public class HomeController {

	private final UsersRepository userRepository;
	
	private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public HomeController(UsersRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @GetMapping({"/", "/login"})
	public String login() {
		return "login";
	}
    
	@PostMapping({"/", "/login"})
	public String validate(@RequestParam("uname")String name,@RequestParam("pass")String password) {
		
		bCryptPasswordEncoder = new BCryptPasswordEncoder();
		List<User> userList = (List<User>) userRepository.findAll();
		for(User user :userList){
			if(user.getName().equalsIgnoreCase(name) && (bCryptPasswordEncoder.matches(password,user.getPassword()))){
				return "redirect:booking";
			}
		}	
		return "redirect:login";
	}	
	
	@GetMapping("/insert/{id}/{name}/{password}")
	@ResponseBody
	public String insert(@PathVariable int id,@PathVariable String name,@PathVariable String password) {
		
		bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = bCryptPasswordEncoder.encode(password);
		User user = new User(id, name, encodedPassword);
		userRepository.save(user);
		return "Saved Successfully";
	}
}
