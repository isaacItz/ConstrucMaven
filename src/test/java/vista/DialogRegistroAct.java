package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.persistence.EntityManager;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

public class DialogRegistroAct extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final PanelRegistroActividad contentPanel = new PanelRegistroActividad();

	public DialogRegistroAct() {
		setTitle("Registro de Actividad");
		setBackground(Color.WHITE);

		setBounds(100, 100, 579, 563);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		JButton okButton = new JButton("OK");
		okButton.setActionCommand("OK");
		okButton.addActionListener(new OyenteAceptar());
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("Cancel");
		cancelButton.addActionListener(new OyenteCancelar());
		buttonPane.add(cancelButton);
		setModal(true);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private class OyenteCancelar implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}

	private class OyenteAceptar implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			if (contentPanel.validar()) {
				EntityManager m = Main.getEntityManager();

				m.getTransaction().begin();
				System.out.println(contentPanel.getActividad());
				contentPanel.getActividad().setCveActividad(0);
				m.persist(contentPanel.getActividad());
				m.getTransaction().commit();
				m.close();
			}

		}
	}

}
