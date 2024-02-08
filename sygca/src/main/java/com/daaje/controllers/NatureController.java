package com.daaje.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.component.commandbutton.CommandButton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.daaje.model.Nature;
import com.daaje.service.Iservice;

@Component
public class NatureController {
	@Autowired
	private Iservice iservice;
	private Nature nature = new Nature();
	private Nature selectedObject = new Nature();
	private List listObject = new ArrayList();
	
//Controle des composants
	private CommandButton cmdBModifier = new CommandButton();
	private CommandButton cmdBEnregistrer = new CommandButton();
		
//Methodes
	@PostConstruct
	public void initialisation(){
		this.cmdBModifier.setDisabled(true);
		genererCode();
	}
	
	public void genererCode() {
		String prefix="";
		int nbEnregistrement = this.iservice.getObjects("Nature").size();
		if(nbEnregistrement < 10)
			prefix = "NA00" ;
		if ((nbEnregistrement >= 10) && (nbEnregistrement < 100)) 
			prefix = "NA0" ;
		if (nbEnregistrement > 100) 
			prefix = "NA" ;
		this.nature.setCodeNature(prefix+(nbEnregistrement+1));
	}
	
	
	public void enregistrer(){
		iservice.addObject(this.nature);
		annuler();
		info("Enregistrement effectué");
	}
	
	public void modifier() {
		iservice.updateObject(nature);
		annuler();
		info("Modification effectuée");
	}
	
	public void annuler() {
		nature.setCodeNature(null);
		nature.setLibelleNature(null);
		cmdBEnregistrer.setDisabled(false);
		cmdBModifier.setDisabled(true);
		genererCode();
		
	}
	
	public void selectionnerLigne() {
		nature = selectedObject;
		cmdBEnregistrer.setDisabled(true);
		cmdBModifier.setDisabled(false);
	}
	
	public void info(String message){
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,message,null));	
	}
		
//Getters and setters
	public Nature getNature() {
		return nature;
	}

	public void setNature(Nature nature) {
		this.nature = nature;
	}

	public List getListObject() {
		return listObject = iservice.getObjects("Nature");
	}

	public void setListObject(List listObject) {
		this.listObject = listObject;
	}

	public Nature getSelectedObject() {
		return selectedObject;
	}

	public void setSelectedObject(Nature selectedObject) {
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
