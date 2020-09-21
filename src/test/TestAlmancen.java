package test;

import java.time.LocalDate;
import java.time.LocalTime;

import almacen.Envio;
import almacen.Ubicacion;

public class TestAlmancen {

	public static void main(String[] args) {
		LocalTime ahora=  LocalTime.now();
		System.out.println(ahora);
		ahora=ahora.plusMinutes(60);
		System.out.println(ahora);
		
		LocalDate hoy = LocalDate.now();
		System.out.println(hoy);
		System.out.println(hoy.getDayOfWeek());
		
		System.out.println(hoy.getDayOfWeek().getValue()==6);
		
		
	}

}
