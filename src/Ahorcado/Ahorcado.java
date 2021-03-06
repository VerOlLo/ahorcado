package Ahorcado;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Ahorcado {
	private JTextField jTextField;
	private JLabel jLabel2;
	private int errores = 0;
	private boolean juegoActivo = false;
	private boolean intentos = false;
	private char[] pElegida;
	private char[] palabra;
	//private String fecha;
	private String palabraJ2;
	private String nombreJ1;

	public Ahorcado(JTextField jTextField, JLabel jLabel2) {
		this.jTextField = jTextField;
		this.jLabel2 = jLabel2;

		// Pide al jugador 2 que introduzca la palabra que tendr� que ser adivinada
		palabraJ2 = JOptionPane.showInputDialog(null, "Jugador 2 - Introduzca la palabra:");
		pElegida = palabraJ2.toUpperCase().toCharArray(); // Por si el J2 introduca la palabra en min�sculas, la paso a
															// may�sculas
		System.out.println(pElegida); // Imprimo por consola para mi control la palabra
		nombreJ1 = JOptionPane.showInputDialog(null, "Jugador 1 - Introduzca su nombre"); // Solicito al Jugador 1 su
																							// nombre
		System.out.println(Arrays.toString(pElegida));
		String relleno = "";
		// Corresponde a la longitud de la palabra en " _ "
		for (int i = 0; i <= pElegida.length - 1; i++) {
			relleno = relleno + " _ ";
		}

		palabra = relleno.toCharArray(); // Pasa a array
		System.out.println(palabra);

		jTextField.setText(relleno);
		jLabel2.setIcon(new ImageIcon(getClass().getResource("/imagenes/ahorcado_0.png")));
		this.juegoActivo = true;

	}

	public void analizar(char l) {
		if (juegoActivo) {  //Si el juego est� activo, por lo que el bot�n comenzar ha sido pulsado
			String sLetra = "";  

			if (errores == 5) {
				JOptionPane.showMessageDialog(null, "Perdiste");
				jLabel2.setIcon(new ImageIcon(getClass().getResource("/imagenes/ahorcado_6.png")));
				//Date hoy = new Date(); 
				//DateFormat fechaH = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy"); 
				//fecha = fechaH.format(hoy); 
			} else {

				for (int i = 0; i <= pElegida.length - 1; i++) {

					if (pElegida[i] == l) {
						System.out.println(pElegida[i]+"- Letra acertada");
						palabra[i] = l;
						intentos = true;
					}
					sLetra = sLetra + this.palabra[i];
				}

				if (intentos == false) {
					errores += 1;
					jLabel2.setIcon(
					new ImageIcon(getClass().getResource("/imagenes/ahorcado_" + errores + ".png")));
					
					if (this.errores < 6) {
						JOptionPane.showMessageDialog(null, (6 - errores) + " intentos restantes","Pruebe otra letra:",JOptionPane.WARNING_MESSAGE);
					}
				} else {
					this.intentos = false;
				}
				this.jTextField.setText(sLetra);
				ganar();
			}
		}
	}

	private void ganar() {
		boolean ganador = false;
		for (int i = 0; i <= this.pElegida.length - 1; i++) {
			if (this.palabra[i] == this.pElegida[i]) {
				ganador = true;

			} else {
				ganador = false;
				break;
			}
		}
		if (ganador) {
			JOptionPane.showMessageDialog(null, "�Ganaste!","�Enhorabuena!",JOptionPane.INFORMATION_MESSAGE);
			Gpuntuaciones();

		}
	}

	@Override
	public String toString() {
		return "   Errores= "+errores+ ", Palabra= " + palabraJ2 + ", Nombre del jugador= " + nombreJ1 + "\n";

	}

	private void Gpuntuaciones() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("Ganadores.txt", true));
			writer.write(toString());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}