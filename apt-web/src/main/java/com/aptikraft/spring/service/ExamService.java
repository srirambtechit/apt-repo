package com.aptikraft.spring.service;

import java.util.List;

import com.aptikraft.spring.view.bean.ExamBO;

public interface ExamService {

	public void addExam(ExamBO examBO);

	public void updateExam(ExamBO examBO);

	public List<ExamBO> listExams();

	public ExamBO getExamById(int id);

	public void removeExam(int id);

}
