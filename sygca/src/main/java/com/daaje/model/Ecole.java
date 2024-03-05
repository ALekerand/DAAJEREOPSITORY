package com.daaje.model;
// Generated 13 f�vr. 2024, 11:13:45 by Hibernate Tools 4.3.6.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Ecole generated by hbm2java
 */
@Entity
@Table(name = "ecole", catalog = "sygca_bd")
public class Ecole implements java.io.Serializable {

	private Integer idEcole;
	private Iep iep;
	private String codeEcole;
	private String nomEcole;
	private String adresseEcole;
	private String telphoneEcole;
	private Set<Centre> centres = new HashSet<Centre>(0);

	public Ecole() {
	}

	public Ecole(Iep iep) {
		this.iep = iep;
	}

	public Ecole(Iep iep, String codeEcole, String nomEcole, String adresseEcole, String telphoneEcole,
			Set<Centre> centres) {
		this.iep = iep;
		this.codeEcole = codeEcole;
		this.nomEcole = nomEcole;
		this.adresseEcole = adresseEcole;
		this.telphoneEcole = telphoneEcole;
		this.centres = centres;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID_ECOLE", unique = true, nullable = false)
	public Integer getIdEcole() {
		return this.idEcole;
	}

	public void setIdEcole(Integer idEcole) {
		this.idEcole = idEcole;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_IEP", nullable = false)
	public Iep getIep() {
		return this.iep;
	}

	public void setIep(Iep iep) {
		this.iep = iep;
	}

	@Column(name = "CODE_ECOLE", length = 10)
	public String getCodeEcole() {
		return this.codeEcole;
	}

	public void setCodeEcole(String codeEcole) {
		this.codeEcole = codeEcole;
	}

	@Column(name = "NOM_ECOLE", length = 100)
	public String getNomEcole() {
		return this.nomEcole;
	}

	public void setNomEcole(String nomEcole) {
		this.nomEcole = nomEcole;
	}

	@Column(name = "ADRESSE_ECOLE", length = 30)
	public String getAdresseEcole() {
		return this.adresseEcole;
	}

	public void setAdresseEcole(String adresseEcole) {
		this.adresseEcole = adresseEcole;
	}

	@Column(name = "TELPHONE_ECOLE", length = 15)
	public String getTelphoneEcole() {
		return this.telphoneEcole;
	}

	public void setTelphoneEcole(String telphoneEcole) {
		this.telphoneEcole = telphoneEcole;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ecole")
	public Set<Centre> getCentres() {
		return this.centres;
	}

	public void setCentres(Set<Centre> centres) {
		this.centres = centres;
	}

}
