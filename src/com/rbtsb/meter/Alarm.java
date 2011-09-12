package com.rbtsb.meter;

import java.util.Date;

public class Alarm extends BaseEntity {

    /**
     * 
     * user, who make this alarm record, possiblity is System. USER is key word in Oracle
     */

    private String user;
    
	private String errorCode;

    private String message;

    private String key;

    private String module;

    private String levels;

    private Boolean ack = false;

    private Date recordCreatedOn;

    private Date occuredOn;


    public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getLevels() {
		return levels;
	}

	public void setLevels(String levels) {
		this.levels = levels;
	}

	public Boolean getAck() {
		return ack;
	}

	public void setAck(Boolean ack) {
		this.ack = ack;
	}

	public Date getRecordCreatedOn() {
		return recordCreatedOn;
	}

	public void setRecordCreatedOn(Date recordCreatedOn) {
		this.recordCreatedOn = recordCreatedOn;
	}

	public Date getOccuredOn() {
		return occuredOn;
	}

	public void setOccuredOn(Date occuredOn) {
		this.occuredOn = occuredOn;
	}
}