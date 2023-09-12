package com.daaje.controllers;

import java.util.ArrayList;
import java.util.List;

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
	public NiveauAnimateur selectedObject = new NiveauAnimateur();
	public List listObject = new ArrayList();
	
	
//Methodes
	public void enregistrer(){
		iservice.addObject(niveauAnimateur);
		annuler();
		info("Enregistrement effectué");
	}
	
	public void annuler() {
		niveauAnimateur.setCodeNiveau(null);
		niveauAnimateur.setNomNiveau(null);
	}
	
	public void selectionnerLigne() {
		niveauAnimateur = selectedObject;
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

	public List getListObject() {
		return listObject = iservice.getObjects("NiveauAnimateur");
	}

	public void setListObject(List listObject) {
		this.listObject = listObject;
	}

	public NiveauAnimateur getSelectedObject() {
		return selectedObject;
	}

	public void setSelectedObject(NiveauAnimateur selectedObject) {
		this.selectedObject = selectedObject;
	}

}
