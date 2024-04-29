package com.daaje.model;
// Generated 8 avr. 2024, 14:52:14 by Hibernate Tools 4.3.6.Final

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
 * Ong generated by hbm2java
 */
@Entity
@Table(name = "ong", catalog = "sygca_bd")
public class Ong implements java.io.Serializable {

	private int idPromoteur;
	private Promoteur promoteur;
	private String codePromoteur;
	private String nomOng;
	private String telephoneOng;
	private String mailOng;

	public Ong() {
	}

	public Ong(Promoteur promoteur) {
		this.promoteur = promoteur;
	}

	public Ong(Promoteur promoteur, String codePromoteur, String nomOng, String telephoneOng, String mailOng) {
		this.promoteur = promoteur;
		this.codePromoteur = codePromoteur;
		this.nomOng = nomOng;
		this.telephoneOng = telephoneOng;
		this.mailOng = mailOng;
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

	@Column(name = "NOM_ONG", length = 25)
	public String getNomOng() {
		return this.nomOng;
	}

	public void setNomOng(String nomOng) {
		this.nomOng = nomOng;
	}

	@Column(name = "TELEPHONE_ONG", length = 15)
	public String getTelephoneOng() {
		return this.telephoneOng;
	}

	public void setTelephoneOng(String telephoneOng) {
		this.telephoneOng = telephoneOng;
	}

	@Column(name = "MAIL_ONG", length = 20)
	public String getMailOng() {
		return this.mailOng;
	}

	public void setMailOng(String mailOng) {
		this.mailOng = mailOng;
	}

}
