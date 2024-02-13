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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Departement generated by hbm2java
 */
@Entity
@Table(name = "departement", catalog = "sygca_bd")
public class Departement implements java.io.Serializable {

	private Integer idDepartement;
	private String codeDepartement;
	private String nomDepartement;
	private Set<SousPrefecture> sousPrefectures = new HashSet<SousPrefecture>(0);
	private Set<DrenaDepartement> drenaDepartements = new HashSet<DrenaDepartement>(0);

	public Departement() {
	}

	public Departement(String codeDepartement, String nomDepartement, Set<SousPrefecture> sousPrefectures,
			Set<DrenaDepartement> drenaDepartements) {
		this.codeDepartement = codeDepartement;
		this.nomDepartement = nomDepartement;
		this.sousPrefectures = sousPrefectures;
		this.drenaDepartements = drenaDepartements;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID_DEPARTEMENT", unique = true, nullable = false)
	public Integer getIdDepartement() {
		return this.idDepartement;
	}

	public void setIdDepartement(Integer idDepartement) {
		this.idDepartement = idDepartement;
	}

	@Column(name = "CODE_DEPARTEMENT", length = 10)
	public String getCodeDepartement() {
		return this.codeDepartement;
	}

	public void setCodeDepartement(String codeDepartement) {
		this.codeDepartement = codeDepartement;
	}

	@Column(name = "NOM_DEPARTEMENT", length = 30)
	public String getNomDepartement() {
		return this.nomDepartement;
	}

	public void setNomDepartement(String nomDepartement) {
		this.nomDepartement = nomDepartement;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "departement")
	public Set<SousPrefecture> getSousPrefectures() {
		return this.sousPrefectures;
	}

	public void setSousPrefectures(Set<SousPrefecture> sousPrefectures) {
		this.sousPrefectures = sousPrefectures;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "departement")
	public Set<DrenaDepartement> getDrenaDepartements() {
		return this.drenaDepartements;
	}

	public void setDrenaDepartements(Set<DrenaDepartement> drenaDepartements) {
		this.drenaDepartements = drenaDepartements;
	}

}
