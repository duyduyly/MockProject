package com.example.demo.service.iservice;

import java.util.List;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import com.example.demo.entity.BaseEntity;
import com.example.demo.entity.ClassBatch;

public interface IEmailService {

	MimeMessage generateEmailContent(ClassBatch classBatch, List<BaseEntity> listOfReceipients, String subjectText,
			String contentText, HttpServletRequest request);

	void sendEmailWhenSubmit(ClassBatch classBatch, HttpServletRequest request);

	void sendEmailWhenApprove(ClassBatch classBatch, HttpServletRequest request);

	void sendEmailWhenReject(ClassBatch classBatch, HttpServletRequest request);

	void sendEmailWhenAccept(ClassBatch classBatch, HttpServletRequest request);

	void sendEmailWhenDecline(ClassBatch classBatch, HttpServletRequest request);

	void sendEmailWhenStart(ClassBatch classBatch, HttpServletRequest request);

	void sendEmailWhenFinish(ClassBatch classBatch, HttpServletRequest request);

	void sendEmailWhenClose(ClassBatch classBatch, HttpServletRequest request);

	void sendEmailWhenRequest(ClassBatch classBatch, HttpServletRequest request);

	void sendEmailWhenCancel(ClassBatch classBatch, HttpServletRequest request);

	void sendEmailWhenUpdate(ClassBatch classBatch, HttpServletRequest request);

	void sendEmailWhenCreate(ClassBatch classBatch, HttpServletRequest request);

}
