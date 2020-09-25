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
	private List<ItemCarrito> lstItemCarrito;
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
		this.lstItemCarrito= new ArrayList<ItemCarrito>();
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
	
	// Trae un articulo de la lista itemCArrito
	public ItemCarrito traerItemCarrito(Articulo articulo) {
		ItemCarrito itemAuxiliar = null;
		for(ItemCarrito i: this.lstItemCarrito) {
			if(i.getArticulo()==articulo) {
				itemAuxiliar = i;
			}
		}
		return itemAuxiliar;
	}
	
	// Si el articulo que intento agregar ya existe se lanza una excepcion, de lo
	//contrario lo agrego a mi lista de itemCarrito
	public boolean agregar(Articulo articulo, int cantidad){
		//Si el articulo ya existe
		if(traerItemCarrito(articulo)!=null) {
			cantidad = traerItemCarrito(articulo).getCantidad()+cantidad;
			
		} 
		
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
		double descuento = 0.0;
		if(diaDescuento==3) {
			for(ItemCarrito i: this.lstItemCarrito) {
				int cantidad= i.getCantidad();
				if(cantidad>=2) {
					int nuevaCantidad= cantidad/2;
					descuento = (nuevaCantidad*i.getArticulo().getPrecio()*porcentajeDescuentoDia)/100;
					
				}
			}
			
		}
		return descuento;
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
		double total= calcularTotalCarrito()-this.descuento;
		return total;
	}
}
