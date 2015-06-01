package com.aptikraft.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aptikraft.common.utils.ViewNameConstants;

@Controller
public class MainController {

    /**
     * Home page of this application's GET request
     * 
     * @return
     */
    @RequestMapping(value = { "/" }, method = RequestMethod.GET)
    public ModelAndView defaultPage() {
	ModelAndView model = new ModelAndView();
	model.addObject("title", "Aptikraft Online Exam Application - LoginPage");
	model.setViewName(ViewNameConstants.LOGIN);
	return model;
    }

    /**
     * Administration page GET request
     * 
     * @return
     */
    @RequestMapping(value = "/admin**", method = RequestMethod.GET)
    public ModelAndView adminPage() {
	ModelAndView model = new ModelAndView();
	model.addObject("title", "Aptikraft Online Exam Application - AdministratorPage");
	model.addObject("message", "This page is for ROLE_ADMIN only!");
	model.setViewName(ViewNameConstants.ADMIN);
	return model;
    }

    /**
     * Spring security redirection to login after user logged out
     * 
     * @param error
     * @param logout
     * @param request
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error, @RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {
System.out.println("MainController entered");
	ModelAndView model = new ModelAndView();
	if (error != null) {
	    model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
	}

	if (logout != null) {
	    model.addObject("msg", "You've been logged out successfully.");
	}
	model.setViewName(ViewNameConstants.LOGIN);
System.out.println("MainController exited");
	return model;

    }

    /**
     * Spring security - user who don't have ROLE_ADMIN redirect to 403 access
     * denied page
     * 
     * @return
     */
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accesssDenied() {
	ModelAndView model = new ModelAndView();
	// check if user is login
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	if (!(auth instanceof AnonymousAuthenticationToken)) {
	    UserDetails userDetail = (UserDetails) auth.getPrincipal();
	    model.addObject("username", userDetail.getUsername());
	}
	model.setViewName(ViewNameConstants.ACCESS_DENIED);
	return model;

    }

    // customize the error message
    private String getErrorMessage(HttpServletRequest request, String key) {

	Exception exception = (Exception) request.getSession().getAttribute(key);

	String error = "";
	if (exception instanceof BadCredentialsException) {
	    error = "Invalid username and password!";
	} else if (exception instanceof LockedException) {
	    error = exception.getMessage();
	} else {
	    error = "Invalid username and password!";
	}

	return error;
    }

}