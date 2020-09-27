package test;
import java.time.LocalDate;
import java.time.LocalTime;

import almacen.Articulo;
import almacen.ItemCarrito;
import almacen.Carrito;

public class TestCarrito {
	public static void main(String[] args) {
		try {
			Carrito carrito1=new Carrito(1, LocalDate.now(), LocalTime.now(), false, 0, null, null);
			Articulo articulo1= new Articulo(1, "Yerba", "7796543217852", 100.00);
			// Articulo articulo2= new Articulo(2, "Harina", "7796543217852", 50.00);
			carrito1.agregarItem(articulo1, 4);
			carrito1.agregarItem(articulo1, 2);
			System.out.println(carrito1.traerItemCarrito(articulo1).getCantidad());
			System.out.println(carrito1.calcularTotalCarrito());
			
			carrito1.sacarItem(articulo1, 5);
			System.out.println(carrito1.traerItemCarrito(articulo1).getCantidad());
			//System.out.println(carrito1.calcularDescuentoEfectivo(10));
			//System.out.println(carrito1.calcularDescuentoDia(2, 10));
			// carrito1.calcularDescuentoCarrito(2,10, 10);
			// System.out.println(carrito1.totalAPagarCarrito());
			// System.out.println(carrito1.traerItemCarrito(articulo1));
			System.out.println(carrito1.toString());
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		
	}

}
