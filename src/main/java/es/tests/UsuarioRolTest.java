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

//		añadirUsuario("jkcx222_", "Ander Fer", "11/11/1999");
//		añadirRol("User");
		asignarRol(1L, 1L);
		mostrarUsuariosRols();

	}

	// Method used to show all user's roles from the database.
	public static void mostrarUsuariosRols() {
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		List<Usuario> result = em.createQuery("from Usuario").getResultList();

		for (Usuario usuario : result) {
			System.out.println(usuario);
			List<Rol> roles = usuario.getRoles();
			for (int i = 0; i < roles.size(); i++) {
				System.out.println("ID user: " + usuario.getId() + " , ID Role: " + roles.get(i).getIdRol()
						+ " , Description: " + roles.get(i).getDescription());
			}
			System.out.println();
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

		Usuario user = em.find(Usuario.class, idUsuario);
		List<Rol> rolesAñadir = em.createQuery("from Rol where idRol=" + idRol, Rol.class).getResultList();

		List<Rol> rolesUsuario = user.getRoles();

		boolean exists = false;

		for (Rol rol : rolesUsuario) {
			for (Rol rol2 : rolesAñadir) {
				if (rol2 == rol) {
					exists = true;
				}
			}
		}

		if (exists) {
			System.out.println("Ese rol ya esta asignado a este usuario.");
			System.out.println();
		} else {
			if (user.getRoles() == null) {
				user.setRoles(rolesAñadir);
			} else {
				user.addRoles(rolesAñadir);
			}

			em.merge(user);

			em.getTransaction().commit();

			em.close();

		}

	}

}
