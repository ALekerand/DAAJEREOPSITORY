package com.daaje.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.component.commandbutton.CommandButton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.daaje.model.NiveauFormation;
import com.daaje.service.Iservice;

@Component
public class NiveauFormationController {
	@Autowired
	public Iservice iservice;
	public NiveauFormation niveauFormation = new NiveauFormation();
	public NiveauFormation selectedObject = new NiveauFormation();
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
		int nbEnregistrement = this.iservice.getObjects("NiveauFormation").size();
		if(nbEnregistrement < 10)
			prefix = "NF00" ;
		if ((nbEnregistrement >= 10) && (nbEnregistrement < 100)) 
			prefix = "NF0" ;
		if (nbEnregistrement > 100) 
			prefix = "NF" ;
		this.niveauFormation.setCodeNiveauFormation(prefix+(nbEnregistrement+1));
	}
	
	public void enregistrer(){
		iservice.addObject(this.niveauFormation);
		annuler();
		info("Enregistrement effectué");
	}
	
	public void modifier() {
		iservice.updateObject(niveauFormation);
		annuler();
		info("Modification effectuée");
	}
	
	public void annuler() {
		niveauFormation.setCodeNiveauFormation(null);
		niveauFormation.setLibelleNiveauFormation(null);
	//	niveauFormation.setTrimestre(null);
		cmdBEnregistrer.setDisabled(false);
		cmdBModifier.setDisabled(true);
		genererCode();		
	}
	
	public void selectionnerLigne() {
		niveauFormation = selectedObject;
		cmdBEnregistrer.setDisabled(true);
		cmdBModifier.setDisabled(false);
	}
	
	public void info(String message){
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,message,null));	
	}
		
//Getters and setters
	public NiveauFormation getNiveauFormation() {
		return niveauFormation;
	}

	public void setNiveauFormation(NiveauFormation niveauFormation) {
		this.niveauFormation = niveauFormation;
	}

	public List getListObject() {
		return listObject = iservice.getObjects("NiveauFormation");
	}

	public void setListObject(List listObject) {
		this.listObject = listObject;
	}

	public NiveauFormation getSelectedObject() {
		return selectedObject;
	}

	public void setSelectedObject(NiveauFormation selectedObject) {
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
