package com.aptikraft.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aptikraft.common.utils.CurrentUser;
import com.aptikraft.common.utils.ViewNameConstants;
import com.aptikraft.spring.service.ExamService;
import com.aptikraft.spring.service.QuestionService;
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
    public String goToInstructionPage(Model model) {

	// Enabling active_login in DB to maintain one time login
	// throughout application life cycle.
	UserBO userBO = CurrentUser.getCurrentUserBO();
	userBO.setActiveLogin(true);
	getUserService().updateUser(userBO);

	int userId = userBO.getId();
	List<TestAnswerBO> testAnswerList = getTestAnswerService().fetchTestAnswerByUserId(userId);
	if (testAnswerList != null && !testAnswerList.isEmpty()) {
	    return ViewNameConstants.INSTRUCTIONS;
	} else {
	    // User is already taken the exam
	    return ViewNameConstants.REDIRECT_TO_INDEX;
	}
    }

}
