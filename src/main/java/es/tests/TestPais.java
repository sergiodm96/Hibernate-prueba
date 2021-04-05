package es.tests;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import es.entidades.Supermercados.Pais;

public class TestPais {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistencia");

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		addPais();

//		modifyPais();
//		removePais(12L);
		mostrarPaises();
		
	}

	private static void addPais() {

		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		Pais pais = new Pais("Italia", 1233153L);

		em.persist(pais);

		em.getTransaction().commit();

		em.close();

		System.out.println("Paises añadidos...");
	}

	private static void mostrarPaises() {
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		Query query = em.createQuery("select p from Pais p", Pais.class);

		List<Pais> paises = query.getResultList();

		for (Pais pais : paises) {
			System.out.println(pais);
			System.out.println();
		}

		em.getTransaction().commit();

		em.close();

	}

	private static void removePais(Long id) {

		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		
		Query query = em.createQuery("select p from Pais p where p.idPais=" + id, Pais.class);

		List<Pais> paises = query.getResultList();

		for (Pais pais : paises) {
			em.remove(pais);
		}

		em.getTransaction().commit();
 
		em.close();

		System.out.println("Paises borrados...");

	}
	
	private static void updatePoblationPais() {
		

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		

		

		
		em.getTransaction().commit();
		
		em.close();
	}

	private static void modifyPais() {
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		Scanner s = new Scanner(System.in);
		System.out.println("<-- Modify -->");
		System.out.println();
		System.out.println("|------------------------|");
		System.out.println("|        1. Id           |");
		System.out.println("|------------------------|");
		System.out.println("|        2. Name         |");
		System.out.println("|------------------------|");
		System.out.println("|        3. Poblation    |");
		System.out.println("|------------------------|");

		int selection = s.nextInt();

		if (selection == 1) {
			System.out.println("Nuevo valor?");
			Long valorId = s.nextLong();

			Query query = em.createQuery("select p from Pais p", Pais.class);

			List<Pais> paises = query.getResultList();

			for (Pais pais : paises) {
				pais.setIdPais(valorId);
				em.persist(pais);
			}

			em.getTransaction().commit();
			em.close();
		} else if (selection == 2) {
			System.out.println("Nuevo valor?");
			s.nextLine();
			String nombre = s.nextLine();

			Query query = em.createQuery("select p from Pais p", Pais.class);

			List<Pais> paises = query.getResultList();

			for (Pais pais : paises) {
				pais.setNombrePais(nombre);
				em.persist(pais);
			}

			em.getTransaction().commit();
			em.close();

		} else if (selection == 3) {

			Query query = em.createQuery("select p from Pais p", Pais.class);

			List<Pais> paises = query.getResultList();
			
			for (Pais pais : paises) {
				pais.setPoblacion(11L);
				em.persist(pais);
			}

			em.getTransaction().commit();
			em.close();

		}
	}

}
