package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import constructoraMaven.modelo.Producto;
import constructoraMaven.modelo.RenglonResurtir;

public class PanelResurtir extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField cajaBusq;
	private JTable tablaResurtido;
	private JTable tablaExistencia;
	private DefaultTableModel modeloExistencias;
	private DefaultTableModel modeloResurtidos;
	private TableRowSorter<DefaultTableModel> sorterExistencias;
	private TableRowSorter<DefaultTableModel> sorterResurtidos;
	private List<RenglonResurtir> productosAgregados;
	private DialognRenglonResurtir dr;
	private boolean añadido;

	public PanelResurtir() {
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));

		JLabel lblResurtirProductos = new JLabel("Resurtir Productos");
		lblResurtirProductos.setFont(new Font("TI Uni", Font.PLAIN, 14));
		add(lblResurtirProductos, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblBusquedaDeProducto = new JLabel("Busqueda de Producto");
		GridBagConstraints gbc_lblBusquedaDeProducto = new GridBagConstraints();
		gbc_lblBusquedaDeProducto.insets = new Insets(0, 0, 5, 5);
		gbc_lblBusquedaDeProducto.anchor = GridBagConstraints.EAST;
		gbc_lblBusquedaDeProducto.gridx = 0;
		gbc_lblBusquedaDeProducto.gridy = 0;
		panel.add(lblBusquedaDeProducto, gbc_lblBusquedaDeProducto);

		cajaBusq = new JTextField();
		GridBagConstraints gbc_cajaBusq = new GridBagConstraints();
		gbc_cajaBusq.insets = new Insets(0, 0, 5, 0);
		gbc_cajaBusq.fill = GridBagConstraints.HORIZONTAL;
		gbc_cajaBusq.gridx = 1;
		gbc_cajaBusq.gridy = 0;
		panel.add(cajaBusq, gbc_cajaBusq);
		cajaBusq.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		panel.add(scrollPane, gbc_scrollPane);

		tablaExistencia = new JTable();
		scrollPane.setViewportView(tablaExistencia);

		JLabel lblListaDeProductos = new JLabel("Productos a Resurtir");
		GridBagConstraints gbc_lblListaDeProductos = new GridBagConstraints();
		gbc_lblListaDeProductos.insets = new Insets(0, 0, 5, 0);
		gbc_lblListaDeProductos.gridwidth = 2;
		gbc_lblListaDeProductos.gridx = 0;
		gbc_lblListaDeProductos.gridy = 2;
		panel.add(lblListaDeProductos, gbc_lblListaDeProductos);

		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridwidth = 2;
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 3;
		panel.add(scrollPane_1, gbc_scrollPane_1);

		tablaResurtido = new JTable();
		scrollPane_1.setViewportView(tablaResurtido);
		inicializarTablas();

		productosAgregados = new ArrayList<>();
	}

	private void inicializarTablas() {
		modeloExistencias = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 9;
			}

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				Class<?>[] clases = { Integer.class, String.class, String.class, String.class, Double.class,
						String.class, Integer.class, Integer.class, Integer.class, JButton.class };
				return clases[columnIndex];
			}
		};

		tablaExistencia.setModel(modeloExistencias);
		tablaExistencia.getTableHeader().setReorderingAllowed(false);
		tablaExistencia.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		sorterExistencias = new TableRowSorter<>(modeloExistencias);
		tablaExistencia.setRowSorter(sorterExistencias);
		modeloExistencias.setColumnIdentifiers(new Object[] { "Clave", "Nombre", "Marca", "Tipo", "Contenido",
				"Unidad-M", "Minimo", "Maximo", "Existencias", "Resurtir" });

		modeloResurtidos = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 8;
			}

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				Class<?>[] clases = { Integer.class, String.class, String.class, String.class, Double.class,
						String.class, Double.class, Integer.class, JButton.class };
				return clases[columnIndex];
			}
		};

		tablaExistencia.getColumn("Resurtir").setCellRenderer(new ButtonRenderer());
		tablaExistencia.getColumn("Resurtir").setCellEditor(new ButtonEditor(new JCheckBox()));

		tablaResurtido.setModel(modeloResurtidos);
		tablaResurtido.getTableHeader().setReorderingAllowed(false);
		tablaResurtido.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		sorterResurtidos = new TableRowSorter<>(modeloResurtidos);
		tablaResurtido.setRowSorter(sorterResurtidos);
		modeloResurtidos.setColumnIdentifiers(new Object[] { "Clave", "Nombre", "Marca", "Tipo", "Contenido",
				"Unidad-M", "PPU", "Resurtidos", "Remover" });

		tablaExistencia.getCellEditor(0, 9).addCellEditorListener(new CellEditorListener() {

			@Override
			public void editingStopped(ChangeEvent e) {
				int fila = tablaExistencia.getSelectedRow();
				int col = tablaExistencia.getSelectedColumn();
				fila = tablaExistencia.convertRowIndexToModel(fila);
				// System.out.println("fila: " + fila + " col: " + col);
				if (fila >= 0 && col == 9) {

					EntityManager m = Main.getEntityManager();
					Producto pr = m.find(Producto.class,
							Integer.valueOf(tablaExistencia.getValueAt(fila, 0).toString()));
					m.close();
					dr = new DialognRenglonResurtir(pr);
					dr.getOkButton().addActionListener(new OyenteDialog());
					dr.setVisible(true);
				}

			}

			@Override
			public void editingCanceled(ChangeEvent e) {
				System.out.println("canceled");

			}
		});

		tablaResurtido.getColumn("Remover").setCellRenderer(new ButtonRenderer());
		tablaResurtido.getColumn("Remover").setCellEditor(new ButtonEditor(new JCheckBox()));

		llenarExistencias();
	}

	public List<RenglonResurtir> getRenglonesRes() {
		return productosAgregados;
	}

	public void añadirEventoRes() {
		añadido = true;
		tablaResurtido.getCellEditor(0, 8).addCellEditorListener(new CellEditorListener() {

			@Override
			public void editingStopped(ChangeEvent e) {
				int fila = tablaResurtido.getSelectedRow();
				int col = tablaResurtido.getSelectedColumn();
				fila = tablaResurtido.convertRowIndexToModel(fila);
				// System.out.println("fila: " + fila + " col: " + col);
				if (fila >= 0 && col == 8) {
					removerResurtido(fila);
				}

			}

			@Override
			public void editingCanceled(ChangeEvent e) {
				System.out.println("canceled");

			}
		});

	}

	public void removerResurtido(int p) {

		productosAgregados.remove(p);
		modeloResurtidos.removeRow(p);

	}

	private class OyenteDialog implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (dr.validar()) {

				EntityManager m = Main.getEntityManager();
				RenglonResurtir rr = dr.getRenglonResurtir();
				Producto p = rr.getProducto();
				productosAgregados.add(rr);
				modeloResurtidos.addRow(new Object[] { p.getCve(), p.getNombre(), p.getMarca(), p.getTipo(),
						p.getContenido(), p.getUnidadMedida(), rr.getPpu(), rr.getBaja(), "Remover" });
				dr.dispose();

				m.close();
				if (!añadido) {
					añadirEventoRes();
				}
			}
		}
	}

	public boolean validar() {

		return productosAgregados.size() > 0;
	}

	@SuppressWarnings("unchecked")
	private void llenarExistencias() {

		EntityManager m = Main.getEntityManager();

		List<Object[]> data = m.createNativeQuery(
				"select p.cve_pro, p.nom_pro, p.marca_pro, tipo_pro, contenido_pro, umedida_pro, min_pro, max_pro, ifnull(sum(baja_renres), 0) existencias from producto p left join renglonresurtir rr on rr.cve_pro = p.cve_pro group by p.cve_pro")
				.getResultList();

		for (Object[] o : data) {
			Object[] n = { o[0], o[1], o[2], o[3], o[4], o[5], o[6], o[7], o[8], "Añadir" };
			modeloExistencias.addRow(n);
		}

		m.close();

	}

	class ButtonRenderer extends JButton implements TableCellRenderer {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public ButtonRenderer() {
			setOpaque(true);
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			if (isSelected) {
				setForeground(table.getSelectionForeground());
				setBackground(table.getSelectionBackground());
			} else {
				setForeground(table.getForeground());
				setBackground(UIManager.getColor("Button.background"));
			}
			setText((value == null) ? "" : value.toString());
			return this;
		}
	}

	class ButtonEditor extends DefaultCellEditor {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		protected JButton button;
		private String label;
		private boolean isPushed;

		public ButtonEditor(JCheckBox checkBox) {
			super(checkBox);
			button = new JButton();
			button.setOpaque(true);
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					fireEditingStopped();
				}
			});
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			if (isSelected) {
				button.setForeground(table.getSelectionForeground());
				button.setBackground(table.getSelectionBackground());
			} else {
				button.setForeground(table.getForeground());
				button.setBackground(table.getBackground());
			}
			label = (value == null) ? "" : value.toString();
			button.setText(label);
			isPushed = true;
			return button;
		}

		@Override
		public Object getCellEditorValue() {
			if (isPushed) {
				// JOptionPane.showMessageDialog(button, label + ": Ouch!");
			}
			isPushed = false;
			return label;
		}

		@Override
		public boolean stopCellEditing() {
			isPushed = false;
			return super.stopCellEditing();
		}
	}

}
