package test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import almacen.Articulo;
import almacen.Carrito;
import almacen.Cliente;
import almacen.Comercio;
import almacen.Contacto;
import almacen.DiaRetiro;
import almacen.Entrega;
import almacen.Envio;
import almacen.RetiroLocal;
import almacen.Ubicacion;

public class TestAlmacen {
	public static void main(String[] args) {

		LocalDate fecha = LocalDate.of(2020, 9, 29);// fecha dia martes utilizada en diferentes partes del test
		LocalTime horaDesde = LocalTime.of(10, 00);
		LocalTime horaHasta = LocalTime.of(17, 00);

		// creamos un Almacen y lo llamamos Almacen GGG
		Comercio almacen = new Comercio(1,
				new Contacto("AlmanceGGG@gmail.com", "1123234565", new Ubicacion(-34.733406, -58.393577)),
				"Almacen G.G.G.", 30707661492L, 500, 15, 3, 25, 10);

		// Le asignamos los dias de entrega
		List<DiaRetiro> lstDiaRetiro = new ArrayList<DiaRetiro>();

		for (int i = 1; i < 7; i++) {// asigno horario de entrega lunes a Sabados de 10 a 17hs intervalo 20 minutos
			DiaRetiro diaRetiro = new DiaRetiro(i, i, horaDesde, horaHasta, 20);
			lstDiaRetiro.add(diaRetiro);
		}
		almacen.setLstDiaRetiro(lstDiaRetiro);

		// Agrego 4 clientes
		Ubicacion ubicacionContacto1 = new Ubicacion(-34.761078, -58.397547);
		Contacto contactoVidal = new Contacto("luchovidal@gmail.com", "1138281421", ubicacionContacto1);

		Ubicacion ubicacionContacto2 = new Ubicacion(-34.797726, -58.385312);
		Contacto contactoJerochim = new Contacto("emijerochim@gmail.com", "1156459914", ubicacionContacto2);

		Ubicacion ubicacionContacto3 = new Ubicacion(-34.665414, -58.368515);
		Contacto contactoSilvestri = new Contacto("matysilvestri@gmail.com", "1165357821", ubicacionContacto3);

		Ubicacion ubicacionContacto4 = new Ubicacion(-34.764091, -58.390095);
		Contacto contactoLopez = new Contacto("eugelopez@gmail.com", "1125957841", ubicacionContacto4);

		try {
			Cliente cliente1 = new Cliente(2, contactoVidal, "Vidal", "Luciano", 35007121L, 'H');
			Cliente cliente2 = new Cliente(3, contactoJerochim, "Jerochim", "Emiliano", 40500720L, 'h');
			Cliente cliente3 = new Cliente(4, contactoSilvestri, "Silvestri", "Matias", 41926641L, 'h');
			Cliente cliente4 = new Cliente(5, contactoLopez, "Lopez", "Eugenia", 43450121L, 'm');

			// Agrego algunos articulos a la lista de Comercio
			almacen.addLstArticulo(new Articulo(1, "Leche", "7791234567890", 89.99));
			almacen.addLstArticulo(new Articulo(2, "Cerveza", "7792345678901", 24.99));
			almacen.addLstArticulo(new Articulo(3, "Tomate", "7793456789012", 149.99));
			almacen.addLstArticulo(new Articulo(4, "Queso", "7794567890123", 870.00));
			almacen.addLstArticulo(new Articulo(5, "Huevos", "7795678901234", 250.00));

			// Creo un Carrito con cliente1
			Carrito carrito = new Carrito(1, fecha, LocalTime.of(12, 0), false, 5, cliente1);// faltan los articulos y
																								// la entrega y los
																								// descuentos
			// Creo un Carrito2 con cliente2
			Carrito carrito2 = new Carrito(2, fecha, LocalTime.of(13, 0), false, 5, cliente2);// idem

			// agrego items a la lista de carrito
			carrito.agregarItem(almacen.getLstArticulo().get(3), 3);
			carrito.agregarItem(almacen.getLstArticulo().get(3), 2);
			carrito.agregarItem(almacen.getLstArticulo().get(1), 4);
			carrito.agregarItem(almacen.getLstArticulo().get(3), 4);
			carrito.agregarItem(almacen.getLstArticulo().get(0), 8);
			carrito.agregarItem(almacen.getLstArticulo().get(2), 6);

			// agrego items a la lista de carrito2
			carrito2.agregarItem(almacen.getLstArticulo().get(3), 1);
			carrito2.agregarItem(almacen.getLstArticulo().get(3), 3);
			carrito2.agregarItem(almacen.getLstArticulo().get(1), 9);
			carrito2.agregarItem(almacen.getLstArticulo().get(3), 4);
			carrito2.agregarItem(almacen.getLstArticulo().get(0), 8);
			carrito2.agregarItem(almacen.getLstArticulo().get(2), 6);

			// Saco algunos items del carrito2
			carrito2.sacarItem(almacen.getLstArticulo().get(1), 4);
			carrito2.sacarItem(almacen.getLstArticulo().get(0), 8);

			// creo la entrega por retiroLocal con la primer hora disponible de la fecha
			Entrega entrega = new RetiroLocal(1, fecha, true, almacen.traerHoraRetiro(fecha));

			// creo la entrega por Envio
			Entrega entrega2 = new Envio(2, fecha, false, horaDesde, horaHasta, almacen.getContacto().getUbicacion(),
					almacen.getCostoFijo(), almacen.getCostoPorKm(), cliente2.getContacto().getUbicacion());

			// agrego la entrega a los carritos
			carrito.setEntrega(entrega);
			carrito2.setEntrega(entrega2);

			/*
			 * Calculo de descuentos si es que corresponde. calcularDescuentoDia y
			 * calcularDescuentoEfectivo estan implementados en calcularDescuentoCarrito
			 */
			carrito.calcularDescuentoCarrito(carrito.getFecha().getDayOfWeek().getValue(),
					almacen.getPorcentajeDescuentoDia(), almacen.getPorcentajeDescuentoEfectivo());

			// cierro los carritos
			carrito.setCerrado(true);
			almacen.addLstCarrito(carrito);
			carrito2.setCerrado(true);
			almacen.addLstCarrito(carrito2);

			// Calculo los totales e imprimo
			System.out.println(almacen);
			System.out.println("Test del identificador unico: " + almacen.validarIdentificadorUnico(almacen.getCuit()));
			System.out.println("----------------------------------");
			System.out.println(cliente1);
			System.out
					.println("Test del identificador unico: " + cliente1.validarIdentificadorUnico(cliente1.getDni()));
			System.out.println("----------------------------------");
			System.out.println(almacen.getLstCarrito().get(0).getLstItemCarrito());
			System.out.println("SubTotal Carrito = $" + almacen.getLstCarrito().get(0).calcularTotalCarrito());
			System.out.print("Descuento = $");
			System.out.println(carrito.calcularTotalCarrito() - carrito.totalAPagarCarrito());
			System.out.println("Total con Descuentos = $" + almacen.getLstCarrito().get(0).totalAPagarCarrito());
			System.out.println("Entrega = " + almacen.getLstCarrito().get(0).getEntrega());
			System.out.println("----------------------------------");
			System.out.println("\n");// saltos de linea
			// Calculo los totales e imprimo carrito2
			System.out.println(almacen);
			System.out.println("----------------------------------");
			System.out.println(cliente2);

			System.out.println("----------------------------------");
			System.out.println(almacen.getLstCarrito().get(1).getLstItemCarrito());
			System.out.println("SubTotal Carrito = $" + almacen.getLstCarrito().get(1).calcularTotalCarrito());
			System.out.print("Descuento = $");
			System.out.println(carrito2.calcularTotalCarrito() - carrito2.totalAPagarCarrito());
			System.out.println("Total con Descuentos = $" + almacen.getLstCarrito().get(1).totalAPagarCarrito());
			System.out.println("Entrega = " + almacen.getLstCarrito().get(1).getEntrega());
			System.out.println("----------------------------------");

			System.out.println("\n");// saltos de linea

			// imprimo la agenda
			System.out.println("Agenda de la fecha: " + almacen.generarAgenda(fecha));
			System.out.println("\n");// saltos de linea
			System.out.println("Turnos ocupados: " + almacen.traerTurnosOcupados(fecha));
			System.out.println("\n");// saltos de linea
			System.out.println("Turnos disponibles: " + almacen.generarTurnosLibres(fecha));
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}