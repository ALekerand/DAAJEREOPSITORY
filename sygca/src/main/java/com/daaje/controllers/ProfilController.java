package com.daaje.controllers;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.component.linkbutton.LinkButton;
import org.springframework.stereotype.Component;

@Component
public class ProfilController {
	private String profil;
	//private String lien;
	private LinkButton linkButton = new LinkButton();
	
	public void router() {
	//	lien ="";
		
		switch (profil) {
		case "coodornateur_iep": {
			linkButton.setOutcome("/templates/templateCoodonnateurIE.xhtml");
			System.out.println("======== lien :"+ linkButton.getOutcome());
			break;
		}
		
		case "superviseur_drena": {
			linkButton.setOutcome("/templates/templateSuperperviseurDRENA.xhtml");
			System.out.println("======== lien :"+ linkButton.getOutcome());
			break;
		}
		
		
		case "utilisateur_simple": {
			linkButton.setOutcome("/templates/templateUtilistaeurSimple.xhtml");
			System.out.println("======== lien :"+ linkButton.getOutcome());
			break;
		}
		
	case "adminstrateur": {
		linkButton.setOutcome("/templates/template.xhtml");
		System.out.println("======== lien :"+ linkButton.getOutcome());
		break;
	
	}
	
		
		default:
	}
		
	}
	
	
	
	public void info(String message){
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,message,null));	
	}
	
	
	
	
	// GETTERS AND SETTERS

	public String getProfil() {
		return profil;
	}

	public void setProfil(String profil) {
		this.profil = profil;
	}




	/*
	 * public String getLien() { return lien; }
	 * 
	 * 
	 * 
	 * 
	 * public void setLien(String lien) { this.lien = lien; }
	 */



	public LinkButton getLinkButton() {
		return linkButton;
	}




	public void setLinkButton(LinkButton linkButton) {
		this.linkButton = linkButton;
	}
}
