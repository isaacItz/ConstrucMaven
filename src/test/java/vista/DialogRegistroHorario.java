package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerListModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import constructoraMaven.modelo.DiaHora;

public class DialogRegistroHorario extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton okButton;
	private JTable table;
	private JCheckBox chckbxAll;
	private JCheckBox chckbxLunes;
	private JCheckBox chckbxMartes;
	private JCheckBox chckbxMiercoles;
	private JCheckBox chckbxJueves;
	private JCheckBox chckbxViernes;
	private JCheckBox chckbxSabado;
	private JCheckBox chckbxDomingo;
	private JButton btnNewButton;
	private JSpinner spinner;
	private JSpinner spinner_1;
	private List<LocalTime[]> horas;
	private DefaultTableModel modeloT;
	private JButton btnRemoverHorario;
	private List<List<DiaHora>> horarios;
	private JCheckBox[] checks;

	public DialogRegistroHorario(List<List<DiaHora>> horarios) {
		this.horarios = horarios;
		horas = new ArrayList<LocalTime[]>();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("A\u00F1adir Horario");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));

		JPanel panelEste = new JPanel();
		panelEste.setBackground(Color.WHITE);
		panelEste.setBorder(
				new TitledBorder(null, "Dias del Horario:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPanel.add(panelEste, BorderLayout.EAST);
		panelEste.setLayout(new GridLayout(0, 1, 0, 0));

		chckbxAll = new JCheckBox("Toda la Semana");
		chckbxAll.setBackground(Color.WHITE);
		panelEste.add(chckbxAll);
		chckbxAll.addActionListener(new seleccionarTodos());

		JSeparator separator = new JSeparator();
		panelEste.add(separator);

		chckbxLunes = new JCheckBox("Lunes");
		chckbxLunes.setBackground(Color.WHITE);
		panelEste.add(chckbxLunes);

		chckbxMartes = new JCheckBox("Martes");
		chckbxMartes.setBackground(Color.WHITE);
		panelEste.add(chckbxMartes);

		chckbxMiercoles = new JCheckBox("Miercoles");
		chckbxMiercoles.setBackground(Color.WHITE);
		panelEste.add(chckbxMiercoles);

		chckbxJueves = new JCheckBox("Jueves");
		chckbxJueves.setBackground(Color.WHITE);
		panelEste.add(chckbxJueves);

		chckbxViernes = new JCheckBox("Viernes");
		chckbxViernes.setBackground(Color.WHITE);
		panelEste.add(chckbxViernes);

		chckbxSabado = new JCheckBox("Sabado");
		chckbxSabado.setBackground(Color.WHITE);
		panelEste.add(chckbxSabado);

		chckbxDomingo = new JCheckBox("Domingo");
		chckbxDomingo.setBackground(Color.WHITE);
		panelEste.add(chckbxDomingo);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		contentPanel.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 46, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		spinner = new JSpinner();
		spinner.setModel(new SpinnerListModel(new String[] { "07:00", "07:30", "08:00", "08:30", "09:00", "09:30",
				"10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00",
				"15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30",
				"21:00", "21:30", "22:00", "22:30", "23:00", "23:30", "00:00", "00:30", "01:00", "01:30", "02:00",
				"02:30", "03:00", "03:30", "04:00", "04:30", "05:00", "05:30", "06:00", "06:30" }));
		GridBagConstraints gbc_spinner = new GridBagConstraints();
		gbc_spinner.fill = GridBagConstraints.BOTH;
		gbc_spinner.insets = new Insets(0, 0, 5, 5);
		gbc_spinner.gridx = 0;
		gbc_spinner.gridy = 0;
		panel.add(spinner, gbc_spinner);
		spinner.addAncestorListener(new OyenteSpinner());

		spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerListModel(new String[] { "07:30", "08:00", "08:30", "09:00", "09:30", "10:00",
				"10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30",
				"16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30", "21:00",
				"21:30", "22:00", "22:30", "23:00", "23:30", "00:00", "00:30", "01:00", "01:30", "02:00", "02:30",
				"03:00", "03:30", "04:00", "04:30", "05:00", "05:30", "06:00", "06:30", "07:00" }));
		GridBagConstraints gbc_spinner_1 = new GridBagConstraints();
		gbc_spinner_1.insets = new Insets(0, 0, 5, 0);
		gbc_spinner_1.fill = GridBagConstraints.BOTH;
		gbc_spinner_1.gridx = 1;
		gbc_spinner_1.gridy = 0;
		panel.add(spinner_1, gbc_spinner_1);

		btnNewButton = new JButton("A\u00F1adir Horario");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 1;
		panel.add(btnNewButton, gbc_btnNewButton);
		btnNewButton.addActionListener(new OyenteAgregarFecha());

		btnRemoverHorario = new JButton("Remover Horario");
		GridBagConstraints gbc_btnRemoverHorario = new GridBagConstraints();
		gbc_btnRemoverHorario.insets = new Insets(0, 0, 5, 0);
		gbc_btnRemoverHorario.gridx = 1;
		gbc_btnRemoverHorario.gridy = 1;
		panel.add(btnRemoverHorario, gbc_btnRemoverHorario);
		btnRemoverHorario.addActionListener(new OyenteRemover());

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new EmptyBorder(2, 2, 2, 2));
		scrollPane.setBackground(Color.WHITE);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		panel.add(scrollPane, gbc_scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.getSelectionModel().addListSelectionListener(new OyenteTabla());

		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(Color.WHITE);
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		okButton = new JButton("OK");
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("Cancel");
		cancelButton.addActionListener(new salidaDialog());
		buttonPane.add(cancelButton);
		checks = new JCheckBox[] { chckbxLunes, chckbxMartes, chckbxMiercoles, chckbxJueves, chckbxViernes,
				chckbxSabado, chckbxDomingo };

		iniciarTabla();
		btnRemoverHorario.setEnabled(false);
		setOyentes();
		setLocationRelativeTo(null);

	}

	private List<Integer> mismoHorario(List<DiaHora> test) {
		List<Integer> tiempos = new ArrayList<>();

		for (int k = 0; k < horarios.size(); k++) {
			List<DiaHora> hor = horarios.get(k);
			if (hor.size() == test.size()) {
				int i = 0;
				for (; i < hor.size(); i++) {
					if (!(hor.get(i).getHoraEntrada().compareTo(test.get(i).getHoraEntrada()) == 0
							&& hor.get(i).getHoraSalida().compareTo(test.get(i).getHoraSalida()) == 0)) {
						break;
					}
				}
				if (i == test.size())
					tiempos.add(k);

			}

		}

		return tiempos;
	}

	private void habilitar(int dia) {

		List<Integer> dias = mismoHorario(horarios.get(dia));
		if (dias.size() != 7) {
			for (int i = 0; i < checks.length; i++) {
				boolean b = false;
				for (int integer : dias) {
					if (i == integer) {
						// dias.remove(i);
						b = true;
						break;
					}
				}
				checks[i].setEnabled(b);

			}
			chckbxAll.setEnabled(false);
			chckbxAll.setSelected(false);
		} else {
			for (JCheckBox c : checks) {
				c.setEnabled(true);
			}

			chckbxAll.setEnabled(true);
		}

	}

	private void setOyentes() {
		for (int i = 0; i < checks.length; i++) {
			añadirOyenteCheck(checks[i], i);
		}
	}

	private void añadirOyenteCheck(JCheckBox check, int dia) {
		check.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				habilitar(dia);
				horas.clear();
				for (DiaHora dh : horarios.get(dia)) {
					horas.add(new LocalTime[] { dh.getHoraEntrada(), dh.getHoraSalida() });

				}
				setModel(ordenarHoras());
			}
		});

	}

	private class OyenteTabla implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
				btnRemoverHorario.setEnabled(true);
			} else
				btnRemoverHorario.setEnabled(false);
		}

	}

	private class OyenteRemover implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int x = table.getSelectedRow();
			horas.remove(x);
			setModel(ordenarHoras());
		}
	}

	private class OyenteAgregarFecha implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			if (validarIntervalo()) {
				int hora = Integer.parseInt(spinner.getValue().toString().substring(0, 2));
				int min = Integer.parseInt(spinner.getValue().toString().substring(3));
				LocalTime ti = LocalTime.of(hora, min);
				hora = Integer.parseInt(spinner_1.getValue().toString().substring(0, 2));
				min = Integer.parseInt(spinner_1.getValue().toString().substring(3));
				LocalTime tf = LocalTime.of(hora, min);

				boolean valido = true;
				Iterator<LocalTime[]> it = horas.iterator();
				while (it.hasNext() && valido) {
					LocalTime[] fechasValidas = it.next();
					if (!(tf.isBefore(fechasValidas[0]) || ti.isAfter(fechasValidas[1]))) {
						valido = false;
					}
				}
				if (valido) {
					horas.add(new LocalTime[] { ti, tf });
					setModel(ordenarHoras());
				} else
					JOptionPane.showMessageDialog(null, "Intervalo Invalido");

			} else
				JOptionPane.showMessageDialog(null, "Seleccione Un Intervalo Valido de Fecha");

		}
	}

	public Integer[] getDias() {

		List<Integer> days = new ArrayList<>();
		List<JCheckBox> checks = Arrays.asList(chckbxLunes, chckbxMartes, chckbxMiercoles, chckbxJueves, chckbxViernes,
				chckbxSabado, chckbxDomingo);

		for (int i = 0; i < checks.size(); i++)
			if (checks.get(i).isSelected())
				days.add(i);

		return days.toArray(new Integer[days.size()]);
	}

	public List<LocalTime[]> getHoras() {
		return horas;
	}

	private boolean validarIntervalo() {
		int hora = Integer.parseInt(spinner.getValue().toString().substring(0, 2));
		int min = Integer.parseInt(spinner.getValue().toString().substring(3));
		LocalTime ti = LocalTime.of(hora, min);
		hora = Integer.parseInt(spinner_1.getValue().toString().substring(0, 2));
		min = Integer.parseInt(spinner_1.getValue().toString().substring(3));
		LocalTime tf = LocalTime.of(hora, min);

		return ti.isBefore(tf);

	}

	public Object[][] getMatrizFormateada() {

		Object[][] mat = new Object[horas.size()][7];
		List<JCheckBox> checks = Arrays.asList(chckbxLunes, chckbxMartes, chckbxMiercoles, chckbxJueves, chckbxViernes,
				chckbxSabado, chckbxDomingo);

		for (int i = 0; i < mat[0].length; i++) {
			if (checks.get(i).isSelected()) {
				for (int j = 0; j < mat.length; j++) {
					mat[j][i] = horas.get(j)[0] + " - " + horas.get(j)[1];
				}
			}
		}

		return mat;

	}

	private Object[][] ordenarHoras() {

		horas.sort((x, y) -> x[0].compareTo(y[0]));
		Object mas[][] = new Object[horas.size()][2];

		for (int i = 0; i < mas.length; i++) {
			mas[i] = horas.get(i);
		}

		return mas;
	}

	public int getRowCount() {
		return table.getRowCount();
	}

	private class OyenteSpinner implements AncestorListener {

		@Override
		public void ancestorAdded(AncestorEvent event) {
		}

		@Override
		public void ancestorMoved(AncestorEvent event) {

		}

		@Override
		public void ancestorRemoved(AncestorEvent event) {

		}

	}

	private class seleccionarTodos implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			boolean state;
			if (chckbxAll.isSelected())
				state = true;
			else
				state = false;

			chckbxLunes.setSelected(state);
			chckbxMartes.setSelected(state);
			chckbxMiercoles.setSelected(state);
			chckbxJueves.setSelected(state);
			chckbxViernes.setSelected(state);
			chckbxSabado.setSelected(state);
			chckbxDomingo.setSelected(state);

		}
	}

	private class salidaDialog implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}

	private void iniciarTabla() {
		modeloT = new DefaultTableModel();
		modeloT.setColumnIdentifiers(new String[] { "Hora Entrado", "Hora Salida" });
		table.setModel(modeloT);
	}

	private void setModel(Object[][] mat) {
		modeloT.setDataVector(mat, new String[] { "Hora Entrado", "Hora Salida" });
	}

	public JButton getOkButton() {
		return okButton;
	}

}
