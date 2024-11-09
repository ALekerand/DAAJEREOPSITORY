package com.daaje.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.component.commandbutton.CommandButton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.daaje.model.Campagne;
import com.daaje.model.Centre;
import com.daaje.model.Departement;
import com.daaje.model.Enseigner;
import com.daaje.model.Genre;
import com.daaje.model.Ministere;
import com.daaje.model.Ong;
import com.daaje.model.PersonneMorale;
import com.daaje.model.PersonnePhysique;
import com.daaje.model.Programme;
import com.daaje.model.Promoteur;
import com.daaje.requetes.RequeteCampagne;
import com.daaje.requetes.RequeteCentre;
import com.daaje.service.Iservice;

@Component
@Scope("session")
public class ConsultationDetailCentreController {
	@Autowired
	private Iservice iservice;
	
	
	private Centre selectedCentre = new Centre();
	private Promoteur promoteur = new Promoteur();
	private PersonnePhysique personnePhysique = new PersonnePhysique();
	

	private PersonneMorale personneMorale = new PersonneMorale();
	private Ong ong = new Ong();
	private Programme programme = new Programme();
	private Ministere ministere = new Ministere();
	private String type_promoteur;
	
	//Gestion des composants sur la vue
	private boolean pGridOng;
	private boolean pGridPh;
	private boolean pGridMini;
	private boolean pGridProg;
	private boolean pGridEntrep;
	
	public void collecterInfo() {
		// Propoteur
		 
			switch (type_promoteur){
			
			
			
			case "personne_physique": {
				personnePhysique.setPromoteur(promoteur);
				personnePhysique.setCodePromoteur(promoteur.getCodePromoteur());
				iservice.addObject(personnePhysique);
				break;
			}
			
			case "personne_morale": {
				personneMorale.setPromoteur(promoteur);
				personneMorale.setCodePromoteur(promoteur.getCodePromoteur());
				iservice.addObject(personneMorale);
				break;
			}
			
			
			case "ong": {
				ong.setPromoteur(promoteur);
				ong.setCodePromoteur(promoteur.getCodePromoteur());
				iservice.addObject(ong);
				break;
			}
			
			case "programme": {
				programme.setPromoteur(promoteur);
				programme.setCodePromoteur(promoteur.getCodePromoteur());
				iservice.addObject(programme);
				break;
			}
			
			case "ministere": {
				ministere.setPromoteur(promoteur);
				ministere.setCodePromoteur(promoteur.getCodePromoteur());
				iservice.addObject(ministere);
				break;
			}
			}
		
	}
	
	public void consulterDetailCentre() {
		
	}

	
	//Getters and setters
	public Centre getSelectedCentre() {
		return selectedCentre;
	}

	public void setSelectedCentre(Centre selectedCentre) {
		this.selectedCentre = selectedCentre;
	}

	public Promoteur getPromoteur() {
		return promoteur;
	}

	public void setPromoteur(Promoteur promoteur) {
		this.promoteur = promoteur;
	}

	public PersonnePhysique getPersonnePhysique() {
		return personnePhysique;
	}


	public void setPersonnePhysique(PersonnePhysique personnePhysique) {
		this.personnePhysique = personnePhysique;
	}


	public PersonneMorale getPersonneMorale() {
		return personneMorale;
	}


	public void setPersonneMorale(PersonneMorale personneMorale) {
		this.personneMorale = personneMorale;
	}


	public Ong getOng() {
		return ong;
	}


	public void setOng(Ong ong) {
		this.ong = ong;
	}


	public Programme getProgramme() {
		return programme;
	}


	public void setProgramme(Programme programme) {
		this.programme = programme;
	}


	public Ministere getMinistere() {
		return ministere;
	}


	public void setMinistere(Ministere ministere) {
		this.ministere = ministere;
	}


	public String getType_promoteur() {
		return type_promoteur;
	}


	public void setType_promoteur(String type_promoteur) {
		this.type_promoteur = type_promoteur;
	}


	public boolean ispGridOng() {
		return pGridOng;
	}


	public void setpGridOng(boolean pGridOng) {
		this.pGridOng = pGridOng;
	}


	public boolean ispGridPh() {
		return pGridPh;
	}


	public void setpGridPh(boolean pGridPh) {
		this.pGridPh = pGridPh;
	}


	public boolean ispGridMini() {
		return pGridMini;
	}


	public void setpGridMini(boolean pGridMini) {
		this.pGridMini = pGridMini;
	}


	public boolean ispGridProg() {
		return pGridProg;
	}


	public void setpGridProg(boolean pGridProg) {
		this.pGridProg = pGridProg;
	}


	public boolean ispGridEntrep() {
		return pGridEntrep;
	}


	public void setpGridEntrep(boolean pGridEntrep) {
		this.pGridEntrep = pGridEntrep;
	}
}
