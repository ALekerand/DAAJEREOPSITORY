package com.daaje.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.component.commandbutton.CommandButton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.daaje.model.Activite;
import com.daaje.model.Apprenant;
import com.daaje.model.Genre;
import com.daaje.service.Iservice;

@Component
public class ApprenantController {
	@Autowired
	public Iservice iservice;
	public int idGenre;
	public int idActivite;
	public Apprenant apprenant = new Apprenant();
	public Apprenant selectedObject = new Apprenant();
	public List listObject = new ArrayList();
	public List<Activite> listActivite = new ArrayList<Activite>();
	public List<Genre> listGenre = new ArrayList<Genre>();
	
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
		int nbEnregistrement = this.iservice.getObjects("Apprenant").size();
		if(nbEnregistrement < 10)
			prefix = "AP00" ;
		if ((nbEnregistrement >= 10) && (nbEnregistrement < 100)) 
			prefix = "AP0" ;
		if (nbEnregistrement > 100) 
			prefix = "AP" ;
		this.apprenant.setCodeApprenant(prefix+(nbEnregistrement+1));
	}	
	
	public void enregistrer(){
		apprenant.setActivite((Activite) iservice.getObjectById(idActivite, "Activite"));
		apprenant.setGenre((Genre) iservice.getObjectById(idGenre, "Genre"));
		iservice.addObject(this.apprenant);
		annuler();
		info("Enregistrement effectué");
	}
	
	public void modifier() {
		iservice.updateObject(apprenant);
		annuler();
		info("Modification effectuée");
	}
	
	public void annuler() {
		apprenant.setCodeApprenant(null);
		apprenant.setNomApprenant(null);
		apprenant.setPrenomApprenant(null);
		apprenant.setDateNaissApprenant(null);
		apprenant.setTelephoneApprenant(null);
		apprenant.setAdresseApprenant(null);
		apprenant.setMailApprenant(null);
		cmdBEnregistrer.setDisabled(false);
		cmdBModifier.setDisabled(true);
		genererCode();
		
	}
	
	public void selectionnerLigne() {
		apprenant = selectedObject;
		cmdBEnregistrer.setDisabled(true);
		cmdBModifier.setDisabled(false);
	}
	
	public void info(String message){
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,message,null));	
	}
		
//Getters and setters
	public List getListObject() {
		return listObject = iservice.getObjects("Apprenant");
	}

	public void setListObject(List listObject) {
		this.listObject = listObject;
	}

	public Apprenant getSelectedObject() {
		return selectedObject;
	}

	public void setSelectedObject(Apprenant selectedObject) {
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


	public Apprenant getApprenant() {
		return apprenant;
	}


	public void setApprenant(Apprenant apprenant) {
		this.apprenant = apprenant;
	}


	public int getIdGenre() {
		return idGenre;
	}


	public void setIdGenre(int idGenre) {
		this.idGenre = idGenre;
	}


	public int getIdActivite() {
		return idActivite;
	}


	public void setIdActivite(int idActivite) {
		this.idActivite = idActivite;
	}


	public List<Activite> getListActivite() {
		return listActivite = iservice.getObjects("Activite");
	}


	public void setListActivite(List<Activite> listActivite) {
		this.listActivite = listActivite;
	}


	public List<Genre> getListGenre() {
		return listGenre = iservice.getObjects("Genre");
	}


	public void setListGenre(List<Genre> listGenre) {
		this.listGenre = listGenre;
	}

}
