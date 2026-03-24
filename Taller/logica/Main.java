package logica;

import java.io.BufferedReader;
import java.io.FileReader;

public class Main {
	public static void main(String[] args) {
		//Eugenio Cortés Egaña;
		//Rut: 22.405.687-7
		//Carrera: Ingenieria Civil en Computacion e Informatica.
		
		FileReader archU;
		FileReader archR;
		BufferedReader lector;
		BufferedReader lectorR;
		
		//Variables con respecto al codigo...
		String[] usuarios = new String[3];
		String[] contraseñas = new String[3];
		String[] fechasUnicas = new String[200];
		
		///Lectura de Usuarios y contraseñas
		try {
			archU = new FileReader("C:\\Users\\eugen\\Desktop\\Ecplise Projects\\Usuarios.txt");
			
			if(archU.ready()) {
				lector = new BufferedReader(archU);
				String cadena;
				int i = 0;
				while((cadena = lector.readLine()) != null) {
					System.out.println(cadena);
					String[] partes= cadena.split(";");
					
					String claves = partes[1];
					String persona = partes[0];
					
				    usuarios[i] =  persona;
				    contraseñas[i] = claves;
				    i++;
					
				}
			}else {
				System.out.println("El archvio no esta listo para ser leido....");
			}
			
		}catch(Exception E) {
			System.out.println("Error. el archivo no se leyo bien...");
		}
		
		///Lectura de actividades....
		
		try {
			archR = new FileReader("C:\\Users\\eugen\\Desktop\\Ecplise Projects\\Registros.txt");
			
			if(archR.ready()) {
				lectorR = new BufferedReader(archR);
				String cadena;
				int i = 0;
				while((cadena = lectorR.readLine()) != null) {
					String[] partes = cadena.split(";");
					
					String persona = partes[0];
					String fecha = partes[1];
					String atividad = partes[2];
					
					
					
					
					
				}
			}else {
				System.out.println("El archvio no esta listo para ser leido....");
			}
			
		}catch(Exception E) {
			System.out.println("Error. el archivo no se leyo bien...");
		}
		
		
		
		
	}//END OF THE MAIN.
	public static int index(String a, String[] lista) {
		int indice = -1;
		
		for(int i = 0; i < lista.length; i++) {
			
			if(lista[i].equals(a) && lista[i] != null) {
				indice = i;
			}
		}
		return indice;
	}
}
