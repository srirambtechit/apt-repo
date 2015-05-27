package com.aptikraft.spring.dal.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.aptikraft.spring.model.TestAnswerDO;
import com.aptikraft.spring.view.bean.TestAnswerBO;

public class TestAnswerProvider {

	private TestAnswerProvider() {
	}

	public static TestAnswerDO getTestAnswerFromBOToDO(TestAnswerBO testAnswerBO) {
		if (testAnswerBO == null)
			return null;
		TestAnswerDO testAnswerDO = new TestAnswerDO();
		BeanUtils.copyProperties(testAnswerBO, testAnswerDO);
		return testAnswerDO;
	}

	public static TestAnswerBO getTestAnswerFromDOToBO(TestAnswerDO testAnswerDO) {
		if (testAnswerDO == null)
			return null;
		TestAnswerBO testAnswerBO = new TestAnswerBO();
		BeanUtils.copyProperties(testAnswerDO, testAnswerBO);
		return testAnswerBO;
	}

	public static List<TestAnswerBO> getTestAnswersFromDOToBO(List<TestAnswerDO> testAnswerDOs) {
		if (testAnswerDOs == null)
			return null;
		List<TestAnswerBO> testAnswerBOs = new ArrayList<>();
		for (TestAnswerDO testAnswerDO : testAnswerDOs) {
			testAnswerBOs.add(getTestAnswerFromDOToBO(testAnswerDO));
		}
		return testAnswerBOs;
	}

	public static List<TestAnswerDO> getTestAnswersFromBOToDO(List<TestAnswerBO> testAnswerBOs) {
		if (testAnswerBOs == null)
			return null;
		List<TestAnswerDO> testAnswerDOs = new ArrayList<>();
		for (TestAnswerBO testAnswerBO : testAnswerBOs) {
			testAnswerDOs.add(getTestAnswerFromBOToDO(testAnswerBO));
		}
		return testAnswerDOs;
	}

}
