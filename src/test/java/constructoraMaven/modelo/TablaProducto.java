package constructoraMaven.modelo;

import java.util.List;

import javax.persistence.EntityManager;

import vista.Main;

public class TablaProducto {

	public static List<Producto> getProductos() {
		EntityManager manager = Main.getEntityManager();

		List<Producto> productos = manager.createQuery("from Producto ", Producto.class).getResultList();

		manager.close();
		return productos;

	}

	public static Producto buscarPorNombre(String nombre) {
		EntityManager manager = Main.getEntityManager();

		List<Producto> productos = manager.createQuery("from Producto where nombre = :nom", Producto.class)
				.setParameter("nom", nombre).getResultList();
		manager.close();
		if (productos.size() > 0) {
			return productos.get(0);
		}
		return null;

	}

}
