package logica;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		// Eugenio Cortés Egaña;
		// Rut: 22.405.687-7
		// Carrera: Ingenieria Civil en Computacion e Informatica.

		Scanner entrada = new Scanner(System.in);
		FileReader archU;
		FileReader archR;
		BufferedReader lector;
		BufferedReader lectorR;

		// Variables con respecto al codigo...
		String[] usuarios = new String[3];
		String[] contraseñas = new String[3];
		String[] fechasUnicas = new String[200];

		/// Lectura de Usuarios y contraseñas
		try {
			archU = new FileReader("C:\\Users\\eugen\\Desktop\\Ecplise Projects\\Usuarios.txt");

			if (archU.ready()) {
				lector = new BufferedReader(archU);
				String cadena;
				int i = 0;
				while ((cadena = lector.readLine()) != null) {
					String[] partes = cadena.split(";");

					String claves = partes[1];
					String persona = partes[0];

					usuarios[i] = persona;
					contraseñas[i] = claves;
					i++;

				}
			} else {
				System.out.println("El archvio no esta listo para ser leido....");
			}

		} catch (Exception E) {
			System.out.println("Error. el archivo no se leyo bien...");
		}

		// Separar Casos....Menu principal y paso a submenus.
		int caso;
		do {
			caso = menuPrincipal();

			switch (caso) {
			case 1:
				boolean indicador = false;

				while (!indicador) {
					System.out.print("Usuario: ");
					String usuario = entrada.nextLine();

					System.out.print("Contraseña: ");
					String contraseña = entrada.nextLine();

					for (int i = 0; i < usuarios.length; i++) {
						if (usuario.equals(usuarios[i]) && contraseña.equals(contraseñas[i])) {
							indicador = true;
						}
					}
					if (indicador == true) {
						//Primer caso.- Menu de Usuario Correcto...
						
						System.out.println("Acceso Correcto!");
						System.out.printf("\nBienvenido %s!",usuario);
						
						subMenuUsuario(entrada);
						
						
						
					} else {
						System.out.println("Acceso Denegado; Usuario o Contraseña Incorrectos.");
					}
					break;
				}
			}

		}while(caso != 0);
		
	}// END OF THE MAIN.

	// Menu Principal....
	public static int menuPrincipal() {
		Scanner entrada = new Scanner(System.in);
		int valor = 0;

		do {

			System.out.println("1) Menu de Usuarios\n2) Menu de Analisis\n3) Salir");
			try {

				valor = Integer.parseInt(entrada.nextLine());

			} catch (Exception e) {

				System.out.println("ERROR. Intente Nuevamente...");
			}

		} while (valor < 1 || valor > 3);

		return valor;

	}

	// Funcion Index sin array...
	public static int index(String a, String[] lista) {
		int indice = -1;

		for (int i = 0; i < lista.length; i++) {

			if (lista[i].equals(a) && lista[i] != null) {
				indice = i;
			}
		}
		return indice;
	}
	
	//SubMenu del Usuario...
	public static void subMenuUsuario(Scanner entrada) {
		int opcion = 0;
		do {
			System.out.println("Que deseas realizar?");
			
			
		}while(opcion < 1 || opcion > 5);
		
	}
}
