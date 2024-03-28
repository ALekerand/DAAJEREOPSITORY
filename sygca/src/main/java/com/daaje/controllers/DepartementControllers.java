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
import com.daaje.service.Iservice;

@Component
public class DepartementControllers {
	@Autowired
	public Iservice iservice;
	
	//public int idDrena;
	private Departement departement = new Departement();
	private Departement selectedObject = new Departement();
	private List<Departement> listObject = new ArrayList<Departement>();
	private List<Drena> listDrena = new ArrayList<Drena>();

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
		iservice.addObject(this.departement);
		annuler();
		info("Enregistrement effectué");
	}
	
	public void modifier() {
		iservice.updateObject(this.departement);
		annuler();
		info("Modification effectuée");
	    selectedObject = null;
	}
	
	public void annuler() {
		departement.setCodeDepartement(null);
		departement.setNomDepartement(null);
		etatBtnEnregistrer = false;
		etatBtnModifier = true;
		genererCode();
		selectedObject = null;// Réinitialiser l'élément sélectionner
	}
	
	public void selectionnerLigne() {
		departement = selectedObject;
		etatBtnEnregistrer = true;
		etatBtnModifier = false;
		
	}

	public void genererCode() {
		String prefix="";
		int nbEnregistrement = this.iservice.getObjects("Departement").size();
		if(nbEnregistrement < 10)
			prefix = "DEP00" ;
		if ((nbEnregistrement >= 10) && (nbEnregistrement < 100)) 
			prefix = "DEP0" ;
		if (nbEnregistrement > 100) 
			prefix = "DEP" ;
		this.departement.setCodeDepartement(prefix+(nbEnregistrement+1));
	}
		
	public void info(String message){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,message,null));	
		}
			
//Getters and setters
	public List<Departement> getListObject() {
		listObject = iservice.getObjects("Departement");
		
		//=======Pour le rangement par ordre alphabétique======
				Collections.sort(listObject, new Comparator<Departement>() {
			        @Override
			        public int compare(Departement ob1, Departement ob2)
			        {
			 
			            return  ob1.getNomDepartement().compareTo(ob2.getNomDepartement());
			        }
			    });
				//========================  Fin  =======================
		
		return listObject;
	}
	
	public void setListObject(List listObject) {
		this.listObject = listObject;
	}
	
	public Departement getSelectedObject() {
		return selectedObject;
	}


	public void setSelectedObject(Departement selectedObject) {
		this.selectedObject = selectedObject;
	}
		
	public Departement getDepartement() {
		return departement;
	}

	public void setDepartement(Departement departement) {
		this.departement = departement;
	}

	
	public List<Drena> getListDrena() {
		return listDrena = iservice.getObjects("Drena");
		}


		public void setListDrena(List<Drena> listDrena) {
			this.listDrena = listDrena;
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
