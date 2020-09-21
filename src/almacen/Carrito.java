package almacen;

import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;
import java.time.LocalTime;

public class Carrito {
	private int id;
	private LocalDate fecha;
	private LocalTime hora;
	private boolean cerrado;
	private double descuento;
	private Cliente cliente;
	private List<ItemCarrito> lstItemCarrito = new ArrayList<ItemCarrito>();
	private Entrega entrega;

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

	protected void setDescuento(double descuento) {
		this.descuento = descuento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemCarrito> getLstItemCarrito() {
		return lstItemCarrito;
	}

	public void setLstItemCarrito(List<ItemCarrito> lstItemCarrito) {
		this.lstItemCarrito = lstItemCarrito;
	}

	public Entrega getEntrega() {
		return entrega;
	}

	public void setEntrega(Entrega entrega) {
		this.entrega = entrega;
	}

	
	// Metodos
	
	// Trae un articulo de la lista itemCArrito por articulo
	public ItemCarrito traerItemCarrito(Articulo articulo) {
		ItemCarrito itemAuxiliar = null;
		for(ItemCarrito i: this.lstItemCarrito) {
			if(i.getArticulo()==articulo) {
				itemAuxiliar = i;
			}
		}
		return itemAuxiliar;
	}
	
	public boolean agregar(Articulo articulo, int cantidad) throws Exception{
		if(traerItemCarrito(articulo)!=null) throw new Exception("El articulo ya existe"); 
			
		ItemCarrito itemCarrito1 = new ItemCarrito(articulo, cantidad);
		return lstItemCarrito.add(itemCarrito1);
	}

	public double calcularTotalCarrito() {
		double total = 0.0;
		for (ItemCarrito i : lstItemCarrito) {
			total += i.calcularSubTotalItem();
		}
		return total;
	}

	public double calcularDescuentoEfectivo(double porcentajeDescuentoEfectivo) {
		return ((this.calcularTotalCarrito() * porcentajeDescuentoEfectivo) / 100);
	}
	
	public double calcularDescuentoDia(int diaDescuento, double porcentajeDescuentoDia) {
		return ((this.calcularTotalCarrito()*porcentajeDescuentoDia)/100);
	}
	
	public void calcularDescuentoCarrito(int diaDescuento, double porcentajeDescuentoDia, double porcentajeDescuentoEfectivo) {
		double efectivo= calcularDescuentoEfectivo(porcentajeDescuentoEfectivo);
		double dia= calcularDescuentoDia(diaDescuento,porcentajeDescuentoDia);
		double mayor=0.0;
		if(efectivo>dia) {
			mayor=efectivo;
		}
		else {
			mayor=dia;
		}
		setDescuento(mayor);
	}
	public double totalAPagarCarrito() {
		 //TODO
		return 0;
	}
}
