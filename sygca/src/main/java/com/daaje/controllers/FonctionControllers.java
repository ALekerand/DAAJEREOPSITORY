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
import com.daaje.model.Fonction;
import com.daaje.service.Iservice;

@Component
@Scope("session")
public class FonctionControllers {
	@Autowired
	private Iservice iservice;
	private Fonction fonction = new Fonction();
	private Fonction selectedObject = new Fonction();
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
		iservice.addObject(this.fonction);
		annuler();
		info("Enregistrement effectué");
	}
	
	public void modifier() {
		iservice.updateObject(this.fonction);
		annuler();
		info("Modification effectuée");
	    selectedObject = null;
	}
	
	public void annuler() {
		fonction.setCodeFonction(null);
		fonction.setLibelleFonction(null);
		etatBtnEnregistrer = false;
		etatBtnModifier = true;
		selectedObject = null;
		genererCode();
	}
	
	public void selectionnerLigne() {
		fonction = selectedObject;
		etatBtnEnregistrer = true;
		etatBtnModifier = false;
		
	}
	
	
	public void genererCode() {
		String prefix="";
		int nbEnregistrement = this.iservice.getObjects("Fonction").size();
		if(nbEnregistrement < 10)
			prefix = "FON00" ;
		if ((nbEnregistrement >= 10) && (nbEnregistrement < 100)) 
			prefix = "FON0" ;
		if (nbEnregistrement > 100) 
			prefix = "FON" ;
		this.fonction.setCodeFonction(prefix+(nbEnregistrement+1));
	}
	
	
		
	public void info(String message){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message,null));	
	}

//Getters and setters
	public Fonction getFonction() {
		return fonction;
	}

	public void setFonction(Fonction fonction) {
		this.fonction = fonction;
	}

	public List<Fonction> getListObject() {
		listObject = iservice.getObjects("Fonction");
		
		//=======Pour le rangement par ordre alphabétique======
		Collections.sort(listObject, new Comparator<Fonction>() {
	        @Override
	        public int compare(Fonction ob1, Fonction ob2)
	        {
	 
	            return  ob1.getLibelleFonction().compareTo(ob2.getLibelleFonction());
	        }
	    });
		//========================  Fin  =======================

return listObject;

	}

	public void setListObject(List listObject) {
		this.listObject = listObject;
	}

	public Fonction getSelectedObject() {
		return selectedObject;
	}

	public void setSelectedObject(Fonction selectedObject) {
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
