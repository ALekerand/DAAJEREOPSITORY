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

import com.daaje.model.Apprenant;
import com.daaje.model.Centre;
import com.daaje.model.Departement;
import com.daaje.model.Drena;
import com.daaje.model.Genre;
import com.daaje.model.Inscription;
import com.daaje.requetes.RequeteCampagne;
import com.daaje.requetes.RequeteInscription;
import com.daaje.service.Iservice;

@Component
public class ListeApprenantController {
	@Autowired
	private Iservice iservice;
	@Autowired
	private RequeteInscription requeteInscription;
	@Autowired
	private RequeteCampagne requeteCampagne;
	
	
	private int idCentre;
	private List<Apprenant> listApprenant = new ArrayList<Apprenant>();
	private List<Centre> listCentres = new ArrayList<Centre>();
	

//METHODES
	public List<Apprenant> chargerListApprenant() {
		listApprenant.clear();
		for (Inscription inscription : requeteInscription.recupInscriptionParCentreParCampagne(idCentre, requeteCampagne.recupLastCampagne().getIdCampagne())) {
			listApprenant.add(inscription.getApprenant());
		}
		
		//=======Pour le rangement par ordre alphabétique======
				Collections.sort(listApprenant, new Comparator<Apprenant>() {
			        @Override
			        public int compare(Apprenant ob1, Apprenant ob2)
			        {
			 
			            return  ob1.getNomApprenant().compareTo(ob2.getNomApprenant());
			        }
			    });
				//========================  Fin  =======================
		return listApprenant;
	}
	
	
//GETTERS AND SETTERS	
	public List<Apprenant> getListApprenant() {
		return listApprenant;
	}
	public void setListApprenant(List<Apprenant> listApprenant) {
		this.listApprenant = listApprenant;
	}


	public int getIdCentre() {
		return idCentre;
	}


	public void setIdCentre(int idCentre) {
		this.idCentre = idCentre;
	}


	public List<Centre> getListCentres() {
		listCentres = iservice.getObjects("Centre");
		
		//=======Pour le rangement par ordre alphabétique======
		Collections.sort(listCentres, new Comparator<Centre>() {
	        @Override
	        public int compare(Centre ob1, Centre ob2){
	 
	            return  ob1.getNomCentre().compareTo(ob2.getNomCentre());
	        }
	    });
		//========================  Fin  =======================
		
		return listCentres;
	}


	public void setListCentres(List<Centre> listCentres) {
		this.listCentres = listCentres;
	}

}
