package com.aptikraft.spring.dao;

import java.util.List;

import com.aptikraft.spring.model.TestAnswerDO;

public interface TestAnswerDAO extends HibernateBulkOperationSupport<TestAnswerDO> {

    public void addTestAnswer(TestAnswerDO testAnswerDO);

    public void updateTestAnswer(TestAnswerDO testAnswerDO);

    public List<TestAnswerDO> listTestAnswers();

    public TestAnswerDO getTestAnswerById(int id);

    public void removeTestAnswer(int id);

    public List<TestAnswerDO> fetchTestAnswerByUserId(int userId);

}
