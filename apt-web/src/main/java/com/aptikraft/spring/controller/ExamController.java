package com.aptikraft.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
// import
// org.springframework.security.authentication.AnonymousAuthenticationToken;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.aptikraft.common.utils.CurrentUser;
import com.aptikraft.common.utils.ViewNameConstants;
import com.aptikraft.spring.service.TestAnswerService;
import com.aptikraft.spring.service.UserService;
import com.aptikraft.spring.view.bean.TestAnswerBO;
import com.aptikraft.spring.view.bean.UserBO;

@Controller
public class ExamController {

    private UserService userService;

    private TestAnswerService testAnswerService;

    @Autowired(required = true)
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
	this.userService = userService;
    }

    public UserService getUserService() {
	return userService;
    }

    @Autowired(required = true)
    @Qualifier(value = "testAnswerService")
    public void setTestAnswerService(TestAnswerService testAnswerService) {
	this.testAnswerService = testAnswerService;
    }

    public TestAnswerService getTestAnswerService() {
	return testAnswerService;
    }

    /**
     * Page to be redirected after successful login in which we are maintaining
     * activeLogin flag to avoid duplicate login
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/instructionPage", method = RequestMethod.GET)
    public ModelAndView goToInstructionPage() {

	ModelAndView model = new ModelAndView();

	// One time login
	// Authentication auth =
	// SecurityContextHolder.getContext().getAuthentication();
	// if (!(auth instanceof AnonymousAuthenticationToken)) {
	// UserDetails userDetail = (UserDetails) auth.getPrincipal();
	// String username = userDetail.getUsername();
	// UserBO userBO = getUserService().findByUserName(username);
	// if(userBO.isActiveLogin()) {
	// model.addObject("error", userBO.getUsername() +
	// " is already login, Please logout previous session");
	// model.setViewName(ViewNameConstants.LOGIN);
	// return model;
	// }
	// }

	UserBO userBO = CurrentUser.getCurrentUserBO();

	int userId = userBO.getId();
	List<TestAnswerBO> testAnswerList = getTestAnswerService().fetchTestAnswerByUserId(userId);
	if (testAnswerList != null && !testAnswerList.isEmpty()) {
	    // User is already taken the exam
	    model.addObject("msg", userBO.getUsername() + " is already taken exam");
	    model.setViewName(ViewNameConstants.LOGIN);
	} else {
	    model.setViewName(ViewNameConstants.INSTRUCTIONS);
	}

	// Enabling active_login in DB to maintain one time login throughout
	// application life cycle. Updating active login status once pass
	// through above scenarios
	userBO.setActiveLogin(true);
	getUserService().updateUser(userBO);

	return model;
    }

}