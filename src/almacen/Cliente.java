package almacen;

public class Cliente extends Actor{
	private String apellido;
	private String nombres;
	private long dni;
	private char sexo;
	
	public Cliente(int id, Contacto contacto, String apellido, String nombres, long dni, char sexo) {
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
	public void setDni(long dni){
		this.dni = dni;
	}

	public char getSexo() {
		return sexo;
	}
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
}
