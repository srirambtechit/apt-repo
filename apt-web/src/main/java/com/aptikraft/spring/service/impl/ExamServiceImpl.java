package com.aptikraft.spring.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aptikraft.spring.dal.utils.ExamProvider;
import com.aptikraft.spring.dao.ExamDAO;
import com.aptikraft.spring.service.ExamService;
import com.aptikraft.spring.view.bean.ExamBO;

@Service
public class ExamServiceImpl implements ExamService {

	private ExamDAO examDAO;

	public void setExamDAO(ExamDAO examDAO) {
		this.examDAO = examDAO;
	}

	@Override
	@Transactional
	public void addExam(ExamBO examBO) {
		this.examDAO.addExam(ExamProvider.getExamFromBOToDO(examBO));
	}

	@Override
	@Transactional
	public void updateExam(ExamBO examBO) {
		this.examDAO.updateExam(ExamProvider.getExamFromBOToDO(examBO));
	}

	@Override
	@Transactional
	public List<ExamBO> listExams() {
		return ExamProvider.getExamsFromDOToBO(this.examDAO.listExams());
	}

	@Override
	@Transactional
	public ExamBO getExamById(int id) {
		return ExamProvider.getExamFromDOToBO(this.examDAO.getExamById(id));
	}

	@Override
	@Transactional
	public void removeExam(int id) {
		this.examDAO.removeExam(id);
	}

}
