package almacen;
import java.time.*;
import java.util.ArrayList;
import java.util.List;

public class Carrito {
	private int id;
	private LocalDate fecha;
	private LocalTime hora;
	private boolean cerrado;
	private double descuento;
	private Cliente cliente;
	private Entrega entrega;
	private List<ItemCarrito> lstItemCarrito= new ArrayList<ItemCarrito>();
	
	public Carrito(int id, LocalDate fecha, LocalTime hora, boolean cerrado, double descuento, Cliente cliente,
			Entrega entrega) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.hora = hora;
		this.cerrado = cerrado;
		this.descuento = descuento;
		this.cliente = cliente;
		this.entrega = entrega;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public boolean isCerrado() {
		return cerrado;
	}

	public void setCerrado(boolean cerrado) {
		this.cerrado = cerrado;
	}

	public double getDescuento() {
		return descuento;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Entrega getEntrega() {
		return entrega;
	}

	public void setEntrega(Entrega entrega) {
		this.entrega = entrega;
	}

	public List<ItemCarrito> getLstItemCarrito() {
		return lstItemCarrito;
	}

	public void setLstItemCarrito(List<ItemCarrito> lstItemCarrito) {
		this.lstItemCarrito = lstItemCarrito;
	}



	public boolean agregar(Articulo articulo, int cantidad) {
		
		ItemCarrito itemCarrito1=new ItemCarrito(articulo,cantidad);
		return lstItemCarrito.add(itemCarrito1);
	}

	public double calcularSubTotalItem() {
		double subTotal=0.0;
		for(ItemCarrito i: lstItemCarrito) {
			subTotal += (i.getCantidad())*((i.getArticulo()).getPrecio());
		}
		return subTotal;
	}
	
	public double totalCarrito() {
		 double total=0.0;
		 
		 return total;
	}
	
	
}
