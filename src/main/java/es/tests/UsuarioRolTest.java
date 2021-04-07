package es.tests;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import es.entidades.Supermercados.Supermercado;
import es.entidades.usuarios.Rol;
import es.entidades.usuarios.Usuario;

public class UsuarioRolTest {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistencia");

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		añadirUsuario("jefa69", "Miriam Calero", "12/11/1955");
//		añadirRol("Guest");
//		asignarRol(2L, 2L);
		mostrarUsuariosRols();

	}

	//Method used to show all user's roles from the database.
	public static void mostrarUsuariosRols(){
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		List<Usuario> result=em.createQuery("from Usuario").getResultList();
		
		for (Usuario usuario : result) {
			List<Rol> roles=usuario.getRoles();
			for (int i = 0; i < roles.size(); i++) {
				System.out.println("ID user: " + usuario.getId() + " , ID Role: " + roles.get(i).getIdRol() + " , Description: " + roles.get(i).getDescription());
			}
		}

		em.getTransaction().commit();

		em.close();
		
		
	}
	
	private static void añadirUsuario(String nombreUsuario, String nombreCompleto, String fechaNacimiento) {

		Usuario user = new Usuario();
		user.setNombreUsuario(nombreUsuario);
		user.setNombreCompleto(nombreCompleto);
		user.setFechaNacimiento(fechaNacimiento);

		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		em.persist(user);

		em.getTransaction().commit();

		em.close();
	}

	private static void añadirRol(String descripcion) {

		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		em.persist(new Rol(descripcion));

		em.getTransaction().commit();

		em.close();
	}
	
	private static void asignarRol(Long idUsuario, Long idRol) {
	
		
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		Usuario user=em.find(Usuario.class,idUsuario);
		List<Rol> roles=em.createQuery("from Rol where idRol=" + idRol, Rol.class).getResultList();
		
		if(user.getRoles()==null) {
			user.setRoles(roles);
		}else {
			user.addRoles(roles);
		}
		
		em.merge(user);

		em.getTransaction().commit();

		em.close();
	}

}
