package test;
import almacen.Actor;
import almacen.Articulo;
import almacen.Carrito;
import almacen.Contacto;
import almacen.Cliente;
import almacen.Ubicacion;
//import almacen.Entrega;
import almacen.ItemCarrito;
import java.util.ArrayList;
import java.util.List;
import java.time.*;

public class TestAlmacen {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<ItemCarrito> lst= new ArrayList<ItemCarrito>();
		//List<ItemCarrito> lst1= new ArrayList<ItemCarrito>();
		//LocalDate fecha= LocalDate.now();
		LocalDate fecha1= LocalDate.now();
		LocalTime hora = LocalTime.now();
		Ubicacion ubicacion1= new Ubicacion(10.0,20.0);
		Contacto contacto1=new Contacto("soyUnCorreo@gmail.com","2334445556",ubicacion1);
		Cliente cliente1= new Cliente(1,contacto1,"Lopez","Eugenia", 40933119,'f');
		//Entrega entrega1= new Entrega(1,fecha, true);
		Carrito carrito= new Carrito(1, fecha1, hora, false, 20.0, cliente1, lst);
		//Carrito carrito2= new Carrito(1, fecha1, hora, true, 20.0, cliente1, lst);
		Articulo articulo1= new Articulo(1,"Pan","codigoDeBarra",50);
		Articulo articulo2= new Articulo(2, "Carne", "cod", 100);
		
		carrito.agregar(articulo1, 2);
		carrito.agregar(articulo2, 3);
		
		//carrito2.agregar(articulo1, 5);
		//carrito.calcularSubTotalItem();
		System.out.println(carrito.calcularSubTotalItem());
		//System.out.println(carrito2.calcularSubTotalItem());
		System.out.println(carrito.totalCarrito());
	}

}
