package com.daaje.model;
// Generated 2 nov. 2023, 15:04:15 by Hibernate Tools 4.3.6.Final

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
 * Langue generated by hbm2java
 */
@Entity
@Table(name = "langue", catalog = "sygca_bd")
public class Langue implements java.io.Serializable {

	private Integer idLangue;
	private String codeLangue;
	private String libLangue;
	private Set<Enseigner> enseigners = new HashSet<Enseigner>(0);

	public Langue() {
	}

	public Langue(String codeLangue, String libLangue, Set<Enseigner> enseigners) {
		this.codeLangue = codeLangue;
		this.libLangue = libLangue;
		this.enseigners = enseigners;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID_LANGUE", unique = true, nullable = false)
	public Integer getIdLangue() {
		return this.idLangue;
	}

	public void setIdLangue(Integer idLangue) {
		this.idLangue = idLangue;
	}

	@Column(name = "CODE_LANGUE", length = 20)
	public String getCodeLangue() {
		return this.codeLangue;
	}

	public void setCodeLangue(String codeLangue) {
		this.codeLangue = codeLangue;
	}

	@Column(name = "LIB_LANGUE", length = 25)
	public String getLibLangue() {
		return this.libLangue;
	}

	public void setLibLangue(String libLangue) {
		this.libLangue = libLangue;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "langue")
	public Set<Enseigner> getEnseigners() {
		return this.enseigners;
	}

	public void setEnseigners(Set<Enseigner> enseigners) {
		this.enseigners = enseigners;
	}

}
