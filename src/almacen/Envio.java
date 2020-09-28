package almacen;

import java.time.*;

public class Envio extends Entrega {
	private LocalTime horaDesde;
	private LocalTime horaHasta;
	private double costo;
	private Ubicacion ubicacion;

	public Envio(int id, LocalDate fecha, boolean efectivo, LocalTime horaDesde, LocalTime horaHasta,
			Ubicacion ubicacion) {
		super(id, fecha, efectivo);
		this.horaDesde = horaDesde;
		this.horaHasta = horaHasta;
		this.ubicacion = ubicacion;
	}

	public Envio(int id, LocalDate fecha, boolean efectivo, LocalTime horaDesde, LocalTime horaHasta,
			Ubicacion ubicacionComercio, double costoFijo, double costoPorKm, Ubicacion ubicacion) {
		super(id, fecha, efectivo);
		this.horaDesde = horaDesde;
		this.horaHasta = horaHasta;
		this.ubicacion = ubicacion;
		this.setCosto(ubicacionComercio, costoFijo, costoPorKm);
	}

	public LocalTime getHoraHasta() {
		return horaHasta;
	}

	public void setHoraHasta(LocalTime horaHasta) {
		this.horaHasta = horaHasta;
	}

	public LocalTime getHoraDesde() {
		return horaDesde;
	}

	public void setHoraDesde(LocalTime horaDesde) {
		this.horaDesde = horaDesde;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(Ubicacion ubicacion, double costoFijo, double costoPorKm) {
		double distancia = distanciaCoord(this.ubicacion.getLatitud(), this.ubicacion.getLongitud(),
				ubicacion.getLatitud(), ubicacion.getLongitud());
		this.costo = costoFijo + (distancia * costoPorKm);
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	@Override
	public String toString() {
		return "Envio [horaDesde = " + horaDesde + ", horaHasta = " + horaHasta + ", costo = " + costo
				+ ", ubicacion = " + ubicacion + "]";
	}

	public double distanciaCoord(double lat1, double lng1, double lat2, double lng2) {
		double radioTierra = 6371; // en kilómetros
		double dLat = Math.toRadians(lat2 - lat1);
		double dLng = Math.toRadians(lng2 - lng1);
		double sindLat = Math.sin(dLat / 2);
		double sindLng = Math.sin(dLng / 2);
		double va1 = Math.pow(sindLat, 2)
				+ Math.pow(sindLng, 2) * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));
		double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));
		return radioTierra * va2;

	}
}
