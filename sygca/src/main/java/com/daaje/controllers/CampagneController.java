package com.daaje.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.component.commandbutton.CommandButton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.daaje.model.Campagne;
import com.daaje.service.Iservice;

@Component
public class CampagneController {
	@Autowired
	public Iservice iservice;
	public Campagne campagne = new Campagne();
	public Campagne selectedObject = new Campagne();
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
		iservice.addObject(this.campagne);
		annuler();
		info("Enregistrement effectué");
	}
	
	public void modifier() {
		iservice.updateObject(campagne);
		annuler();
		info("Modification effectuée");
	}
	
	public void annuler() {
		campagne.setCodeCampagne(null);
		campagne.setDebutCampagne(null);
		campagne.setFinCampagne(null);
		campagne.setLibelleCampagne(null);
		campagne.setEtatCampagne(null);
		cmdBEnregistrer.setDisabled(false);
		cmdBModifier.setDisabled(true);
		
	}
	
	public void selectionnerLigne() {
		campagne = selectedObject;
		cmdBEnregistrer.setDisabled(true);
		cmdBModifier.setDisabled(false);
	}
	
	public void info(String message){
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,message,null));	
	}
		
//Getters and setters
	public Campagne getCampagne() {
		return campagne;
	}

	public void setCampagne(Campagne campagne) {
		this.campagne = campagne;
	}

	public List getListObject() {
		return listObject = iservice.getObjects("Campagne");
	}

	public void setListObject(List listObject) {
		this.listObject = listObject;
	}

	public Campagne getSelectedObject() {
		return selectedObject;
	}

	public void setSelectedObject(Campagne selectedObject) {
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
