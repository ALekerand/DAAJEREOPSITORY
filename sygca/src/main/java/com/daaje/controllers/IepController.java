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
import com.daaje.model.Drena;
import com.daaje.model.Iep;
import com.daaje.service.Iservice;

@Component
public class IepController {
	@Autowired
	private Iservice iservice;
	private int idDrena;
	private Iep iep = new Iep();
	private Iep selectedObject = new Iep();
	private List listObject = new ArrayList();
	private List<Drena> listDrena = new ArrayList<Drena>();
	
//Controle des composants
	private CommandButton cmdBModifier = new CommandButton();
	private CommandButton cmdBEnregistrer = new CommandButton();
		
//Methodes
@PostConstruct
	public void initialisation(){
		this.cmdBModifier.setDisabled(true);
		//genererCode();
	}

/*public void genererCode() {
	String prefix="";
	int nbEnregistrement = this.iservice.getObjects("Iep").size();
	if(nbEnregistrement < 10)
		prefix = "IEP00" ;
	if ((nbEnregistrement >= 10) && (nbEnregistrement < 100)) 
		prefix = "IEP0" ;
	if (nbEnregistrement > 100) 
		prefix = "IEP" ;
	this.iep.setCodeIep(prefix+(nbEnregistrement+1));
}*/
		
	public void enregistrer(){
		iep.setDrena((Drena) iservice.getObjectById(idDrena, "Drena"));
		iservice.addObject(this.iep);
		annuler();
		info("Enregistrement effectué");
	}
	
	public void modifier() {
		iservice.updateObject(iep);
		annuler();
		info("Modification effectuée");
	}
	
	public void annuler() {
		iep.setCodeIep(null);
		iep.setNomIep(null);
		iep.setMailIep(null);
		cmdBEnregistrer.setDisabled(false);
		cmdBModifier.setDisabled(true);
		//genererCode();
	}
	
	public void selectionnerLigne() {
		iep = selectedObject;
		cmdBEnregistrer.setDisabled(true);
		cmdBModifier.setDisabled(false);
	}
	
	public void info(String message){
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,message,null));	
	}
		
//Getters and setters
	public List<Iep> getListObject() {
		listObject = iservice.getObjects("Iep");

		//=======Pour le rangement par ordre alphabétique======
		Collections.sort(listObject, new Comparator<Iep>() {
	        @Override
	        public int compare(Iep ob1, Iep ob2)
	        {
	 
	            return  ob1.getNomIep().compareTo(ob2.getNomIep());
	        }
	    });
		//========================  Fin  =======================

return listObject;
		
	}

	public void setListObject(List listObject) {
		this.listObject = listObject;
	}

	public Iep getSelectedObject() {
		return selectedObject;
	}

	public void setSelectedObject(Iep selectedObject) {
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


	public Iep getIep() {
		return iep;
	}


	public void setIep(Iep iep) {
		this.iep = iep;
	}

	public List<Drena> getListDrena() {
		return listDrena = iservice.getObjects("Drena");
		
	}


	public void setListDrena(List<Drena> listDrena) {
		this.listDrena = listDrena;
	}


	public int getIdDrena() {
		return idDrena;
	}


	public void setIdDrena(int idDrena) {
		this.idDrena = idDrena;
	}

}
