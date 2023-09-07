package com.daaje.controllers;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.daaje.model.TypeActivite;
import com.daaje.service.Iservice;

@Component
public class TypeActiviteControllers {
	@Autowired
	public Iservice iservice;
	
	public TypeActivite typeActivite =  new TypeActivite();
	
//Methodes
	public void enregistrer(){
		iservice.addObject(typeActivite);
		info("Enregistrement effectué");
	}
	
	public void info(String message){
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", message));	
	}

//Getters and setters
	public TypeActivite getTypeActivite() {
		return typeActivite;
	}

	public void setTypeActivite(TypeActivite typeActivite) {
		this.typeActivite = typeActivite;
	}
		
}
