package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import constructoraMaven.modelo.Ciudad;
import constructoraMaven.modelo.CodigoPostal;
import constructoraMaven.modelo.Colonia;
import constructoraMaven.modelo.Direccion;
import constructoraMaven.modelo.DireccionPersona;
import constructoraMaven.modelo.JTextFieldLimit;
import constructoraMaven.modelo.Persona;
import constructoraMaven.modelo.TablaPersona;
import constructoraMaven.modelo.TrabajadorContrato;
import constructoraMaven.modelo.Utileria;

public class PanelDatosUsuario extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField cajaCvePer;
	private JTextField cajaNomPer;
	private JTextField cajaAP;
	private JTextField cajaAM;
	private JTextField cajaCalle;
	private JTextField cajaNumCasa;
	private JComboBox<String> comboOrientacion;
	private JComboBox<String> comboEdoCivil;
	private JTextField cajaCurp;
	private JTextField cajaTel;
	private JTextField cajaEntreCalles1;
	private JTextField cajaMail;
	private JComboBox<String> comboTipoVia;
	private JTextField cajaEstado;
	private JTextField cajaCiudad;
	private JTextField cajaCodigo;
	private JComboBox<String> comboColPer;
	private JTextArea cajaReferencias;
	private JTextField cajaEntreCalles2;
	private JRadioButton rdbtnHombre;
	private JRadioButton rdbtnMujer;
	private JDateChooser dateChooser;
	private List<Colonia> colonias;
	private CodigoPostal cp;

	/**
	 * Create the panel.
	 */

	public PanelDatosUsuario() {
		ButtonGroup generos = new ButtonGroup();
		setToolTipText("Datos Personales");
		setBorder(new EmptyBorder(0, 8, 8, 8));
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));
		JPanel panelIzq = new JPanel();
		JPanel panelDer = new JPanel();
		JPanel panelCentro = new JPanel(new GridLayout(0, 2, 15, 0));
		panelCentro.add(panelDer);
		panelCentro.add(panelIzq);
		add(panelCentro);
		panelDer.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblCveper = new JLabel("Cve-Per:");
		panelDer.add(lblCveper);
		lblCveper.setFont(new Font("Arial", Font.PLAIN, 15));

		cajaCvePer = new JTextField();
		panelDer.add(cajaCvePer);
		cajaCvePer.setEnabled(false);
		cajaCvePer.setColumns(10);

		JLabel lblNombre = new JLabel("Nombre:");
		panelDer.add(lblNombre);
		lblNombre.setFont(new Font("Arial", Font.PLAIN, 15));

		cajaNomPer = new JTextField();
		panelDer.add(cajaNomPer);
		cajaNomPer.setColumns(10);

		JLabel lblApellidoPaterno = new JLabel("Apellido Paterno:");
		panelDer.add(lblApellidoPaterno);
		lblApellidoPaterno.setFont(new Font("Arial", Font.PLAIN, 15));

		cajaAP = new JTextField();
		panelDer.add(cajaAP);
		cajaAP.setColumns(10);

		JLabel lblApellidoMaterno = new JLabel("Apellido Materno:");
		panelDer.add(lblApellidoMaterno);
		lblApellidoMaterno.setFont(new Font("Arial", Font.PLAIN, 15));

		cajaAM = new JTextField();
		panelDer.add(cajaAM);
		cajaAM.setColumns(10);

		JLabel lblFechaDeNacimiento = new JLabel("Fecha de Nacimiento:");
		panelDer.add(lblFechaDeNacimiento);
		lblFechaDeNacimiento.setFont(new Font("Arial", Font.PLAIN, 15));

		dateChooser = new JDateChooser();
		panelDer.add(dateChooser);
		Dimension d = dateChooser.getJCalendar().getPreferredSize();
		dateChooser.getJCalendar().setPreferredSize(d);

		JLabel lblEdoCivil = new JLabel("Edo. Civil:");
		panelDer.add(lblEdoCivil);
		lblEdoCivil.setFont(new Font("Arial", Font.PLAIN, 15));

		comboEdoCivil = new JComboBox<>();
		panelDer.add(comboEdoCivil);

		JLabel lblCurp = new JLabel("Curp:");
		panelDer.add(lblCurp);
		lblCurp.setFont(new Font("Arial", Font.PLAIN, 15));

		cajaCurp = new JTextField();
		panelDer.add(cajaCurp);
		cajaCurp.setColumns(10);
		cajaCurp.setDocument(new JTextFieldLimit(18));

		JLabel lblGenero = new JLabel("Genero:");
		panelDer.add(lblGenero);
		lblGenero.setFont(new Font("Arial", Font.PLAIN, 15));

		JPanel panelGenero = new JPanel();
		panelDer.add(panelGenero);
		panelGenero.setBackground(Color.WHITE);
		panelGenero.setLayout(new GridLayout(0, 2, 0, 0));

		rdbtnHombre = new JRadioButton("Masculino");
		panelGenero.add(rdbtnHombre);
		rdbtnHombre.setBackground(Color.WHITE);
		generos.add(rdbtnHombre);

		rdbtnMujer = new JRadioButton("Femenino");
		panelGenero.add(rdbtnMujer);
		rdbtnMujer.setBackground(Color.WHITE);
		generos.add(rdbtnMujer);

		JLabel lblTelefono = new JLabel("Telefono:");
		panelDer.add(lblTelefono);
		lblTelefono.setFont(new Font("Arial", Font.PLAIN, 15));

		cajaTel = new JTextField();
		panelDer.add(cajaTel);
		cajaTel.setColumns(10);

		JLabel lblMail = new JLabel("Mail:");
		panelDer.add(lblMail);
		lblMail.setFont(new Font("Arial", Font.PLAIN, 15));

		cajaMail = new JTextField();
		panelDer.add(cajaMail);
		cajaMail.setColumns(10);
		panelIzq.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblCalle = new JLabel("Calle:");
		panelIzq.add(lblCalle);
		lblCalle.setFont(new Font("Arial", Font.PLAIN, 15));

		cajaCalle = new JTextField();
		panelIzq.add(cajaCalle);
		cajaCalle.setColumns(10);

		JLabel lblNumCasa = new JLabel("Num. Casa:");
		panelIzq.add(lblNumCasa);
		lblNumCasa.setFont(new Font("Arial", Font.PLAIN, 15));

		cajaNumCasa = new JTextField();
		panelIzq.add(cajaNumCasa);
		cajaNumCasa.setColumns(10);

		JLabel lblOrientacion = new JLabel("Orientacion:");
		panelIzq.add(lblOrientacion);
		lblOrientacion.setFont(new Font("Arial", Font.PLAIN, 15));

		comboOrientacion = new JComboBox<>();
		panelIzq.add(comboOrientacion);

		JLabel lblEntreCalles = new JLabel("Entre Calles:");
		panelIzq.add(lblEntreCalles);
		lblEntreCalles.setFont(new Font("Arial", Font.PLAIN, 15));

		cajaEntreCalles1 = new JTextField();
		panelIzq.add(cajaEntreCalles1);
		cajaEntreCalles1.setColumns(10);

		cajaEntreCalles2 = new JTextField();
		panelIzq.add(cajaEntreCalles2);
		cajaEntreCalles2.setColumns(10);

		JLabel lblTipoDeVia = new JLabel("Tipo de Via:");
		panelIzq.add(lblTipoDeVia);
		lblTipoDeVia.setFont(new Font("Arial", Font.PLAIN, 15));

		comboTipoVia = new JComboBox<>();
		panelIzq.add(comboTipoVia);

		JLabel lblCodigoPostal = new JLabel("Codigo Postal:");
		panelIzq.add(lblCodigoPostal);
		lblCodigoPostal.setFont(new Font("Arial", Font.PLAIN, 15));

		cajaCodigo = new JTextField();
		panelIzq.add(cajaCodigo);
		cajaCodigo.setColumns(10);

		JLabel lblColonia = new JLabel("Colonia:");
		panelIzq.add(lblColonia);
		lblColonia.setFont(new Font("Arial", Font.PLAIN, 15));

		comboColPer = new JComboBox<>();
		panelIzq.add(comboColPer);

		JLabel lblCiudad = new JLabel("Ciudad:");
		panelIzq.add(lblCiudad);
		lblCiudad.setFont(new Font("Arial", Font.PLAIN, 15));

		cajaCiudad = new JTextField();
		panelIzq.add(cajaCiudad);
		cajaCiudad.setEditable(false);
		cajaCiudad.setColumns(10);

		JLabel lblEstado = new JLabel("Estado:");
		panelIzq.add(lblEstado);
		lblEstado.setFont(new Font("Arial", Font.PLAIN, 15));

		cajaEstado = new JTextField();
		panelIzq.add(cajaEstado);
		cajaEstado.setEditable(false);
		cajaEstado.setColumns(10);

		JLabel lblReferencias = new JLabel("Referencias:");
		panelIzq.add(lblReferencias);
		lblReferencias.setFont(new Font("Arial", Font.PLAIN, 15));

		JScrollPane scroll = new JScrollPane();
		panelIzq.add(scroll);
		cajaReferencias = new JTextArea();
		cajaReferencias.setWrapStyleWord(true);
		cajaReferencias.setLineWrap(true);
		cajaReferencias.setBackground(SystemColor.info);
		scroll.setViewportView(cajaReferencias);

		JLabel lblDatosPersonales = new JLabel("Datos Personales");
		lblDatosPersonales.setFont(new Font("TI Uni", Font.PLAIN, 15));
		add(lblDatosPersonales, BorderLayout.NORTH);
		d.setSize(d.width + 30, d.height + 30);

		añadirOyentes();
		setCombos();

	}

	private void añadirOyentes() {

		Utileria.upperFirst(cajaNomPer, cajaAM, cajaAP, cajaCalle, cajaEntreCalles1, cajaEntreCalles2);
		Utileria.soloNums(cajaTel, cajaCodigo, cajaNumCasa);
		Utileria.setLimiteText(10, cajaTel);
		Utileria.setLimiteText(18, cajaCurp);
		Utileria.UpperAll(cajaCurp);
		Utileria.setLimiteText(5, cajaCodigo, cajaNumCasa);
		cajaCodigo.addActionListener(new OyenteCP());
		cajaCodigo.addFocusListener(new OyenteCPFocus());
		cajaCurp.addActionListener(new OyenteBuscarCurpAction());
		//cajaCurp.addFocusListener(new OyenteBuscarCurp());
		cajaTel.addActionListener(new OyenteTel());
		cajaMail.addActionListener(new OyenteMail());

	}

	private void setCombos() {
		Object[] items = { "Casado", "Soltero", "Separado", "Divorciado", "Viudo" };
		Utileria.añandirItems(comboEdoCivil, items);
		items = new String[] { "Andador", "Avenida", "Boulevard", "Calle", "Callejñn", "Calzada", "Cerrada", "Circuito",
				"Continuaciñn", "Perifñrico", "Privada", "Prolongaciñn", "Retorno", "Ninguna" };
		Utileria.añandirItems(comboTipoVia, items);
		items = new String[] { "Norte", "Sur", "Poniente", "Oriente", "Ninguno" };
		Utileria.añandirItems(comboOrientacion, items);

	}

	class OyenteCP implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			buscarCP();
		}

	}

	class OyenteCPFocus extends FocusAdapter {
		@Override
		public void focusLost(FocusEvent e) {
			buscarCP();
		}
	}

	private void buscarCP() {
		if (cajaCodigo.getText().length() >= 4) {
			EntityManager manager = Main.emf.createEntityManager();

			cp = manager.find(CodigoPostal.class, Utileria.getEntero(cajaCodigo));
			if (cp != null) {
				colonias = cp.getColonias();
				Object[] cols = colonias.stream().map(x -> x.getColonia()).collect(Collectors.toList()).toArray();
				Utileria.añandirItems(comboColPer, cols);
				Ciudad ciu = cp.getCiudad();
				cajaCiudad.setText(ciu.getNombre());
				cajaEstado.setText(ciu.getEstado().getNombre());
			} else {
				comboColPer.removeAllItems();
				cajaCiudad.setText("");
				cajaEstado.setText("");
				Utileria.mensajeError("Codigo Postal No Existente");
			}

			manager.close();
		}
	}

	public Persona getPersona() {

		Persona p = TablaPersona.existePersonaCurp(cajaCurp.getText());
		if (p != null) {
			return p;
		}
		p = new Persona();
		p.setNombre(cajaNomPer.getText());
		p.setApellidoM(cajaAM.getText());
		p.setApellidoP(cajaAP.getText());
		p.setCurp(cajaCurp.getText());
		if (rdbtnHombre.isSelected()) {
			p.setGenero(rdbtnHombre.getText());
		} else
			p.setGenero(rdbtnMujer.getText());

		p.setEdoCivil(comboEdoCivil.getSelectedItem().toString());
		p.setFechaNac(Utileria.getLocalDate(dateChooser.getDate()));
		p.setMail(cajaMail.getText());
		p.setNumTel(Utileria.getEnteroL(cajaTel));

		DireccionPersona dp = new DireccionPersona();
		dp.setDireccion(getDireccion());
		dp.setFecha(LocalDate.now());
		dp.setPersona(p);
		p.añadirDireccion(dp);

		return p;
	}

	private Direccion getDireccion() {
		Direccion d = new Direccion();
		d.setCalle(cajaCalle.getText());
		d.setEntreCalles(cajaEntreCalles1.getText().concat(",").concat(cajaEntreCalles2.getText()));
		d.setNumero(Utileria.getEntero(cajaNumCasa));
		d.setColonia(colonias.get(comboColPer.getSelectedIndex()));
		d.setTipoVia(comboTipoVia.getSelectedItem().toString());
		d.setOrientacion(comboOrientacion.getSelectedItem().toString());
		d.setReferencias(cajaReferencias.getText());

		return d;
	}

	private class OyenteBuscarCurp extends FocusAdapter {
		@Override
		public void focusLost(FocusEvent e) {
			if (cajaCurp.getText().length() == 18) {
				buscarpersonaTrabajador(TablaPersona.existePersonaCurp(cajaCurp.getText()));
			}

		}

	}

	private class OyenteBuscarCurpAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			buscarpersonaTrabajador(TablaPersona.existePersonaCurp(cajaCurp.getText()));
		}

	}

	private class OyenteMail implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			buscarpersonaTrabajador(TablaPersona.existePersonaMail(cajaMail.getText()));
		}
	}

	private class OyenteTel implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			buscarpersonaTrabajador(TablaPersona.existePersonaTel(Utileria.getEnteroL(cajaTel)));
		}
	}

	private void buscarpersonaTrabajador(Persona p) {
		if (p != null) {
			if (contratoVigente() > 0) {
				Utileria.mensajeError("La Persona Tiene un Contrato Vigente");
			} else
				llenarCamposPersona(p);
		}
	}

	private void llenarCamposPersona(Persona p) {

		EntityManager m = Main.getEntityManager();
		p = m.merge(p);
		Query q = m.createNamedQuery("ultima direccion persona", Direccion.class).setParameter("cve", p.getCve())
				.setMaxResults(1);

		Direccion d = (Direccion) q.getSingleResult();
		cajaNomPer.setText(p.getNombre());
		cajaAP.setText(p.getApellidoP());
		cajaAM.setText(p.getApellidoM());
		cajaCalle.setText(d.getCalle());
		dateChooser.setDate(Utileria.getDate(p.getFechaNac()));
		cajaMail.setText(p.getMail());
		cajaTel.setText(String.valueOf(p.getNumTel()));
		cajaNumCasa.setText(String.valueOf(d.getNumero()));
		String[] entreCalles = d.getEntreCalles().split(",");
		cajaEntreCalles1.setText(entreCalles[0]);
		cajaEntreCalles2.setText(entreCalles[1]);
		if (p.getGenero().equalsIgnoreCase(Persona.FEMENINO)) {
			rdbtnMujer.setSelected(true);
		} else
			rdbtnHombre.setSelected(true);

		comboEdoCivil.setSelectedItem(p.getEdoCivil());
		comboOrientacion.setSelectedItem(d.getOrientacion());
		comboTipoVia.setSelectedItem(d.getTipoVia());
		cajaCurp.setText(p.getCurp().toUpperCase());
		Colonia col = d.getColonia();
		cajaCodigo.requestFocus();
		cajaCodigo.setText(String.valueOf(col.getCodigoPostal().getCp()));
		cajaNomPer.requestFocus();
		cajaReferencias.setText(d.getReferencias());
		m.close();
	}

	private int contratoVigente() {
		EntityManager manager = Main.getEntityManager();

		List<TrabajadorContrato> tc = manager.createQuery(
				"Select tc from TrabajadorContrato tc join tc.persona p where p.curp = :curp and tc.fechaFin >= curdate()",
				TrabajadorContrato.class).setParameter("curp", cajaCurp.getText()).getResultList();
		manager.close();
		return tc.size();
	}

	public boolean validar() {

		JTextField[] cajas = { cajaNomPer, cajaAM, cajaAP, cajaCurp, cajaTel, cajaMail, cajaCalle, cajaNumCasa,
				cajaEntreCalles1, cajaEntreCalles2, cajaCodigo };

		for (JTextField caja : cajas) {
			if (caja.getText().trim().isEmpty()) {
				Utileria.mensajeError("Llene la Caja");
				caja.requestFocus();
				return false;
			}
		}

		if (cajaCurp.getText().length() < 18) {
			Utileria.mensajeError("La Curp Debe Contener 18 Digitos");
			return false;

		}

		if (contratoVigente() > 0) {
			Utileria.mensajeError("Persona Ya Contratada");
			return false;
		}

		if (dateChooser.getDate() == null) {
			Utileria.mensajeError("Verifique Fecha");
			return false;
		}
		if (Utileria.getLocalDate(dateChooser.getDate()).isAfter(LocalDate.now().minusYears(18))) {
			Utileria.mensajeError("La Persona Debe Ser Adulta");
			return false;
		}

		if (!(rdbtnHombre.isSelected() || rdbtnMujer.isSelected())) {
			Utileria.mensajeError("Seleccione el Genero");
			return false;
		}

		if (!cajaMail.getText().matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")) {
			Utileria.mensajeError("Verifique E-Mail");
			return false;
		}

		if (cp == null) {
			Utileria.mensajeError("Codigo Postal No Existente");
			return false;
		}

		return true;
	}

}
