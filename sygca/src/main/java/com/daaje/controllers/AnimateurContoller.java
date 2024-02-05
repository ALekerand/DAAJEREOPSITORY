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
import org.springframework.stereotype.Component;


import com.daaje.model.Animateur;
import com.daaje.model.Departement;
import com.daaje.model.Genre;
import com.daaje.model.NiveauAnimateur;
import com.daaje.service.Iservice;

@Component
public class AnimateurContoller {
	@Autowired
	private Iservice iservice;
	private int idGenre;
	private int idNiveau;
	private Animateur animateur = new Animateur();
	private Animateur selectedObject = new Animateur();
	private List listObject = new ArrayList();
	private List<NiveauAnimateur> listNiveauAnimateur = new ArrayList<NiveauAnimateur>();
	private List<Genre> listGenre = new ArrayList<Genre>();
	
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
		int nbEnregistrement = this.iservice.getObjects("Animateur").size();
		if(nbEnregistrement < 10)
			prefix = "AN00" ;
		if ((nbEnregistrement >= 10) && (nbEnregistrement < 100)) 
			prefix = "AN0" ;
		if (nbEnregistrement > 100) 
			prefix = "AN" ;
		this.animateur.setCodeAnimateur(prefix+(nbEnregistrement+1));
	}
		
	public void enregistrer(){
		animateur.setNiveauAnimateur((NiveauAnimateur) iservice.getObjectById(idNiveau, "NiveauAnimateur"));
		animateur.setGenre((Genre) iservice.getObjectById(idGenre, "Genre"));
		iservice.addObject(this.animateur);
		annuler();
		info("Enregistrement effectué");
	}
	
	public void modifier() {
		iservice.updateObject(animateur);
		annuler();
		info("Modification effectuée");
	}
	
	public void annuler() {
		animateur.setCodeAnimateur(null);
		animateur.setNomAnimateur(null);
		animateur.setPrenomAnimateur(null);
		animateur.setDateNaisAnimateur(null);
		animateur.setTelephoneAnimateur(null);
		animateur.setAdresseAnimateur(null);
		animateur.setMailAnimateur(null);
		cmdBEnregistrer.setDisabled(false);
		cmdBModifier.setDisabled(true);
		genererCode();
		
	}
	
	public void selectionnerLigne() {
		animateur = selectedObject;
		cmdBEnregistrer.setDisabled(true);
		cmdBModifier.setDisabled(false);
	}
	
	public void info(String message){
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,message,null));	
	}
		
//Getters and setters
	public List<Animateur> getListObject() {
		listObject = iservice.getObjects("Animateur");
		
		//=======Pour le rangement par ordre alphabétique======
		Collections.sort(listObject, new Comparator<Animateur>() {
	        @Override
	        public int compare(Animateur ob1, Animateur ob2)
	        {
	 
	            return  ob1.getNomAnimateur().compareTo(ob2.getNomAnimateur());
	        }
	    });
		//========================  Fin  =======================

return listObject;

	}

	public void setListObject(List listObject) {
		this.listObject = listObject;
	}

	public Animateur getSelectedObject() {
		return selectedObject;
	}

	public void setSelectedObject(Animateur selectedObject) {
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


	public Animateur getAnimateur() {
		return animateur;
	}


	public void setAnimateur(Animateur animateur) {
		this.animateur = animateur;
	}


	public List<NiveauAnimateur> getListNiveauAnimateur() {
		return listNiveauAnimateur = iservice.getObjects("NiveauAnimateur");
		
	}


	public void setListNiveauAnimateur(List<NiveauAnimateur> listNiveauAnimateur) {
		this.listNiveauAnimateur = listNiveauAnimateur;
	}


	public int getidNiveau() {
		return idNiveau;
	}


	public void setidNiveau(int idNiveau) {
		this.idNiveau = idNiveau;
	}


	public int getidGenre() {
		return idGenre;
	}


	public void setIdGenre(int idGenre) {
		this.idGenre = idGenre;
	}


	public int getIdNiveau() {
		return idNiveau;
	}


	public void setIdNiveau(int idNiveau) {
		this.idNiveau = idNiveau;
	}


	public List<Genre> getListGenre() {
		return listGenre = iservice.getObjects("Genre");
	}


	public void setListGenre(List<Genre> listGenre) {
		this.listGenre = listGenre;
	}


	public int getIdGenre() {
		return idGenre;
	}

}
