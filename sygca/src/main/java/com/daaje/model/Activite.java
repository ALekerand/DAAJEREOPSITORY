package com.daaje.model;
// Generated 8 avr. 2024, 14:52:14 by Hibernate Tools 4.3.6.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Activite generated by hbm2java
 */
@Entity
@Table(name = "activite", catalog = "sygca_bd")
public class Activite implements java.io.Serializable {

	private Integer idActivite;
	private String codeActivite;
	private String nomActivite;
	private Set<Profession> professions = new HashSet<Profession>(0);
	private Set<Apprenant> apprenants = new HashSet<Apprenant>(0);

	public Activite() {
	}

	public Activite(String codeActivite, String nomActivite, Set<Profession> professions, Set<Apprenant> apprenants) {
		this.codeActivite = codeActivite;
		this.nomActivite = nomActivite;
		this.professions = professions;
		this.apprenants = apprenants;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID_ACTIVITE", unique = true, nullable = false)
	public Integer getIdActivite() {
		return this.idActivite;
	}

	public void setIdActivite(Integer idActivite) {
		this.idActivite = idActivite;
	}

	@Column(name = "CODE_ACTIVITE", length = 10)
	public String getCodeActivite() {
		return this.codeActivite;
	}

	public void setCodeActivite(String codeActivite) {
		this.codeActivite = codeActivite;
	}

	@Column(name = "NOM_ACTIVITE", length = 30)
	public String getNomActivite() {
		return this.nomActivite;
	}

	public void setNomActivite(String nomActivite) {
		this.nomActivite = nomActivite;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "activite")
	public Set<Profession> getProfessions() {
		return this.professions;
	}

	public void setProfessions(Set<Profession> professions) {
		this.professions = professions;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "activite")
	public Set<Apprenant> getApprenants() {
		return this.apprenants;
	}

	public void setApprenants(Set<Apprenant> apprenants) {
		this.apprenants = apprenants;
	}

}
