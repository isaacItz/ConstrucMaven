package constructoraMaven.tests;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import constructoraMaven.modelo.Direccion;
import constructoraMaven.modelo.DireccionPersona;
import constructoraMaven.modelo.Persona;

public class TestPersona {

	private static EntityManagerFactory emf;

	public static void main(String[] args) {

		emf = Persistence.createEntityManagerFactory("Constructora");
		EntityManager m = emf.createEntityManager();
		Persona p = new Persona(null, "isaac", "valdespino", "lugo", "1", LocalDate.of(1998, 11, 17), "soltero",
				"BARI991015HMNLVV09", "@lugo.com", 7151109550L);

		m.getTransaction().begin();

		m.persist(p);

		Query query = m.createNativeQuery(
				" select t.cve_tracon, cve_act, fi_tracon, ff_tracon, puesto_tracon, nss_tracon from puedohacer p join trabajadorcon t on t.cve_tracon = p.cve_tracon");

		query = m.createQuery("select from ");
		List<Object[]> lista = query.getResultList();
		for (Object[] objects : lista) {
			for (int i = 0; i < objects.length; i++) {
				System.out.print(objects[i] + "  ");
			}
			System.out.println();
		}

		Query q = m.createNamedQuery("emp por curp");

		q.setParameter("curp", "BARI991015HMNLVV07");

		List<Persona> r = q.getResultList();

		r.forEach(x -> System.out.println(x));
		System.out.println(p);

		Direccion d = m.find(Direccion.class, 2);
		p.getDirecciones().add(new DireccionPersona(null, LocalDate.now(), d, p));

		p.setCurp("BARI991015HMNLVV07");

		r = m.createQuery("from Persona").getResultList();
		r.forEach(x -> System.out.println(x));

		m.getTransaction().rollback();

		m.close();
	}

}
