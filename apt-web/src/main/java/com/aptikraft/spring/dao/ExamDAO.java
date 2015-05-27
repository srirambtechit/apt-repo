package com.aptikraft.spring.dao;

import java.util.List;

import com.aptikraft.spring.model.ExamDO;

public interface ExamDAO {

	public void addExam(ExamDO examDO);

	public void updateExam(ExamDO examDO);

	public List<ExamDO> listExams();

	public ExamDO getExamById(int id);

	public void removeExam(int id);

}
