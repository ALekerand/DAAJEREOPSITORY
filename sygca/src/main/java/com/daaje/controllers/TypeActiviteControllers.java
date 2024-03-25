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

import com.daaje.model.Departement;
import com.daaje.model.TypeActivite;
import com.daaje.service.Iservice;

@Component
public class TypeActiviteControllers {
@Autowired
	private Iservice iservice;
    private TypeActivite typeActivite =  new TypeActivite();
    private TypeActivite selectedObject = new TypeActivite();
    private List listObject = new ArrayList();
	
//Controle des composants
    private CommandButton cmdBModifier = new CommandButton();
    private CommandButton cmdBEnregistrer = new CommandButton();
    private boolean enregistrerDisabled = false;
	
//Methodes
@PostConstruct

	public void init() {
		initialisation();
	}
	
	public boolean isEnregistrerDisabled() {
	    return enregistrerDisabled;
	}
	
	public void setEnregistrerDisabled(boolean enregistrerDisabled) {
	    this.enregistrerDisabled = enregistrerDisabled;
	}
	
public void genererCode() {
	String prefix="";
	int nbEnregistrement = this.iservice.getObjects("TypeActivite").size();
	if(nbEnregistrement < 10)
		prefix = "TA00" ;
	if ((nbEnregistrement >= 10) && (nbEnregistrement < 100)) 
		prefix = "TA0" ;
	if (nbEnregistrement > 100) 
		prefix = "TA" ;
	this.typeActivite.setCodeTypeactivite(prefix+(nbEnregistrement+1));
}

	public void initialisation(){
		cmdBModifier.setDisabled(true);
		cmdBEnregistrer.setDisabled(true);
		genererCode();
	}
	
	public void enregistrer(){
		iservice.addObject(this.typeActivite);
		cmdBEnregistrer.setDisabled(true);
		annuler();
		info("Enregistrement effectué");
	}
	
	public void modifier() {
		iservice.updateObject(this.typeActivite);
		annuler();
		info("Modification effectuée");
		selectedObject = null;
	}
	
	public void annuler() {
		typeActivite.setCodeTypeactivite(null);
		typeActivite.setLibelleTypeactivite(null);
		setEnregistrerDisabled(false);//Réactivez le bouton Enregistrer
		cmdBModifier.setDisabled(true);
		genererCode();
		selectedObject = null;// Réinitialiser l'élément sélectionner
	}
	
	public void selectionnerLigne() {
		typeActivite = selectedObject;
		cmdBModifier.setDisabled(false);
		setEnregistrerDisabled(true);
		
	}
	
	public void info(String message){
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,message,null));	
	}

//Getters and setters
	public TypeActivite getTypeActivite() {
		return typeActivite;
	}

	public void setTypeActivite(TypeActivite typeActivite) {
		this.typeActivite = typeActivite;
	}

	public List<TypeActivite> getListObject() {
		listObject = iservice.getObjects("TypeActivite");
		
		//=======Pour le rangement par ordre alphabétique======
		Collections.sort(listObject, new Comparator<TypeActivite>() {
	        @Override
	        public int compare(TypeActivite ob1, TypeActivite ob2)
	        {
	 
	            return  ob1.getLibelleTypeactivite().compareTo(ob2.getLibelleTypeactivite());
	        }
	    });
		//========================  Fin  =======================

return listObject;

	}

	public void setListObject(List listObject) {
		this.listObject = listObject;
	}

	public TypeActivite getSelectedObject() {
		return selectedObject;
	}

	public void setSelectedObject(TypeActivite selectedObject) {
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
