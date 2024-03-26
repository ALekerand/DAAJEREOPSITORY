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

import com.daaje.model.Langue;
import com.daaje.model.LocaliteDImplantation;
import com.daaje.service.Iservice;

@Component
public class LangueController {
	@Autowired
	private Iservice iservice;
	private Langue langue = new Langue();
	private Langue selectedObject = new Langue();
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
		iservice.addObject(this.langue);
		annuler();
		info("Enregistrement effectué");
	}
	
	public void modifier() {
		iservice.updateObject(this.langue);
		annuler();
		info("Modification effectuée");
        selectedObject = null;
	}
	
	public void annuler() {
		langue.setCodeLangue(null);
		langue.setLibLangue(null);
		etatBtnEnregistrer = false;
		etatBtnModifier = true;
		genererCode();
		selectedObject = null;// Réinitialiser l'élément sélectionner
	}
	
	public void selectionnerLigne() {
		langue = selectedObject;
		etatBtnEnregistrer = true;
		etatBtnModifier = false;
		
	}
	
	public void genererCode() {
		String prefix="";
		int nbEnregistrement = this.iservice.getObjects("Langue").size();
		if(nbEnregistrement < 10)
			prefix = "LANG00" ;
		if ((nbEnregistrement >= 10) && (nbEnregistrement < 100)) 
			prefix = "LANG0" ;
		if (nbEnregistrement > 100) 
			prefix = "LANG" ;
		this.langue.setCodeLangue(prefix+(nbEnregistrement+1));
	}
	
	public void info(String message){
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,message,null));	
	}
		
//Getters and setters
	public Langue getLangue() {
		return langue;
	}

	public void setLangue(Langue langue) {
		this.langue = langue;
	}

	public List<Langue> getListObject() {
		listObject = iservice.getObjects("Langue");
		
		
		//=======Pour le rangement par ordre alphabétique======
		Collections.sort(listObject, new Comparator<Langue>() {
	        @Override
	        public int compare(Langue ob1, Langue ob2)
	        {
	            return  ob1.getLibLangue().compareTo(ob2.getLibLangue());
	        }
	    });
		//========================  Fin  =======================
		return listObject;
	}

	public void setListObject(List listObject) {
		this.listObject = listObject;
	}

	public Langue getSelectedObject() {
		return selectedObject;
	}

	public void setSelectedObject(Langue selectedObject) {
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
