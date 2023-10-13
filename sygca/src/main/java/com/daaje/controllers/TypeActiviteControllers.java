package com.daaje.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.component.commandbutton.CommandButton;
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
	int nbEnregistrement = this.iservice.getObjects("TypeActivite").size();
	if(nbEnregistrement < 10)
		prefix = "TA00" ;
	if ((nbEnregistrement >= 10) && (nbEnregistrement < 100)) 
		prefix = "TA0" ;
	if (nbEnregistrement > 100) 
		prefix = "TA" ;
	this.typeActivite.setCodeTypeactivite(prefix+(nbEnregistrement+1));
}

	public void enregistrer(){
		iservice.addObject(this.typeActivite);
		annuler();
		info("Enregistrement effectué");
	}
	
	public void modifier() {
		iservice.updateObject(typeActivite);
		annuler();
		info("Modification effectuée");
	}
	
	public void annuler() {
		typeActivite.setCodeTypeactivite(null);
		typeActivite.setLibelleTypeactivite(null);
		cmdBEnregistrer.setDisabled(false);
		cmdBModifier.setDisabled(true);
		genererCode();
	}	
	
	public void selectionnerLigne() {
		typeActivite = selectedObject;
		cmdBEnregistrer.setDisabled(true);
		cmdBModifier.setDisabled(false);
	}
	
	public void info(String message){
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,message,null));	
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
