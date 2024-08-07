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

import com.beust.ah.A;
import com.daaje.model.Activite;
import com.daaje.model.Departement;
import com.daaje.service.Iservice;

@Component
@Scope("session")
public class ActiviteController {
@Autowired
	private Iservice iservice;
    private Activite activite = new Activite();
    private Activite selectedObject = new Activite();
    private List listObject = new ArrayList();
	
//Controle des composants
    private CommandButton cmdBModifier = new CommandButton();
    private CommandButton cmdBEnregistrer = new CommandButton();
    private boolean etatBtnEnregistrer = false;
	private boolean etatBtnModifier = false;
		
//Methodes
@PostConstruct
	
	public void initialisation(){
		setEtatBtnModifier(true);
		genererCode();
	}
	
	public void enregistrer(){
		iservice.addObject(this.activite);
		annuler();
		info("Enregistrement effectué");
	}
	
	public void modifier() {
		iservice.updateObject(this.activite);
		annuler();
		info("Modification effectuée");
	    selectedObject = null;
	}
	
	public void annuler() {
		activite.setCodeActivite(null);
		activite.setNomActivite(null);
		setEtatBtnEnregistrer(false);
		setEtatBtnModifier(true);
		genererCode();
		selectedObject = null;// Réinitialiser l'élément sélectionner
	}
	
	public void selectionnerLigne() {
		activite = selectedObject;
		setEtatBtnEnregistrer(true);
		setEtatBtnModifier(false);
		
	}	

	public void genererCode() {
		String prefix="";
		int nbEnregistrement = this.iservice.getObjects("Activite").size();
		if(nbEnregistrement < 10)
			prefix = "ACT00" ;
		if ((nbEnregistrement >= 10) && (nbEnregistrement < 100)) 
			prefix = "ACT0" ;
		if (nbEnregistrement > 100) 
			prefix = "ACT" ;
		this.activite.setCodeActivite(prefix+(nbEnregistrement+1));
	}
	
	public void info(String message){
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,message,null));	
	}
		
//Getters and setters
	public Activite getActivite() {
		return activite;
	}

	public void setActivite(Activite activite) {
		this.activite = activite;
	}

	public List<Activite> getListObject() {
		listObject = iservice.getObjects("Activite");
		
		//=======Pour le rangement par ordre alphabétique======
		Collections.sort(listObject, new Comparator<Activite>() {
	        @Override
	        public int compare(Activite ob1, Activite ob2)
	        {
	 
	            return  ob1.getNomActivite().compareTo(ob2.getNomActivite());
	        }
	    });
		//========================  Fin  =======================

return listObject;

	}

	public void setListObject(List listObject) {
		this.listObject = listObject;
	}

	public Activite getSelectedObject() {
		return selectedObject;
	}

	public void setSelectedObject(Activite selectedObject) {
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
