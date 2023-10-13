package com.daaje.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.component.commandbutton.CommandButton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.daaje.model.Fonction;
import com.daaje.service.Iservice;

@Component
public class FonctionControllers {
	@Autowired
	public Iservice iservice;
	public Fonction fonction = new Fonction();
	public Fonction selectedObject = new Fonction();
	public List listObject = new ArrayList();

//Controle des composants
	public CommandButton cmdBModifier = new CommandButton();
	public CommandButton cmdBEnregistrer = new CommandButton();
	
//Methodes
	@PostConstruct
	public void initialisation(){
		this.cmdBModifier.setDisabled(true);
		genererCode();
	}
	
	public void genererCode() {
		String prefix="";
		int nbEnregistrement = this.iservice.getObjects("Fonction").size();
		if(nbEnregistrement < 10)
			prefix = "FON00" ;
		if ((nbEnregistrement >= 10) && (nbEnregistrement < 100)) 
			prefix = "FON0" ;
		if (nbEnregistrement > 100) 
			prefix = "FON" ;
		this.fonction.setCodeFonction(prefix+(nbEnregistrement+1));
	}
	
	public void enregistrer(){
		iservice.addObject(fonction);
		annuler();
		info("Enregistrement effectué");
	}
		
	public void modifier() {
		iservice.updateObject(fonction);
		annuler();
		info("Modification effectuée");
	}
		
	public void annuler() {
		fonction.setCodeFonction(null);
		fonction.setLibelleFonction(null);
		cmdBEnregistrer.setDisabled(false);
		cmdBModifier.setDisabled(true);
		genererCode();
	}
		
	public void selectionnerLigne() {
		fonction = selectedObject;
	}
		
	public void info(String message){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message,null));	
	}

//Getters and setters
	public Fonction getFonction() {
		return fonction;
	}

	public void setFonction(Fonction fonction) {
		this.fonction = fonction;
	}

	public List getListObject() {
		return listObject = iservice.getObjects("Fonction");
	}

	public void setListObject(List listObject) {
		this.listObject = listObject;
	}

	public Fonction getSelectedObject() {
		return selectedObject;
	}

	public void setSelectedObject(Fonction selectedObject) {
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
