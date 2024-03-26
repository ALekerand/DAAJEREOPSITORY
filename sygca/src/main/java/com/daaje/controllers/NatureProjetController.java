package com.daaje.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.component.commandbutton.CommandButton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.daaje.model.NatureProjet;
import com.daaje.service.Iservice;

@Component
public class NatureProjetController {
	@Autowired
	private Iservice iservice;
	private NatureProjet natureProjet = new NatureProjet();
	private NatureProjet selectedObject = new NatureProjet();
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
		iservice.addObject(this.natureProjet);
		annuler();
		info("Enregistrement effectué");
	}
	
	public void modifier() {
		iservice.updateObject(this.natureProjet);
		annuler();
		info("Modification effectuée");
        selectedObject = null;
	}
	
	public void annuler() {
		natureProjet.setCodeNatureProjet(null);
		natureProjet.setLibelleNatureProjet(null);
		etatBtnEnregistrer = false;
		etatBtnModifier = true;
		genererCode();
		selectedObject = null;// Réinitialiser l'élément sélectionner
	}
	
	public void selectionnerLigne() {
		natureProjet = selectedObject;
		etatBtnEnregistrer = true;
		etatBtnModifier = false;
		
	}
	
	public void genererCode() {
		String prefix="";
		int nbEnregistrement = this.iservice.getObjects("NatureProjet").size();
		if(nbEnregistrement < 10)
			prefix = "NP00" ;
		if ((nbEnregistrement >= 10) && (nbEnregistrement < 100)) 
			prefix = "NP0" ;
		if (nbEnregistrement > 100) 
			prefix = "NP" ;
		this.natureProjet.setCodeNatureProjet(prefix+(nbEnregistrement+1));
	}
	
	public void info(String message){
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,message,null));	
	}
		
//Getters and setters
	public NatureProjet getNatureProjet() {
		return natureProjet;
	}

	public void setNatureProjet(NatureProjet natureProjet) {
		this.natureProjet = natureProjet;
	}

	public List getListObject() {
		return listObject = iservice.getObjects("NatureProjet");
	}

	public void setListObject(List listObject) {
		this.listObject = listObject;
	}

	public NatureProjet getSelectedObject() {
		return selectedObject;
	}

	public void setSelectedObject(NatureProjet selectedObject) {
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
