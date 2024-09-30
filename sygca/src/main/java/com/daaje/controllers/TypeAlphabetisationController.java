package com.daaje.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.component.commandbutton.CommandButton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.daaje.model.TypeAlphabetisation;
import com.daaje.service.Iservice;

@Component
@Scope("session")
public class TypeAlphabetisationController {
	@Autowired
	private Iservice iservice;
	private TypeAlphabetisation typeAlphabetisation = new TypeAlphabetisation();
	private TypeAlphabetisation selectedObject = new TypeAlphabetisation();
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
		iservice.addObject(this.typeAlphabetisation);
		annuler();
		info("Enregistrement effectué");
	}
	
	public void modifier() {
		iservice.updateObject(this.typeAlphabetisation);
		annuler();
		info("Modification effectuée");
        selectedObject = null;
	}
	
	public void annuler() {
		typeAlphabetisation.setCodeTypeAlpha(null);
		typeAlphabetisation.setLibTypeAlpha(null);
		etatBtnEnregistrer = false;
		etatBtnModifier = true;
		genererCode();
		selectedObject = null;// Réinitialiser l'élément sélectionner
	}
	
	public void selectionnerLigne() {
		typeAlphabetisation = selectedObject;
		etatBtnEnregistrer = true;
		etatBtnModifier = false;
		
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
