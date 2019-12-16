package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenuBar barra;
	public static String BD_Name;
	public static EntityManagerFactory emf;

	public Main() {

		emf = Persistence.createEntityManagerFactory("Constructora");

		barra = new JMenuBar();
		setJMenuBar(barra);

		JMenu mnTrabajadores = new JMenu("Trabajadores");
		barra.add(mnTrabajadores);

		JMenuItem mntmRegistrar = new JMenuItem("Registrar");
		mnTrabajadores.add(mntmRegistrar);
		mntmRegistrar.addActionListener(new OyenteTrabajador());

		JMenu mnActividades = new JMenu("Actividades");
		barra.add(mnActividades);

		JMenuItem mntmRegistrar_1 = new JMenuItem("Registrar");
		mnActividades.add(mntmRegistrar_1);
		mntmRegistrar_1.addActionListener(new OyenteCrearActividad());

		JMenu menuCliente = new JMenu("Clientes");
		barra.add(menuCliente);
		JMenuItem registrarCliente = new JMenuItem("Registrar");
		menuCliente.add(registrarCliente);

		JMenu mnProductos = new JMenu("Productos");
		barra.add(mnProductos);

		JMenuItem mntmRegistrar_2 = new JMenuItem("Registrar");
		mnProductos.add(mntmRegistrar_2);

		JMenuItem mntmResurtir = new JMenuItem("Resurtir");
		mntmResurtir.addActionListener(new OyenteResurtir());
		mnProductos.add(mntmResurtir);
		mntmRegistrar_2.addActionListener(new OyenteRegistroProducto());
		setSize(900, 500);
		setTitle("Constructora");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		addWindowListener(new OyenteSalida());
		setVisible(true);

	}

	public static void main(String[] args) {
		new Main();
	}

	private class OyenteResurtir implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			new DialogResurtir();
		}
	}

	private class OyenteCrearActividad implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			new DialogRegistroAct();
		}
	}

	private class OyenteRegistroProducto implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			new DialogRegistroPro();
		}
	}

	private class OyenteTrabajador implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			getContentPane().removeAll();
			getContentPane().add(new PanelRegistroTrabajador());
			setVisible(true);
		}

	}

	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	private class OyenteSalida extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent e) {
			emf.close();
			super.windowClosing(e);
		}
	}

	public void validarFrame() {
		validate();
	}

}
