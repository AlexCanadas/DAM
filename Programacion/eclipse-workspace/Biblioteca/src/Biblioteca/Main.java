package Biblioteca;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		ArrayList <Personas> biblioteca = new ArrayList<>();
		ArrayList <Articulos> catalogo = new ArrayList<>();
		ArrayList <Prestamos> prestamos = new ArrayList<>();
		//Usuarios user = new Usuarios();
		Administradores admin = new Administradores("Alex", "47411148Y", 33);
		biblioteca.add(admin);
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
		System.out.println("Introduce tu DNI:");	
		String dniIntroducido = sc.next();
		
		boolean encontrado = false;
		for (Personas i : biblioteca) {
			if (i instanceof Administradores) {
				Administradores administrador = (Administradores) i;
				if (administrador.getDni().equals(dniIntroducido)) {
					System.out.println("Bienvenido administrador " + administrador.getNombre() + "\n");
					menuAdmin(sc, biblioteca, catalogo);
					encontrado = true;
					break;
				}
			}
			else if (i instanceof Usuarios) {
				Usuarios usuario = (Usuarios) i;
				if (usuario.getDni().equals(dniIntroducido)) {
					System.out.println("Bienvenido usuario " + usuario.getNombre() + "\n");
					menuUsuario(sc, biblioteca, prestamos, catalogo);
					encontrado = true;
					break;
				}
			}
		}
		if (!encontrado) {
		    System.out.println("Lo siento, no está registrado en el sistema, contacte con un administrador\n");
		}
	}

	public static void menuAdmin(Scanner sc, ArrayList <Personas> biblioteca, ArrayList <Articulos> catalogo) {
		int opcionAdmin;

		do {
		System.out.println("----- Menú Administradores -----");
		System.out.println("1. Dar de alta a un usuario para poder hacer los préstamos"); //Ok
		System.out.println("2. Penalizar a un usuario por no haber devuelto en fecha");
		System.out.println("3. Dar de alta un artículo"); //OK
		System.out.println("4. Dar de baja artículos"); // OK
		System.out.println("5. Ver los préstamos de un cliente");
		System.out.println("6. Acceder al menú de usuarios");
		System.out.println("7. Salir");
		opcionAdmin=sc.nextInt();
		
		switch(opcionAdmin) {
		case 1: 
			System.out.println("Introduce el DNI del nuevo usuario:");
			String dniIntroducido = sc.next();
			boolean usuarioExiste = false;
			
			for (Personas i : biblioteca) {
				if (i instanceof Usuarios) {
					if (dniIntroducido.equals(i.getDni())) {
						usuarioExiste = true;
						System.out.println("Este usuario ya existe en la base de datos");
						break;
					}
				}
			}
			 if (!usuarioExiste) {
			        Usuarios usuario = new Usuarios();
			        Personas nuevaPersonaARegistrar = usuario.altaUsuario(sc, dniIntroducido);
			        biblioteca.add(nuevaPersonaARegistrar);
			    }
			    break;
			    
		case 2: 
			
			break;
			
		case 3: 
			System.out.println("Introduce que tipo de artículo quieres dar de alta:");
			System.out.println("L = Libro, R = Revista, P = Película");
			char articulo = Character.toUpperCase(sc.next().charAt(0));
				switch(articulo) {
				case 'L': 
					Libro libro = new Libro();
					Articulos nuevoArticuloLibro = libro.darDeAlta(sc);
					catalogo.add(nuevoArticuloLibro);
					Articulos ultimoArticuloLibro = catalogo.get(catalogo.size() - 1);
		            System.out.println("Detalles del último artículo agregado:");
		            ultimoArticuloLibro.mostrarDetalles();
					break;
				case 'R': 
					Revista revista = new Revista();
					Articulos nuevoArticuloRevista = revista.darDeAlta(sc);
					catalogo.add(nuevoArticuloRevista);
					Articulos ultimoArticuloRevista = catalogo.get(catalogo.size() - 1);
		            System.out.println("Detalles del último artículo agregado:");
		            ultimoArticuloRevista.mostrarDetalles();
					break;
				case 'P': 
					Pelicula pelicula = new Pelicula();
					Articulos nuevoArticuloPelicula = pelicula.darDeAlta(sc);
					catalogo.add(nuevoArticuloPelicula);
					Articulos ultimoArticuloPelicula = catalogo.get(catalogo.size() - 1);
		            System.out.println("Detalles del último artículo agregado:");
		            ultimoArticuloPelicula.mostrarDetalles();
					break;
				}
			break;
		case 4: 
			menuBaja(sc);
			break;
		case 5: 
			break;
		case 6: 
			//ArrayList <Prestamos> prestamos = new ArrayList<>();
			menuUsuario(sc, biblioteca, prestamos, catalogo);
		break;
		case 7: System.out.println("Que tengas un buen día");
			break;
		default: System.out.println("Opción no válida \n");
        	break;
		}
		
		}while (opcionAdmin!=6);
		sc.close();
	}
	
	public static void menuUsuario(Scanner sc, ArrayList <Personas> biblioteca, ArrayList <Prestamos> prestamos, 
			ArrayList <Articulos> catalogo) {
		int opcionUsuario;
		System.out.println("Por favor, introduce tu DNI: ");
		String dniIntroducido = sc.next();
		for (Personas usuarios : biblioteca) {
			if (usuarios instanceof Usuarios) {
				Usuarios usuario = new Usuarios();
				if (usuario.dni.equals(dniIntroducido)) {
					Prestamos prestamo = new Prestamos();
					prestamo.hacerPrestamo(sc, dniIntroducido);
				}
			}	
		}
		
		do {
		System.out.println("----- Menú Usuarios-----");
		System.out.println("1. Hacer un préstamo o varios a la vez.");
		System.out.println("2. Hacer una devolución o varias a la vez.");
		System.out.println("3. Ver sus préstamos en activo para ver los días que le quedan para la devolución.");
		System.out.println("4. Salir");
		opcionUsuario=sc.nextInt();
		
		switch(opcionUsuario) {
		case 1: 
			Prestamos nuevoPrestamo_ = new Prestamos();
			nuevoPrestamo_.hacerPrestamo(sc, dniIntroducido);
			prestamos.add(nuevoPrestamo_);
			break;
		case 2:
			Prestamos prestamoBaja = new Prestamos();
			prestamoBaja.hacerDevolucion(sc, prestamos);
			break;
		case 3:
			for (Prestamos verPrestamos : prestamos) {
					// Mostrar prestamos en activo y dias para la devolucion
					if (verPrestamos.getDniUsuario().equals(dniIntroducido)) {
						verPrestamos.toString(verPrestamos);
					}
			}
			break;
		case 4:
			System.out.println("Que tengas un buen día");
			break;
		default: System.out.println("Opción no válida \n");
        	break;
		}
		
		}while (opcionUsuario!=6);
		sc.close();
	}
	
	protected static void menuBaja(Scanner sc) {
		ArrayList <Articulos> catalogo = new ArrayList<>();
		System.out.println("Selecciona que tipo de artículo quieres dar de baja: ");
		System.out.println("L = Libro, R = Revista, P = Película");
		char articulo =  Character.toUpperCase(sc.next().charAt(0));
		switch(articulo) {
		case 'L':
			Libro libro = new Libro();
			libro.darDeBaja(sc, catalogo);
			break;
		case 'R':
			Revista revista = new Revista();
			revista.darDeBaja(sc, catalogo);
			break;
		case 'P': 
			Pelicula pelicula = new Pelicula();
			pelicula.darDeBaja(sc, catalogo);
			break;
		}
	}
}
