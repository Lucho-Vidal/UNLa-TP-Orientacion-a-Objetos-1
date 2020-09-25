package test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import almacen.Cliente;
import almacen.Comercio;
import almacen.Contacto;
import almacen.DiaRetiro;
import almacen.Ubicacion;

public class TestAlmancen {

	public static void main(String[] args) {
		
		// creamos un Almacen y lo llamamos Almacen GGG 
		Ubicacion ubicacionComercio = new Ubicacion(-34.733406,-58.393577);
		Contacto contactoComercio= new Contacto("AlmanceGGG@gmail.com","1123234565",ubicacionComercio);
		Comercio Almacen = new Comercio(1,contactoComercio,"Almacen G.G.G.",30707661492L,500,15,3,25,10);
		
		//Le asignamos los dias de entrega
		List<DiaRetiro> lstDiaRetiro = new ArrayList<DiaRetiro>();
		LocalTime horaDesde = LocalTime.of(10, 00);
		LocalTime horaHasta = LocalTime.of(17, 00);
		
		for(int i = 1;i<7;i++) {//asigno horario de entrega lunes a Sabados de 10 a 17hs intervalo 20 minutos
			
			DiaRetiro diaRetiro = new DiaRetiro(i,i,horaDesde,horaHasta,20);
			lstDiaRetiro.add(diaRetiro);
		}
		Almacen.setLstDiaRetiro(lstDiaRetiro);
		
		//Agrego 4 clientes 
		Ubicacion ubicacionContacto1 = new Ubicacion(-34.761078,-58.397547);
		Contacto contactoVidal = new Contacto("luchovidal@gmail.com","1138281421",ubicacionContacto1);
		
		Ubicacion ubicacionContacto2 = new Ubicacion(-34.797726,-58.385312);
		Contacto contactoJerochim = new Contacto("emijerochim@gmail.com","1156459914",ubicacionContacto2);
		
		Ubicacion ubicacionContacto3 = new Ubicacion(-34.665414,-58.368515);
		Contacto contactoSilvestri = new Contacto("matysilvestri@gmail.com","1165357821",ubicacionContacto3);
		
		Ubicacion ubicacionContacto4 = new Ubicacion(-34.764091,-58.390095);
		Contacto contactoLopez = new Contacto("eugelopez@gmail.com","1125957841",ubicacionContacto4);
		
		try {
			Cliente cliente1 = new Cliente(2,contactoVidal,"Vidal","Luciano",35007121L,'M');
			Cliente cliente2 = new Cliente(3,contactoJerochim,"Jerochim","Emiliano",40500720L,'M');
			Cliente cliente3 = new Cliente(4,contactoSilvestri,"Silvestri","Matias",41926641L,'M');
			Cliente cliente4 = new Cliente(5,contactoLopez,"Lopez","Eugenia",43450121L,'F');
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		
	}

}
