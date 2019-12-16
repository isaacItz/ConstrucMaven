package constructoraMaven.tests;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import constructoraMaven.modelo.TrabajadorContrato;

public class TestHorario {

	static EntityManagerFactory emf;

	// public static void main(String[] args) {
	// emf = Persistence.createEntityManagerFactory("Constructora");
	// EntityManager m = emf.createEntityManager();
	//
	// Query q = m.createNamedQuery("horarios emp");
	// q.setParameter("cve", 1);
	//
	// List<Horario> h = q.getResultList();
	//
	// h.forEach(horario -> System.out.println(horario));
	//
	// }

	public static void main(String[] args) {
		emf = Persistence.createEntityManagerFactory("Constructora");
		EntityManager manager = emf.createEntityManager();

		String curp = "BARI991015HMNLVV02";
		Object tc = manager.createQuery(
				"from TrabajadorContrato ").getSingleResult();

		System.out.println(tc);
		manager.close();
		emf.close();
	}

}
