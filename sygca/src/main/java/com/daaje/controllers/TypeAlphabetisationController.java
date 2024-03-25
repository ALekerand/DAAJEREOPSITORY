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
	private Iservice iservice;
	private TypeAlphabetisation typeAlphabetisation = new TypeAlphabetisation();
	private TypeAlphabetisation selectedObject = new TypeAlphabetisation();
	private List listObject = new ArrayList();
	
//Controle des composants
	private CommandButton cmdBModifier = new CommandButton();
	private CommandButton cmdBEnregistrer = new CommandButton();
	private boolean enregistrerDisabled = false;
		
//Methodes
	@PostConstruct
	
	public void init() {
		initialisation();
	}
	
	public boolean isEnregistrerDisabled() {
	    return enregistrerDisabled;
	}

	public void setEnregistrerDisabled(boolean enregistrerDisabled) {
	    this.enregistrerDisabled = enregistrerDisabled;
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
	
	public void initialisation(){
		cmdBModifier.setDisabled(true);
		cmdBEnregistrer.setDisabled(true);
		genererCode();
	}
	
	public void enregistrer(){
		iservice.addObject(this.typeAlphabetisation);
		cmdBEnregistrer.setDisabled(true);
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
		setEnregistrerDisabled(false);//Réactivez le bouton Enregistrer
		cmdBModifier.setDisabled(true);
		genererCode();
		selectedObject = null;// Réinitialiser l'élément sélectionner
	}
	
	public void selectionnerLigne() {
		typeAlphabetisation = selectedObject;
		cmdBModifier.setDisabled(false);
		setEnregistrerDisabled(true);
		
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
