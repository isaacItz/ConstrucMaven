package constructoraMaven.tests;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import constructoraMaven.modelo.Persona;
import constructoraMaven.modelo.PuedoHacer;

public class TestPuedoHacer {

	private static EntityManagerFactory emf;

	public static void main(String[] args) {

		emf = Persistence.createEntityManagerFactory("Constructora");

		EntityManager manager = emf.createEntityManager();

		System.out.println(manager.find(PuedoHacer.class, 1));

		Query query = manager.createNativeQuery(
				"select p.* from persona p join trabajadorcon tc on tc.cve_per = p.cve_per where tc.ff_tracon > curdate()",
				Persona.class);

		query = manager.createQuery("select p from TrabajadorContrato tc join tc.persona p");
		List<Persona> personas = query.getResultList();

		personas.forEach(p -> System.out.println(p));

	}

}
