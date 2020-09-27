package almacen;

public class Cliente extends Actor{
	private String apellido;
	private String nombres;
	private long dni;
	private char sexo;
	
	public Cliente(int id, Contacto contacto, String apellido, String nombres, long dni, char sexo) throws Exception {
		super(id, contacto);
		setApellido(apellido);
		setNombres(nombres);
		setDni(dni);
		setSexo(sexo);
	}

	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public long getDni() {
		return dni;
	}
	public void setDni(long dni) throws Exception{
		if(!validarIdentificadorUnico(dni))	throw new Exception("Error: DNI invalido");
		this.dni = dni;
	}

	public char getSexo() {
		return sexo;
	}
	public void setSexo(char sexo) throws Exception{
		if(!validarSexo(sexo)) throw new Exception("Error: Sexo invalido ('H' o 'M')");
		this.sexo = Character.toUpperCase(sexo);
	}

	public boolean validarSexo(char sexo) {
		return(sexo=='H' || sexo=='h' || sexo=='M' || sexo=='m');
	}

	@Override
	public String toString() {
		return "Cliente [apellido=" + apellido + ", nombres=" + nombres + ", dni=" + dni + ", sexo=" + sexo + "]";
	}
	
}
