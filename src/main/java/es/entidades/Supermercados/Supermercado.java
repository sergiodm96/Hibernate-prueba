package es.entidades.Supermercados;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table
public class Supermercado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSupermercado;

	private String nombreSupermercado;

//	@Transient
	private String ciudad;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "pais_id")
	private Pais pais;

	public Supermercado(String nombreSupermercado, String ciudad, Pais pais) {
		this.pais = pais;
		this.nombreSupermercado = nombreSupermercado;
		this.ciudad = ciudad;
	}

	public Supermercado() {

	}

	public Long getIdSupermercado() {
		return idSupermercado;
	}

	public void setIdSupermercado(Long idSupermercado) {
		this.idSupermercado = idSupermercado;
	}

	public String getNombreSupermercado() {
		return nombreSupermercado;
	}

	public void setNombreSupermercado(String nombreSupermercado) {
		this.nombreSupermercado = nombreSupermercado;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	@Override
	public String toString() {
		return "Supermercado [idSupermercado=" + idSupermercado + ", nombreSupermercado=" + nombreSupermercado
				+ ", ciudad=" + ciudad +", " + pais.getNombrePais() +"]";
	}
	
	

}
