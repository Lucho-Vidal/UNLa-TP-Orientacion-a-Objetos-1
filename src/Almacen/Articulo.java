package Almacen;

public class Articulo {
	private int id;
	private String nombre;
	private String codBarras;
	private double precio;
	
	public Articulo(int id, String nombre, String codBarras, double precio) throws Exception {
		super();
		this.id = id;
		this.nombre = nombre;
		this.setCodBarras(codBarras);
		this.precio = precio;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodBarras() {
		return codBarras;
	}

	public void setCodBarras(String codBarras) throws Exception {
		boolean valido=validarCodBarras(codBarras);
		if(valido) {
			this.codBarras=codBarras;
		}
		else {
			throws new Exception("Codigo de barras no valido");
		}
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	public boolean validarCodBarras(String codBarras) {
		return codBarras.matches("^[7]{1}[7]{1}[9]{1}[0-9]{10}$");	
	}
	
	
	
	
}
