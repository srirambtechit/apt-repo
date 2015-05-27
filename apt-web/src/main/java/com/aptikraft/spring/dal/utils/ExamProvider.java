package com.aptikraft.spring.dal.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.aptikraft.spring.model.ExamDO;
import com.aptikraft.spring.view.bean.ExamBO;

public class ExamProvider {

	private ExamProvider() {
	}

	public static ExamDO getExamFromBOToDO(ExamBO examBO) {
		if (examBO == null)
			return null;
		ExamDO examDO = new ExamDO();
		BeanUtils.copyProperties(examBO, examDO);
		return examDO;
	}

	public static ExamBO getExamFromDOToBO(ExamDO examDO) {
		if (examDO == null)
			return null;
		ExamBO examBO = new ExamBO();
		BeanUtils.copyProperties(examDO, examBO);
		return examBO;
	}

	public static List<ExamBO> getExamsFromDOToBO(List<ExamDO> examDOs) {
		if (examDOs == null)
			return null;
		List<ExamBO> examBOs = new ArrayList<>();
		for (ExamDO examDO : examDOs) {
			examBOs.add(getExamFromDOToBO(examDO));
		}
		return examBOs;
	}

	public static List<ExamDO> getExamsFromBOToDO(List<ExamBO> examBOs) {
		if (examBOs == null)
			return null;
		List<ExamDO> examDOs = new ArrayList<>();
		for (ExamBO examBO : examBOs) {
			examDOs.add(getExamFromBOToDO(examBO));
		}
		return examDOs;
	}

}
