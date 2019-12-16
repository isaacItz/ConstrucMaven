package constructoraMaven.modelo;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import org.apache.commons.lang3.text.WordUtils;

public class Utileria {

	public static boolean validarFlotante(JTextField text) {

		try {
			getFlotante(text);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public static boolean fechaMayorActual(LocalDate fecha) {
		return fecha.isAfter(LocalDate.now());
	}

	public static void añandirItems(JComboBox<String> combo, Object... strings) {
		combo.removeAllItems();
		for (Object string : strings) {
			combo.addItem(string.toString());
		}

	}

	public static int getOpcion(String msj) {
		return JOptionPane.showConfirmDialog(null, msj, "Confirmar", JOptionPane.YES_NO_OPTION);
	}

	public static Double pedirDecimal(String cad) {
		try {

			String c = JOptionPane.showInputDialog(cad);
			if (c != null) {
				return Double.parseDouble(c);
			}

		} catch (Exception e) {

		}
		return null;
	}

	public static Integer pedirEntero(String cad) {
		try {

			String c = JOptionPane.showInputDialog(cad);
			if (c != null) {
				return Integer.parseInt(c);
			}

		} catch (Exception e) {

		}
		return null;
	}

	public static int validarEntero(JSpinner text) {
		try {
			return Integer.parseInt(text.getValue().toString());
		} catch (Exception e) {
			return -1;
		}

	}

	public static int getEntero(JTextField text) {
		return Integer.parseInt(text.getText());
	}

	public static long getEnteroL(JTextField text) {
		return Long.parseLong(text.getText());
	}

	public static float getFlotante(JTextField text) {
		return Float.parseFloat(text.getText());
	}

	public static double getDouble(JTextField text) {
		return Double.parseDouble(text.getText());
	}

	public static String getCadena(JTextField text) {
		return text.getText();
	}

	public static void soloNums(JTextField... cajas) {

		for (JTextField caja : cajas) {
			caja.addKeyListener(new KeyAdapter() {

				@Override
				public void keyTyped(KeyEvent evt) {
					char c = evt.getKeyChar();

					if (!(Character.isDigit(c))) {
						evt.consume();
					}
				}

			});
		}

	}

	public static void soloDecimales(JTextField caja) {
		caja.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent evt) {
				char c = evt.getKeyChar();

				if (!(Character.isDigit(c) || c == '.')) {
					evt.consume();
				}
			}

		});

	}

	public static void soloLetras(JTextField... cajas) {
		for (JTextField caja : cajas) {
			caja.addKeyListener(new KeyAdapter() {

				@Override
				public void keyTyped(KeyEvent evt) {
					char c = evt.getKeyChar();
					if (!(Character.isLetter(c) || (c == ' '))) {
						evt.consume();
					}
				}

			});
		}

	}

	public static void soloLetrasYNums(KeyEvent evt) {
		char c = evt.getKeyChar();
		if (!(Character.isLetter(c) || (c == ' ') || Character.isDigit(c))) {
			evt.consume();
		}
	}

	public static boolean empty(JTextField text) {
		return text.getText().isEmpty();
	}

	public static void setLimiteText(int limit, JTextField... cajas) {
		for (JTextField caja : cajas) {
			caja.setDocument(new JTextFieldLimit(limit));
		}
	}

	public static void UpperAll(JTextField... cajas) {
		for (JTextField caja : cajas) {
			caja.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					caja.setText(caja.getText().toUpperCase());
				}
			});
		}
	}

	public static LocalDate getLocalDate(Date dateToConvert) {
		return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
	}

	public static Date getDate(LocalDate dateToConvert) {
		return java.sql.Date.valueOf(dateToConvert);
	}

	public static void mensajeError(String msj) {
		JOptionPane.showMessageDialog(null, msj, "Error", JOptionPane.ERROR_MESSAGE);
	}

	public static void mensajeAdvertencia(String msj) {
		JOptionPane.showMessageDialog(null, msj, "Advertencia", JOptionPane.WARNING_MESSAGE);
	}

	public static void mensaje(String msj) {
		JOptionPane.showMessageDialog(null, msj, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
	}

	public static <T extends Comparable<T>> int busquedaSecuencial(T[] arr, T dato) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].compareTo(dato) == 0) {
				return i;
			}
		}
		return -1;
	}

	public static void upperFirst(JTextField... cajas) {

		for (JTextField text : cajas) {
			text.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {

					text.setText(WordUtils.capitalizeFully(text.getText()));

				}
			});
		}

	}

}
