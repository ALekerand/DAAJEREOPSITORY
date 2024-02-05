package com.daaje.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.component.commandbutton.CommandButton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.daaje.model.Apprenant;
import com.daaje.model.Campagne;
import com.daaje.model.Centre;
import com.daaje.model.NiveauFormation;
import com.daaje.model.Inscription;
import com.daaje.service.Iservice;

@Component
public class InscriptionContoller {
	@Autowired
	private Iservice iservice;
	private int idApprenant;
	private int idCampagne;
	private int idCentre;
	private int idNiveauFormation;	
	private Inscription inscription = new Inscription();
	private Inscription selectedObject = new Inscription();
	private List listObject = new ArrayList();
	private List<Apprenant> listApprenant = new ArrayList<Apprenant>();
	private List<Campagne> listCampagne = new ArrayList<Campagne>();
	private List<Centre> listCentre = new ArrayList<Centre>();
	private List<NiveauFormation> listNiveauFormation = new ArrayList<NiveauFormation>();
	
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
		int nbEnregistrement = this.iservice.getObjects("Inscription").size();
		if(nbEnregistrement < 10)
			prefix = "IN00" ;
		if ((nbEnregistrement >= 10) && (nbEnregistrement < 100)) 
			prefix = "IN0" ;
		if (nbEnregistrement > 100) 
			prefix = "IN" ;
		this.inscription.setCodeInscription(prefix+(nbEnregistrement+1));
	}
	
	public void enregistrer(){
		inscription.setApprenant((Apprenant) iservice.getObjectById(idApprenant, "Apprenant"));
		inscription.setCampagne((Campagne) (iservice.getObjectById(idCampagne, "Campagne")));
		inscription.setCentre((Centre) iservice.getObjectById(idCentre, "Centre"));
		inscription.setNiveauFormation((NiveauFormation) iservice.getObjectById(idNiveauFormation, "NiveauFormation"));
		iservice.addObject(this.inscription);
		annuler();
		info("Enregistrement effectué");
	}
	
	public void modifier() {
		iservice.updateObject(inscription);
		annuler();
		info("Modification effectuée");
	}
	
	public void annuler() {
		inscription.setCodeInscription(null);
		inscription.setDateInscription(null);
		cmdBEnregistrer.setDisabled(false);
		cmdBModifier.setDisabled(true);
		genererCode();
	}
	
	public void selectionnerLigne() {
		inscription = selectedObject;
		cmdBEnregistrer.setDisabled(true);
		cmdBModifier.setDisabled(false);
	}
	
	public void info(String message){
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,message,null));	
	}
		
//Getters and setters
	public List getListObject() {
		return listObject = iservice.getObjects("Inscription");
	}

	public void setListObject(List listObject) {
		this.listObject = listObject;
	}

	public Inscription getSelectedObject() {
		return selectedObject;
	}

	public void setSelectedObject(Inscription selectedObject) {
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


	public Inscription getInscription() {
		return inscription;
	}


	public void setInscription(Inscription inscription) {
		this.inscription = inscription;
	}

	
	public int getIdApprenant() {
		return idApprenant;
	}


	public void setIdApprenant(int idApprenant) {
		this.idApprenant = idApprenant;
	}


	public int getIdCampagne() {
		return idCampagne;
	}


	public void setIdCampagne(int idCampagne) {
		this.idCampagne = idCampagne;
	}


	public int getIdCentre() {
		return idCentre;
	}


	public void setIdCentre(int idCentre) {
		this.idCentre = idCentre;
	}


	public int getIdNiveauFormation() {
		return idNiveauFormation;
	}


	public void setIdNiveauFormation(int idNiveauFormation) {
		this.idNiveauFormation = idNiveauFormation;
	}


	public List<Apprenant> getListApprenant() {
		return listApprenant = iservice.getObjects("Apprenant");
	}


	public void setListApprenant(List<Apprenant> listApprenant) {
		this.listApprenant = listApprenant;
	}


	public List<Campagne> getListCampagne() {
		return listCampagne = iservice.getObjects("Campagne");
	}


	public void setListCampagne(List<Campagne> listCampagne) {
		this.listCampagne = listCampagne;
	}


	public List<Centre> getListCentre() {
		return listCentre = iservice.getObjects("Centre");
	}


	public void setListCentre(List<Centre> listCentre) {
		this.listCentre = listCentre;
	}


	public List<NiveauFormation> getListNiveauFormation() {
		return listNiveauFormation = iservice.getObjects("NiveauFormation");
	}


	public void setListNiveauFormation(List<NiveauFormation> listNiveauFormation) {
		this.listNiveauFormation = listNiveauFormation;
	}

}
