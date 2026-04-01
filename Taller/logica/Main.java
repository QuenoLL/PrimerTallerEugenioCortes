package logica;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Main {
	
	//Listas en Static para acceder en ellas en todo momento...
	static String[] mx = new String[300];
	static String[] usuarios = new String[3];
	static String[] contraseñas = new String[3];
	static String[] usuariosIndividualesActividad = new String[300];
	static String[] actividadesIndividuales = new String[300];
	static int[] conteoIndividual = new int[300];
	static int totalIndividual = 0;
	static String[] actividades = new String[300];
	static int[] conteo = new int[300];
	static int totalActividades = 0;
	
	public static void main(String[] args) throws IOException{
		//Nombre: Eugenio Román Cortés Egaña 
		//Rut: 22.405.687-7
		//Carrera: Ingenieria Civil en Computacion Informatica.
		
		//Variables y Lectura de Usuario....
		
		File file = new File("txt/Usuarios.txt");
		Scanner lector = new Scanner(file);
		Scanner entrada = new Scanner(System.in);

		/// Lectura de Usuarios y contraseñas(lo hago al principio y no en funcion por orden)
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
		boolean indicador = false;
		
		//Lo encapsula un while, para que al volver a este menu en el main me permita contestar de nuevo y que no se corte.
		while(!indicador) {
			do {
				try {
					System.out.println("\n1) Menu de Usuarios\n2) Menu de Analisis\n3) Salir");
					System.out.print("\n<");
					opcion = Integer.parseInt(entrada.nextLine());
					
					//Menus y salida.
					switch(opcion) {
					
					case 1: IngresoUsuario(entrada);
					        break;
					case 2: menuAnalisis(entrada);
					        break;
					case 3: System.out.println("\nHasta pronto!!!");
					        indicador = true;
					        break;
					}
					
				}catch(Exception e) {
					System.out.println("ERROR. Valor ingresado erroneo..."+e.getLocalizedMessage());
				}
			}while(opcion < 1 || opcion > 3 && !indicador);
		}
		lector.close();
		
	}//END OF THE MAIN..................................................
	
	//Funcion de ingreso de Usuario.
	public static void IngresoUsuario(Scanner entrada) {
		boolean indicador = false;
		
		while (!indicador) {
			
			System.out.print("\nUsuario: ");					
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
				
				System.out.println("\nAcceso Correcto!");
				System.out.printf("\nBienvenido %s!",usuario);
				
				submenuUsuario(entrada, usuario);
														
				
			} else {
				System.out.println("Acceso Denegado; Usuario o Contraseña Incorrectos.");
			}
		}
		
	}
	
	//Funcion subMenu del usuario.
	public static void submenuUsuario(Scanner entrada, String usuario) {
		int opcion = 0;
		
		do {
			try {
				System.out.println("\nQue deseas realizar?");
				System.out.print("\n1) Registrar Actividad\n2) Modificar Actividad\n3) Eliminar Actividad\n4) Cambiar Contraseña\n5) Salir");
				System.out.print("\n<");
				opcion = Integer.parseInt(entrada.nextLine());
				
				switch(opcion) {
				case 1: registrarArchivo(entrada,usuario);
				        break;
				case 2: modificarActividad(usuario,entrada);
				        break;
				case 3: eliminarActividad(entrada,usuario);
				        break;
				case 4: cambiarContraseña(entrada,usuario);
				        break;
				case 5: return;
				}
				
			}catch(Exception e) {
				System.out.println("ERROR. Valor ingresado erroneo...");
			}
		}while(opcion < 1 || opcion > 5);
	}
	
	//Funcion de registrar actividad.
	public static void registrarArchivo(Scanner entrada, String usuario) {
		boolean indice = false;
		int dia, mes, año, horas;
		
		//CONTROL DE ERRORES DE LAS FECHAS, EL MINIMO DEL AÑO ES 2010 SOLO PARA TENER UN RANGO....
		do {
			try {
				System.out.println("---Ingrese su fecha---");
				
				do {
					System.out.println("\nIngrese el numero del dia:");
					System.out.print("<");
				    dia = Integer.parseInt(entrada.nextLine());
				}while(dia < 0 || dia > 31);
				String diaReal = String.valueOf(dia);
				if(diaReal.length() == 1) {
					diaReal = "0"+dia;
				}
				
				do {
					System.out.println("Ingrese el mes:");
					System.out.print("<");
					mes = Integer.parseInt(entrada.nextLine());
				}while(mes < 0 || mes > 12);
				
				String mesReal = String.valueOf(mes);
				if(mesReal.length() == 1) {
					mesReal = "0"+mes;
				}
				
				do {
					System.out.println("Ingrese el respectivo año:");
					System.out.print("<");
					año = Integer.parseInt(entrada.nextLine());
				}while(año > 2026 || año < 2010);
				
				String fecha = diaReal+"/"+mesReal+"/"+año;
				
				do {
					System.out.println("Ingrese las horas de su actividad:");
					horas = Integer.parseInt(entrada.nextLine());
				}while(horas < 1 || horas > 24);
				
				System.out.printf("Ingrese la actividad que realizo el %s :\n",fecha);
				System.out.print("<");
				String actividad = entrada.nextLine();
				
				String linea = usuario+";"+fecha+";"+horas+";"+actividad;
				System.out.println("\nActividad a agregar: "+linea);
				
				//Llamado a funcion que agrega la linea en concreto(solo pra ser mas pulcro).
				agregarArchivo(linea,entrada,usuario);
				indice = true;
				
			}catch(Exception e) {
				System.out.println("ERROR. Valor ingresado erroneo...");
			}
		}while(!indice);
		submenuUsuario(entrada, usuario);
	}
	
	//Funcion de registro de actividad...
	public static void agregarArchivo(String texto,Scanner entrada,String usuario) {
		String ruta = "txt/Registros.txt";//Ingreso como ruta mi archivo en la carpeta en el project java
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta,true))){
			//Ocupamos Bufferedw y Filew, con true para agregar al mismo archivo.txt la linea que deseamos(modo append)...
			bw.newLine();
			bw.write(texto);
			System.out.println("Archivo agregado con exito...");
			
		}catch(IOException e) {
			
			System.out.println("No se pudo agregar la lista correctamente..."+e.getMessage());
			
		}
	}
	
	//Funcion de modificacion de actividad en concreto...#
	public static void modificarActividad(String usuario,Scanner entrada) throws IOException{
		
		//Llamo a la funcion de llenado del arreglo.
		agregarItemsLista();
		
		System.out.println("\nCual actividad deseas modificar?");
		int opcion = 0;
		int contador = 1;
		int[] indices = new int[mx.length];
		//Tomo una lista indices, de manera que sea paralela a la opcion creciente que ingrese el usuario.
		
		System.out.println("\n0) Regresar");
		for(int i = 0; i < mx.length; i++) {
			if(mx[i] == null) {
				break;
			}
			String[] partes = mx[i].split(";");
			
			if(partes[0].equals(usuario)) {
				System.out.printf("%d) "+mx[i],contador);
				System.out.println();
				indices[contador] = i;
				contador++;
			}			
		}
		
		System.out.print("\n<");
		//Ingrese la opcion de la actividad a modificar...
		try {
			do {
				opcion = Integer.parseInt(entrada.nextLine());
				
			}while(opcion >= contador || opcion < 0);
		}catch(Exception e) {
			System.out.println("ERROR. el valor ingreso no es correcto...");
		}
		
		if(opcion == 0) {
			submenuUsuario(entrada, usuario);
		}else {
			int indiceReal = indices[opcion];
			actividadModificada(indiceReal, entrada,usuario);
		}
	}
	
	//Funcion para llenar matriz antes de modificarla...
	public static void agregarItemsLista() throws IOException{
		//En vez de una matriz tomo una lista con 300 elementos maximos, de manera que cada linea del texto es una posicion.
		File arch = new File("txt/Registros.txt");
		Scanner lector = new Scanner(arch);
		
		try {
			int fila = 0;
			while(lector.hasNextLine()) {
				String linea = lector.nextLine();
				
				mx[fila] = linea;
				fila++;
				
			}
		}catch(Exception e) {
			System.out.println("ERROR. No se pudo llenar correctamente el arreglo...");
		}
		lector.close();
	}
	
	//Modificacion de la actividad en concreto (mejor dicho la linea).
	public static void actividadModificada(int indice, Scanner entrada, String usuario) throws IOException{
		int eleccion = 0, dia = 0, mes = 0, año = 0, horas = 0;
		String actividad = null;
		
		System.out.println("¿Que deseas modificar?");
		
		System.out.println("\n0) Regresar");
		System.out.println("1) Fecha");
		System.out.println("2) Duracion");
		System.out.println("3) Actividad");
		System.out.print("<");
		
		try {
			do {
				eleccion = Integer.parseInt(entrada.nextLine());
			}while(eleccion < 0 || eleccion > 3);
		}catch(Exception e) {
			System.out.println("ERROR. Eleccion incorrecta "+e.getMessage());
		}
		//Separo las partes para cambiar lo especifico de la linea...
		String[] partes = mx[indice].split(";");
		
		
		switch(eleccion) {
		case 0: submenuUsuario(entrada, usuario);
		        break;
		case 1: try {
			       do{
			    	   System.out.println("Ingrese el dia:");
			    	   System.out.print("<");
			    	   dia = Integer.parseInt(entrada.nextLine());
			       }while(dia < 0 || dia > 31);
			       String diaReal = String.valueOf(dia);
			       if(diaReal.length() == 1) {
			    	   diaReal = "0"+dia;
			       }
			       
			       do {
			    	   System.out.println("Ingrese el mes:");
			    	   System.out.print("<");
			    	   mes = Integer.parseInt(entrada.nextLine());
			       }while(mes < 1 || mes > 12);
			       
			       String mesReal = String.valueOf(mes);
					if(mesReal.length() == 1) {
						mesReal = "0"+mes;
					}
					
					do {
						System.out.println("Ingrese el respectivo año:");
						System.out.print("<");
						año = Integer.parseInt(entrada.nextLine());
					}while(año > 2026 || año < 2010);
					
					String fecha = diaReal+"/"+mesReal+"/"+año;
					
					//Lo que hago es unir con join las partes del arreglo ya modificadas(Me las une como string)...
					partes[1] = fecha;
					mx[indice] = String.join(";", partes);
					System.out.println(mx[indice]);
			       
		        }catch(Exception e){
		        	System.out.println("ERROR. Ingreso erroneo de datos...");
		        }
		        break;
		        
		case 2: try {
			       do {
			    	   System.out.println("Ingrese las horas nuevas:");
			    	   System.out.print("<");
			    	   horas = Integer.parseInt(entrada.nextLine());
			       }while(horas < 0 || horas > 24);
		        }catch(Exception e) {
		        	System.out.println("ERROR. Valor ingresado no valido "+e.getMessage());
		        }
		        partes[2] = String.valueOf(horas);
		        mx[indice] = String.join(";", partes);
		        System.out.println(mx[indice]);
		        break;
		        
		case 3: try{
			      System.out.println("Ingrese nuevo tipo de actividad:");
			      System.out.print("<");
			      actividad = entrada.nextLine();
		        }catch(Exception e) {
		        	System.out.println("ERROR. valor ingresado erroneo"+e.getMessage());
		        }
		        partes[3] = actividad;
		        mx[indice] = String.join(";", partes);
		        System.out.println(mx[indice]);
		        break;
		}
		
		//Sobrescribo el archivo de tal manera que no me quede un salto en linea al final gracias a total y actual.
		String ruta = "txt/Registros.txt";
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))){
			int total = 0;
			for(String i: mx) {
				if(i != null) {
					total++;
				}
			}
			int actual = 0;
			for(String i : mx) {
				if(i != null) {
					actual++;
					bw.write(i);
					if(actual < total) {
						bw.newLine();
					}
					
				}
			}
		}catch(Exception e) {
			System.out.println("ERROR. Ingreso a la lista fallido "+e.getMessage());
		}
		System.out.println("Archivo modificado correctamente...");
		submenuUsuario(entrada, usuario);
	}
	
	//Metodo Eliminar actividad.
	public static void eliminarActividad(Scanner entrada, String usuario) throws IOException{
		agregarItemsLista();
		int opcion = 0;
		int contador = 1;
		int[] indices = new int[mx.length];
		System.out.println("¿Cual actividad desea eliminar?");
		
		System.out.println("\n0) Regresar");
		for(int i = 0; i < mx.length; i++) {
			if(mx[i] == null) {
				break;
			}
			String[] partes = mx[i].split(";");
			
			if(partes[0].equals(usuario)) {
				System.out.printf("%d) "+mx[i],contador);
				System.out.println();
				indices[contador] = i;
				contador++;
				
			}
		}
		System.out.print("\n<");
		try {
			do {
				opcion = Integer.parseInt(entrada.nextLine());	
			}while(opcion < 0 || opcion >= contador);
		}catch(Exception e) {
			System.out.println("ERROR. Valor introducido erroneo "+e.getMessage());
		}
		
		if(opcion == 0) {
			submenuUsuario(entrada, usuario);
		}
		//La linea a elimar sera null, de modo que sobrescribo el archivo saltandome los null
		mx[indices[opcion]] = null;
		
		String ruta = "txt/Registros.txt";
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))){
			int total = 0;
			
			for(String j: mx) {
				if(j != null) {
					total++;
				}
			}
			
			int actual = 0;
			for(String i: mx) {
				if(i != null) {
					actual++;
					bw.write(i);
					if(actual < total) {
						bw.newLine();
					}
				}
				
			}
		}catch(Exception e) {
			System.out.println("ERROR. Ingreso a la lista fallido "+e.getMessage());
		}
		System.out.println("Archivo eliminado correctamente...");
		submenuUsuario(entrada, usuario);
		
		
		
	}
	
	//Metodo cambiar contraseñas.
	public static void cambiarContraseña(Scanner entrada, String usuario) throws IOException{
		System.out.println("---Cambio de contraseña---");
		
		//Cambio la contraseña de la lista del principio donde estab contenida.
		for(int i = 0; i < usuarios.length; i++) {
			if(usuario.equals(usuarios[i])) {
				System.out.printf("\nSu contraseña actual es %s",contraseñas[i]);
				
				System.out.println("\nIngrese su nueva contraseña:");
				
				do {
					System.out.print("<");
					contraseñas[i] = entrada.nextLine();
				}while(contraseñas[i].equals(""));
				
				guardarContraseña();
				System.out.println("Cambio exitoso de contraseña.");
				
				submenuUsuario(entrada, usuario);
			}
		}
		
	}
	
	//Guardado de la contrseña en txt
	public static void guardarContraseña() throws IOException{
		String ruta = "txt/Usuarios.txt";
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
			int total = usuarios.length;
			int actual = 0;
			
			for(int i = 0; i < usuarios.length; i++) {
				actual++;
				bw.write(usuarios[i]+";"+contraseñas[i]);
				
				if(actual < total) {
					bw.newLine();
				}
			}
		}catch(Exception e) {
			System.out.println("ERROR. No se pudo concretar el ingreso de datos.");
		}
	}
	
	//Menu Analisis con switch.
	public static void menuAnalisis(Scanner entrada) {
		int opcion = 0;
		
		System.out.println("\nBienvenido al menu de analisis!");
		System.out.println("\n¿Que deseas realizar?");
		
		System.out.println("\n1) Actividad mas realizada\n2) Actividad más realizada por cada usuario\n3) Usuario con mayor procastinacion\n4) Ver todas las actividades\n5) Salir");
		
		try {
			do {
				System.out.print("<");
				opcion = Integer.parseInt(entrada.nextLine());
			}while(opcion < 1 || opcion > 5);
		}catch(Exception e) {
			System.out.println("ERROR. Valor ingresado erroneo.."+e.getMessage());
		}
		
		switch(opcion) {
		case 1: actividadMasRealizada(entrada);
		        break;
		case 2: actividadPorUsuario(entrada);
		        break;
		case 3: usuarioMayorProcastinacion(entrada);
		        break;
		case 4: imprimirActividades(entrada);
		        break;
		case 5: return;
		        
		}
	}
	
	//Actividad mas realizada por archivo txt
	public static void actividadMasRealizada(Scanner entrada) {
		try {
			llenadoArregloActividad();//Llamo al metodo que me llena las listas de actividades.
		}catch(Exception e) {
			System.out.println("ERROR....");
		}
		
		//Imprimo con respecto a un ciclo que con un mayor me da la actividad mas hecha.
		int mayor = Integer.MIN_VALUE;
		String mayorNom = null;
		for(int i = 0; i < actividades.length; i++) {
			
			if(conteo[i] != 0) {
				if(conteo[i] > mayor) {
					mayor = conteo[i];
					mayorNom = actividades[i];
				}
			}
		}
		
		System.out.printf("\nActividad mas realizada -> %s con %d veces!!!\n",mayorNom,mayor);
		menuAnalisis(entrada);
		
	}
	
	//Llenado arreglo actividad
	public static void llenadoArregloActividad() throws FileNotFoundException{
		File file = new File("txt/Registros.txt");
		Scanner lector = new Scanner(file);
		//Ocupo listas en static para llamarlas, las cuales tienen espacio para 300 actividades distintas.
		
		while(lector.hasNextLine()) {
			String linea = lector.nextLine();
			String[] partes = linea.split(";");
			
			String actividad = partes[3];
			boolean encontrada = false;
			
			for(int i = 0; i < totalActividades; i++) {
				if(actividades[i] != null && actividades[i].equalsIgnoreCase(actividad)) {//veo que no es una posicion null y que la actividad concuerde si es que existe.
					conteo[i]++;
					encontrada = true;
					break;
				}
			}
			
			//Si no existe la actividad en la lista se agrega.
			if(!encontrada) {
				actividades[totalActividades] = actividad;
				conteo[totalActividades] = 1;
				totalActividades++;
			}
		}
		lector.close();
		
	}
	
	//Actividades mas realizadas por usuario.
	public static void actividadPorUsuario(Scanner entrada) {
		System.out.println("\nActividades mas realizadas por cada usuario:\n");
		try {
			llenadoActividadUsuario();//Lleno la lista con su respectivo try.
		}catch(Exception e) {
			System.out.println("ERROR. Llenado de lista fallido..."+e.getMessage());
		}
		
		for(int i = 0; i < usuarios.length; i++) {//Imprimo en funcion de los 3 usuarios existentes.
			int max = Integer.MIN_VALUE;
			String actividadMax = null;
			
			for(int j = 0; j < totalIndividual; j++) {
				if(usuarios[i].equalsIgnoreCase(usuariosIndividualesActividad[j]) && conteoIndividual[j] > max) {
					max = conteoIndividual[j];
					actividadMax = actividadesIndividuales[j];
				}
			}
			
			System.out.printf("%s -> %s -> con %d horas registradas\n",usuarios[i],actividadMax,max);
		}
		menuAnalisis(entrada);
	}
	
	//Llenado actividad usuario individuales.
	public static void llenadoActividadUsuario() throws FileNotFoundException{
		File file = new File("txt/Registros.txt");
		Scanner lector = new Scanner(file);
		
		while(lector.hasNextLine()) {
			String linea = lector.nextLine();
			
			String[] partes = linea.split(";");
			String user = partes[0];
			int horas = Integer.parseInt(partes[2]);
			String actividad = partes[3];
			
			boolean encontrado = false;
			
			//total individual me da la oportunidad de contar cuantas actividades hay en la lista.
			for(int i = 0; i < totalIndividual; i++) {
				if(usuariosIndividualesActividad[i] != null && actividadesIndividuales[i] != null) {
					if(user.equals(usuariosIndividualesActividad[i]) && actividad.equalsIgnoreCase(actividadesIndividuales[i])) {
						conteoIndividual[i] += horas;//Sumo las horas pertinentes a la actividad.
						encontrado = true;
						break;
					}
				}
			}
			//Si es que la actividad no esta se agrega, en funcion del numero de actividades totales que es mi indice.
			if(!encontrado) {
				usuariosIndividualesActividad[totalIndividual] = user;
				actividadesIndividuales[totalIndividual] = actividad;
				conteoIndividual[totalIndividual] = horas;
				totalIndividual++;
			}

		}
		
		lector.close();
		
	}
	
	//Usuario con mas horas registradas
	public static void usuarioMayorProcastinacion(Scanner entrada) {
		try {
			File file = new File("txt/Registros.txt");
			Scanner lector = new Scanner(file);
			
			//Creo lista que me guarda las horas de procastinacion de cada usuario, de modo que es acorde al largo de la lista de usuarios
			int[] horasProcastinacion = new int[usuarios.length];
			
			while(lector.hasNextLine()) {
				String linea = lector.nextLine();
				
				String[] partes = linea.split(";");
				
				String user = partes[0];
				int horas = Integer.parseInt(partes[2]);
				
				for(int i = 0; i < usuarios.length; i++) {
					if(usuarios[i].equalsIgnoreCase(user)) {
						horasProcastinacion[i] += horas;//Sumo las horas en la posicion adecuada de cada persona
					}
				}
			}
			lector.close();
			
			int mayor = Integer.MIN_VALUE;
			String mayorNombre = null;
			
			//Imprimo el mayor.
			for(int i = 0; i < usuarios.length; i++) {
				if(horasProcastinacion[i] > mayor) {
					mayor = horasProcastinacion[i];
					mayorNombre = usuarios[i];
				}
			}
			
			System.out.printf("\nEl usuario que mas procastina de todos es %s con %d horas en total!!!\n",mayorNombre,mayor);
			menuAnalisis(entrada);
			
		}catch(Exception e) {
			System.out.println("ERROR. Archivo No encontrado");
		}
			
		
	}
	
	//Actividades registradas.
	public static void imprimirActividades(Scanner entrada) {
		//Printeo normal de cada una de las lineas del archivo txt.
		try {
			File file = new File("txt/Registros.txt");
			Scanner lector = new Scanner(file);
			
			System.out.println("\n----ACTIVIDADES REGISTRADAS----\n");
			
			while(lector.hasNextLine()) {
				String linea = lector.nextLine();
				System.out.println(linea);
			}
			
			lector.close();
			menuAnalisis(entrada);
			
		}catch(Exception e) {
			System.out.println("ERROR. Archivo no se pudo abrir correctamente.");
		}
	}

}
