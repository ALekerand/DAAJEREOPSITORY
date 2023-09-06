package com.daaje.controllers;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.daaje.model.NiveauAnimateur;
import com.daaje.service.Iservice;

@Component
public class NiveauAnimateurControllers {
	@Autowired
	public Iservice iservice;
	
	public NiveauAnimateur niveauAnimateur = new NiveauAnimateur();
	
//Methodes
	public void enregistrer(){
		iservice.addObject(niveauAnimateur);
		info("Enregistrement effectué");
	}
	
	public void info(String message){
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", message));	
	}
	
	
//Getters and setters
	public NiveauAnimateur getNiveauAnimateur() {
		return niveauAnimateur;
	}

	public void setNiveauAnimateur(NiveauAnimateur niveauAnimateur) {
		this.niveauAnimateur = niveauAnimateur;
	}

}
