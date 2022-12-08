package com.example.demo.form;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.demo.entity.Audit;
import com.example.demo.entity.Trainer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuditFormOfClassBatch {
	
	private Integer id;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;

	
	private String eventCategoty;

	
	private Trainer relatedPartyPeople;

	private Integer action;

	
	private Trainer pic;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date deadline;

	private String auditNote;
	
	public AuditFormOfClassBatch(Audit form) {		
		this.setId(form.getId());
		this.setDate(form.getDate());
		this.setEventCategoty(form.getEventCategoty());
		this.setRelatedPartyPeople(form.getRelatedPartyPeople());
		this.setAction(form.getAction());
		this.setPic(form.getPic());
		this.setDeadline(form.getDeadline());
		this.setAuditNote(form.getNote());
	}
}
