package com.daaje.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.component.commandbutton.CommandButton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.daaje.model.Commune;
import com.daaje.service.Iservice;

@Component
public class CommuneController {
	@Autowired
	public Iservice iservice;
	public Commune commune = new Commune();
	public Commune selectedObject = new Commune();
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
		iservice.addObject(this.commune);
		annuler();
		info("Enregistrement effectué");
	}
	
	public void modifier() {
		iservice.updateObject(commune);
		annuler();
		info("Modification effectuée");
	}
		
	public void annuler() {
		commune.setCodeCommune(null);
		commune.setNomCommune(null);
		cmdBEnregistrer.setDisabled(false);
		cmdBModifier.setDisabled(true);
	}
		
	public void selectionnerLigne() {
		commune = selectedObject;
		cmdBEnregistrer.setDisabled(true);
		cmdBModifier.setDisabled(false);
	}
		
	public void info(String message){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,message,null));	
	}
			
//Getters and setters
	public Commune getCommune() {
		return commune;
	}

	public void setCommune(Commune commune) {
		this.commune = commune;
	}

	public List getListObject() {
		return listObject = iservice.getObjects("Commune");
	}

	public void setListObject(List listObject) {
		this.listObject = listObject;
	}

	public Commune getSelectedObject() {
		return selectedObject;
	}

	public void setSelectedObject(Commune selectedObject) {
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
