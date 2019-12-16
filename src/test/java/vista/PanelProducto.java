package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import constructoraMaven.modelo.Producto;
import constructoraMaven.modelo.TablaProducto;
import constructoraMaven.modelo.Utileria;

public class PanelProducto extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField cajaNombre;
	private JTextField cajaMarca;
	private JTable table;
	private JFormattedTextField cajaContenido;
	private JFormattedTextField cajaMin;
	private JFormattedTextField cajaMax;
	private JComboBox<String> comboUnidadMedida;
	private JComboBox<String> comboTipo;
	private JButton btnRegistrar;
	private JButton btnLimpiar;
	private JTextField[] cajas;
	private DefaultTableModel modelo;
	private TableRowSorter<DefaultTableModel> sorter;
	private List<Producto> productos;
	private Producto productoAct;
	private DecimalFormat df;

	public PanelProducto() {

		df = new DecimalFormat("#####.##");
		setLayout(new BorderLayout(0, 0));

		JLabel lblRegistroDeProducto = new JLabel("Registro de Producto");
		lblRegistroDeProducto.setFont(new Font("TI Uni", Font.PLAIN, 14));
		add(lblRegistroDeProducto, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(0, 0, 0, 4));
		panel.setBackground(Color.WHITE);
		add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 15, 130, 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 15, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 15, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
				0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblProductosRegistrados = new JLabel("Productos Registrados");
		GridBagConstraints gbc_lblProductosRegistrados = new GridBagConstraints();
		gbc_lblProductosRegistrados.insets = new Insets(0, 0, 5, 0);
		gbc_lblProductosRegistrados.gridx = 4;
		gbc_lblProductosRegistrados.gridy = 0;
		panel.add(lblProductosRegistrados, gbc_lblProductosRegistrados);

		JLabel lblNombre = new JLabel("Nombre:");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.gridwidth = 2;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 1;
		gbc_lblNombre.gridy = 1;
		panel.add(lblNombre, gbc_lblNombre);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 16;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 4;
		gbc_scrollPane.gridy = 1;
		panel.add(scrollPane, gbc_scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		cajaNombre = new JTextField();
		GridBagConstraints gbc_cajaNombre = new GridBagConstraints();
		gbc_cajaNombre.gridwidth = 2;
		gbc_cajaNombre.fill = GridBagConstraints.BOTH;
		gbc_cajaNombre.insets = new Insets(0, 0, 5, 5);
		gbc_cajaNombre.gridx = 1;
		gbc_cajaNombre.gridy = 2;
		panel.add(cajaNombre, gbc_cajaNombre);
		cajaNombre.setColumns(10);

		JLabel lblMarca = new JLabel("Marca:");
		GridBagConstraints gbc_lblMarca = new GridBagConstraints();
		gbc_lblMarca.gridwidth = 2;
		gbc_lblMarca.insets = new Insets(0, 0, 5, 5);
		gbc_lblMarca.gridx = 1;
		gbc_lblMarca.gridy = 3;
		panel.add(lblMarca, gbc_lblMarca);

		cajaMarca = new JTextField();
		GridBagConstraints gbc_cajaMarca = new GridBagConstraints();
		gbc_cajaMarca.gridwidth = 2;
		gbc_cajaMarca.fill = GridBagConstraints.BOTH;
		gbc_cajaMarca.insets = new Insets(0, 0, 5, 5);
		gbc_cajaMarca.gridx = 1;
		gbc_cajaMarca.gridy = 4;
		panel.add(cajaMarca, gbc_cajaMarca);
		cajaMarca.setColumns(10);

		JLabel lblTipo = new JLabel("Tipo:");
		GridBagConstraints gbc_lblTipo = new GridBagConstraints();
		gbc_lblTipo.gridwidth = 2;
		gbc_lblTipo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTipo.gridx = 1;
		gbc_lblTipo.gridy = 5;
		panel.add(lblTipo, gbc_lblTipo);

		comboTipo = new JComboBox<>();
		GridBagConstraints gbc_comboTipo = new GridBagConstraints();
		gbc_comboTipo.gridwidth = 2;
		gbc_comboTipo.insets = new Insets(0, 0, 5, 5);
		gbc_comboTipo.fill = GridBagConstraints.BOTH;
		gbc_comboTipo.gridx = 1;
		gbc_comboTipo.gridy = 6;
		panel.add(comboTipo, gbc_comboTipo);

		JLabel lblUnidadDeMedida = new JLabel("Unidad de Medida:");
		GridBagConstraints gbc_lblUnidadDeMedida = new GridBagConstraints();
		gbc_lblUnidadDeMedida.gridwidth = 2;
		gbc_lblUnidadDeMedida.insets = new Insets(0, 0, 5, 5);
		gbc_lblUnidadDeMedida.gridx = 1;
		gbc_lblUnidadDeMedida.gridy = 7;
		panel.add(lblUnidadDeMedida, gbc_lblUnidadDeMedida);

		comboUnidadMedida = new JComboBox<>();
		GridBagConstraints gbc_comboUnidadMedida = new GridBagConstraints();
		gbc_comboUnidadMedida.gridwidth = 2;
		gbc_comboUnidadMedida.insets = new Insets(0, 0, 5, 5);
		gbc_comboUnidadMedida.fill = GridBagConstraints.BOTH;
		gbc_comboUnidadMedida.gridx = 1;
		gbc_comboUnidadMedida.gridy = 8;
		panel.add(comboUnidadMedida, gbc_comboUnidadMedida);

		JLabel lblContenido = new JLabel("Contenido:");
		GridBagConstraints gbc_lblContenido = new GridBagConstraints();
		gbc_lblContenido.gridwidth = 2;
		gbc_lblContenido.insets = new Insets(0, 0, 5, 5);
		gbc_lblContenido.gridx = 1;
		gbc_lblContenido.gridy = 9;
		panel.add(lblContenido, gbc_lblContenido);

		cajaContenido = new JFormattedTextField(df);
		GridBagConstraints gbc_cajaContenido = new GridBagConstraints();
		gbc_cajaContenido.gridwidth = 2;
		gbc_cajaContenido.insets = new Insets(0, 0, 5, 5);
		gbc_cajaContenido.fill = GridBagConstraints.BOTH;
		gbc_cajaContenido.gridx = 1;
		gbc_cajaContenido.gridy = 10;
		panel.add(cajaContenido, gbc_cajaContenido);

		JLabel lblCantidadMinima = new JLabel("Cantidad Minima:");
		GridBagConstraints gbc_lblCantidadMinima = new GridBagConstraints();
		gbc_lblCantidadMinima.gridwidth = 2;
		gbc_lblCantidadMinima.insets = new Insets(0, 0, 5, 5);
		gbc_lblCantidadMinima.gridx = 1;
		gbc_lblCantidadMinima.gridy = 11;
		panel.add(lblCantidadMinima, gbc_lblCantidadMinima);

		cajaMin = new JFormattedTextField(df);
		GridBagConstraints gbc_cajaMin = new GridBagConstraints();
		gbc_cajaMin.gridwidth = 2;
		gbc_cajaMin.insets = new Insets(0, 0, 5, 5);
		gbc_cajaMin.fill = GridBagConstraints.BOTH;
		gbc_cajaMin.gridx = 1;
		gbc_cajaMin.gridy = 12;
		panel.add(cajaMin, gbc_cajaMin);

		JLabel lblCantidadMaxima = new JLabel("Cantidad Maxima:");
		GridBagConstraints gbc_lblCantidadMaxima = new GridBagConstraints();
		gbc_lblCantidadMaxima.gridwidth = 2;
		gbc_lblCantidadMaxima.insets = new Insets(0, 0, 5, 5);
		gbc_lblCantidadMaxima.gridx = 1;
		gbc_lblCantidadMaxima.gridy = 13;
		panel.add(lblCantidadMaxima, gbc_lblCantidadMaxima);

		cajaMax = new JFormattedTextField(df);
		GridBagConstraints gbc_cajaMax = new GridBagConstraints();
		gbc_cajaMax.gridwidth = 2;
		gbc_cajaMax.insets = new Insets(0, 0, 5, 5);
		gbc_cajaMax.fill = GridBagConstraints.BOTH;
		gbc_cajaMax.gridx = 1;
		gbc_cajaMax.gridy = 14;
		panel.add(cajaMax, gbc_cajaMax);

		btnRegistrar = new JButton("Registrar");
		GridBagConstraints gbc_btnRegistrar = new GridBagConstraints();
		gbc_btnRegistrar.anchor = GridBagConstraints.SOUTH;
		gbc_btnRegistrar.insets = new Insets(0, 0, 5, 5);
		gbc_btnRegistrar.gridx = 1;
		gbc_btnRegistrar.gridy = 15;
		panel.add(btnRegistrar, gbc_btnRegistrar);

		btnLimpiar = new JButton("Limpiar");
		GridBagConstraints gbc_btnLimpiar = new GridBagConstraints();
		gbc_btnLimpiar.insets = new Insets(0, 0, 5, 5);
		gbc_btnLimpiar.gridx = 2;
		gbc_btnLimpiar.gridy = 15;
		panel.add(btnLimpiar, gbc_btnLimpiar);

		cajas = new JTextField[] { cajaNombre, cajaMarca, cajaContenido, cajaMin, cajaMax };
		modelo = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				Class<?>[] clases = { Integer.class, String.class, String.class, String.class, Double.class,
						String.class, Integer.class, Integer.class };
				return clases[columnIndex];
			}
		};

		table.setModel(modelo);
		table.getTableHeader().setReorderingAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		sorter = new TableRowSorter<>(modelo);
		table.setRowSorter(sorter);
		modelo.setColumnIdentifiers(
				new Object[] { "Clave", "Nombre", "Marca", "Tipo", "Contenido", "Unidad-M", "Minimo", "Maximo" });
		llenarTabla();
		añadirOyentes();
	}

	private void añadirOyentes() {

		btnLimpiar.addActionListener(new OyenteLimpiar());
		table.addMouseListener(new OyenteTabla());
		btnRegistrar.addActionListener(new OyenteRegistroActualizacion());
		llenarCombos();
		comboTipo.addItemListener(new OyenteComboTipo());

	}

	private class OyenteLimpiar implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			limpiarCajas();
		}
	}

	private void limpiarCajas() {
		for (JTextField caja : cajas) {
			caja.setText("");
		}
		comboTipo.setSelectedIndex(0);
		comboUnidadMedida.setSelectedIndex(0);
		cajaNombre.requestFocus();
		cancelarAct();
	}

	private class OyenteTabla extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {

			if (e.getClickCount() == 2) {
				int fila = table.convertRowIndexToModel(table.getSelectedRow());
				if (fila >= 0) {
					LlenarCajas(productos.get(fila));
					btnRegistrar.setText("Actualizar");
					productoAct = productos.get(fila);
				}
			}
		}
	}

	private void llenarCombos() {
		Object[] st = { "Material", "Herramienta" };
		Utileria.añandirItems(comboTipo, st);
		st = new Object[] { "Centimetro", "Gramo", "Kilogramo", "Metro", "Pieza", "Pulgada", "Otro" };
		Utileria.añandirItems(comboUnidadMedida, st);
	}

	private class OyenteRegistroActualizacion implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			if (validar()) {
				Producto p = productoAct;
				EntityManager m = Main.getEntityManager();
				if (p == null) {
					p = getProducto(null);
					m.getTransaction().begin();
					m.persist(p);
					m.getTransaction().commit();
					llenarTabla();
					Utileria.mensaje("Registrado");

				} else {
					int op = Utileria.getOpcion("Desea Actualizar el Producto");
					if (op == JOptionPane.YES_OPTION) {
						p = m.merge(p);
						m.getTransaction().begin();
						p = getProducto(p);
						m.getTransaction().commit();
						llenarTabla();
						Utileria.mensaje("Actualizado");

					} else {

					}
				}
				cancelarAct();
				limpiarCajas();
				m.close();
			}

		}
	}

	private class OyenteComboTipo implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				if (e.getItem().toString().equals(comboTipo.getItemAt(1))) {
					comboUnidadMedida.setEnabled(false);
				} else
					comboUnidadMedida.setEnabled(true);
			}
		}

	}

	private void cancelarAct() {
		productoAct = null;
		btnRegistrar.setText("Registrar");
	}

	public static Producto buscarPorNMT(String nombre, String marca, String tipo) {
		EntityManager manager = Main.getEntityManager();

		List<Producto> productos = manager
				.createQuery("from Producto where nombre = :nom and tipo = :t and marca = :m", Producto.class)
				.setParameter("nom", nombre).setParameter("t", tipo).setParameter("m", marca).getResultList();
		manager.close();
		if (productos.size() > 0) {
			return productos.get(0);
		}
		return null;

	}

	private boolean validar() {

		if (cajaNombre.getText().isEmpty()) {
			Utileria.mensajeError("Escriba un Nombre");
			cajaNombre.requestFocus();
			return false;
		}

		if (cajaMarca.getText().isEmpty()) {
			Utileria.mensajeError("Escriba una Marca");
			cajaMarca.requestFocus();
			return false;
		}

		if (cajaContenido.getText().isEmpty()) {
			Utileria.mensajeError("Escriba el Contenido");
			cajaContenido.requestFocus();
			return false;
		}

		if (Double.valueOf(cajaContenido.getText()) < 1) {
			Utileria.mensajeError("Escriba un Contenido Valido");
			cajaContenido.requestFocus();
			return false;
		}

		if (cajaMin.getText().isEmpty()) {
			Utileria.mensajeError("Escriba una Cantidad Minima");
			cajaMin.requestFocus();
			return false;
		}

		if (cajaMax.getText().isEmpty()) {
			Utileria.mensajeError("Escriba una Cantidad Maxima");
			cajaMax.requestFocus();
			return false;
		}

		double min = Utileria.getDouble(cajaMin);
		double max = Utileria.getDouble(cajaMax);

		if (min >= max) {
			Utileria.mensajeError("La Cantidad Minima no Puede ser Mayor a la Maxima");
			cajaMin.requestFocus();
			return false;
		}

		return true;
	}

	private Producto getProducto(Producto p) {
		if (p == null) {
			p = new Producto();
			p.setCve(null);
		}
		p.setNombre(cajaNombre.getText());
		p.setMarca(cajaMarca.getText());
		p.setTipo(comboTipo.getSelectedItem().toString());
		if (!p.getTipo().equals(comboTipo.getItemAt(1))) {
			p.setUnidadMedida(comboUnidadMedida.getSelectedItem().toString());
		} else
			p.setUnidadMedida(null);

		p.setContenido(Double.valueOf(cajaContenido.getText()));
		p.setMinimo(Integer.valueOf(cajaMin.getText()));
		p.setMaximo(Integer.valueOf(cajaMax.getText()));

		return p;
	}

	private void LlenarCajas(Producto p) {
		cajaNombre.setText(p.getNombre());
		cajaMarca.setText(p.getMarca());
		cajaContenido.setText(String.valueOf(p.getContenido()));
		cajaMax.setText(String.valueOf(p.getMaximo()));
		cajaMin.setText(String.valueOf(p.getMinimo()));
		comboTipo.setSelectedItem(p.getTipo());
		comboUnidadMedida.setSelectedItem(p.getUnidadMedida());
	}

	private void llenarTabla() {
		productos = TablaProducto.getProductos();
		modelo.setRowCount(0);
		for (Producto p : productos) {
			modelo.addRow(new Object[] { p.getCve(), p.getNombre(), p.getMarca(), p.getTipo(), p.getContenido(),
					p.getUnidadMedida(), p.getMinimo(), p.getMaximo() });
		}

	}

}
