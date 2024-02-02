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

import com.daaje.model.Commune;
import com.daaje.model.Departement;
import com.daaje.service.Iservice;

@Component
public class CommuneController {
	@Autowired
	private Iservice iservice;
	private Commune commune = new Commune();
	private Commune selectedObject = new Commune();
	private List listObject = new ArrayList();

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
		int nbEnregistrement = this.iservice.getObjects("Commune").size();
		if(nbEnregistrement < 10)
			prefix = "COM00" ;
		if ((nbEnregistrement >= 10) && (nbEnregistrement < 100)) 
			prefix = "COM0" ;
		if (nbEnregistrement > 100) 
			prefix = "COM" ;
		this.commune.setCodeCommune(prefix+(nbEnregistrement+1));
	}
	
	public void enregistrer(){
		iservice.addObject(this.commune);
		annuler();
		info("Enregistrement effectué");
	}
	
	public void modifier() {
		iservice.updateObject(commune);
		annuler();
		info("Modification effectuée");
	}
		
	public void annuler() {
		commune.setCodeCommune(null);
		commune.setNomCommune(null);
		cmdBEnregistrer.setDisabled(false);
		cmdBModifier.setDisabled(true);
		genererCode();
	}
		
	public void selectionnerLigne() {
		commune = selectedObject;
		cmdBEnregistrer.setDisabled(true);
		cmdBModifier.setDisabled(false);
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
}
