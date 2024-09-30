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

import com.daaje.model.Activite;
import com.daaje.model.Animateur;
import com.daaje.model.Profession;
import com.daaje.model.TypeActivite;
import com.daaje.service.Iservice;

@Component
@Scope("session")
public class ProfessionController {
	@Autowired
	private Iservice iservice;
	private int idAnimateur;
	private int idActivite;
	private int idTypeactivite;
	private Profession profession = new Profession();
	private Profession selectedObject = new Profession();
	private List listObject = new ArrayList();
	private List<Animateur> listAnimateur = new ArrayList<Animateur>();
	private List<Activite> listActivite = new ArrayList<Activite>();
	private List<TypeActivite> listTypeActivite = new ArrayList<TypeActivite>();
	
//Controle des composants
	private CommandButton cmdBModifier = new CommandButton();
	private CommandButton cmdBEnregistrer = new CommandButton();
		
//Methodes
	@PostConstruct
	public void initialisation(){
		this.cmdBModifier.setDisabled(true);
	}
	
	
	public void enregistrer(){
		profession.setAnimateur((Animateur) iservice.getObjectById(idAnimateur, "Animateur"));
		profession.setActivite((Activite) iservice.getObjectById(idActivite, "Activite"));
		profession.setTypeActivite((TypeActivite) iservice.getObjectById(idTypeactivite, "TypeActivite"));
		iservice.addObject(this.profession);
		annuler();
		info("Enregistrement effectué");
	}
	
	public void modifier() {
		iservice.updateObject(profession);
		annuler();
		info("Modification effectuée");
	}
	
	public void annuler() {
		cmdBEnregistrer.setDisabled(false);
		cmdBModifier.setDisabled(true);	
	}
	
	public void selectionnerLigne() {
		profession = selectedObject;
		cmdBEnregistrer.setDisabled(true);
		cmdBModifier.setDisabled(false);
	}
	
	public void info(String message){
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,message,null));	
	}
		
//Getters and setters
	public List getListObject() {
		return listObject = iservice.getObjects("Profession");
	}

	public void setListObject(List listObject) {
		this.listObject = listObject;
	}

	public Profession getSelectedObject() {
		return selectedObject;
	}

	public void setSelectedObject(Profession selectedObject) {
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


	public Profession getProfession() {
		return profession;
	}


	public void setProfession(Profession profession) {
		this.profession = profession;
	}


	public int getIdAnimateur() {
		return idAnimateur;
	}


	public void setIdAnimateur(int idAnimateur) {
		this.idAnimateur = idAnimateur;
	}


	public int getIdActivite() {
		return idActivite;
	}


	public void setIdActivite(int idActivite) {
		this.idActivite = idActivite;
	}


	public int getIdTypeactivite() {
		return idTypeactivite;
	}


	public void setIdTypeactivite(int idTypeactivite) {
		this.idTypeactivite = idTypeactivite;
	}


	public List<Animateur> getListAnimateur() {
		return listAnimateur;
	}


	public void setListAnimateur(List<Animateur> listAnimateur) {
		this.listAnimateur = listAnimateur;
	}


	public List<Activite> getListActivite() {
		return listActivite;
	}


	public void setListActivite(List<Activite> listActivite) {
		this.listActivite = listActivite;
	}


	public List<TypeActivite> getListTypeActivite() {
		return listTypeActivite;
	}


	public void setListTypeActivite(List<TypeActivite> listTypeActivite) {
		this.listTypeActivite = listTypeActivite;
	}
}
