package Almacen;

public abstract class Actor {
	protected int id;
	protected Contacto contacto;
	
	public Actor(int id, Contacto contacto) {
		setId(id);
		setContacto(contacto);
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public Contacto getContacto() {
		return contacto;
	}
	public void setContacto(Contacto contacto) {
		this.contacto = contacto;
	}
	
	public boolean validarIdentificadorUnico(long identificador){
		if(this instanceof Cliente) {			//Si es cliente
			return validarDni(identificador);	//tiene dni y se valida
		}										//Sino
		return validarCuit(identificador);		//tiene cuit y se valida
	}
	public boolean validarDni(long dni){
		if(String.valueOf(dni).length() != 8 || String.valueOf(dni).length() != 9) {	//Se parsea dni a String y se obtiene su largo
			return false;	//los dni solo tienen 8 o 9 dígitos
		}
		return true;
	}
	public boolean validarCuit(long cuit) {
		if(String.valueOf(cuit).length() != 11) {	//se parsea dni a String y se obtiene su largo
			return false;		//los cuit solo tienen 11 dígitos
		}
		
		char[] cuitArray = (String.valueOf(cuit)).toCharArray(); 	//se obtiene un array de chars con los dígitos del cuit
	    int[] serie = {5, 4, 3, 2, 7, 6, 5, 4, 3, 2};				//numeros a multiplicar
	    long z = cuit % 10;											//numero de validación
	    
	    int sum = 0;
	    for (int i=0; i<10; i++){
	        sum += Character.getNumericValue(cuitArray[i]) * serie[i];	//se multiplican ambos array dígito a dígito
	    }
	    
	    sum = 11 - (sum % 11);	//se desencripta la suma
	    if(sum==11) sum=0; 		//se corrige excepcion
	    
	    if(sum==z) { 			// se evalua que la suma y el numero de validacion coincidan
	    	return true;
	    }
	    else {
	    	return false;
	    }
	}
	
	public boolean validarSexo(char sexo) {
		if(sexo=='h' || sexo=='m') {
			return true;
		}
		return false;
	}
}
