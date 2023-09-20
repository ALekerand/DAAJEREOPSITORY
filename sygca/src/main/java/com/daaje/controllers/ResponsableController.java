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
import com.daaje.model.Responsable;
import com.daaje.service.Iservice;

@Component
public class ResponsableController {
	@Autowired
	public Iservice iservice;
	public int idFonction;
	public Responsable responsable = new Responsable();
	public Responsable selectedObject = new Responsable();
	public List listObject = new ArrayList();
	public List<Fonction> listFonction = new ArrayList<Fonction>();
	
//Controle des composants
	public CommandButton cmdBModifier = new CommandButton();
	public CommandButton cmdBEnregistrer = new CommandButton();
		
//Methodes
	@PostConstruct
	public void initialisation(){
		this.cmdBModifier.setDisabled(true);
	}
	
	
	public void enregistrer(){
		responsable.setFonction((Fonction) iservice.getObjectById(idFonction, "Fonction"));
		iservice.addObject(this.responsable);
		annuler();
		info("Enregistrement effectué");
	}
	
	public void modifier() {
		iservice.updateObject(responsable);
		annuler();
		info("Modification effectuée");
	}
	
	public void annuler() {
		responsable.setMatriculeResponsable(null);
		responsable.setNomResponsable(null);
		responsable.setPrenomResponsable(null);
		responsable.setTelephoneResponsable(null);
		responsable.setAdresseResponsable(null);
		responsable.setDatePriseService(null);
		responsable.setDateRetraite(null);
		responsable.setDateNaissance(null);
		responsable.setMailResponsable(null);		
		cmdBEnregistrer.setDisabled(false);
		cmdBModifier.setDisabled(true);
		
	}
	
	public void selectionnerLigne() {
		responsable = selectedObject;
		cmdBEnregistrer.setDisabled(true);
		cmdBModifier.setDisabled(false);
	}
	
	public void info(String message){
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,message,null));	
	}
		
//Getters and setters
	

	

	public List getListObject() {
		return listObject = iservice.getObjects("Responsable");
	}

	public void setListObject(List listObject) {
		this.listObject = listObject;
	}

	public Responsable getSelectedObject() {
		return selectedObject;
	}

	public void setSelectedObject(Responsable selectedObject) {
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


	public Responsable getResponsable() {
		return responsable;
	}


	public void setIep(Responsable responsable) {
		this.responsable = responsable;
	}


	public List<Fonction> getListFonction() {
		return listFonction = iservice.getObjects("Fonction");
		
	}


	public void setListFonction(List<Fonction> listFonction) {
		this.listFonction = listFonction;
	}


	public int getIdFonction() {
		return idFonction;
	}


	public void setIdFonction(int idDrena) {
		this.idFonction = idFonction;
	}

}
