package almacen;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Comercio extends Actor {
	private String nombreComercio;
	private long cuit;
	private double costoFijo;
	private double costoPorKm;
	private int diaDescuento;
	private int porcentajeDescuentoDia;
	private int porcentajeDescuentoEfectivo;
	private List<DiaRetiro> lstDiaRetiro;
	private List<Carrito> lstCarrito;
	private List<Articulo> lstArticulo;

	// Constructor

	public Comercio(int id, Contacto contacto, String nombreComercio, long cuit, double costoFijo, double costoPorKm,
			int diaDescuento, int porcentajeDescuentoDia, int porcentajeDescuentoEfectivo, List<DiaRetiro> lstDiaRetiro,
			List<Carrito> lstCarrito, List<Articulo> lstArticulo) {
		super(id, contacto);
		this.nombreComercio = nombreComercio;
		this.cuit = cuit;
		this.costoFijo = costoFijo;
		this.costoPorKm = costoPorKm;
		this.diaDescuento = diaDescuento;
		this.porcentajeDescuentoDia = porcentajeDescuentoDia;
		this.porcentajeDescuentoEfectivo = porcentajeDescuentoEfectivo;
		this.lstDiaRetiro = lstDiaRetiro;
		this.lstCarrito = lstCarrito;
		this.lstArticulo = lstArticulo;
	}

	// Constructor Sobrecargado
	public Comercio(int id, Contacto contacto, String nombreComercio, long cuit, double costoFijo, double costoPorKm,
			int diaDescuento, int porcentajeDescuentoDia, int porcentajeDescuentoEfectivo) {
		super(id, contacto);
		this.nombreComercio = nombreComercio;
		this.cuit = cuit;
		this.costoFijo = costoFijo;
		this.costoPorKm = costoPorKm;
		this.diaDescuento = diaDescuento;
		this.porcentajeDescuentoDia = porcentajeDescuentoDia;
		this.porcentajeDescuentoEfectivo = porcentajeDescuentoEfectivo;

	}

	// Getters and Setters
	public String getNombreComercio() {
		return nombreComercio;
	}

	public void setNombreComercio(String nombreComercio) {
		this.nombreComercio = nombreComercio;
	}

	public long getCuit() {
		return cuit;
	}

	public void setCuit(long cuit) throws Exception {
		if (!validarIdentificadorUnico(cuit))
			throw new Exception("Error: CUIT invalido");
		this.cuit = cuit;
	}

	public double getCostoFijo() {
		return costoFijo;
	}

	public void setCostoFijo(double costoFijo) {
		this.costoFijo = costoFijo;
	}

	public double getCostoPorKm() {
		return costoPorKm;
	}

	public void setCostoPorKm(double costoPorKm) {
		this.costoPorKm = costoPorKm;
	}

	public int getDiaDescuento() {
		return diaDescuento;
	}

	public void setDiaDescuento(int diaDescuento) {
		this.diaDescuento = diaDescuento;
	}

	public int getPorcentajeDescuentoDia() {
		return porcentajeDescuentoDia;
	}

	public void setPorcentajeDescuentoDia(int porcentajeDescuentoDia) {
		this.porcentajeDescuentoDia = porcentajeDescuentoDia;
	}

	public int getPorcentajeDescuentoEfectivo() {
		return porcentajeDescuentoEfectivo;
	}

	public void setPorcentajeDescuentoEfectivo(int porcentajeDescuentoEfectivo) {
		this.porcentajeDescuentoEfectivo = porcentajeDescuentoEfectivo;
	}

	public List<DiaRetiro> getLstDiaRetiro() {
		return lstDiaRetiro;
	}

	public void setLstDiaRetiro(List<DiaRetiro> lstDiaRetiro) {
		this.lstDiaRetiro = lstDiaRetiro;
	}

	public List<Carrito> getLstCarrito() {
		return lstCarrito;
	}

	public void setLstCarrito(List<Carrito> lstCarrito) {
		this.lstCarrito = lstCarrito;
	}

	public void addLstCarrito(Carrito carrito) {
		if (lstCarrito == null)
			lstCarrito = new ArrayList<Carrito>();
		lstCarrito.add(carrito);
	}

	public List<Articulo> getLstArticulo() {
		return lstArticulo;
	}

	public void setLstArticulo(List<Articulo> lstArticulo) {
		this.lstArticulo = lstArticulo;
	}

	public void addLstArticulo(Articulo articulo) {
		if (lstArticulo == null)
			lstArticulo = new ArrayList<Articulo>();
		lstArticulo.add(articulo);
	}

	@Override
	public boolean equals(Object obj) {
		return this.cuit == ((Comercio) obj).cuit;
	}

	// ToString
	@Override
	public String toString() {
		return "Comercio [nombreComercio = " + nombreComercio + ", cuil = " + cuit + "]";
	}

	
	// Metodos de casos de uso

	public List<Turno> generarTurnosLibres(LocalDate fecha) throws Exception {// retorna una lista de objetos Turnos libres
		List<Turno> agenda = new ArrayList<Turno>();
		int indiceLista = buscarIndiceDiaRetiro(fecha);
		LocalTime hora = lstDiaRetiro.get(indiceLista).getHoraDesde();

		while (hora.isBefore(lstDiaRetiro.get(indiceLista).getHoraHasta())) {// mientras hora sea antes de horaHasta
			if (!buscarSiEstaOcupado(hora)) {// si el turno NO esta ocupado
				agenda.add(new Turno(fecha, hora, false)); // creo un turno disponible y lo agrego a la agenda
			}
			hora = hora.plusMinutes(lstDiaRetiro.get(indiceLista).getIntervalo());// sumamos el intervalo
		}
		return agenda;
	}

	public List<Turno> traerTurnosOcupados(LocalDate fecha) throws Exception {// retorna una lista de objetos Turno
		List<Turno> agenda = new ArrayList<Turno>();
		int indiceLista = buscarIndiceDiaRetiro(fecha);
		LocalTime hora = lstDiaRetiro.get(indiceLista).getHoraDesde();

		while (hora.isBefore(lstDiaRetiro.get(indiceLista).getHoraHasta())) {// mientras hora sea antes de horaHasta
			if (buscarSiEstaOcupado(hora)) {// si el turno SI esta ocupado
				agenda.add(new Turno(fecha, hora, true));// creo un turno disponible y lo agrego a la agenda
			}
			hora = hora.plusMinutes(lstDiaRetiro.get(indiceLista).getIntervalo());// sumamos el intervalo
		}
		return agenda;
	}

	public List<Turno> generarAgenda(LocalDate fecha) throws Exception {// retorna una lista de objetos Turno indicando si esta ocupado o libre
		List<Turno> agenda = new ArrayList<Turno>();
		int indiceLista = buscarIndiceDiaRetiro(fecha);
		LocalTime hora = lstDiaRetiro.get(indiceLista).getHoraDesde();

		while(hora.isBefore(lstDiaRetiro.get(indiceLista).getHoraHasta())) {// mientras hora sea antes de horaHasta
																			// busco si el el turno fue asignado a un Carrito en la lista Carrito
			agenda.add(new Turno(fecha, hora, buscarSiEstaOcupado(hora))); // creo un turno disponible u ocupado y lo agrego a la lista agenda
			hora = hora.plusMinutes(lstDiaRetiro.get(indiceLista).getIntervalo());// sumamos el intervalo del diaRetiro
		}
		return agenda;
	}

	public boolean agregarDiaRetiro(int diaSemana, LocalTime horaDesde, LocalTime horaHasta, int intervalo) throws Exception {
		int idDiaRetiro = 1;
		
		// Busco el ultimo Id
		if(getLstDiaRetiro().size() != 0) {	// si la lista esta vacia el Id va a ser = 1
			idDiaRetiro = lstDiaRetiro.get(getLstDiaRetiro().size() - 1).getId() + 1;	// el ultimo id + 1
		}

		DiaRetiro nuevoDiaRetiro = new DiaRetiro(idDiaRetiro, diaSemana, horaDesde, horaHasta, intervalo);// creo el nuevo dia

		// Busco si el dia Existe y tiro una Exception
		for(int i = 0; i < lstDiaRetiro.size(); i++) {
			if(nuevoDiaRetiro.equals(lstDiaRetiro.get(i)))
				throw new Exception("El dia Ya Existe! " + nuevoDiaRetiro);
		}
		lstDiaRetiro.add(nuevoDiaRetiro);
		return true;
	}

	public LocalTime traerHoraRetiro(LocalDate fecha) throws Exception {// busco la primer hora de retiro disponible para asignar a carrito
		int indiceLista = buscarIndiceDiaRetiro(fecha);
		LocalTime hora = lstDiaRetiro.get(indiceLista).getHoraDesde();
		boolean turnoLibre = false;
		while(!turnoLibre) {
			if(!buscarSiEstaOcupado(hora))
				turnoLibre = true; // si esta libre salimos del bucle
			else
				hora = hora.plusMinutes(lstDiaRetiro.get(indiceLista).getIntervalo());// si esta ocupado sumamos el intervalo del diaRetiro
		}
		return hora;
	}

	private int buscarIndiceDiaRetiro(LocalDate fecha) throws Exception {
		int indiceLista = -1;
		for(int i = 0; i < lstDiaRetiro.size(); i++) {
			if(fecha.getDayOfWeek().getValue() == lstDiaRetiro.get(i).getDiaSemana()) {// si coincide con diaSemana
				indiceLista = i;
			}
		}
		if(indiceLista == -1)
			throw new Exception("En la fecha ingresada no se realizan entregas: " + fecha);

		return indiceLista;
	}

	private boolean buscarSiEstaOcupado(LocalTime hora) {
		boolean ocupado = false;
		if(lstCarrito != null) {
			for(int i=0; i<lstCarrito.size(); i++) {// busquemos si el turno esta asignado a una entrega
				Entrega entrega = lstCarrito.get(i).getEntrega();
				if(entrega instanceof RetiroLocal) {// si la entrega es retiro local
					if(hora == ((RetiroLocal) entrega).getHoraEntrega())// casteamos para obtener la fecha e igualarla con hora
						ocupado = true;
				}
			}
		}
		return ocupado;
	}
}