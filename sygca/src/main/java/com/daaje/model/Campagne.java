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
 * Campagne generated by hbm2java
 */
@Entity
@Table(name = "campagne", catalog = "sygca_bd")
public class Campagne implements java.io.Serializable {

	private Integer idCampagne;
	private String codeCampagne;
	private Date debutCampagne;
	private Date finCampagne;
	private String libelleCampagne;
	private Boolean etatCampagne;

	public Campagne() {
	}

	public Campagne(String codeCampagne, Date debutCampagne, Date finCampagne, String libelleCampagne,
			Boolean etatCampagne) {
		this.codeCampagne = codeCampagne;
		this.debutCampagne = debutCampagne;
		this.finCampagne = finCampagne;
		this.libelleCampagne = libelleCampagne;
		this.etatCampagne = etatCampagne;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID_CAMPAGNE", unique = true, nullable = false)
	public Integer getIdCampagne() {
		return this.idCampagne;
	}

	public void setIdCampagne(Integer idCampagne) {
		this.idCampagne = idCampagne;
	}

	@Column(name = "CODE_CAMPAGNE", length = 10)
	public String getCodeCampagne() {
		return this.codeCampagne;
	}

	public void setCodeCampagne(String codeCampagne) {
		this.codeCampagne = codeCampagne;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DEBUT_CAMPAGNE", length = 10)
	public Date getDebutCampagne() {
		return this.debutCampagne;
	}

	public void setDebutCampagne(Date debutCampagne) {
		this.debutCampagne = debutCampagne;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FIN_CAMPAGNE", length = 10)
	public Date getFinCampagne() {
		return this.finCampagne;
	}

	public void setFinCampagne(Date finCampagne) {
		this.finCampagne = finCampagne;
	}

	@Column(name = "LIBELLE_CAMPAGNE", length = 25)
	public String getLibelleCampagne() {
		return this.libelleCampagne;
	}

	public void setLibelleCampagne(String libelleCampagne) {
		this.libelleCampagne = libelleCampagne;
	}

	@Column(name = "ETAT_CAMPAGNE")
	public Boolean getEtatCampagne() {
		return this.etatCampagne;
	}

	public void setEtatCampagne(Boolean etatCampagne) {
		this.etatCampagne = etatCampagne;
	}

}
