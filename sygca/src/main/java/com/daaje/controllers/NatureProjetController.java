package com.daaje.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.component.commandbutton.CommandButton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.daaje.model.NatureProjet;
import com.daaje.service.Iservice;

@Component
public class NatureProjetController {
	@Autowired
	public Iservice iservice;
	public NatureProjet natureProjet = new NatureProjet();
	public NatureProjet selectedObject = new NatureProjet();
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
		iservice.addObject(this.natureProjet);
		annuler();
		info("Enregistrement effectué");
	}
	
	public void modifier() {
		iservice.updateObject(natureProjet);
		annuler();
		info("Modification effectuée");
	}
	
	public void annuler() {
		natureProjet.setCodeNatureProjet(null);
		natureProjet.setLibelleNatureProjet(null);
		cmdBEnregistrer.setDisabled(false);
		cmdBModifier.setDisabled(true);
		
	}
	
	public void selectionnerLigne() {
		natureProjet = selectedObject;
		cmdBEnregistrer.setDisabled(true);
		cmdBModifier.setDisabled(false);
	}
	
	public void info(String message){
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,message,null));	
	}
		
//Getters and setters
	public NatureProjet getNatureProjet() {
		return natureProjet;
	}

	public void setNatureProjet(NatureProjet natureProjet) {
		this.natureProjet = natureProjet;
	}

	public List getListObject() {
		return listObject = iservice.getObjects("NatureProjet");
	}

	public void setListObject(List listObject) {
		this.listObject = listObject;
	}

	public NatureProjet getSelectedObject() {
		return selectedObject;
	}

	public void setSelectedObject(NatureProjet selectedObject) {
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
