package com.aptikraft.spring.dal.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.aptikraft.spring.model.QuestionDO;
import com.aptikraft.spring.view.bean.QuestionBO;

public class QuestionProvider {

	private QuestionProvider() {
	}

	public static QuestionDO getQuestionFromBOToDO(QuestionBO questionBO) {
		if (questionBO == null)
			return null;
		QuestionDO questionDO = new QuestionDO();
		BeanUtils.copyProperties(questionBO, questionDO);
		return questionDO;
	}

	public static QuestionBO getQuestionFromDOToBO(QuestionDO questionDO) {
		if (questionDO == null)
			return null;
		QuestionBO questionBO = new QuestionBO();
		BeanUtils.copyProperties(questionDO, questionBO);
		return questionBO;
	}

	public static List<QuestionBO> getQuestionsFromDOToBO(List<QuestionDO> questionDOs) {
		if (questionDOs == null)
			return null;
		List<QuestionBO> questionBOs = new ArrayList<>();
		for (QuestionDO questionDO : questionDOs) {
			questionBOs.add(getQuestionFromDOToBO(questionDO));
		}
		return questionBOs;
	}

	public static List<QuestionDO> getQuestionsFromBOToDO(List<QuestionBO> questionBOs) {
		if (questionBOs == null)
			return null;
		List<QuestionDO> questionDOs = new ArrayList<>();
		for (QuestionBO questionBO : questionBOs) {
			questionDOs.add(getQuestionFromBOToDO(questionBO));
		}
		return questionDOs;
	}

}
