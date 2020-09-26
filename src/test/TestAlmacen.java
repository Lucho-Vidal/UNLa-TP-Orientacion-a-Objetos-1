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
import almacen.RetiroLocal;
import almacen.Turno;
import almacen.Ubicacion;

public class TestAlmacen {

	public static void main(String[] args) {

		// creamos un Almacen y lo llamamos Almacen GGG
		Ubicacion ubicacionComercio = new Ubicacion(-34.733406, -58.393577);
		Contacto contactoComercio = new Contacto("AlmanceGGG@gmail.com", "1123234565", ubicacionComercio);
		Comercio almacen = new Comercio(1, contactoComercio, "Almacen G.G.G.", 30707661492L, 500, 15, 3, 25, 10);

		// Le asignamos los dias de entrega
		List<DiaRetiro> lstDiaRetiro = new ArrayList<DiaRetiro>();
		LocalTime horaDesde = LocalTime.of(10, 00);
		LocalTime horaHasta = LocalTime.of(17, 00);

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
			Cliente cliente1 = new Cliente(2, contactoVidal, "Vidal", "Luciano", 35007121L, 'h');
			Cliente cliente2 = new Cliente(3, contactoJerochim, "Jerochim", "Emiliano", 40500720L, 'h');
			Cliente cliente3 = new Cliente(4, contactoSilvestri, "Silvestri", "Matias", 41926641L, 'h');
			Cliente cliente4 = new Cliente(5, contactoLopez, "Lopez", "Eugenia", 43450121L, 'm');

			// Agrego algunos articulos

			Articulo[] articulo = new Articulo[5];

			articulo[0] = new Articulo(1, "Leche", "7791234567890", 89.99);
			articulo[1] = new Articulo(2, "Cerveza", "7792345678901", 24.99);
			articulo[2] = new Articulo(3, "Tomate", "7793456789012", 149.99);
			articulo[3] = new Articulo(4, "Queso", "7794567890123", 870.00);
			articulo[4] = new Articulo(5, "Huevos", "7795678901234", 250.00);

			for (int i = 0; i < 5; i++) {
				almacen.addLstArticulo(articulo[i]);
			}

			// Creo un Carrito
			List<Turno> turnos = almacen.generarAgenda(LocalDate.now());
			System.out.println(almacen.toString());
			System.out.println(turnos);// Imprimo la agenda de turnos
			Entrega entrega = new RetiroLocal(1, LocalDate.now(), true, almacen.traerHoraRetiro(LocalDate.now()));
			turnos = almacen.traerTurnosOcupados(LocalDate.now());
			
			

			// TODO corregir toString para este caso
			System.out.println("Turnos ocupados" + turnos.toString());// Imprimo los turnos ocupados

			Carrito carrito = new Carrito(1, LocalDate.now(), LocalTime.now(), false, 5, cliente1, entrega);
			System.out.println("Carrito: " + carrito.toString());
			carrito.agregar(almacen.getLstArticulo().get(0), 3);
			carrito.agregar(almacen.getLstArticulo().get(0), 2);
			// Calculo el total de carrito.
			System.out.println("Calcular Total Carrito " + carrito.calcularTotalCarrito());
			// Calculo de cuanto es el descuento en efectvio
			System.out.println("Calcular Descuento Efectivo " + carrito.calcularDescuentoEfectivo(10));
			// Calculo de cuanto es el descuento, si el dia es Miercoles
			// Pero como el dia es Martes, el descuento es de cero
			System.out.println("Calcular Descuento Dia: " + carrito.calcularDescuentoDia(2, 10));
			// Me fijo cual descuento es mejor
			carrito.calcularDescuentoCarrito(2, 10, 10);
			// Calculo cuanto debo pagar con descuento incluido--- el mejor decuento es el
			// de pago con efectivo
			System.out.println("Total a Pagar: " + carrito.totalAPagarCarrito());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
