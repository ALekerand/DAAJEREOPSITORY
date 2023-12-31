package com.daaje.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.component.commandbutton.CommandButton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.daaje.model.Genre;
import com.daaje.service.Iservice;

@Component
public class GenreController {
	@Autowired
	public Iservice iservice;
	public Genre genre = new Genre();
	public Genre selectedObject = new Genre();
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
		int nbEnregistrement = this.iservice.getObjects("Genre").size();
		if(nbEnregistrement < 10)
			prefix = "GN00" ;
		if ((nbEnregistrement >= 10) && (nbEnregistrement < 100)) 
			prefix = "GN0" ;
		if (nbEnregistrement > 100) 
			prefix = "GN" ;
		this.genre.setCodeGenre(prefix+(nbEnregistrement+1));
	}
	
	
	public void enregistrer(){
		iservice.addObject(this.genre);
		annuler();
		info("Enregistrement effectué");
	}
	
	public void modifier() {
		iservice.updateObject(genre);
		annuler();
		info("Modification effectuée");
	}
	
	public void annuler() {
		genre.setCodeGenre(null);
		genre.setLibelleGenre(null);
		cmdBEnregistrer.setDisabled(false);
		cmdBModifier.setDisabled(true);
		genererCode();
		
	}
	
	public void selectionnerLigne() {
		genre = selectedObject;
		cmdBEnregistrer.setDisabled(true);
		cmdBModifier.setDisabled(false);
	}
	
	public void info(String message){
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,message,null));	
	}
		
//Getters and setters
	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public List getListObject() {
		return listObject = iservice.getObjects("Genre");
	}

	public void setListObject(List listObject) {
		this.listObject = listObject;
	}

	public Genre getSelectedObject() {
		return selectedObject;
	}

	public void setSelectedObject(Genre selectedObject) {
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
