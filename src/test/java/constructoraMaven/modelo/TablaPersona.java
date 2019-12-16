package constructoraMaven.modelo;

import javax.persistence.EntityManager;

import vista.Main;

public class TablaPersona {

	public static Persona existePersonaCurp(String curp) {
		EntityManager manager = Main.getEntityManager();
		Persona p = null;
		try {
			p = manager.createQuery("from Persona e where curp = :curp", Persona.class).setParameter("curp", curp)
					.getSingleResult();
		} catch (javax.persistence.NoResultException e) {
			System.err.println("Entidad No Encontrada");
		} finally {
			manager.close();
		}

		return p;
	}

	public static Persona existePersonaMail(String mail) {
		EntityManager manager = Main.getEntityManager();
		Persona p = null;
		try {
			p = manager.createQuery("from Persona e where mail = :mail", Persona.class).setParameter("mail", mail)
					.getSingleResult();
		} catch (javax.persistence.NoResultException e) {
			System.err.println("Entidad No Encontrada");
		} finally {
			manager.close();
		}

		return p;
	}

	public static Persona existePersonaTel(long tel) {
		EntityManager manager = Main.getEntityManager();
		Persona p = null;
		try {
			p = manager.createQuery("from Persona e where numTel = :telPer", Persona.class).setParameter("telPer", tel)
					.getSingleResult();
		} catch (javax.persistence.NoResultException e) {
			Utileria.mensajeAdvertencia("Entidad No Encontrada");
		} finally {
			manager.close();
		}

		return p;
	}

}
