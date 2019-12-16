package constructoraMaven.modelo;

import java.time.LocalDate;
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
@Table(name = "resurtir")
public class Resurtir {

	@Id
	@Column(name = "num_res")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cve;

	@Column(name = "fecha_Res")
	private LocalDate fecha;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "resurtido")
	private List<RenglonResurtir> renglonesRes;

	public Resurtir() {
		renglonesRes = new ArrayList<>();
	}

	public Resurtir(Integer cve, LocalDate fecha) {
		renglonesRes = new ArrayList<>();
		this.cve = cve;
		this.fecha = fecha;
	}

	public void añadirResurtido(RenglonResurtir rr) {
		renglonesRes.add(rr);
	}

	public List<RenglonResurtir> getRenglonesRes() {
		return renglonesRes;
	}

	public void setRenglonesRes(List<RenglonResurtir> renglonesRes) {
		this.renglonesRes = renglonesRes;
	}

	public List<RenglonResurtir> getRenglones() {
		return renglonesRes;
	}

	public Integer getCve() {
		return cve;
	}

	public void setCve(Integer cve) {
		this.cve = cve;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "Resurtir [cve=" + cve + ", fecha=" + fecha + "]";
	}

}
