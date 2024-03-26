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
	private Iservice iservice;
	private NiveauFormation niveauFormation = new NiveauFormation();
	private NiveauFormation selectedObject = new NiveauFormation();
	private List listObject = new ArrayList();
	
//Controle des composants
	private CommandButton cmdBModifier = new CommandButton();
	private CommandButton cmdBEnregistrer = new CommandButton();
	private boolean etatBtnEnregistrer = false;
	private boolean etatBtnModifier = false;
		
//Methodes
	@PostConstruct
	
	public void initialisation(){
		etatBtnModifier = true;
		genererCode();
	}
	
	public void enregistrer(){
		iservice.addObject(this.niveauFormation);
		annuler();
		info("Enregistrement effectué");
	}
	
	public void modifier() {
		iservice.updateObject(this.niveauFormation);
		annuler();
		info("Modification effectuée");
        selectedObject = null;
	}
	
	public void annuler() {
		niveauFormation.setCodeNiveauFormation(null);
		niveauFormation.setLibelleNiveauFormation(null);
		etatBtnEnregistrer = false;
		etatBtnModifier = true;
		genererCode();
		selectedObject = null;// Réinitialiser l'élément sélectionner
	}
	
	public void selectionnerLigne() {
		niveauFormation = selectedObject;
		etatBtnEnregistrer = true;
		etatBtnModifier = false;
		
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

	public boolean isEtatBtnEnregistrer() {
		return etatBtnEnregistrer;
	}

	public void setEtatBtnEnregistrer(boolean etatBtnEnregistrer) {
		this.etatBtnEnregistrer = etatBtnEnregistrer;
	}

	public boolean isEtatBtnModifier() {
		return etatBtnModifier;
	}

	public void setEtatBtnModifier(boolean etatBtnModifier) {
		this.etatBtnModifier = etatBtnModifier;
	}

}
