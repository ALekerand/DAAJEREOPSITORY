package com.daaje.model;
// Generated 15 sept. 2023, 12:30:20 by Hibernate Tools 4.3.6.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Responsable generated by hbm2java
 */
@Entity
@Table(name = "responsable", catalog = "sygca_bd")
public class Responsable implements java.io.Serializable {

	private Integer idResponsable;
	private int idFonction;
	private String matriculeResponsable;
	private String nomResponsable;
	private String prenomResponsable;
	private String telephoneResponsable;
	private String adresseResponsable;
	private Date datePriseService;
	private Date dateRetraite;
	private Date dateNaissance;
	private String mailResponsable;

	public Responsable() {
	}

	public Responsable(int idFonction) {
		this.idFonction = idFonction;
	}

	public Responsable(int idFonction, String matriculeResponsable, String nomResponsable, String prenomResponsable,
			String telephoneResponsable, String adresseResponsable, Date datePriseService, Date dateRetraite,
			Date dateNaissance, String mailResponsable) {
		this.idFonction = idFonction;
		this.matriculeResponsable = matriculeResponsable;
		this.nomResponsable = nomResponsable;
		this.prenomResponsable = prenomResponsable;
		this.telephoneResponsable = telephoneResponsable;
		this.adresseResponsable = adresseResponsable;
		this.datePriseService = datePriseService;
		this.dateRetraite = dateRetraite;
		this.dateNaissance = dateNaissance;
		this.mailResponsable = mailResponsable;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID_RESPONSABLE", unique = true, nullable = false)
	public Integer getIdResponsable() {
		return this.idResponsable;
	}

	public void setIdResponsable(Integer idResponsable) {
		this.idResponsable = idResponsable;
	}

	@Column(name = "ID_FONCTION", nullable = false)
	public int getIdFonction() {
		return this.idFonction;
	}

	public void setIdFonction(int idFonction) {
		this.idFonction = idFonction;
	}

	@Column(name = "MATRICULE_RESPONSABLE", length = 10)
	public String getMatriculeResponsable() {
		return this.matriculeResponsable;
	}

	public void setMatriculeResponsable(String matriculeResponsable) {
		this.matriculeResponsable = matriculeResponsable;
	}

	@Column(name = "NOM_RESPONSABLE", length = 30)
	public String getNomResponsable() {
		return this.nomResponsable;
	}

	public void setNomResponsable(String nomResponsable) {
		this.nomResponsable = nomResponsable;
	}

	@Column(name = "PRENOM_RESPONSABLE", length = 30)
	public String getPrenomResponsable() {
		return this.prenomResponsable;
	}

	public void setPrenomResponsable(String prenomResponsable) {
		this.prenomResponsable = prenomResponsable;
	}

	@Column(name = "TELEPHONE_RESPONSABLE", length = 10)
	public String getTelephoneResponsable() {
		return this.telephoneResponsable;
	}

	public void setTelephoneResponsable(String telephoneResponsable) {
		this.telephoneResponsable = telephoneResponsable;
	}

	@Column(name = "ADRESSE_RESPONSABLE", length = 20)
	public String getAdresseResponsable() {
		return this.adresseResponsable;
	}

	public void setAdresseResponsable(String adresseResponsable) {
		this.adresseResponsable = adresseResponsable;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_PRISE_SERVICE", length = 10)
	public Date getDatePriseService() {
		return this.datePriseService;
	}

	public void setDatePriseService(Date datePriseService) {
		this.datePriseService = datePriseService;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_RETRAITE", length = 10)
	public Date getDateRetraite() {
		return this.dateRetraite;
	}

	public void setDateRetraite(Date dateRetraite) {
		this.dateRetraite = dateRetraite;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_NAISSANCE", length = 10)
	public Date getDateNaissance() {
		return this.dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	@Column(name = "MAIL_RESPONSABLE", length = 30)
	public String getMailResponsable() {
		return this.mailResponsable;
	}

	public void setMailResponsable(String mailResponsable) {
		this.mailResponsable = mailResponsable;
	}

}
