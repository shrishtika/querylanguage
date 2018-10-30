package com.cybage.controller;



import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cybage.dao.ProductDaoImpl;
import com.cybage.dao.UserDaoImpl;
import com.cybage.model.ProductDetail;
import com.cybage.model.UserDetail;

@Controller
public class LoginController {
	
	/*UserServiceImpl userservice=new UserServiceImpl();
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView dispalyLogin(HttpServletRequest request, HttpServletResponse response){
		ModelAndView model=new ModelAndView("UserDetail");
		model.addObject("UserDetail", new UserDetail());
	//	userservice.userLogin(username, password);
		return model;		
	}
	@RequestMapping(value="/loginProcess")
	public String loginProcess(@ModelAttribute("loggedUser") UserDetail user){
		System.out.println("check user running");
		if(new UserDaoImpl().isExist(user))
			return "index";
		else
			return "login";
	}  */
	
	
/*	@RequestMapping(value="/")
	public String displayLog(Model model){
		System.out.println("Hello in ///");
		model.addAttribute("user", new UserDetail());
		
		System.out.println("user");
		return "login";
	}
	*/
	
	
	@RequestMapping(value="/login")
	public ModelAndView redirectToLogin()
	{
		System.out.println("redirect to login page");
		return  new ModelAndView("login","command",new UserDetail());
	}
	
	@RequestMapping(value="/loginprocess",method=RequestMethod.POST)
	public ModelAndView displayLogin(@ModelAttribute("user") UserDetail user, Model model){
		System.out.println("In login");
		model.addAttribute("user", new UserDetail());
		System.out.println("Working");
		
		if(new UserDaoImpl().isExist(user))
		{
			List<ProductDetail> products=new ProductDaoImpl().listProduct();
			model.addAttribute("product", products);
			return new ModelAndView("home", "command", new ProductDetail());
		}
		
		else 
			return new ModelAndView("login", "command", new UserDetail());
	}

	
	@RequestMapping(value="/register")
	public ModelAndView redirectToRegister()
	{
		System.out.println("redirect to register page");
		return  new ModelAndView("register","command",new UserDetail());
	}

	@RequestMapping(value="/createUser")
	public ModelAndView register(@ModelAttribute("user") UserDetail user, Model model){
		System.out.println("Hello user"+user.getUsername()+" "+user.getPassword());
		new UserDaoImpl().saveUser(user);
		model.addAttribute("createuser", new UserDetail());
		return new ModelAndView("login","command",new UserDetail());
	}
	/*
	@RequestMapping(value="/loginProcess")
	public String loginProcess(@ModelAttribute("loggedUser") UserDetail user){
		System.out.println("check user running");
		if(new UserDaoImpl().isExist(user))
			return "index";
		else
			return "login";
	}*/
}
