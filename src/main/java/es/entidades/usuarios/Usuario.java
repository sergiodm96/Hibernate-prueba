package es.entidades.usuarios;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Usuario implements Serializable {

	private static final long serialVersionUID = 3689583150369471472L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String nombreUsuario;
	@Column
	private String nombreCompleto;
	@Column
	private String fechaNacimiento;

	
	//Needed to connect with the entity "Rol".s
	@ManyToMany
	@JoinTable(name = "usuario_roles", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Rol> roles;



	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombreUsuario=" + nombreUsuario + ", nombreCompleto=" + nombreCompleto
				+ ", fechaNacimiento=" + fechaNacimiento + ", roles=" + roles + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fechaNacimiento == null) ? 0 : fechaNacimiento.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombreCompleto == null) ? 0 : nombreCompleto.hashCode());
		result = prime * result + ((nombreUsuario == null) ? 0 : nombreUsuario.hashCode());
		result = prime * result + ((roles == null) ? 0 : roles.hashCode());
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
		Usuario other = (Usuario) obj;
		if (fechaNacimiento == null) {
			if (other.fechaNacimiento != null)
				return false;
		} else if (!fechaNacimiento.equals(other.fechaNacimiento))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nombreCompleto == null) {
			if (other.nombreCompleto != null)
				return false;
		} else if (!nombreCompleto.equals(other.nombreCompleto))
			return false;
		if (nombreUsuario == null) {
			if (other.nombreUsuario != null)
				return false;
		} else if (!nombreUsuario.equals(other.nombreUsuario))
			return false;
		if (roles == null) {
			if (other.roles != null)
				return false;
		} else if (!roles.equals(other.roles))
			return false;
		return true;
	}

	public Usuario(String nombreUsuario, String nombreCompleto, String fechaNacimiento, List<Rol> roles) {
	
		this.nombreUsuario = nombreUsuario;
		this.nombreCompleto = nombreCompleto;
		this.fechaNacimiento = fechaNacimiento;
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}
	
	public void addRoles(List<Rol> roles) {
		
		
		for (Rol rol : roles) {
			this.roles.add(rol);
		}
	}

	public Usuario() {

	}

}
