package com.daaje.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.component.commandbutton.CommandButton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.daaje.model.Iepp;
import com.daaje.model.NiveauAnimateur;
import com.daaje.service.Iservice;

@Component
public class IepController {
	@Autowired
	public Iservice iservice;
	public Iepp iep = new Iepp();
	public Iepp selectedObject = new Iepp();
	public List listObject = new ArrayList();
	
//Controle des composants
	public CommandButton cmdBModifier = new CommandButton();
	public CommandButton cmdBEnregistrer = new CommandButton();
		
//Methodes
	@PostConstruct
	public void initialisation(){
		this.cmdBModifier.setDisabled(true);
	}
	
	
	public void enregistrer(){
		iservice.addObject(this.iep);
		annuler();
		info("Enregistrement effectué");
	}
	
	public void modifier() {
		iservice.updateObject(iep);
		annuler();
		info("Modification effectuée");
	}
	
	public void annuler() {
		iep.setCodeIepp(null);
		iep.setNomIepp(null);
		iep.setMailIepp(null);
		cmdBEnregistrer.setDisabled(false);
		cmdBModifier.setDisabled(true);
		
	}
	
	public void selectionnerLigne() {
		iep = selectedObject;
		cmdBEnregistrer.setDisabled(true);
		cmdBModifier.setDisabled(false);
	}
	
	public void info(String message){
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,message,null));	
	}
		
//Getters and setters
	

	public List getListObject() {
		return listObject = iservice.getObjects("Iepp");
	}

	public void setListObject(List listObject) {
		this.listObject = listObject;
	}

	

	public CommandButton getCmdBModifier() {
		return cmdBModifier;
	}

	public void setCmdBModifier(CommandButton cmdBModifier) {
		this.cmdBModifier = cmdBModifier;
	}

	public CommandButton getCmdBEnregistrer() {
		return cmdBEnregistrer;
	}

	public void setCmdBEnregistrer(CommandButton cmdBEnregistrer) {
		this.cmdBEnregistrer = cmdBEnregistrer;
	}


	public Iepp getIep() {
		return iep;
	}


	public void setIep(Iepp iep) {
		this.iep = iep;
	}


	public Iepp getSelectedObject() {
		return selectedObject;
	}


	public void setSelectedObject(Iepp selectedObject) {
		this.selectedObject = selectedObject;
	}
	
	

}
