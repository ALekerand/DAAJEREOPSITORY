package com.daaje.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.component.commandbutton.CommandButton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.daaje.model.Departement;
import com.daaje.model.Genre;
import com.daaje.service.Iservice;

@Component
@Scope("session")
public class GenreController {
	@Autowired
	private Iservice iservice;
	private Genre genre = new Genre();
	private Genre selectedObject = new Genre();
	private List listObject = new ArrayList();
	
//Controle des composants
	private CommandButton cmdBModifier = new CommandButton();
	private CommandButton cmdBEnregistrer = new CommandButton();
	private boolean etatBtnEnregistrer;
	private boolean etatBtnModifier;
		
//Methodes
	@PostConstruct
	public void initialisation(){
		etatBtnModifier = true;
		genererCode();
	}
	
	public void enregistrer(){
		iservice.addObject(this.genre);
		annuler();
		info("Enregistrement effectué");
	}
	
	public void modifier() {
		iservice.updateObject(this.genre);
		annuler();
		info("Modification effectuée");
        selectedObject = null;
	}
	
	public void annuler() {
		genre.setCodeGenre(null);
		genre.setLibelleGenre(null);
		etatBtnEnregistrer = false;
		etatBtnModifier = true;
		genererCode();
		selectedObject = null;// Réinitialiser l'élément sélectionner
	}
	
	public void selectionnerLigne() {
		genre = selectedObject;
		etatBtnEnregistrer = true;
		etatBtnModifier = false;
		
	}
	
	public void info(String message){
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,message,null));	
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
		
//Getters and setters
	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public List<Genre> getListObject() {
		listObject = iservice.getObjects("Genre");
		
		//=======Pour le rangement par ordre alphabétique======
		Collections.sort(listObject, new Comparator<Genre>() {
	        @Override
	        public int compare(Genre ob1, Genre ob2)
	        {
	 
	            return  ob1.getLibelleGenre().compareTo(ob2.getLibelleGenre());
	        }
	    });
		//========================  Fin  =======================

return listObject;

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

	public boolean isEtatBtnModifier() {
		return etatBtnModifier;
	}

	public void setEtatBtnModifier(boolean etatBtnModifier) {
		this.etatBtnModifier = etatBtnModifier;
	}

	public boolean isEtatBtnEnregistrer() {
		return etatBtnEnregistrer;
	}

	public void setEtatBtnEnregistrer(boolean etatBtnEnregistrer) {
		this.etatBtnEnregistrer = etatBtnEnregistrer;
	}

}
