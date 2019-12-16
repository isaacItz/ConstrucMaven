package constructoraMaven.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "persona")
@NamedQuery(query = "select p from Persona p where p.curp = :curp", name = "emp por curp")
public class Persona {

	public static final String MASCULINO = "Masculino";
	public static final String FEMENINO = "Femenino";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cve_per")
	private Integer cve;

	@Column(name = "nom_per")
	private String nombre;

	@Column(name = "ap_per")
	private String apellidoP;

	@Column(name = "am_per")
	private String apellidoM;

	@Column(name = "genero_per", columnDefinition = "set")
	private String genero;

	@Column(name = "fnac_per")
	private LocalDate fechaNac;

	@Column(name = "edocivil_per", columnDefinition = "set")
	private String edoCivil;

	@Column(name = "curp", unique = true)
	private String curp;

	@Column(name = "mail_per")
	private String mail;

	@Column(name = "tel_per", columnDefinition = "varchar")
	private long numTel;

	@OneToMany(mappedBy = "persona", cascade = CascadeType.ALL)
	private List<DireccionPersona> direcciones;

	public Persona() {
		direcciones = new ArrayList<>();
	}

	public Persona(Integer cve, String nombre, String apellidoP, String apellidoM, String genero, LocalDate fechaNac,
			String edoCivil, String curp, String mail, long numTel) {
		super();
		this.cve = cve;
		this.nombre = nombre;
		this.apellidoP = apellidoP;
		this.apellidoM = apellidoM;
		this.genero = genero;
		this.fechaNac = fechaNac;
		this.edoCivil = edoCivil;
		this.curp = curp;
		this.mail = mail;
		this.numTel = numTel;
		direcciones = new ArrayList<>();
	}

	public int getCve() {
		return cve;
	}

	public void añadirDireccion(DireccionPersona d) {
		if (!direcciones.contains(d)) {
			direcciones.add(d);
		}
	}

	public boolean quitarDireccion(DireccionPersona d) {
		return direcciones.remove(d);
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

	public String getApellidoP() {
		return apellidoP;
	}

	public void setApellidoP(String apellidoP) {
		this.apellidoP = apellidoP;
	}

	public String getApellidoM() {
		return apellidoM;
	}

	public void setApellidoM(String apellidoM) {
		this.apellidoM = apellidoM;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public LocalDate getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(LocalDate fechaNac) {
		this.fechaNac = fechaNac;
	}

	public void setFechaNacDate(Date date) {
		this.fechaNac = Utileria.getLocalDate(date);
	}

	public String getEdoCivil() {
		return edoCivil;
	}

	public void setEdoCivil(String edoCivil) {
		this.edoCivil = edoCivil;
	}

	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public long getNumTel() {
		return numTel;
	}

	public void setNumTel(long numTel) {
		this.numTel = numTel;
	}

	public List<DireccionPersona> getDirecciones() {
		return direcciones;
	}

	public void setDirecciones(List<DireccionPersona> direcciones) {
		this.direcciones = direcciones;
	}

	@Override
	public String toString() {
		return "Persona [cve=" + cve + ", nombre=" + nombre + ", apellidoP=" + apellidoP + ", apellidoM=" + apellidoM
				+ ", genero=" + genero + ", fechaNac=" + fechaNac + ", edoCivil=" + edoCivil + ", curp=" + curp
				+ ", mail=" + mail + ", numTel=" + numTel + "]";
	}

}