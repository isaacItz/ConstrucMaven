package constructoraMaven.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "producto")
public class Producto implements Comparable<Producto> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cve_pro")
	private Integer cve;

	@Column(name = "nom_pro")
	private String nombre;

	@Column(name = "marca_pro")
	private String marca;

	@Column(name = "tipo_pro", columnDefinition = "set")
	private String tipo;

	@Column(name = "umedida_pro", columnDefinition = "set")
	private String unidadMedida;

	@Column(name = "contenido_pro")
	private double contenido;

	@Column(name = "min_pro")
	private int minimo;

	@Column(name = "max_pro")
	private int maximo;

	public Producto() {
		// TODO Auto-generated constructor stub
	}

	public Producto(Integer cve, String nombre, String marca, String tipo, String unidadMedida, double contenido,
			int minimo, int maximo) {
		super();
		this.cve = cve;
		this.nombre = nombre;
		this.marca = marca;
		this.tipo = tipo;
		this.unidadMedida = unidadMedida;
		this.contenido = contenido;
		this.minimo = minimo;
		this.maximo = maximo;
	}

	public Integer getCve() {
		return cve;
	}

	public void setCve(Integer cve) {
		this.cve = cve;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public double getContenido() {
		return contenido;
	}

	public void setContenido(double contenido) {
		this.contenido = contenido;
	}

	public int getMinimo() {
		return minimo;
	}

	public void setMinimo(int minimo) {
		this.minimo = minimo;
	}

	public int getMaximo() {
		return maximo;
	}

	public void setMaximo(int maximo) {
		this.maximo = maximo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(contenido);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((cve == null) ? 0 : cve.hashCode());
		result = prime * result + ((marca == null) ? 0 : marca.hashCode());
		result = prime * result + maximo;
		result = prime * result + minimo;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		result = prime * result + ((unidadMedida == null) ? 0 : unidadMedida.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		if (Double.doubleToLongBits(contenido) != Double.doubleToLongBits(other.contenido))
			return false;
		if (cve == null) {
			if (other.cve != null)
				return false;
		} else if (!cve.equals(other.cve))
			return false;
		if (marca == null) {
			if (other.marca != null)
				return false;
		} else if (!marca.equals(other.marca))
			return false;
		if (maximo != other.maximo)
			return false;
		if (minimo != other.minimo)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		if (unidadMedida == null) {
			if (other.unidadMedida != null)
				return false;
		} else if (!unidadMedida.equals(other.unidadMedida))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Producto [cve=" + cve + ", nombre=" + nombre + ", marca=" + marca + ", tipo=" + tipo + ", unidadMedida="
				+ unidadMedida + ", contenido=" + contenido + ", minimo=" + minimo + ", maximo=" + maximo + "]";
	}

	@Override
	public int compareTo(Producto o) {
		return o.cve.compareTo(cve);
	}

}