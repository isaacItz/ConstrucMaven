package constructoraMaven.tests;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import constructoraMaven.modelo.Actividad;

public class TestActividad {

	private static EntityManagerFactory emf;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		emf = Persistence.createEntityManagerFactory("Constructora");
		EntityManager manager = emf.createEntityManager();

		// manager.getTransaction().begin();

		Actividad a = new Actividad(0, "marchar", "se marcha bien pprin", "1");
		a = manager.find(Actividad.class, 1);
		Query query = manager.createQuery(
				"select a.nombreActividad, a.descActividad, ca.fechaCobroAct from Actividad a join a.cobrosActividad ca");

		List<Object[]> resultados = query.getResultList();

		for (Object[] objects : resultados) {
			for (int i = 0; i < objects.length; i++) {
				System.out.print(objects[i].toString().concat("  "));
			}
			System.out.println();
		}
		System.out.println(manager.contains(a));
		// manager.getTransaction().commit();
		a.getCobrosActividad().size();
		manager.close();

		// System.out.println(a);
		// a.getCobrosActividad().stream().forEach(x -> System.out.println(x));
		// imprimir();

	}

	@SuppressWarnings("unchecked")
	public static void imprimir() {
		EntityManager manager = emf.createEntityManager();
		List<Actividad> lista = manager.createQuery("from Actividad").getResultList();
		lista.forEach(x -> System.out.println(x));
		manager.close();
	}

}
