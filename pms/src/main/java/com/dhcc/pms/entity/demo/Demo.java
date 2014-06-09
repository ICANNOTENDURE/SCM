package com.dhcc.pms.entity.demo;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;


/**
 * The persistent class for the T_DEMO database table.
 * 
 */
@Entity
@Table(name="T_DEMO")
public class Demo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name="idGenerator",strategy="uuid")
	@GeneratedValue(generator="idGenerator")
	@Column(name="DEMO_ID", unique=true, nullable=false, length=32)
	private String demoId;

	@Column(name="DEMO_NAME", nullable=false, length=20)
	private String demoName;
	
	@Column(name="DEMO_GENDER", nullable=false, length=20)
	private String demoGender;
	
	@Column(name="DEMO_AGE", nullable=false, length=20)
	private Integer demoAge;

	public Demo() {
	}

	public String getDemoId() {
		return this.demoId;
	}

	public void setDemoId(String demoId) {
		this.demoId = demoId;
	}

	public String getDemoName() {
		return this.demoName;
	}

	public void setDemoName(String demoName) {
		this.demoName = demoName;
	}

	public String getDemoGender() {
		return demoGender;
	}

	public void setDemoGender(String demoGender) {
		this.demoGender = demoGender;
	}

	public Integer getDemoAge() {
		return demoAge;
	}

	public void setDemoAge(Integer demoAge) {
		this.demoAge = demoAge;
	}

}