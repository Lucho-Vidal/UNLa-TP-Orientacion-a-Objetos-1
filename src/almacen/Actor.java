package almacen;

public abstract class Actor {
	protected int id;
	protected Contacto contacto;
	
	public Actor(int id, Contacto contacto) {
		setId(id);
		setContacto(contacto);
	}

	//Getters and setters
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
	
	//Casos de uso
	public boolean validarIdentificadorUnico(long identificador){
		String identificadorString = String.valueOf(identificador); 	//se parsea el identificador a String para heredar sus metodos
		
		if(this instanceof Cliente) {															//Si es cliente tiene dni
			return(identificadorString.length() == 7 || identificadorString.length() == 8);		//Los dni solo tienen 8 o 9 digitos
		}
		
		else if(identificadorString.length() != 11) {											//Si no es cliente es comercio y tiene cuit
	    	return false;																		//los cuit solo tienen 11 dígitos
		}
		
		char[] cuitArray = identificadorString.toCharArray(); 			//se obtiene un array de chars con los digitos del cuit
	    int[] serie = {5, 4, 3, 2, 7, 6, 5, 4, 3, 2};					//numeros a multiplicar
	    long z = identificador % 10, sum = 0;							//numero de validacion y acumulador
	    
	    for (int i=0; i<10; i++){
	        sum += Character.getNumericValue(cuitArray[i]) * serie[i];		//se multiplican ambos array digito a digito
	    }
	    
	    sum = 11 - (sum % 11);	//se desencripta la suma
	    if(sum==11) sum=0; 		//se corrige excepcion
		return (sum == z);		// se evalua que la suma y el numero de validacion coincidan
	}
}
