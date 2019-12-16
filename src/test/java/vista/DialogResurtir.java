package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import constructoraMaven.modelo.Resurtir;
import constructoraMaven.modelo.Utileria;

public class DialogResurtir extends JDialog {

	private static final long serialVersionUID = 1L;
	private final PanelResurtir contentPanel = new PanelResurtir();

	public DialogResurtir() {
		setSize(900, 500);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		JButton okButton = new JButton("OK");
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("Cancel");
		okButton.addActionListener(new OyenteOk());
		buttonPane.add(cancelButton);
		cancelButton.addActionListener(new OyenteSalida());
		setModal(true);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private class OyenteOk implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (contentPanel.validar()) {
				Resurtir r = new Resurtir(null, LocalDate.now());
				contentPanel.getRenglonesRes().forEach(x -> x.setResurtido(r));
				r.setRenglonesRes(contentPanel.getRenglonesRes());
				EntityManager m = Main.getEntityManager();
				m.getTransaction().begin();
				m.persist(r);
				m.getTransaction().commit();
				m.close();
				Utileria.mensaje("Registrado!");
				dispose();
			} else {
				Utileria.mensajeError("Seleccione Productos Para Resurtir");
			}
		}

	}

	private class OyenteSalida implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}
}
