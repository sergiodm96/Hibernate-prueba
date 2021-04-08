package es.tests;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import es.entidades.Supermercados.Pais;
import es.entidades.Supermercados.Supermercado;

public class TestSupermercado {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistencia");

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String nombre = "Carrefour";
		String ciudad = "París";

//		añadirSupermercado(nombre, ciudad);
//		actualizarPaisIdSupermercado();
		

		 actualizarSupermercado("from Supermercado where idSupermercado=5", "ciudad", "Toulouse");
		 
		 
		 mostrarSupermercados();

		// borrarSupermercados("from Supermercado");

	}

	private static void mostrarSupermercados() {

		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		TypedQuery<Supermercado> query = em.createQuery("from Supermercado", Supermercado.class);

		List<Supermercado> listaResultante = (query.getResultList());

		for (Supermercado supermercado : listaResultante) {
			System.out.println(supermercado);
		}

		em.getTransaction().commit();

		em.close();
	}
	
	

	private static void borrarSupermercados(String typedQuery) {
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		TypedQuery<Supermercado> query = em.createQuery(typedQuery, Supermercado.class);

		// query.setParameter("nombreSupermercado", "Carrefour");

		List<Supermercado> listaResultante = (query.getResultList());

		for (Supermercado supermercado : listaResultante) {
			em.remove(supermercado);
		}

		em.getTransaction().commit();

		em.close();

	}

	private static void añadirSupermercado(String nombre, String ciudad) {
		EntityManager em = emf.createEntityManager();

		Pais pais=em.find(Pais.class,1L);
		Supermercado supermarket = new Supermercado(nombre, ciudad, pais);

		em.getTransaction().begin();

		em.persist(supermarket);

		em.getTransaction().commit();

		em.close();

	}
	


	private static void actualizarSupermercado(String typedQuery, String campoACambiar, String valorNuevo) {

		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		TypedQuery<Supermercado> query = em.createQuery(typedQuery, Supermercado.class);
		List<Supermercado> listaSupermercados = query.getResultList();

		if (campoACambiar.equals("idSupermercado")) {
			for (Supermercado sm : listaSupermercados) {
				sm.setIdSupermercado(Long.valueOf(valorNuevo));
			}

		} else if (campoACambiar.equals("nombreSupermercado")) {
			for (Supermercado sm : listaSupermercados) {
				sm.setNombreSupermercado(valorNuevo);
			}

		} else if (campoACambiar.equals("ciudad")) {
			for (Supermercado sm : listaSupermercados) {
				sm.setCiudad(valorNuevo);
			}

		}else if(campoACambiar.equals("pais_id")) {
			for (Supermercado sm : listaSupermercados) {
				Pais pais=em.find(Pais.class, Long.valueOf(valorNuevo));
				if(pais!=null) {
					sm.setPais(pais);
				}else {
					sm.setPais(null);
				}
				
			}
				
			}

		em.getTransaction().commit();

		em.close();

	}

}
