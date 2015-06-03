package com.aptikraft.spring.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.aptikraft.common.utils.CurrentUser;
import com.aptikraft.common.utils.ViewNameConstants;
import com.aptikraft.spring.service.ExamService;
import com.aptikraft.spring.service.QuestionService;
import com.aptikraft.spring.service.TestAnswerService;
import com.aptikraft.spring.service.UserService;
import com.aptikraft.spring.ui.bean.ExamWrapper;
import com.aptikraft.spring.ui.bean.JsonResponse;
import com.aptikraft.spring.ui.bean.Question;
import com.aptikraft.spring.view.bean.ExamBO;
import com.aptikraft.spring.view.bean.QuestionBO;
import com.aptikraft.spring.view.bean.TestAnswerBO;
import com.aptikraft.spring.view.bean.UserBO;

@Controller
public class QuestionController {

    private UserService userService;

    private ExamService examService;

    private QuestionService questionService;

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
	return ViewNameConstants.EXAM;
    }

    @RequestMapping(value = "/getExamDetailsInJSON", method = RequestMethod.GET)
    public @ResponseBody ExamWrapper getExamDetailsInJSON() {
	ExamBO examBO = fetchExamDetails();

	ExamWrapper examWrapper = new ExamWrapper();
	examWrapper.setDuration(examBO.getDurationMinute());
	examWrapper.setNoOfQuestion(examBO.getNoOfQuestion());

	if (examBO != null) {
	    List<QuestionBO> questionBOs = fetchQuestionDetails(examBO);
	    if (questionBOs != null && !questionBOs.isEmpty()) {
		for (QuestionBO questionBO : questionBOs) {
		    Question question = prepareQuestion(questionBO);
		    if (question != null) {
			examWrapper.addQuestion(question);
		    }
		}
	    }
	}
	return examWrapper;
    }

    @RequestMapping(headers = "Content-Type=application/json", value = "/saveAnswerDetailsFromJSON", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody JsonResponse saveAnswerDetailsFromJSON(@RequestBody ExamWrapper examWrapper, HttpServletRequest request, HttpServletResponse response) {
	System.out.println(examWrapper);
	JsonResponse jsonResponse = null;

	if (examWrapper != null && !examWrapper.getQuestionList().isEmpty()) {
	    List<Question> questionList = examWrapper.getQuestionList();
	    List<Integer> questionIds = new ArrayList<>();
	    for (Question question : questionList) {
		questionIds.add(question.getId());
	    }

	    Map<Integer, String> qaMap = new HashMap<>();
	    List<QuestionBO> qaList = getQuestionService().listQuestionsByIds(questionIds);
	    if (qaList != null && !qaList.isEmpty()) {
		for (QuestionBO q : qaList) {
		    qaMap.put(q.getId(), q.getAnswer());
		}
	    }
	    List<TestAnswerBO> testAnswerBOs = new ArrayList<>();
	    for (Question question : examWrapper.getQuestionList()) {
		// Persisting question and answer in DB
		if (question != null) {
		    TestAnswerBO testAnswerBO = prepareTestAnswerBO(question);

		    // validating user entered answer with actual answer
		    if (qaMap != null && !qaMap.isEmpty()) {
			if (qaMap.get(question.getId()).equals(question.getAnswer())) {
			    // by default for all correct answer, mark will be 1
			    testAnswerBO.setWeightage(1.0f);
			}
		    }
		    testAnswerBOs.add(testAnswerBO);
		    // getTestAnswerService().addTestAnswer(testAnswerBO);
		}
	    }
	    // Adding all testAnswerBO in a single shot to improve performance
	    getTestAnswerService().addBulkTestAnswer(testAnswerBOs);

	    // Enabling active_login in DB to maintain one time login
	    // throughout application life cycle.
	    UserBO userBO = CurrentUser.getCurrentUserBO();
	    userBO.setActiveLogin(false);
	    getUserService().updateUser(userBO);

	    // Automatically logout once exam submitted.
	    logout(request, response);
	    jsonResponse = new JsonResponse("OK");
	} else {
	    jsonResponse = new JsonResponse("Not Found");
	    jsonResponse.setData(new ArrayList<Map<String, String>>());
	    Map<String, String> map = new HashMap<>();
	    map.put("name", "errorMessage");
	    map.put("value", "Problem Occurred");
	    jsonResponse.getData().add(map);
	}
	return jsonResponse;
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

    private Question prepareQuestion(QuestionBO questionBO) {
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
	question.setChoiceMap(choices);
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