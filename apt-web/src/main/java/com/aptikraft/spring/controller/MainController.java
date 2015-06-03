package com.aptikraft.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

}