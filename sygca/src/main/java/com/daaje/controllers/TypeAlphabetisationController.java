package com.daaje.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.component.commandbutton.CommandButton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.daaje.model.TypeAlphabetisation;
import com.daaje.service.Iservice;

@Component
public class TypeAlphabetisationController {
	@Autowired
	public Iservice iservice;
	public TypeAlphabetisation typeAlphabetisation = new TypeAlphabetisation();
	public TypeAlphabetisation selectedObject = new TypeAlphabetisation();
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
		int nbEnregistrement = this.iservice.getObjects("TypeAlphabetisation").size();
		if(nbEnregistrement < 10)
			prefix = "ALPHA00" ;
		if ((nbEnregistrement >= 10) && (nbEnregistrement < 100)) 
			prefix = "ALPHA0" ;
		if (nbEnregistrement > 100) 
			prefix = "ALPHA" ;
		this.typeAlphabetisation.setCodeTypeAlpha(prefix+(nbEnregistrement+1));
	}
	
	public void enregistrer(){
		iservice.addObject(this.typeAlphabetisation);
		annuler();
		info("Enregistrement effectué");
	}
	
	public void modifier() {
		iservice.updateObject(typeAlphabetisation);
		annuler();
		info("Modification effectuée");
	}
	
	public void annuler() {
		typeAlphabetisation.setCodeTypeAlpha(null);
		typeAlphabetisation.setLibTypeAlpha(null);
		cmdBEnregistrer.setDisabled(false);
		cmdBModifier.setDisabled(true);
		genererCode();
		
	}
	
	public void selectionnerLigne() {
		typeAlphabetisation = selectedObject;
		cmdBEnregistrer.setDisabled(true);
		cmdBModifier.setDisabled(false);
	}
	
	public void info(String message){
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,message,null));	
	}
		
//Getters and setters
	public TypeAlphabetisation getTypeAlphabetisation() {
		return typeAlphabetisation;
	}

	public void setTypeAlphabetisation(TypeAlphabetisation typeAlphabetisation) {
		this.typeAlphabetisation = typeAlphabetisation;
	}

	public List getListObject() {
		return listObject = iservice.getObjects("TypeAlphabetisation");
	}

	public void setListObject(List listObject) {
		this.listObject = listObject;
	}

	public TypeAlphabetisation getSelectedObject() {
		return selectedObject;
	}

	public void setSelectedObject(TypeAlphabetisation selectedObject) {
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
