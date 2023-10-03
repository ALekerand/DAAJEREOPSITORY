package com.daaje.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.component.commandbutton.CommandButton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.daaje.model.Activite;
import com.daaje.service.Iservice;

@Component
public class ActiviteController {
@Autowired
	public Iservice iservice;
	public Activite activite = new Activite();
	public Activite selectedObject = new Activite();
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
		iservice.addObject(this.activite);
		annuler();
		info("Enregistrement effectué");
	}
	
	public void modifier() {
		iservice.updateObject(activite);
		annuler();
		info("Modification effectuée");
	}
	
	public void annuler() {
		activite.setCodeActivite(null);
		activite.setNomActivite(null);
		cmdBEnregistrer.setDisabled(false);
		cmdBModifier.setDisabled(true);
		
	}
	
	public void selectionnerLigne() {
		activite = selectedObject;
		cmdBEnregistrer.setDisabled(true);
		cmdBModifier.setDisabled(false);
	}
	
	public void info(String message){
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,message,null));	
	}
		
//Getters and setters
	public Activite getActivite() {
		return activite;
	}

	public void setActivite(Activite activite) {
		this.activite = activite;
	}

	public List getListObject() {
		return listObject = iservice.getObjects("Activite");
	}

	public void setListObject(List listObject) {
		this.listObject = listObject;
	}

	public Activite getSelectedObject() {
		return selectedObject;
	}

	public void setSelectedObject(Activite selectedObject) {
		this.selectedObject = selectedObject;
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

}
