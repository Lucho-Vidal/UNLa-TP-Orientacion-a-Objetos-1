package almacen;

import java.util.ArrayList;
import java.util.List;
import java.time.DayOfWeek;
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

	public Carrito(int id, LocalDate fecha, LocalTime hora, boolean cerrado, double descuento, Cliente cliente, Entrega entrega) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.hora = hora;
		this.cerrado = cerrado;
		this.descuento= descuento;
		this.cliente = cliente;
		this.lstItemCarrito= new ArrayList<ItemCarrito>();
		this.setEntrega(entrega); 
	}
	
	//Constructor sobrecargado sin Entrega entrega
	public Carrito(int id, LocalDate fecha, LocalTime hora, boolean cerrado, double descuento, Cliente cliente) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.hora = hora;
		this.cerrado = cerrado;
		this.descuento = descuento;
		this.cliente = cliente;
		this.lstItemCarrito = new ArrayList<ItemCarrito>();
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
	
	
	@Override
	public String toString() {
		return "Carrito [id=" + id + ", fecha=" + fecha + ", hora=" + hora + ", cerrado=" + cerrado + ", descuento="
		+ descuento + ", cliente=" + cliente + ", lstItemCarrito=" + lstItemCarrito + ", entrega=" + entrega + "]/n";
	}
	
	public boolean equals(Carrito carrito) {
		return (id==carrito.getId());
	}
	
	
	
	// Trae un articulo de la lista itemCArrito
	public ItemCarrito traerItemCarrito(Articulo articulo) {
		ItemCarrito itemAuxiliar = null;
		
		for(ItemCarrito i: this.lstItemCarrito) {
			if(i.getArticulo() == articulo) {
				itemAuxiliar = i;
			}
		}
		return itemAuxiliar;
	}
	
	//Agrego articulos y una cantidad a la lista de itemCarrito
	public boolean agregarItem(Articulo articulo, int cantidad){
		ItemCarrito itemAux = traerItemCarrito(articulo);
		
		if(itemAux!=null) {												//Si el item ya existe
			itemAux.setCantidad((itemAux.getCantidad() + cantidad));	//sumo la cantidad ingresada a la existente
		}
		else {															//sino
			lstItemCarrito.add(new ItemCarrito(articulo, cantidad));	//creo un nuevo item con la cantidad ingresada
		}
		return true;
	}
	
	
	public boolean sacarItem(Articulo articulo, int cantidad) {
		ItemCarrito itemAux = traerItemCarrito(articulo);
		
		if(itemAux!=null) {													//Si el articulo existe
			if(itemAux.getCantidad() > cantidad) {							//Si la cantidad ingresada es menor
				itemAux.setCantidad((itemAux.getCantidad() - cantidad));	//resto la cantidad ingresada a la existente
			}
			else {															//Si la cantidad ingresada es igual o mayor
				lstItemCarrito.remove(itemAux);  							//Saco el articulo
			}
			return true;	//operacion exitosa
		}
		return false;		//error
	}
	
	//Es el total pero sin descuento.
	public double calcularTotalCarrito() {
		double total = 0.0;
		//Realizo un for de los distintos item, y en la variable total llamo al metodo calcularSubTotalItem
		//y acumulo todo
		for (ItemCarrito i : lstItemCarrito) {
			total += i.calcularSubTotalItem();
		}
		return total;
	}

	//Calculo el descuento si el cliente paga con efectivo
	public double calcularDescuentoEfectivo(double porcentajeDescuentoEfectivo) {
		return ((this.calcularTotalCarrito() * porcentajeDescuentoEfectivo) / 100);
	}
	
	//El dia que se recibe por parametros es el dia donde se efectua el descuento
	public double calcularDescuentoDia(int diaDescuento, double porcentajeDescuentoDia) {
		double descuento = 0.0;
		// 3= Miercoles, dia de descuento
		DayOfWeek dayOfWeek= DayOfWeek.of(3);
		int dia=dayOfWeek.getValue();
	
		if(dia==diaDescuento) {
			for(ItemCarrito i: this.lstItemCarrito) {
				int cantidad= i.getCantidad();
				//Si la cantidad de articulos es mayor o igual a dos realizo descuento
				if(cantidad>=2) {
					int nuevaCantidad= cantidad/2;
					descuento = (nuevaCantidad*i.getArticulo().getPrecio()*porcentajeDescuentoDia)/100;	
				}
			}
		}
		return descuento;
	}
	
	//Determino cual descuento es mas efectivo, si por dia o efectivo
	public void calcularDescuentoCarrito(int diaDescuento,double porcentajeDescuentoDia, double porcentajeDescuentoEfectivo) {
		double efectivo= calcularDescuentoEfectivo(porcentajeDescuentoEfectivo);
		double dia= calcularDescuentoDia(diaDescuento,porcentajeDescuentoDia);
		double descuentoMayor=0.0;
		if(efectivo>dia) {
			descuentoMayor=efectivo;
		}
		else {
			descuentoMayor=dia;
		}
		setDescuento(descuentoMayor);
	}
	
	//Total a pagar pero ya con el descuento
	public double totalAPagarCarrito() {
		return calcularTotalCarrito() - descuento;
	}
}