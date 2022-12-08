package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.example.demo.entity.BaseEntity;
import com.example.demo.entity.ClassAdmin;
import com.example.demo.entity.ClassBatch;
import com.example.demo.entity.DeliveryManager;
import com.example.demo.entity.FAManager;
import com.example.demo.entity.FARec;
import com.example.demo.entity.Trainee;
import com.example.demo.entity.Trainer;
import com.example.demo.service.iservice.IDeliveryManagerService;
import com.example.demo.service.iservice.IEmailService;
import com.example.demo.service.iservice.IFAManagerService;
import com.example.demo.service.iservice.IFARecService;

@Service
public class EmailService implements IEmailService {

	@Autowired
	private JavaMailSender emailSender;
	
	@Autowired
	private IFAManagerService faManagerService;
	
	@Autowired
	private IFARecService faRecService;
	
	@Autowired
	private IDeliveryManagerService deliManagerService;
	
	@Override
	public MimeMessage generateEmailContent(ClassBatch classBatch, List<BaseEntity> listOfReceipients, String subjectText, String contentText, HttpServletRequest request) {
		MimeMessage message = emailSender.createMimeMessage();
		try {
			List<String> receipientsNames = addReceipientsAndGetTheirNames(message, listOfReceipients);
			// Goi mail toi em de test tinh nang mail
//			addRecipientsToMail(message, "nguyenminhkhoa.nmk98@gmail.com");
			//
			message.setSubject("[FA_MANAGEMENT]: The Class " + classBatch.getClassCode() + " " + subjectText);
			String content = "<html>" +
					"<head><title>"+"Title"+"</title></head>" +
					"<body>" +
					"Dear " + receipientsNames.toString() + " ,<br><br>" +			
					"The Class " + classBatch.getClassCode() + " - " + classBatch.getClassName() + " " + contentText + " <br>" +
					"To view Class details, please click <a href=\"http://" + request.getHeader("Host") + "/classBatch/view?classBatchId=" + classBatch.getId() + "\">here</a><br><br>" +
					"Sincerely,<br>" +
					"Application Admin.<br>" +
					"Note: This is an auto-generated email. Please do not reply." +
					"</body>" +
					"</html>";
			message.setContent(content, "text/html");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return message;
	}
	
	@Override
	public void sendEmailWhenSubmit(ClassBatch classBatch, HttpServletRequest request) {
		List<BaseEntity> listOfReceipients = new ArrayList<BaseEntity>();
		listOfReceipients.addAll(deliManagerService.findAll());
		emailSender.send(generateEmailContent(classBatch, listOfReceipients, "needs your approval.",
				"has been pending for your approval.", request));
	}

	@Override
	public void sendEmailWhenApprove(ClassBatch classBatch, HttpServletRequest request) {
		List<BaseEntity> listOfReceipients = new ArrayList<BaseEntity>();
		listOfReceipients.addAll(faManagerService.findAll());
		emailSender.send(generateEmailContent(classBatch, listOfReceipients, "needs your acceptance",
				"has been pending for your acceptance.", request));
	}

	@Override
	public void sendEmailWhenReject(ClassBatch classBatch, HttpServletRequest request) {
		List<BaseEntity> listOfReceipients = new ArrayList<BaseEntity>();
		listOfReceipients.add(classBatch.getClassAdmin());
		listOfReceipients.add(classBatch.getMasterTrainer());
		listOfReceipients.addAll(classBatch.getSetOfTrainers());
		emailSender.send(generateEmailContent(classBatch, listOfReceipients, "has been rejected.",
				"has been rejected by Delivery Manager.", request));
	}

	@Override
	public void sendEmailWhenAccept(ClassBatch classBatch, HttpServletRequest request) {
		List<BaseEntity> listOfReceipients = new ArrayList<BaseEntity>();
		listOfReceipients.add(classBatch.getClassAdmin());
		listOfReceipients.add(classBatch.getMasterTrainer());
		listOfReceipients.addAll(classBatch.getSetOfTrainers());
		listOfReceipients.addAll(deliManagerService.findAll());
		emailSender.send(generateEmailContent(classBatch, listOfReceipients, "has been accepted.",
				"has been accepted by FA Manager.", request));
	}

	@Override
	public void sendEmailWhenDecline(ClassBatch classBatch, HttpServletRequest request) {
		List<BaseEntity> listOfReceipients = new ArrayList<BaseEntity>();
		listOfReceipients.add(classBatch.getClassAdmin());
		listOfReceipients.add(classBatch.getMasterTrainer());
		listOfReceipients.addAll(classBatch.getSetOfTrainers());
		listOfReceipients.addAll(deliManagerService.findAll());
		emailSender.send(generateEmailContent(classBatch, listOfReceipients, "has been declined.",
				"has been declined by FA Manager.", request));
	}

	@Override
	public void sendEmailWhenStart(ClassBatch classBatch, HttpServletRequest request) {
		List<BaseEntity> listOfReceipients = new ArrayList<BaseEntity>();
		listOfReceipients.add(classBatch.getClassAdmin());
		listOfReceipients.add(classBatch.getMasterTrainer());
		listOfReceipients.addAll(classBatch.getSetOfTrainers());
		listOfReceipients.addAll(classBatch.getSetOfTrainees());
		listOfReceipients.addAll(deliManagerService.findAll());
		emailSender.send(generateEmailContent(classBatch, listOfReceipients, "has been started.",
				"has been started by Class Admin.", request));
	}

	@Override
	public void sendEmailWhenFinish(ClassBatch classBatch, HttpServletRequest request) {
		List<BaseEntity> listOfReceipients = new ArrayList<BaseEntity>();
		listOfReceipients.addAll(deliManagerService.findAll());
		emailSender.send(generateEmailContent(classBatch, listOfReceipients, "has been finished.",
				"has been finished by Class Admin", request));
	}

	@Override
	public void sendEmailWhenClose(ClassBatch classBatch, HttpServletRequest request) {
		List<BaseEntity> listOfReceipients = new ArrayList<BaseEntity>();
		listOfReceipients.addAll(faManagerService.findAll());
		emailSender.send(generateEmailContent(classBatch, listOfReceipients, "has been closed.",
				"has been closed by Delivery Manager.", request));
	}

	@Override
	public void sendEmailWhenRequest(ClassBatch classBatch, HttpServletRequest request) {
		List<BaseEntity> listOfReceipients = new ArrayList<BaseEntity>();
		listOfReceipients.add(classBatch.getClassAdmin());
		emailSender.send(generateEmailContent(classBatch, listOfReceipients, "has been requested for more information.",
				"has been requested for more information by Delivery Manager", request));
	}

	@Override
	public void sendEmailWhenCancel(ClassBatch classBatch, HttpServletRequest request) {
		List<BaseEntity> listOfReceipients = new ArrayList<BaseEntity>();
		listOfReceipients.add(classBatch.getClassAdmin());
		listOfReceipients.add(classBatch.getMasterTrainer());
		listOfReceipients.addAll(classBatch.getSetOfTrainers());
		listOfReceipients.addAll(faManagerService.findAll());
		listOfReceipients.addAll(faRecService.findAll());
		listOfReceipients.addAll(deliManagerService.findAll());
		emailSender.send(generateEmailContent(classBatch, listOfReceipients, "has been cancelled.",
				"has been cancelled by Delivery Manager.", request));
	}
	
	@Override
	public void sendEmailWhenUpdate(ClassBatch classBatch, HttpServletRequest request) {
		List<BaseEntity> listOfReceipients = new ArrayList<BaseEntity>();
		listOfReceipients.add(classBatch.getClassAdmin());
		emailSender.send(generateEmailContent(classBatch, listOfReceipients, "is updated.",
				"has been updated.", request));
	}
	
	@Override
	public void sendEmailWhenCreate(ClassBatch classBatch, HttpServletRequest request) {
		List<BaseEntity> listOfReceipients = new ArrayList<BaseEntity>();
		listOfReceipients.add(classBatch.getClassAdmin());
		emailSender.send(generateEmailContent(classBatch, listOfReceipients, "is assigned to you.",
				"has been assigned to you.", request));
	}
	
	private void addRecipientsToMail(MimeMessage message, String emailAddress) throws MessagingException {
			message.addRecipients(Message.RecipientType.TO, emailAddress);
	}
	
	private <T extends BaseEntity> List<String> addReceipientsAndGetTheirNames(MimeMessage message,
			List<T> listOfReceipients) throws MessagingException {
		List<String> receipientNames = new ArrayList<String>();
		for (T receipient : listOfReceipients) {
			if (receipient instanceof ClassAdmin) {
				addRecipientsToMail(message, ((ClassAdmin) receipient).getClassAdminProfile().getEmail());
				receipientNames.add(((ClassAdmin) receipient).getClassAdminProfile().getFullName());
			} else if (receipient instanceof Trainer) {
				addRecipientsToMail(message, ((Trainer) receipient).getTrainerProfile().getEmail());
				receipientNames.add(((Trainer) receipient).getTrainerProfile().getFullName());
			} else if (receipient instanceof Trainee) {
				addRecipientsToMail(message, ((Trainee) receipient).getTraineeCandidateProfile().getEmail());
				receipientNames.add(((Trainee) receipient).getTraineeCandidateProfile().getFullName());
			} else if (receipient instanceof FAManager) {
				addRecipientsToMail(message, ((FAManager) receipient).getEmail());
				receipientNames.add(((FAManager) receipient).getFullName());
			} else if (receipient instanceof FARec) {
				addRecipientsToMail(message, ((FARec) receipient).getEmail());
				receipientNames.add(((FARec) receipient).getFullName());
			} else if (receipient instanceof DeliveryManager) {
				addRecipientsToMail(message, ((DeliveryManager) receipient).getEmail());
				receipientNames.add(((DeliveryManager) receipient).getFullName());
			}
		}
		return receipientNames;
	}

}
