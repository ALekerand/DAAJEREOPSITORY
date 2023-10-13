package com.daaje.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.component.commandbutton.CommandButton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.daaje.model.NiveauAnimateur;
import com.daaje.service.Iservice;

@Component
public class NiveauAnimateurControllers {
	@Autowired
	public Iservice iservice;
	public NiveauAnimateur niveauAnimateur = new NiveauAnimateur();
	public NiveauAnimateur selectedObject = new NiveauAnimateur();
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
		int nbEnregistrement = this.iservice.getObjects("NiveauAnimateur").size();
		if(nbEnregistrement < 10)
			prefix = "NIA00" ;
		if ((nbEnregistrement >= 10) && (nbEnregistrement < 100)) 
			prefix = "NIA" ;
		if (nbEnregistrement > 100) 
			prefix = "NIA" ;
		this.niveauAnimateur.setCodeNiveau(prefix+(nbEnregistrement+1));
	}
	
	public void enregistrer(){
		iservice.addObject(this.niveauAnimateur);
		annuler();
		info("Enregistrement effectué");
	}
	
	public void modifier() {
		iservice.updateObject(niveauAnimateur);
		annuler();
		info("Modification effectuée");
	}
	
	public void annuler() {
		niveauAnimateur.setCodeNiveau(null);
		niveauAnimateur.setNomNiveau(null);
		cmdBEnregistrer.setDisabled(false);
		cmdBModifier.setDisabled(true);
		genererCode();
		
	}
	
	public void selectionnerLigne() {
		niveauAnimateur = selectedObject;
		cmdBEnregistrer.setDisabled(true);
		cmdBModifier.setDisabled(false);
	}
	
	public void info(String message){
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,message,null));	
	}
		
//Getters and setters
	public NiveauAnimateur getNiveauAnimateur() {
		return niveauAnimateur;
	}

	public void setNiveauAnimateur(NiveauAnimateur niveauAnimateur) {
		this.niveauAnimateur = niveauAnimateur;
	}

	public List getListObject() {
		return listObject = iservice.getObjects("NiveauAnimateur");
	}

	public void setListObject(List listObject) {
		this.listObject = listObject;
	}

	public NiveauAnimateur getSelectedObject() {
		return selectedObject;
	}

	public void setSelectedObject(NiveauAnimateur selectedObject) {
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
