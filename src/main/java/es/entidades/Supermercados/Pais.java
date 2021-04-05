package es.entidades.Supermercados;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity

public class Pais implements Serializable {

	private static final long serialVersionUID = 1248780995512223123L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long idPais;

	private String nombrePais;

	private Long poblacion;
	
	@OneToMany(mappedBy = "pais", cascade = CascadeType.ALL)
	private List<Supermercado> supermercados;

	public List<Supermercado> getSupermercados() {
		return supermercados;
	}

	public void setSupermercados(List<Supermercado> supermercados) {
		this.supermercados = supermercados;
	}

	public Pais() {

	}

	public Pais( String nombrePais, Long poblacion) {
	
		this.nombrePais = nombrePais;
		this.poblacion = poblacion;
	}

	public Long getIdPais() {
		return idPais;
	}

	public void setIdPais(Long idPais) {
		this.idPais = idPais;
	}

	public String getNombrePais() {
		return nombrePais;
	}

	public void setNombrePais(String nombrePais) {
		this.nombrePais = nombrePais;
	}

	public Long getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(Long poblacion) {
		this.poblacion = poblacion;
	}

	
	@Override
	public String toString() {
		return "Pais [idPais=" + idPais + ", nombrePais=" + nombrePais + ", poblacion=" + poblacion + ", supermercados="
				+ supermercados + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPais == null) ? 0 : idPais.hashCode());
		result = prime * result + ((nombrePais == null) ? 0 : nombrePais.hashCode());
		result = prime * result + ((poblacion == null) ? 0 : poblacion.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pais other = (Pais) obj;
		if (idPais == null) {
			if (other.idPais != null)
				return false;
		} else if (!idPais.equals(other.idPais))
			return false;
		if (nombrePais == null) {
			if (other.nombrePais != null)
				return false;
		} else if (!nombrePais.equals(other.nombrePais))
			return false;
		if (poblacion == null) {
			if (other.poblacion != null)
				return false;
		} else if (!poblacion.equals(other.poblacion))
			return false;
		return true;
	}

}
