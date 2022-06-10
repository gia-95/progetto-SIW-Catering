package it.siw.catering.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Buffet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String nome;
	
	private String descrizione;

	private String urlImg;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	private List<Piatto> primiPiatti;

	@ManyToMany(cascade = CascadeType.PERSIST)
	private List<Piatto> secondiPiatti;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	private List<Piatto> dolci;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Chef chef;

	public Chef getChef() {
		return chef;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public Buffet() {
		this.primiPiatti = new ArrayList<>();
		this.secondiPiatti = new ArrayList<>();
		this.dolci = new ArrayList<>();

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Piatto> getPrimiPiatti() {
		return primiPiatti;
	}

	public void setPrimiPiatti(List<Piatto> primiPiatti) {
		this.primiPiatti = primiPiatti;
	}

	public List<Piatto> getSecondiPiatti() {
		return secondiPiatti;
	}

	public void setSecondiPiatti(List<Piatto> secondiPiatti) {
		this.secondiPiatti = secondiPiatti;
	}
	
	public void setDolci(List<Piatto> dolci) {
		this.dolci = dolci;
	}
	
	public List<Piatto> getDolci() {
		return dolci;
	}

	public void setUrlImg(String urlImg) {
		this.urlImg = urlImg;
	}
	
	public String getUrlImg() {
		return urlImg;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}
