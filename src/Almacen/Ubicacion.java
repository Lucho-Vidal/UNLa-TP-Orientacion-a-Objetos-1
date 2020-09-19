package Almacen;

public class Ubicacion {
	private double latitud;
	private double longitud;
	
	public Ubicacion(double latitud, double longitud) {
		setLatitud(latitud);
		setLongitud(longitud);
	}

	public double getLatitud() {
		return latitud;
	}
	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public double getLongitud() {
		return longitud;
	}
	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}
	
	@Override
	public String toString() {
		return "Ubicacion [latitud=" + latitud + ", longitud=" + longitud + "]";
	}
	
	public Ubicacion traerUbicacion() {
		return new Ubicacion(latitud, longitud);
	}
}
