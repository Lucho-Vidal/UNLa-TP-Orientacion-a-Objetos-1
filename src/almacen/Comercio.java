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
	private List<Articulo> lstArticulo = new ArrayList<Articulo>();

	// Constructor
	public Comercio(int id, Contacto contacto, String nombreComercio, long cuit, double costoFijo, double costoPorKm,
			int diaDescuento, int porcentajeDescuentoDia, int porcentajeDescuentoEfectivo, List<DiaRetiro> lstDiaRetiro,
			List<Carrito> lstCarrito) {
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
	}

	// Gets and Sets
	public String getNombreComercio() {
		return nombreComercio;
	}

	public void setNombreComercio(String nombreComercio) {
		this.nombreComercio = nombreComercio;
	}

	public long getCuit() {
		return cuit;
	}

	public void setCuit(long cuit) {
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

	public List<Articulo> getLstArticulo() {
		return lstArticulo;
	}

	public void setLstArticulo(List<Articulo> lstArticulo) {
		this.lstArticulo = lstArticulo;
	}

	// TODO realizar el Equals

	// ToString
	@Override
	public String toString() {
		return "Comercio [nombreComercio= " + nombreComercio + ", cuil= " + cuit + ", costoFijo= " + costoFijo
				+ ", costoPorKm= " + costoPorKm + ", diaDescuento= " + diaDescuento + ", porcentajeDescuentoDia= "
				+ porcentajeDescuentoDia + ", porcentajeDescuentoEfectivo= " + porcentajeDescuentoEfectivo + "]";
	}

	// Metodos de casos de uso
	// TODO desarrollo

	public List<Turno> generarTurnosLibres(LocalDate fecha) {//retorna una lista de objetos Turno libres
		
		//List<Turno> turnosLibres = new ArrayList<Turno>();
		//lstDiaRetiro. 
		
		
		return null;
	}

	public List<Turno> traerTurnosOcupados(LocalDate fecha) {//retorna una lista de objetos Turno dados
		
		
		return null;
	}

	public List<Turno> generarAgenda(LocalDate fecha)throws Exception {//retorna una lista de objetos Turno indicando si est� ocupado o libre.
		
		List<Turno> agenda = new ArrayList<Turno>();
		
		int indiceLista=-1; 
		
		for (int i = 0; i < lstDiaRetiro.size(); i++) {
			
			if (fecha.getDayOfWeek().getValue()== lstDiaRetiro.get(i).getDiaSemana() ) {// si coincide con diaSemana 
				indiceLista = i;
			}
		}	
		if(indiceLista==-1)
			throw new Exception("En la fecha ingresada no se realizan entregas: "+fecha);
		
		LocalTime hora=lstDiaRetiro.get(indiceLista).getHoraDesde();
		
		//busquemos si el turno esta asignado a una entrega
		for (int i = 0; i < lstDiaRetiro.size(); i++) {
			Entrega entrega=lstCarrito.get(i).getEntrega();
			if(entrega instanceof RetiroLocal) {
				((RetiroLocal)entrega).getHoraEntrega();
			}
		}
		
		while(hora.isBefore(lstDiaRetiro.get(indiceLista).getHoraHasta())) {//mientras hora sea antes de horaHasta
			hora=hora.plusMinutes(lstDiaRetiro.get(indiceLista).getIntervalo());//sumamos el intervalo
			Turno turno=new Turno(fecha,hora,false);//creo un turno disponible
			agenda.add(turno);
		}
		
		return agenda;
	}

	public boolean agregarDiaRetiro(int diaSemana, LocalTime horaDesde, LocalTime horaHasta, int intervalo)
			throws Exception {

		int idDiaRetiro;

		// Busco el ultimo Id
		if (getLstDiaRetiro().size() == 0)// si la lista esta vacia el Id va a ser = 1
			idDiaRetiro = 1;
		else {// sino busco el ultimo diaRetiro en la lista y le asigno el ultimo id
			DiaRetiro ultimoDia = lstDiaRetiro.get(getLstDiaRetiro().size() - 1);
			idDiaRetiro = ultimoDia.getId() + 1;// el ultimo Id mas 1
		}

		DiaRetiro nuevoDiaRetiro = new DiaRetiro(idDiaRetiro, diaSemana, horaDesde, horaHasta, intervalo);

		// Busco si el dia Existe y tiro una Exception
		for (int i = 0; i < lstDiaRetiro.size(); i++) {
			if (nuevoDiaRetiro.equals(lstDiaRetiro.get(i)))
				throw new Exception("El dia Ya Existe! " + nuevoDiaRetiro);
		}
		lstDiaRetiro.add(nuevoDiaRetiro);

		return true;

	}
}