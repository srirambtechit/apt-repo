package com.aptikraft.spring.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.aptikraft.common.utils.ViewNameConstants;
import com.aptikraft.spring.ui.bean.JsonResponse;

@Controller
public class LoginController {

    /**
     * Spring security login validation and generate JSON response for either
     * successful login or failure login when clicking login form, action will
     * be /j_spring_security_check when clicking success form, action will be
     * j_spring_security_logout
     * 
     * @param error
     * @param logout
     * @param request
     * @return
     */
    @RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
    public @ResponseBody JsonResponse login(@RequestParam(value = "success", required = false) String success, @RequestParam(value = "error", required = false) String error, HttpServletRequest request) {
	System.out.println("MainController entered");

	JsonResponse jsonResponse = null;
	if (error != null) {
	    String errorMessage = getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION");
	    jsonResponse = new JsonResponse("error");
	    jsonResponse.setData(new ArrayList<Map<String, String>>());
	    Map<String, String> map = new HashMap<>();
	    map.put("name", "errorMessage");
	    map.put("value", errorMessage);
	    jsonResponse.getData().add(map);
	}

	if (success != null) {
	    jsonResponse = new JsonResponse("success");
	    jsonResponse.setData(new ArrayList<Map<String, String>>());
	    Map<String, String> map = new HashMap<>();
	    map.put("name", "url");
	    map.put("value", "instructionPage");
	    jsonResponse.getData().add(map);
	}

	System.out.println("MainController exited");
	return jsonResponse;

    }

    /**
     * Getting login page as response by GET request. Spring security for logout
     * operation. when clicking logout in any page which logout operation,
     * action will be j_spring_security_logout
     * 
     * @param error
     * @param logout
     * @param request
     * @return
     */
    @RequestMapping(value = "/loginPage", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {

	ModelAndView model = new ModelAndView();

	if (logout != null) {
	    model.addObject("msg", "You've been logged out successfully.");
	}
	model.setViewName(ViewNameConstants.LOGIN);

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
