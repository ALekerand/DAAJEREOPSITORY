package com.daaje.controllers;

import java.util.ArrayList;
import java.util.List;

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
	public TypeActivite selectedObject = new TypeActivite();
	public List listObject = new ArrayList();
	
//Methodes
	public void enregistrer(){
		iservice.addObject(typeActivite);
		annuler();
		info("Enregistrement effectué");
	}
	
	public void annuler() {
		typeActivite.setCodeTypeactivite(null);
		typeActivite.setLibelleTypeactivite(null);
	}	
	
	public void selectionnerLigne() {
		typeActivite = selectedObject;
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

	public List getListObject() {
		return listObject = iservice.getObjects("TypeActivite");
	}

	public void setListObject(List listObject) {
		this.listObject = listObject;
	}

	public TypeActivite getSelectedObject() {
		return selectedObject;
	}

	public void setSelectedObject(TypeActivite selectedObject) {
		this.selectedObject = selectedObject;
	}
		
}
