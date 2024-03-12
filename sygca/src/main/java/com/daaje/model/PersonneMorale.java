package com.daaje.model;
// Generated 13 f�vr. 2024, 11:13:45 by Hibernate Tools 4.3.6.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * PersonneMorale generated by hbm2java
 */
@Entity
@Table(name = "personne_morale", catalog = "sygca_bd")
public class PersonneMorale implements java.io.Serializable {

	private int idPromoteur;
	private Promoteur promoteur;
	private String codePromoteur;
	private String raisonSociale;
	private String emailPersMorale;
	private String telephonePersMorale;

	public PersonneMorale() {
	}

	public PersonneMorale(Promoteur promoteur) {
		this.promoteur = promoteur;
	}

	public PersonneMorale(Promoteur promoteur, String codePromoteur, String raisonSociale, String emailPersMorale,
			String telephonePersMorale) {
		this.promoteur = promoteur;
		this.codePromoteur = codePromoteur;
		this.raisonSociale = raisonSociale;
		this.emailPersMorale = emailPersMorale;
		this.telephonePersMorale = telephonePersMorale;
	}

	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "promoteur"))
	@Id
	@GeneratedValue(generator = "generator")

	@Column(name = "ID_PROMOTEUR", unique = true, nullable = false)
	public int getIdPromoteur() {
		return this.idPromoteur;
	}

	public void setIdPromoteur(int idPromoteur) {
		this.idPromoteur = idPromoteur;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public Promoteur getPromoteur() {
		return this.promoteur;
	}

	public void setPromoteur(Promoteur promoteur) {
		this.promoteur = promoteur;
	}

	@Column(name = "CODE_PROMOTEUR", length = 10)
	public String getCodePromoteur() {
		return this.codePromoteur;
	}

	public void setCodePromoteur(String codePromoteur) {
		this.codePromoteur = codePromoteur;
	}

	@Column(name = "RAISON_SOCIALE", length = 30)
	public String getRaisonSociale() {
		return this.raisonSociale;
	}

	public void setRaisonSociale(String raisonSociale) {
		this.raisonSociale = raisonSociale;
	}

	@Column(name = "EMAIL_PERS_MORALE", length = 50)
	public String getEmailPersMorale() {
		return this.emailPersMorale;
	}

	public void setEmailPersMorale(String emailPersMorale) {
		this.emailPersMorale = emailPersMorale;
	}

	@Column(name = "TELEPHONE_PERS_MORALE", length = 20)
	public String getTelephonePersMorale() {
		return this.telephonePersMorale;
	}

	public void setTelephonePersMorale(String telephonePersMorale) {
		this.telephonePersMorale = telephonePersMorale;
	}

}