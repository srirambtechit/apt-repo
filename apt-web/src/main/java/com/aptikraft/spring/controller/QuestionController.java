package com.aptikraft.spring.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aptikraft.common.utils.CurrentUser;
import com.aptikraft.common.utils.ViewNameConstants;
import com.aptikraft.spring.service.ExamService;
import com.aptikraft.spring.service.QuestionService;
import com.aptikraft.spring.service.TestAnswerService;
import com.aptikraft.spring.view.bean.ExamBO;
import com.aptikraft.spring.view.bean.QuestionBO;
import com.aptikraft.spring.view.bean.TestAnswerBO;
import com.aptikraft.ui.form.Question;
import com.aptikraft.ui.form.QuestionWrapper;

@Controller("/")
public class QuestionController {

    private ExamService examService;

    private QuestionService questionService;

    private TestAnswerService testAnswerService;

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

    @Autowired(required = true)
    @Qualifier(value = "testAnswerService")
    public void setTestAnswerService(TestAnswerService testAnswerService) {
	this.testAnswerService = testAnswerService;
    }

    public TestAnswerService getTestAnswerService() {
	return testAnswerService;
    }

    // returns the ModelAttribute fooListWrapper with the view qa startExamPage
    @RequestMapping(value = { "/startExamPage" }, method = RequestMethod.GET)
    public String getForm(Model model) {
	ExamBO examBO = fetchExamDetails();

	// Map<Integer, List<String>> choiceMap = new HashMap<>();
	Map<Integer, Map<String, String>> choiceMap = new HashMap<>();
	QuestionWrapper questionWrapper = new QuestionWrapper();

	if (examBO != null) {
	    List<QuestionBO> questionBOs = fetchQuestionDetails(examBO);
	    if (questionBOs != null && !questionBOs.isEmpty()) {
		for (QuestionBO questionBO : questionBOs) {
		    Question question = prepareQuestion(questionBO, choiceMap);
		    if (question != null) {
			questionWrapper.add(question);
		    }
		}
	    }
	}

	model.addAttribute("questionWrapper", questionWrapper);
	model.addAttribute("choiceMap", choiceMap);

	return ViewNameConstants.QUESTION_ANSWER;
    }

    @RequestMapping(value = "/saveAnswer", method = RequestMethod.POST)
    public String postForm(@ModelAttribute("questionWrapper") QuestionWrapper questionWrapper, Model model, HttpServletRequest request, HttpServletResponse response) {
	for (Question question : questionWrapper.getQuestionList()) {
	    // Persisting question and answer in DB
	    TestAnswerBO testAnswerBO = prepareTestAnswerBO(question);
	    getTestAnswerService().addTestAnswer(testAnswerBO);
	}
	// Automatically logout once exam submitted.
	logout(request, response);
	// Redirecting to Home page
	return ViewNameConstants.REDIRECT_TO_INDEX;
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) {
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	if (auth != null) {
	    new SecurityContextLogoutHandler().logout(request, response, auth);
	}
	SecurityContextHolder.getContext().setAuthentication(null);
    }

    private TestAnswerBO prepareTestAnswerBO(Question question) {
	if (question == null)
	    return null;
	TestAnswerBO testAnswerBO = new TestAnswerBO();
	testAnswerBO.setAnswer(question.getAnswer());
	testAnswerBO.setQuestion(getQuestionService().getQuestionById(question.getId()));
	testAnswerBO.setUser(CurrentUser.getCurrentUserBO());
	return testAnswerBO;
    }

    private Question prepareQuestion(QuestionBO questionBO, Map<Integer, Map<String, String>> choiceMap) {
	if (questionBO == null) {
	    return null;
	}
	Question question = new Question();
	question.setId(questionBO.getId());
	question.setQuestion(questionBO.getQuestion());
	// Don't set answer value as
	// it appear as default selected value in Web Form
	// question.setAnswer(questionBO.getAnswer());
	Map<String, String> choices = new HashMap<>();
	choices.put("CHOICE_A", questionBO.getChoiceA());
	choices.put("CHOICE_B", questionBO.getChoiceB());
	choices.put("CHOICE_C", questionBO.getChoiceC());
	choices.put("CHOICE_D", questionBO.getChoiceD());
	choices.put("CHOICE_E", questionBO.getChoiceE());
	choiceMap.put(question.getId(), choices);
	return question;
    }

    private List<QuestionBO> fetchQuestionDetails(ExamBO examBO) {
	List<QuestionBO> questionBOs = null;
	if (examBO != null) {
	    questionBOs = getQuestionService().listQuestions();
	    int questionCount = examBO.getNoOfQuestion();
	    Collections.shuffle(questionBOs, new Random(questionCount));
	    if (questionBOs.size() > questionCount) {
		questionBOs = questionBOs.subList(0, questionCount + 1);
	    }
	}
	return questionBOs;
    }

    private ExamBO fetchExamDetails() {
	List<ExamBO> examBOs = getExamService().listExams();
	// Assuming that exam table have only one record for ever.
	if (examBOs != null && examBOs.size() == 1) {
	    return examBOs.get(0);
	}
	return null;
    }
}
