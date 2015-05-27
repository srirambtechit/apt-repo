package com.aptikraft.spring.service;

import java.util.List;

import com.aptikraft.spring.view.bean.TestAnswerBO;

public interface TestAnswerService {

	public void addTestAnswer(TestAnswerBO testAnswerBO);

	public void updateTestAnswer(TestAnswerBO testAnswerBO);

	public List<TestAnswerBO> listTestAnswers();

	public TestAnswerBO getTestAnswerById(int id);

	public void removeTestAnswer(int id);

}
