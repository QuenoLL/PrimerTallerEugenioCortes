package logica;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Main {
	
	// Variables con respecto al codigo...
	static String[][] mx = new String[300][4];
	static String[] usuarios = new String[3];
	static String[] contraseñas = new String[3];
	
	public static void main(String[] args) throws IOException{
		// Eugenio Cortés Egaña;
		// Rut: 22.405.687-7
		// Carrera: Ingenieria Civil en Computacion e Informatica.

		File file = new File("txt/Usuarios.txt");
		Scanner lector = new Scanner(file);
		Scanner entrada = new Scanner(System.in);

		try {
			
			int i = 0;
			while (lector.hasNextLine ()) {
				
				String linea = lector.nextLine ();
				
				String[] partes = linea.split(";");

				String claves = partes[1];
				String persona = partes[0];

				usuarios[i] = persona;
				contraseñas[i] = claves;
				i++;
			}

		} catch (Exception E) {
			System.out.println("Error. el archivo no se leyo bien...");
		}

		//Menu principal...deriva a metodos...
		int opcion = 0;
		do {
			try {
				System.out.println("1) Menu de Usuarios\n2) Menu de Analisis\n3) Salir");
				System.out.print("\n<");
				opcion = Integer.parseInt(entrada.nextLine());
				
				switch(opcion) {
				
				case 1: IngresoUsuario(entrada);
					
				}
				
			}catch(Exception e) {
				System.out.println("ERROR. Valor ingresado erroneo...");
			}
		}while(opcion < 1 || opcion > 3);
		
	}// END OF THE MAIN.

	//Funcion de ingreso y autenticacion del usuario, listas en static para poder acceder a las listas paralelas...
	public static void IngresoUsuario(Scanner entrada) {
		boolean indicador = false;
		int opcion;

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
				
				submenuUsuario(entrada, usuario);
														
				
			} else {
				System.out.println("Acceso Denegado; Usuario o Contraseña Incorrectos.");
			}
		}
		
	}
	
	//Submenu del usuario, tipo de actividad a realizar en su registro...
	public static void submenuUsuario(Scanner entrada, String usuario) {
		int opcion = 0;
		
		do {
			try {
				System.out.println("\nQue deseas realizar?");
				System.out.print("\n1) Registrar Actividad\n2) Modificar Actividad\n3) Eliminar Actividad\n4) Cambiar Contraseña\n5) Salir");
				System.out.print("\n<");
				opcion = Integer.parseInt(entrada.nextLine());
				
				switch(opcion) {
				case 1: registrarArchivo(entrada,usuario);//Llamado a la funcion para registrar actividad...
				}
				
			}catch(Exception e) {
				System.out.println("ERROR. Valor ingresado erroneo...");
			}
		}while(opcion < 1 || opcion > 5);
	}
	
	//Funcion para registrar actividad...
	public static void registrarArchivo(Scanner entrada, String usuario) {
		
		String[] partes = new String[3];
		boolean indice = false;
		int dia, mes, año, horas;
		
		//CONTROL DE ERRORES DE LAS FECHAS, EL MINIMO DEL AÑO ES 2010 SOLO PARA TENER UN RANGO....
		do {
			try {
				System.out.println("Ingrese su fecha:");
				
				//Control de errores por separado para ingreso correcto de datos...
				do {
					System.out.println("\nIngrese el numero del dia:");
					System.out.print("<");
				    dia = Integer.parseInt(entrada.nextLine());
				}while(dia < 0 || dia > 31);
				
				do {
					System.out.println("Ingrese el mes(si no tiene dos digitos agregue un 0 al principio):");
					System.out.print("<");
					mes = Integer.parseInt(entrada.nextLine());
				}while(mes < 0 || mes > 12);
				
				do {
					System.out.println("Ingrese el respectivo año:");
					System.out.print("<");
					año = Integer.parseInt(entrada.nextLine());
				}while(año > 2026 || año < 2010);
				
				String fecha = dia+"/"+mes+"/"+año;
				
				do {
					System.out.println("Ingrese su las horas de su actividad:");
					horas = Integer.parseInt(entrada.nextLine());
				}while(horas < 1 || horas > 24);
				
				
				
			}catch(Exception e) {
				System.out.println("ERROR. Valor ingresado erroneo...");
			}
		}while(!indice);
	}
	
}
