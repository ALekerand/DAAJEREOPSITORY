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
 * Ministere generated by hbm2java
 */
@Entity
@Table(name = "ministere", catalog = "sygca_bd")
public class Ministere implements java.io.Serializable {

	private int idPromoteur;
	private Promoteur promoteur;
	private String codePromoteur;
	private String nomMinistere;
	private String telephoneMinistere;

	public Ministere() {
	}

	public Ministere(Promoteur promoteur) {
		this.promoteur = promoteur;
	}

	public Ministere(Promoteur promoteur, String codePromoteur, String nomMinistere, String telephoneMinistere) {
		this.promoteur = promoteur;
		this.codePromoteur = codePromoteur;
		this.nomMinistere = nomMinistere;
		this.telephoneMinistere = telephoneMinistere;
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

	@Column(name = "NOM_MINISTERE", length = 30)
	public String getNomMinistere() {
		return this.nomMinistere;
	}

	public void setNomMinistere(String nomMinistere) {
		this.nomMinistere = nomMinistere;
	}

	@Column(name = "TELEPHONE_MINISTERE", length = 15)
	public String getTelephoneMinistere() {
		return this.telephoneMinistere;
	}

	public void setTelephoneMinistere(String telephoneMinistere) {
		this.telephoneMinistere = telephoneMinistere;
	}

}
