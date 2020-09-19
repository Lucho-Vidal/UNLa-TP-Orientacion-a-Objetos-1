package Almacen;
import java.time.*;

public class Envio extends Entrega {
	private LocalDate horaHasta;
	private LocalDate horaDesde;
	private double costo;
	private Ubicacion ubicacion;
	
	public Envio(int id, LocalDate fecha, boolean efectivo, LocalDate horaHasta, LocalDate horaDesde, double costo, Ubicacion ubicacion) {
		super(id, fecha, efectivo);
		this.horaHasta = horaHasta;
		this.horaDesde = horaDesde;
		this.costo = costo;
		this.ubicacion = ubicacion;
	}

	public LocalDate getHoraHasta() {
		return horaHasta;
	}
	public void setHoraHasta(LocalDate horaHasta) {
		this.horaHasta = horaHasta;
	}

	public LocalDate getHoraDesde() {
		return horaDesde;
	}
	public void setHoraDesde(LocalDate horaDesde) {
		this.horaDesde = horaDesde;
	}

	public double getCosto() {
		return costo;
	}
	public void setCosto(double costo) {
		this.costo = costo;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	@Override
	public String toString() {
		return "Envio [horaHasta=" + horaHasta + ", horaDesde=" + horaDesde + ", costo=" + costo + ", ubicacion="
				+ ubicacion + "]";
	}
	
	
	
}
