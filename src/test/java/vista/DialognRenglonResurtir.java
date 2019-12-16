package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import constructoraMaven.modelo.Producto;
import constructoraMaven.modelo.Proveedor;
import constructoraMaven.modelo.RenglonResurtir;
import constructoraMaven.modelo.Utileria;
import java.awt.Font;

public class DialognRenglonResurtir extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton okButton;
	private JTextField cajaProv;
	private JList<String> listProv;
	private JSpinner spinnerCant;
	private DecimalFormat df;
	private List<Proveedor> proveedores;
	private DefaultListModel<String> modeloLista;
	private JFormattedTextField cajaPPu;
	private Producto producto;

	public DialognRenglonResurtir(Producto producto) {
		this.producto = producto;
		modeloLista = new DefaultListModel<>();
		df = new DecimalFormat("######.##");
		getContentPane().setLayout(new BorderLayout());
		setSize(399, 508);
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 291, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 39, 0, 38, 0, 35, 143, 0 };
		gbl_contentPanel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);

		JLabel lblResurtir = new JLabel("Resurtir ".concat(producto.getNombre()));
		lblResurtir.setFont(new Font("TI Uni", Font.PLAIN, 14));
		GridBagConstraints gbc_lblResurtir = new GridBagConstraints();
		gbc_lblResurtir.insets = new Insets(0, 0, 5, 0);
		gbc_lblResurtir.gridx = 0;
		gbc_lblResurtir.gridy = 0;
		contentPanel.add(lblResurtir, gbc_lblResurtir);

		JLabel lblCantidadAResurtir = new JLabel("Cantidad a Resurtir:");
		GridBagConstraints gbc_lblCantidadAResurtir = new GridBagConstraints();
		gbc_lblCantidadAResurtir.insets = new Insets(0, 0, 5, 0);
		gbc_lblCantidadAResurtir.gridx = 0;
		gbc_lblCantidadAResurtir.gridy = 1;
		contentPanel.add(lblCantidadAResurtir, gbc_lblCantidadAResurtir);

		spinnerCant = new JSpinner();
		spinnerCant.setModel(new SpinnerNumberModel(0, 0, 9999, 10));
		GridBagConstraints gbc_spinnerCant = new GridBagConstraints();
		gbc_spinnerCant.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerCant.fill = GridBagConstraints.BOTH;
		gbc_spinnerCant.gridx = 0;
		gbc_spinnerCant.gridy = 2;
		contentPanel.add(spinnerCant, gbc_spinnerCant);

		JLabel lblPrecioPorUnidad = new JLabel("Precio por Unidad");
		GridBagConstraints gbc_lblPrecioPorUnidad = new GridBagConstraints();
		gbc_lblPrecioPorUnidad.insets = new Insets(0, 0, 5, 0);
		gbc_lblPrecioPorUnidad.gridx = 0;
		gbc_lblPrecioPorUnidad.gridy = 3;
		contentPanel.add(lblPrecioPorUnidad, gbc_lblPrecioPorUnidad);

		cajaPPu = new JFormattedTextField(df);
		GridBagConstraints gbc_cajaPPu = new GridBagConstraints();
		gbc_cajaPPu.insets = new Insets(0, 0, 5, 0);
		gbc_cajaPPu.fill = GridBagConstraints.BOTH;
		gbc_cajaPPu.gridx = 0;
		gbc_cajaPPu.gridy = 4;
		contentPanel.add(cajaPPu, gbc_cajaPPu);

		JLabel lblProveedor = new JLabel("Proveedor:");
		GridBagConstraints gbc_lblProveedor = new GridBagConstraints();
		gbc_lblProveedor.insets = new Insets(0, 0, 5, 0);
		gbc_lblProveedor.gridx = 0;
		gbc_lblProveedor.gridy = 5;
		contentPanel.add(lblProveedor, gbc_lblProveedor);

		cajaProv = new JTextField();
		GridBagConstraints gbc_cajaProv = new GridBagConstraints();
		gbc_cajaProv.insets = new Insets(0, 0, 5, 0);
		gbc_cajaProv.fill = GridBagConstraints.BOTH;
		gbc_cajaProv.gridx = 0;
		gbc_cajaProv.gridy = 6;
		contentPanel.add(cajaProv, gbc_cajaProv);
		cajaProv.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 7;
		contentPanel.add(scrollPane, gbc_scrollPane);

		listProv = new JList<>();
		scrollPane.setViewportView(listProv);
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		okButton = new JButton("OK");
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("Cancel");
		cancelButton.addActionListener(new OyenteSalida());
		buttonPane.add(cancelButton);
		cargarProveedores(null);
		añadirOyentes();
		listProv.setModel(modeloLista);
		pack();
		setModal(true);
		setLocationRelativeTo(null);
	}

	private void añadirOyentes() {

		cajaProv.getDocument().addDocumentListener(new OyenteCajaB());
		cajaProv.addActionListener(new OyenteCajaProv());
		listProv.addMouseListener(new OyenteList());

	}

	private class OyenteCajaProv implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (modeloLista.size() > 0) {
				cajaProv.setText(modeloLista.elementAt(0));
			}
		}
	}

	private class OyenteList extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			int fila = listProv.getSelectedIndex();

			if (e.getClickCount() == 2) {
				if (fila >= 0) {
					cajaProv.setText(listProv.getSelectedValue());
				}
			}

		}

	}

	private class OyenteCajaB implements DocumentListener {

		@Override
		public void insertUpdate(DocumentEvent e) {
			cargarProveedores(cajaProv.getText());
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			cargarProveedores(cajaProv.getText());
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
			cargarProveedores(cajaProv.getText());

		}

	}

	private class OyenteSalida implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}

	private void cargarProveedores(String regex) {
		EntityManager m = Main.getEntityManager();
		modeloLista.removeAllElements();
		if (proveedores == null) {
			proveedores = m.createQuery("from Proveedor", Proveedor.class).getResultList();
		}

		if (regex != null) {
			for (Proveedor p : proveedores) {
				if (p.getEmpresa().contains(regex)) {
					modeloLista.addElement(p.getEmpresa());
				}
			}
		} else
			for (Proveedor p : proveedores) {
				modeloLista.addElement(p.getEmpresa());
			}

		m.close();
	}

	public boolean validar() {

		if (Utileria.validarEntero(spinnerCant) <= 0) {
			Utileria.mensajeError("Seleccione una Cantidad Valida");
			spinnerCant.requestFocus();
			return false;
		}

		if (cajaPPu.getText().isEmpty()) {
			Utileria.mensajeError("Asigne un Precio Valido");
			cajaPPu.requestFocus();
			return false;
		}

		if (Utileria.getEntero(cajaPPu) <= 0) {
			Utileria.mensajeError("Escriba un Precio Mayor a 0");
			cajaPPu.requestFocus();
			return false;
		}

		if (cajaProv.getText().isEmpty()) {
			Utileria.mensajeError("Seleccione Proveedor");
			cajaProv.requestFocus();
			return false;
		}

		return true;
	}

	public RenglonResurtir getRenglonResurtir() {

		RenglonResurtir rr = new RenglonResurtir();

		rr.setCve(null);
		rr.setCantidadR(Utileria.validarEntero(spinnerCant));
		rr.setBaja(rr.getCantidadR());
		rr.setProveedor(getProveedor());
		rr.setPpu(Utileria.getDouble(cajaPPu));
		rr.setProducto(producto);

		return rr;

	}

	public Proveedor getProveedor() {
		EntityManager m = Main.getEntityManager();
		List<Proveedor> pr = m.createQuery("from Proveedor where empresa = :emp", Proveedor.class)
				.setParameter("emp", cajaProv.getText()).getResultList();

		m.close();
		if (pr.size() > 0) {
			return pr.get(0);
		} else {
			return new Proveedor(null, LocalDate.now(), cajaProv.getText());
		}
	}

	public JButton getOkButton() {
		return okButton;
	}

}
