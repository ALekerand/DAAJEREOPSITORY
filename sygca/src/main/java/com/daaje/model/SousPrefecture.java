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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * SousPrefecture generated by hbm2java
 */
@Entity
@Table(name = "sous_prefecture", catalog = "sygca_bd")
public class SousPrefecture implements java.io.Serializable {

	private Integer idSousPrefecture;
	private Departement departement;
	private String codeSousPrefecture;
	private String nomSousPrefecture;
	private Set<LocaliteDImplantation> localiteDImplantations = new HashSet<LocaliteDImplantation>(0);

	public SousPrefecture() {
	}

	public SousPrefecture(Departement departement) {
		this.departement = departement;
	}

	public SousPrefecture(Departement departement, String codeSousPrefecture, String nomSousPrefecture,
			Set<LocaliteDImplantation> localiteDImplantations) {
		this.departement = departement;
		this.codeSousPrefecture = codeSousPrefecture;
		this.nomSousPrefecture = nomSousPrefecture;
		this.localiteDImplantations = localiteDImplantations;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID_SOUS_PREFECTURE", unique = true, nullable = false)
	public Integer getIdSousPrefecture() {
		return this.idSousPrefecture;
	}

	public void setIdSousPrefecture(Integer idSousPrefecture) {
		this.idSousPrefecture = idSousPrefecture;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_DEPARTEMENT", nullable = false)
	public Departement getDepartement() {
		return this.departement;
	}

	public void setDepartement(Departement departement) {
		this.departement = departement;
	}

	@Column(name = "CODE_SOUS_PREFECTURE", length = 10)
	public String getCodeSousPrefecture() {
		return this.codeSousPrefecture;
	}

	public void setCodeSousPrefecture(String codeSousPrefecture) {
		this.codeSousPrefecture = codeSousPrefecture;
	}

	@Column(name = "NOM_SOUS_PREFECTURE", length = 30)
	public String getNomSousPrefecture() {
		return this.nomSousPrefecture;
	}

	public void setNomSousPrefecture(String nomSousPrefecture) {
		this.nomSousPrefecture = nomSousPrefecture;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "sousPrefecture")
	public Set<LocaliteDImplantation> getLocaliteDImplantations() {
		return this.localiteDImplantations;
	}

	public void setLocaliteDImplantations(Set<LocaliteDImplantation> localiteDImplantations) {
		this.localiteDImplantations = localiteDImplantations;
	}

}
