package com.ferraz.erp.util;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FacesMessages implements Serializable {
	
	private static final long serialVersionUID = 1L;
	

	public void info(String message) {
		add(message, FacesMessage.SEVERITY_INFO);
	}
	
	
	private void add(String message, FacesMessage.Severity severity) {
		FacesMessage facesMessage = new FacesMessage(message);
		facesMessage.setSeverity(severity);
		
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}

}
