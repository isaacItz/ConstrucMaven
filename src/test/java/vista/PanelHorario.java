package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import constructoraMaven.modelo.DiaHora;
import constructoraMaven.modelo.Horario;
import constructoraMaven.modelo.TrabajadorContrato;
import constructoraMaven.modelo.Utileria;

public class PanelHorario extends JPanel {

	private static final long serialVersionUID = 1L;
	private JScrollPane scrollTabla;
	private DefaultTableModel modeloTabla;
	private JTable table;
	private DialogRegistroHorario dialogHorario;
	private JComboBox<String> comboTipoEmp;
	private JFormattedTextField cajaSueldo;
	private JComboBox<String> comboTipoHorario;
	private JDateChooser dateChooser;
	private List<List<DiaHora>> horarios;

	public PanelHorario() {

		horarios = new ArrayList<List<DiaHora>>(7);
		inicializarHorarios();
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));

		JLabel lblHorarioDelTrabajador = new JLabel("Horario del Trabajador:");
		add(lblHorarioDelTrabajador, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 30, 0, 30, 0, 20 };
		gbl_panel.rowHeights = new int[] { 15, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 1.0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0 };

		panel.setLayout(gbl_panel);

		JLabel lblTipoDelEmpleado = new JLabel("Tipo del Empleado:");
		GridBagConstraints gbc_lblTipoDelEmpleado = new GridBagConstraints();
		gbc_lblTipoDelEmpleado.anchor = GridBagConstraints.WEST;
		gbc_lblTipoDelEmpleado.insets = new Insets(0, 0, 5, 5);
		gbc_lblTipoDelEmpleado.gridx = 1;
		gbc_lblTipoDelEmpleado.gridy = 1;
		panel.add(lblTipoDelEmpleado, gbc_lblTipoDelEmpleado);

		comboTipoEmp = new JComboBox<>();
		GridBagConstraints gbc_comboTipoEmp = new GridBagConstraints();
		gbc_comboTipoEmp.anchor = GridBagConstraints.NORTH;
		gbc_comboTipoEmp.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboTipoEmp.insets = new Insets(0, 0, 5, 5);
		gbc_comboTipoEmp.gridx = 1;
		gbc_comboTipoEmp.gridy = 2;
		panel.add(comboTipoEmp, gbc_comboTipoEmp);

		JLabel lblSueldo = new JLabel("Sueldo:");
		GridBagConstraints gbc_lblSueldo = new GridBagConstraints();
		gbc_lblSueldo.anchor = GridBagConstraints.WEST;
		gbc_lblSueldo.insets = new Insets(0, 0, 5, 5);
		gbc_lblSueldo.gridx = 1;
		gbc_lblSueldo.gridy = 3;
		panel.add(lblSueldo, gbc_lblSueldo);

		cajaSueldo = new JFormattedTextField(new Double(0));
		GridBagConstraints gbc_cajaSueldo = new GridBagConstraints();
		gbc_cajaSueldo.anchor = GridBagConstraints.NORTH;
		gbc_cajaSueldo.fill = GridBagConstraints.HORIZONTAL;
		gbc_cajaSueldo.insets = new Insets(0, 0, 5, 5);
		gbc_cajaSueldo.gridx = 1;
		gbc_cajaSueldo.gridy = 4;
		panel.add(cajaSueldo, gbc_cajaSueldo);

		JLabel lblTipoDeHorario = new JLabel("Tipo de Horario:");
		GridBagConstraints gbc_lblTipoDeHorario = new GridBagConstraints();
		gbc_lblTipoDeHorario.anchor = GridBagConstraints.WEST;
		gbc_lblTipoDeHorario.insets = new Insets(0, 0, 5, 5);
		gbc_lblTipoDeHorario.gridx = 1;
		gbc_lblTipoDeHorario.gridy = 5;
		panel.add(lblTipoDeHorario, gbc_lblTipoDeHorario);

		comboTipoHorario = new JComboBox<>();
		GridBagConstraints gbc_comboTipoHorario = new GridBagConstraints();
		gbc_comboTipoHorario.anchor = GridBagConstraints.NORTH;
		gbc_comboTipoHorario.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboTipoHorario.insets = new Insets(0, 0, 5, 5);
		gbc_comboTipoHorario.gridx = 1;
		gbc_comboTipoHorario.gridy = 6;
		panel.add(comboTipoHorario, gbc_comboTipoHorario);

		JLabel lblFechaDeFin = new JLabel("Fecha de Fin de Contrato:");
		GridBagConstraints gbc_lblFechaDeFin = new GridBagConstraints();
		gbc_lblFechaDeFin.anchor = GridBagConstraints.WEST;
		gbc_lblFechaDeFin.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaDeFin.gridx = 1;
		gbc_lblFechaDeFin.gridy = 7;
		panel.add(lblFechaDeFin, gbc_lblFechaDeFin);

		dateChooser = new JDateChooser();
		GridBagConstraints gbc_dateChooser = new GridBagConstraints();
		gbc_dateChooser.anchor = GridBagConstraints.NORTH;
		gbc_dateChooser.fill = GridBagConstraints.HORIZONTAL;
		gbc_dateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_dateChooser.gridx = 1;
		gbc_dateChooser.gridy = 8;
		panel.add(dateChooser, gbc_dateChooser);

		JButton btnAadirHorario = new JButton("A\u00F1adir Horario");
		GridBagConstraints gbc_btnAadirHorario = new GridBagConstraints();
		gbc_btnAadirHorario.anchor = GridBagConstraints.NORTH;
		gbc_btnAadirHorario.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAadirHorario.insets = new Insets(0, 0, 0, 5);
		gbc_btnAadirHorario.gridx = 1;
		gbc_btnAadirHorario.gridy = 9;
		panel.add(btnAadirHorario, gbc_btnAadirHorario);
		btnAadirHorario.addActionListener(new OyenteAñadirHorario());

		scrollTabla = new JScrollPane();
		scrollTabla.setBorder(new TitledBorder(null, "Horario", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollTabla.setBackground(Color.WHITE);
		GridBagConstraints gbc_scrollTabla = new GridBagConstraints();
		gbc_scrollTabla.insets = new Insets(0, 0, 0, 5);
		gbc_scrollTabla.gridheight = 9;
		gbc_scrollTabla.fill = GridBagConstraints.BOTH;
		gbc_scrollTabla.gridx = 3;
		gbc_scrollTabla.gridy = 1;
		panel.add(scrollTabla, gbc_scrollTabla);

		table = new JTable();
		table.setBackground(Color.WHITE);
		scrollTabla.setViewportView(table);
		iniciarTabla();
		inicializarHorarios();
		setComb();
		agregarOyentes();

	}

	private void agregarOyentes() {
		comboTipoEmp.addActionListener(new OyenteCombo());
	}

	private void setComb() {
		Object[] items = { "Administrador", "Albañil", "Arquitecto", "Camionero", "Chalan", "Contador", "Limpieza",
				"Maestro de obra", "Recepcionista", "Recursos Humanos", "Recursos Materiales", "Secretario" };

		Utileria.añandirItems(comboTipoEmp, items);

		items = new Object[] { "Fijo", "Variable" };

		Utileria.añandirItems(comboTipoHorario, items);

	}

	private class OyenteCombo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			int i = comboTipoEmp.getSelectedIndex();
			if (i == 1 || i == 3 || i == 4) {
				cambiarEstadoCajas(false);
				cajaSueldo.setText("0");
				comboTipoHorario.setSelectedIndex(1);
			} else {
				comboTipoHorario.setSelectedIndex(0);
				cambiarEstadoCajas(true);
			}

		}

	}

	private void cambiarEstadoCajas(Boolean state) {
		cajaSueldo.setEnabled(state);
		comboTipoHorario.setEnabled(state);
	}

	private class OyenteBtnHorario implements ActionListener {
		int maximo = 0;

		@Override
		public void actionPerformed(ActionEvent e) {
			if (dialogHorario.getRowCount() > 0 && dialogHorario.getDias().length > 0) {

				Integer[] dias = dialogHorario.getDias();

				for (int dia : dias) {
					List<LocalTime[]> horas = dialogHorario.getHoras();
					List<DiaHora> d = new ArrayList<>();
					for (int j = 0; j < horas.size(); j++) {
						DiaHora dh = new DiaHora();
						dh.setDia(dia);
						dh.setHoraEntrada(horas.get(j)[0]);
						dh.setHoraSalida(horas.get(j)[1]);
						d.add(dh);
					}

					horarios.set(dia, d);
				}
				maximo = 0;
				horarios.forEach(x -> maximo = x.size() > maximo ? x.size() : maximo);

				Object[][] mat = new Object[maximo][7];
				for (int i = 0; i < 7; i++) {
					for (int j = 0; j < horarios.get(i).size(); j++) {

						DiaHora d = horarios.get(i).get(j);
						mat[j][i] = d.getHoraEntrada() + "  " + d.getHoraSalida();

					}

				}

				setDataTable(mat);
				dialogHorario.dispose();
			} else
				JOptionPane.showMessageDialog(null, "Seleccione Horas y Dias Validos");
		}
	}

	public void iniciarTabla() {
		modeloTabla = new DefaultTableModel();
		modeloTabla.setColumnIdentifiers(
				new Object[] { "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo" });
		table.setModel(modeloTabla);

	}

	private void setDataTable(Object[][] mat) {
		modeloTabla.setDataVector(mat,
				new Object[] { "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo" });
	}

	private void inicializarHorarios() {
		for (int i = 0; i < 7; i++) {
			horarios.add(new ArrayList<>());
		}
	}

	private class OyenteAñadirHorario implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						dialogHorario = new DialogRegistroHorario(horarios);
						dialogHorario.getOkButton().addActionListener(new OyenteBtnHorario());
						dialogHorario.setModal(true);
						dialogHorario.setVisible(true);
					}
				});
			} catch (Exception e1) {
				System.out.println(e1.getCause());
			}

		}
	}

	public Horario getHorario() {

		Horario horario = new Horario();
		horario.setCve(null);
		horario.setFecha(LocalDate.now());
		horario.setTipo(comboTipoHorario.getSelectedItem().toString());

		for (List<DiaHora> list : horarios) {
			for (DiaHora diaHora : list) {
				diaHora.setHorario(horario);
				horario.añadirDiaHora(diaHora);
			}
		}

		return horario;
	}

	public TrabajadorContrato getTrabajadorContrato() {
		TrabajadorContrato tc = new TrabajadorContrato();
		tc.setCve(null);
		tc.setFechaInicio(LocalDate.now());
		tc.setFechaFin(Utileria.getLocalDate(dateChooser.getDate()));
		tc.setNss(null);
		tc.setPuesto(comboTipoEmp.getSelectedItem().toString());
		tc.setSueldo(Utileria.getDouble(cajaSueldo));
		return tc;
	}

	public boolean validarHorario() {
		int max = 0;
		for (List<DiaHora> list : horarios)
			max = list.size() > max ? list.size() : max;
		if (max == 0) {
			Utileria.mensajeError("Debe Seleccionar Un Horario");
			return false;
		}

		if (dateChooser.getDate() == null) {
			Utileria.mensajeError("Seleccione Fecha");
			return false;
		}

		if (Utileria.getLocalDate(dateChooser.getDate()).isBefore(LocalDate.now())) {
			Utileria.mensajeError("Elija Una Fecha Valida");
			return false;
		}

		return true;
	}

}
