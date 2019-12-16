package constructoraMaven.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "actividad")
public class Actividad {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cve_act")
	private Integer cveActividad;

	@Column(name = "nom_act")
	private String nombreActividad;

	@Column(name = "desc_act")
	private String descActividad;

	@Column(name = "umedida_act", columnDefinition = "set")
	private String unidadMedidaAct;

	@OneToMany(mappedBy = "actividad", cascade = CascadeType.ALL)
	private List<CobroActividad> cobrosCliActividad;

	@OneToMany(mappedBy = "actividad", cascade = CascadeType.ALL)
	private List<PagoActividad> pagosEmpActividad;

	@OneToMany(mappedBy = "actividad", cascade = CascadeType.ALL)
	private List<MaterialActividad> materialesAct;

	public Actividad() {
		cobrosCliActividad = new ArrayList<>();
		pagosEmpActividad = new ArrayList<>();
		materialesAct = new ArrayList<>();
	}

	public Actividad(Integer cveActividad, String nomrbeActividad, String descActividad, String unidadMedidaAct) {
		this.cveActividad = cveActividad;
		this.nombreActividad = nomrbeActividad;
		this.descActividad = descActividad;
		this.unidadMedidaAct = unidadMedidaAct;
		cobrosCliActividad = new ArrayList<>();
		pagosEmpActividad = new ArrayList<>();
		materialesAct = new ArrayList<>();
	}

	public void añadirMaterialAct(MaterialActividad matAct) {
		materialesAct.add(matAct);
	}

	public void añadirCobroAct(CobroActividad cobro) {
		cobrosCliActividad.add(cobro);
	}

	public void añadirPagoAct(PagoActividad pago) {
		pagosEmpActividad.add(pago);
	}

	public int getCveActividad() {
		return cveActividad;
	}

	public void setCveActividad(Integer cveActividad) {
		this.cveActividad = cveActividad;
	}

	public String getNomrbeActividad() {
		return nombreActividad;
	}

	public void setNomrbeActividad(String nomrbeActividad) {
		this.nombreActividad = nomrbeActividad;
	}

	public String getDescActividad() {
		return descActividad;
	}

	public void setDescActividad(String descActividad) {
		this.descActividad = descActividad;
	}

	public String getUnidadMedidaAct() {
		return unidadMedidaAct;
	}

	public void setUnidadMedidaAct(String unidadMedidaAct) {
		this.unidadMedidaAct = unidadMedidaAct;
	}

	public List<CobroActividad> getCobrosActividad() {
		return cobrosCliActividad;
	}

	public void setCobrosActividad(List<CobroActividad> cobrosActividad) {
		this.cobrosCliActividad = cobrosActividad;
	}

	@Override
	public String toString() {
		return "Actividad [cveActividad=" + cveActividad + ", nomrbeActividad=" + nombreActividad + ", descActividad="
				+ descActividad + ", unidadMedidaAct=" + unidadMedidaAct + "]";
	}

}
