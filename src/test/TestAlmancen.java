package test;

import java.time.LocalDate;
import java.time.LocalTime;

public class TestAlmancen {

	public static void main(String[] args) {
		LocalTime ahora=  LocalTime.of(15,20,00);
		System.out.println(ahora);
		ahora=ahora.plusMinutes(30);
		System.out.println(ahora);
		
		LocalDate hoy = LocalDate.now();
		System.out.println(hoy);
		System.out.println(hoy.getDayOfWeek());
		
		System.out.println(hoy.getDayOfWeek().getValue()==6);
		
	}

}
