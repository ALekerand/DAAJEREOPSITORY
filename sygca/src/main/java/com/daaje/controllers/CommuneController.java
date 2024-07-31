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

import com.daaje.model.Commune;
import com.daaje.model.Departement;
import com.daaje.service.Iservice;

@Component
@Scope("session")
public class CommuneController {
	@Autowired
	private Iservice iservice;
	private Commune commune = new Commune();
	private Commune selectedObject = new Commune();
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
		iservice.addObject(this.commune);
		annuler();
		info("Enregistrement effectué");
	}
	
	public void modifier() {
		iservice.updateObject(this.commune);
		annuler();
		info("Modification effectuée");
        selectedObject = null;
	}
	
	public void annuler() {
		commune.setCodeCommune(null);
		commune.setNomCommune(null);
		etatBtnEnregistrer = false;
		etatBtnModifier = true;
		genererCode();
		selectedObject = null;// Réinitialiser l'élément sélectionner
	}
	
	public void selectionnerLigne() {
		commune = selectedObject;
		etatBtnEnregistrer = true;
		etatBtnModifier = false;
		
	}
	
	public void genererCode() {
		String prefix="";
		int nbEnregistrement = this.iservice.getObjects("Commune").size();
		if(nbEnregistrement < 10)
			prefix = "COM00" ;
		if ((nbEnregistrement >= 10) && (nbEnregistrement < 100)) 
			prefix = "COM0" ;
		if (nbEnregistrement > 100) 
			prefix = "COM" ;
		this.commune.setCodeCommune(prefix+(nbEnregistrement+1));
	}
	
	
		
	public void info(String message){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,message,null));	
	}
			
//Getters and setters
	public List<Commune> getListObject() {
		listObject = iservice.getObjects("Commune");
		
		//=======Pour le rangement par ordre alphabétique======
		        Collections.sort(listObject, new Comparator<Commune>() {
	                @Override
	                public int compare(Commune ob1, Commune ob2)
	               {
	 
	                    return  ob1.getNomCommune().compareTo(ob2.getNomCommune());
	               }
	            });
		        //========================  Fin  =======================

        return listObject;
	}
	
	public Commune getCommune() {
		return commune;
	}

	public void setCommune(Commune commune) {
		this.commune = commune;
	}

	public void setListObject(List listObject) {
		this.listObject = listObject;
	}

	public Commune getSelectedObject() {
		return selectedObject;
	}

	public void setSelectedObject(Commune selectedObject) {
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
