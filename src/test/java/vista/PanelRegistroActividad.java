package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import constructoraMaven.modelo.Actividad;
import constructoraMaven.modelo.CobroActividad;
import constructoraMaven.modelo.MaterialActividad;
import constructoraMaven.modelo.PagoActividad;
import constructoraMaven.modelo.Producto;
import constructoraMaven.modelo.TablaProducto;
import constructoraMaven.modelo.Utileria;

public class PanelRegistroActividad extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField cajaNombre;
	private JComboBox<String> comboUnidadMedida;
	private JTextField cajaFiltro;
	private JList<String> listPro;
	private DefaultTableModel modeloTabla;
	private DefaultListModel<String> modeloLista;
	private List<Producto> productos;
	private JTable tablePro;
	private List<Producto> productosAct;
	private JFormattedTextField cajaCobro;
	private JFormattedTextField cajaPago;
	private TableRowSorter<DefaultTableModel> sorter;
	private JTextArea cajaDesc;
	private DecimalFormat df;
	private List<Double> cantidades;

	public PanelRegistroActividad() {

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel panelAct = new JPanel();
		panelAct.setBorder(new EmptyBorder(7, 5, 7, 5));
		add(panelAct);
		panelAct.setLayout(new BorderLayout(0, 0));

		JLabel lblDatosDeLa = new JLabel("Datos de la Actividad");
		lblDatosDeLa.setFont(new Font("TI Uni", Font.PLAIN, 14));
		panelAct.add(lblDatosDeLa, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panelAct.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(1, 2, 0, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(new EmptyBorder(7, 7, 0, 0));
		panel.add(panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		JLabel lblNombre = new JLabel("Nombre:");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.insets = new Insets(0, 0, 5, 0);
		gbc_lblNombre.gridx = 0;
		gbc_lblNombre.gridy = 0;
		panel_1.add(lblNombre, gbc_lblNombre);

		cajaNombre = new JTextField();
		GridBagConstraints gbc_cajaNombre = new GridBagConstraints();
		gbc_cajaNombre.anchor = GridBagConstraints.NORTH;
		gbc_cajaNombre.insets = new Insets(0, 0, 5, 0);
		gbc_cajaNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_cajaNombre.gridx = 0;
		gbc_cajaNombre.gridy = 1;
		panel_1.add(cajaNombre, gbc_cajaNombre);
		cajaNombre.setColumns(10);

		JLabel lblUnidadDeMedida = new JLabel("Unidad de Medida:");
		GridBagConstraints gbc_lblUnidadDeMedida = new GridBagConstraints();
		gbc_lblUnidadDeMedida.insets = new Insets(0, 0, 5, 0);
		gbc_lblUnidadDeMedida.gridx = 0;
		gbc_lblUnidadDeMedida.gridy = 2;
		panel_1.add(lblUnidadDeMedida, gbc_lblUnidadDeMedida);

		comboUnidadMedida = new JComboBox<>();
		GridBagConstraints gbc_comboUnidadMedida = new GridBagConstraints();
		gbc_comboUnidadMedida.insets = new Insets(0, 0, 5, 0);
		gbc_comboUnidadMedida.anchor = GridBagConstraints.NORTH;
		gbc_comboUnidadMedida.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboUnidadMedida.gridx = 0;
		gbc_comboUnidadMedida.gridy = 3;
		panel_1.add(comboUnidadMedida, gbc_comboUnidadMedida);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBorder(new EmptyBorder(7, 7, 7, 7));
		panel.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel_2.add(scrollPane);

		cajaDesc = new JTextArea();
		cajaDesc.setLineWrap(true);
		cajaDesc.setWrapStyleWord(true);
		scrollPane.setViewportView(cajaDesc);

		JLabel lblDescripcion = new JLabel("Descripcion:");
		panel_2.add(lblDescripcion, BorderLayout.NORTH);

		JPanel panelCobroPagoAct = new JPanel();
		panelCobroPagoAct.setBorder(new EmptyBorder(7, 5, 7, 5));
		add(panelCobroPagoAct);
		panelCobroPagoAct.setLayout(new BorderLayout(0, 0));

		JLabel lblDatosDeCobro = new JLabel("Datos de Cobro y Pago");
		lblDatosDeCobro.setFont(new Font("TI Uni", Font.PLAIN, 14));
		panelCobroPagoAct.add(lblDatosDeCobro, BorderLayout.NORTH);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panelCobroPagoAct.add(panel_3, BorderLayout.CENTER);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel_3.rowHeights = new int[] { 15, 0, 15, 0 };
		gbl_panel_3.columnWeights = new double[] { 0.0, 0.0, 1.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panel_3.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_3.setLayout(gbl_panel_3);

		JLabel lblCobroAClientes = new JLabel("Cobro a Clientes:");
		GridBagConstraints gbc_lblCobroAClientes = new GridBagConstraints();
		gbc_lblCobroAClientes.insets = new Insets(0, 0, 5, 5);
		gbc_lblCobroAClientes.anchor = GridBagConstraints.EAST;
		gbc_lblCobroAClientes.gridx = 1;
		gbc_lblCobroAClientes.gridy = 1;
		panel_3.add(lblCobroAClientes, gbc_lblCobroAClientes);

		df = new DecimalFormat("#####.##");

		cajaCobro = new JFormattedTextField(df);
		GridBagConstraints gbc_cajaCobro = new GridBagConstraints();
		gbc_cajaCobro.fill = GridBagConstraints.HORIZONTAL;
		gbc_cajaCobro.insets = new Insets(0, 0, 5, 5);
		gbc_cajaCobro.gridx = 2;
		gbc_cajaCobro.gridy = 1;
		panel_3.add(cajaCobro, gbc_cajaCobro);

		JLabel lblPagoAEmpleados = new JLabel("Pago a Empleados:");
		GridBagConstraints gbc_lblPagoAEmpleados = new GridBagConstraints();
		gbc_lblPagoAEmpleados.anchor = GridBagConstraints.EAST;
		gbc_lblPagoAEmpleados.insets = new Insets(0, 0, 5, 5);
		gbc_lblPagoAEmpleados.gridx = 4;
		gbc_lblPagoAEmpleados.gridy = 1;
		panel_3.add(lblPagoAEmpleados, gbc_lblPagoAEmpleados);

		cajaPago = new JFormattedTextField(df);
		GridBagConstraints gbc_cajaPago = new GridBagConstraints();
		gbc_cajaPago.insets = new Insets(0, 0, 5, 5);
		gbc_cajaPago.fill = GridBagConstraints.HORIZONTAL;
		gbc_cajaPago.gridx = 5;
		gbc_cajaPago.gridy = 1;
		panel_3.add(cajaPago, gbc_cajaPago);

		JPanel panel_4 = new JPanel();
		add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));

		JLabel lblMaterialesParaActividad = new JLabel("Materiales Necesarios Para Actividad");
		lblMaterialesParaActividad.setFont(new Font("TI Uni", Font.PLAIN, 14));
		panel_4.add(lblMaterialesParaActividad, BorderLayout.NORTH);
		panel_4.setBorder(new EmptyBorder(7, 5, 7, 5));

		JPanel panel_8 = new JPanel();
		panel_8.setBackground(Color.WHITE);
		panel_4.add(panel_8, BorderLayout.CENTER);
		GridBagLayout gbl_panel_8 = new GridBagLayout();
		gbl_panel_8.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_panel_8.rowHeights = new int[] { 0, 0, 0 };
		gbl_panel_8.columnWeights = new double[] { 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_panel_8.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		panel_8.setLayout(gbl_panel_8);

		JLabel lblFiltrarProductos = new JLabel("Filtrar Productos");
		GridBagConstraints gbc_lblFiltrarProductos = new GridBagConstraints();
		gbc_lblFiltrarProductos.anchor = GridBagConstraints.EAST;
		gbc_lblFiltrarProductos.insets = new Insets(0, 0, 5, 5);
		gbc_lblFiltrarProductos.gridx = 0;
		gbc_lblFiltrarProductos.gridy = 0;
		panel_8.add(lblFiltrarProductos, gbc_lblFiltrarProductos);

		cajaFiltro = new JTextField();
		GridBagConstraints gbc_cajaFiltro = new GridBagConstraints();
		gbc_cajaFiltro.fill = GridBagConstraints.HORIZONTAL;
		gbc_cajaFiltro.insets = new Insets(0, 0, 5, 5);
		gbc_cajaFiltro.gridx = 1;
		gbc_cajaFiltro.gridy = 0;
		panel_8.add(cajaFiltro, gbc_cajaFiltro);
		cajaFiltro.setColumns(10);

		JLabel lblProductosNecesarios = new JLabel("Productos Necesarios:");
		GridBagConstraints gbc_lblProductosNecesarios = new GridBagConstraints();
		gbc_lblProductosNecesarios.insets = new Insets(0, 0, 5, 0);
		gbc_lblProductosNecesarios.gridx = 2;
		gbc_lblProductosNecesarios.gridy = 0;
		panel_8.add(lblProductosNecesarios, gbc_lblProductosNecesarios);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBackground(Color.WHITE);
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridwidth = 2;
		gbc_scrollPane_1.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 1;
		panel_8.add(scrollPane_1, gbc_scrollPane_1);

		tablePro = new JTable();
		tablePro.setBackground(Color.WHITE);
		scrollPane_1.setViewportView(tablePro);

		JScrollPane scrollPane_2 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
		gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_2.gridx = 2;
		gbc_scrollPane_2.gridy = 1;
		panel_8.add(scrollPane_2, gbc_scrollPane_2);

		listPro = new JList<>();
		scrollPane_2.setViewportView(listPro);
		modeloTabla = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 6;
			}

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				Class<?>[] clases = { Integer.class, String.class, String.class, String.class, Double.class,
						String.class, Boolean.class };
				return clases[columnIndex];
			}
		};
		modeloLista = new DefaultListModel<>();
		modeloTabla.setColumnIdentifiers(
				new Object[] { "Clave", "Nombre", "Marca", "Tipo", "Contenido", "Unidad-M", "Usar" });
		tablePro.setModel(modeloTabla);
		tablePro.getTableHeader().setReorderingAllowed(false);
		tablePro.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listPro.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		sorter = new TableRowSorter<>(modeloTabla);
		tablePro.setRowSorter(sorter);
		productosAct = new ArrayList<>();
		cantidades = new ArrayList<>();
		listPro.setModel(modeloLista);
		listPro.addMouseListener(new OyenteLista());
		llenarTabla(null);
		añadirOyentes();
		validate();

	}

	private void añadirOyentes() {
		tablePro.addMouseListener(new OyenteTabla());

		tablePro.getCellEditor(1, 6).addCellEditorListener(new CellEditorListener() {

			@Override
			public void editingStopped(ChangeEvent e) {
				int fila = tablePro.getSelectedRow();
				int col = tablePro.getSelectedColumn();
				fila = tablePro.convertRowIndexToModel(fila);
				// System.out.println("fila: " + fila + " col: " + col);
				if (fila >= 0 && col == 6) {
					if ((boolean) modeloTabla.getValueAt(fila, col)) {
						if (!añadirProducto(productos.get(fila))) {
							tablePro.setValueAt(false, fila, col);
						}
					} else
						removerProducto(productos.get(fila));
				}

			}

			@Override
			public void editingCanceled(ChangeEvent e) {
				System.out.println("canceled");

			}
		});
		Object uMedida[] = { "Metro cuadrádo", "Metro cúbico", "Metro lineal" };
		Utileria.añandirItems(comboUnidadMedida, uMedida);
		cajaFiltro.getDocument().addDocumentListener(new OyenteFiltro());
		cajaNombre.addFocusListener(new OyenteCajaNomFo());
		cajaNombre.addActionListener(new OyenteCajaNombre());

	}

	private class OyenteFiltro implements DocumentListener {

		private void methodz() {
			RowFilter<DefaultTableModel, Object> rf = null;
			try {
				rf = RowFilter.regexFilter("(?i)".concat(cajaFiltro.getText()));
			} catch (PatternSyntaxException e) {
				System.out.println(e.getMessage());
				return;
			}
			sorter.setRowFilter(rf);
		}

		@Override
		public void insertUpdate(DocumentEvent e) {
			methodz();
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			methodz();
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
			methodz();
		}

	}

	private void llenarTabla(Actividad actividad) {
		if (productos == null) {
			productos = TablaProducto.getProductos();
		}
		if (actividad == null) {

			for (Producto p : productos) {
				modeloTabla.addRow(new Object[] { p.getCve(), p.getNombre(), p.getMarca(), p.getTipo(),
						p.getContenido(), p.getUnidadMedida(), false });
			}

		}

	}

	private class OyenteTabla extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {

		}
	}

	private boolean añadirProducto(Producto p) {
		if (!productosAct.contains(p)) {
			Double cant = Utileria.pedirDecimal("Cantiad");
			if (cant != null) {
				cantidades.add(cant);
				productosAct.add(p);
				modeloLista.addElement(cant + "  " + p.getNombre().concat(" ").concat(p.getMarca()));
				return true;
			}

		}
		return false;

	}

	private class OyenteCajaNombre implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (existeActividad(cajaNombre.getText())) {
				Utileria.mensajeError("La Actividad ya Exsite");
			}
		}
	}

	private class OyenteCajaNomFo extends FocusAdapter {
		@Override
		public void focusLost(FocusEvent e) {
			if (existeActividad(cajaNombre.getText())) {
				Utileria.mensajeError("La Actividad ya Exsite");
			}
		}
	}

	private boolean existeActividad(String nombre) {
		EntityManager m = Main.getEntityManager();
		Query q = m.createQuery("from Actividad where nombreActividad = :nom").setParameter("nom", nombre);
		int resutado = q.getResultList().size();
		m.close();
		return resutado > 0;
	}

	private class OyenteLista extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {

			int fila = listPro.getSelectedIndex();

			if (fila >= 0) {
				if (e.getClickCount() == 2) {
					if (e.getButton() == MouseEvent.BUTTON1) {
						añadirProductos(fila, 1);
					}
				} else if (e.getButton() == MouseEvent.BUTTON3) {
					añadirProductos(fila, -1);
				}

			}

		}
	}

	private void añadirProductos(int index, int cant) {

		Double c = cantidades.get(index) + cant;

		if (c > 0) {
			cantidades.set(index, c);
			Producto p = productosAct.get(index);
			modeloLista.set(index, c + "  " + p.getNombre().concat(" ").concat(p.getMarca()));
		} else {
			tablePro.setValueAt(false, getFila(productosAct.get(index)), 6);
			productosAct.remove(index);
			modeloLista.remove(index);
			cantidades.remove(index);
		}

	}

	private int getFila(Producto p) {
		for (int i = 0; i < modeloTabla.getRowCount(); i++) {
			if (p.getCve() == tablePro.getValueAt(i, 0)) {
				return i;
			}
		}
		return -1;
	}

	private void removerProducto(Producto p) {
		// System.out.println(p);
		int i = productosAct.indexOf(p);
		if (i > -1) {
			cantidades.remove(i);
			productosAct.remove(i);
			modeloLista.remove(i);
		}

	}

	public Actividad getActividad() {

		Actividad act = new Actividad();
		act.setCveActividad(null);
		act.setNomrbeActividad(cajaNombre.getText());
		act.setDescActividad(cajaDesc.getText());
		act.setUnidadMedidaAct(comboUnidadMedida.getSelectedItem().toString());

		CobroActividad c = new CobroActividad(null, LocalDate.now(), Utileria.getDouble(cajaCobro), act);
		PagoActividad p = new PagoActividad(null, LocalDate.now(), Utileria.getDouble(cajaPago), act);

		act.añadirCobroAct(c);
		act.añadirPagoAct(p);

		for (int i = 0; i < productosAct.size(); i++) {
			MaterialActividad mat = new MaterialActividad(null, cantidades.get(i), productosAct.get(i), act);
			act.añadirMaterialAct(mat);
		}

		return act;
	}

	public boolean validar() {

		if (cajaNombre.getText().isEmpty()) {
			Utileria.mensajeError("Escriba un Nombre Para la Actividad");
			cajaNombre.requestFocus();
			return false;
		}
		if (cajaDesc.getText().isEmpty()) {
			Utileria.mensajeError("Escriba una Descripcion Para la Actividad");
			cajaDesc.requestFocus();
			return false;
		}

		if (Utileria.getFlotante(cajaCobro) <= 0) {
			Utileria.mensajeError("Escoja una Cantidad de Cobro valida");
			cajaCobro.requestFocus();
			return false;
		}
		if (Utileria.getFlotante(cajaPago) <= 0) {
			Utileria.mensajeError("Escoja una Cantidad de Cobro valida");
			cajaPago.requestFocus();
			return false;
		}

		if (productosAct.size() == 0) {
			Utileria.mensajeError("Debe Escoger al Menos un Material");
			cajaFiltro.requestFocus();
			return false;
		}

		return true;
	}

}
