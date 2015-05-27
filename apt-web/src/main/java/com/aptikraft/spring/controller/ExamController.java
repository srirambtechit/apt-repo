package com.aptikraft.spring.controller;

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
import com.aptikraft.spring.service.UserService;
import com.aptikraft.spring.view.bean.UserBO;

@Controller
public class ExamController {

	private UserService userService;

	private ExamService examService;

	private QuestionService questionService;

	@Autowired(required = true)
	@Qualifier(value = "userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserService getUserService() {
		return userService;
	}

	@Autowired(required = true)
	@Qualifier(value = "examService")
	public void setExamService(ExamService examService) {
		this.examService = examService;
	}

	public ExamService getExamService() {
		return examService;
	}

	@Autowired(required = true)
	@Qualifier(value = "questionService")
	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	public QuestionService getQuestionService() {
		return questionService;
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

		return ViewNameConstants.INSTRUCTIONS;
	}

}
