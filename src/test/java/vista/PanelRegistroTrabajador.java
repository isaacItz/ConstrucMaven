package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.persistence.EntityManager;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import constructoraMaven.modelo.Horario;
import constructoraMaven.modelo.Persona;
import constructoraMaven.modelo.TrabajadorContrato;
import constructoraMaven.modelo.Utileria;

public class PanelRegistroTrabajador extends JPanel {

	private static final long serialVersionUID = 1L;
	private PanelDatosUsuario panelDatosUsuario;
	private PanelHorario panelHorario;
	private JButton btnNewButton;

	public PanelRegistroTrabajador() {
		setLayout(new BorderLayout());
		JPanel panelP = new JPanel(new BorderLayout());
		add(panelP, BorderLayout.CENTER);

		JPanel panel = new JPanel(new FlowLayout());
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnNewButton = new JButton("Aceptar");
		panel.add(btnNewButton);
		panelP.add(panel, BorderLayout.SOUTH);
		btnNewButton.addActionListener(new OyenteAceptar());

		panelDatosUsuario = new PanelDatosUsuario();
		panelDatosUsuario.setBorder(null);
		panelDatosUsuario.setPreferredSize(new Dimension(400, 600));
		panelDatosUsuario.setSize(100, 200);
		panelHorario = new PanelHorario();
		JPanel contenedor = new JPanel();
		contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.Y_AXIS));

		contenedor.add(panelDatosUsuario);
		contenedor.add(panelHorario);
		setBackground(Color.WHITE);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new EmptyBorder(0, 6, 0, 0));
		scrollPane.setBackground(Color.WHITE);
		panelP.add(scrollPane, BorderLayout.CENTER);
		scrollPane.setViewportView(contenedor);

	}

	class OyenteAceptar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (panelDatosUsuario.validar() && panelHorario.validarHorario()) {
				EntityManager m = Main.getEntityManager();
				TrabajadorContrato tc = panelHorario.getTrabajadorContrato();
				Persona p = panelDatosUsuario.getPersona();
				Horario h = panelHorario.getHorario();
				tc.setP(p);
				h.setTrabajadorContrato(tc);

				try {
					m.getTransaction().begin();
					if (p.getCve() < 0) {
						m.persist(p);
					}

					m.persist(h);
					// m.persist(tc);
					m.getTransaction().commit();

					Utileria.mensaje("Registrado");
				} catch (Exception e2) {
					e2.printStackTrace();
				}

			}

		}

	}

}
