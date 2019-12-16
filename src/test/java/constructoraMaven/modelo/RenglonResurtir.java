package constructoraMaven.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "renglonresurtir")
public class RenglonResurtir {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cve_renres")
	private Integer cve;

	@Column(name = "cant_renres")
	private int cantidadR;

	@Column(name = "ppu_renres")
	private double ppu;

	@Column(name = "baja_renres")
	private int baja;

	@JoinColumn(name = "cve_pro")
	@ManyToOne(fetch = FetchType.LAZY)
	private Producto producto;

	@JoinColumn(name = "cve_prov")
	@ManyToOne(fetch = FetchType.LAZY)
	private Proveedor proveedor;

	@JoinColumn(name = "num_res")
	@ManyToOne(fetch = FetchType.LAZY)
	private Resurtir resurtido;

	public RenglonResurtir() {
		// TODO Auto-generated constructor stub
	}

	public RenglonResurtir(Integer cve, int cantidadR, double ppu, int baja, Producto producto, Proveedor proveedor,
			Resurtir resurtido) {
		this.cve = cve;
		this.cantidadR = cantidadR;
		this.ppu = ppu;
		this.baja = baja;
		this.producto = producto;
		this.proveedor = proveedor;
		this.resurtido = resurtido;
	}

	public Integer getCve() {
		return cve;
	}

	public void setCve(Integer cve) {
		this.cve = cve;
	}

	public int getCantidadR() {
		return cantidadR;
	}

	public void setCantidadR(int cantidadR) {
		this.cantidadR = cantidadR;
	}

	public double getPpu() {
		return ppu;
	}

	public void setPpu(double ppu) {
		this.ppu = ppu;
	}

	public int getBaja() {
		return baja;
	}

	public void setBaja(int baja) {
		this.baja = baja;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public Resurtir getResurtido() {
		return resurtido;
	}

	public void setResurtido(Resurtir resurtido) {
		this.resurtido = resurtido;
	}

	@Override
	public String toString() {
		return "RenglonResurtir [cve=" + cve + ", cantidadR=" + cantidadR + ", ppu=" + ppu + ", baja=" + baja
				+ ", producto=" + producto + ", proveedor=" + proveedor + ", resurtido=" + resurtido + "]";
	}

}
