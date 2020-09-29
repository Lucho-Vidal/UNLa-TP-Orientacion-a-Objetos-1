package test;

import almacen.Cliente;
import almacen.Comercio;
import almacen.Contacto;
import almacen.Ubicacion;

public class TestValidar {
	public static void main(String[] args) {
		try {
			//Creo comercio
			Ubicacion ubicacionComercio = new Ubicacion(-34.733406, -58.393577);
			Contacto contactoComercio = new Contacto("AlmanceGGG@gmail.com", "1123234565", ubicacionComercio);
			Comercio almacen = new Comercio(1, contactoComercio, "Almacen G.G.G.", 30707661492L, 500, 15, 3, 25, 10);
			
			//Creo cliente
			Ubicacion ubicacionContacto1 = new Ubicacion(-34.761078, -58.397547);
			Contacto contactoVidal = new Contacto("luchovidal@gmail.com", "1138281421", ubicacionContacto1);
			Cliente cliente1 = new Cliente(2, contactoVidal, "Vidal", "Luciano", 35007121L, 'h');
			
			
			
			// TEST VALIDAR IDENTIFICADOR
			//Test DNI incorrecto
			System.out.println("TEST DNI INCORRECTO");
			//corto
				System.out.println("Corto: ");
				try {
					cliente1.setDni(1234L);
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				
			//largo
				System.out.println("Largo");
				try {
					cliente1.setDni(20350071215L);
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			
			System.out.println();
			System.out.println();
			//Test sexo incorrecto
			System.out.println("TEST SEXO INCORRECTO");
			try {
				cliente1.setSexo('T');
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
			System.out.println();
			System.out.println();
			//Test cuit incorrecto
			System.out.println("TEST CUIT INCORRECTO");
			//corto
				System.out.println("Corto: ");
				try {
					almacen.setCuit(35007121L);
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			
			//numero verificador alterado
				System.out.println("Numero verificador alterado: ");
				try {
					almacen.setCuit(30707661490L);
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			
			//digito alterado
				System.out.println("Digito alterado: ");
				try {
					almacen.setCuit(30707061492L);
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}