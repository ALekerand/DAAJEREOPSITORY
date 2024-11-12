package com.daaje.controllers;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.component.linkbutton.LinkButton;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class ProfilController {
	private String profil;
	private LinkButton linkButton = new LinkButton();
	
	public void router() {
		
		switch (profil) {
		
		case "conseiller": {
			linkButton.setOutcome("/templates/templateConseiller.xhtml");
			break;
		}
		case "coodornateur_iep": {
			linkButton.setOutcome("/templates/templateCoodonnateurIEP.xhtml");
			break;
		}
		
		case "superviseur_drena": {
			linkButton.setOutcome("/templates/templateSuperperviseurDRENA.xhtml");
			break;
		}
		
		
		case "utilisateur_simple": {
			linkButton.setOutcome("/templates/templateUtilistaeurSimple.xhtml");
			break;
		}
		
	case "adminstrateur": {
		linkButton.setOutcome("/templates/template.xhtml");
		break;
	
	}
	
	case "drena": {
		linkButton.setOutcome("/templates/template_DRENA.xhtml");
		break;
	}
	
	case "iepp": {
		linkButton.setOutcome("/templates/template_IEP.xhtml");
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

	public LinkButton getLinkButton() {
		return linkButton;
	}

	public void setLinkButton(LinkButton linkButton) {
		this.linkButton = linkButton;
	}
}
