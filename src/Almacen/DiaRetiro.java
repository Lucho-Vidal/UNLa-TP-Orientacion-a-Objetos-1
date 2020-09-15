package Almacen;

import java.time.LocalTime;

public class DiaRetiro {
	private int id;
	private int diaSemana;
	private LocalTime horaDesde;
	private LocalTime horaHasta;
	private int Intervalo;
	public DiaRetiro(int id, int diaSemana, LocalTime horaDesde, LocalTime horaHasta, int intervalo) {
		super();
		this.id = id;
		this.diaSemana = diaSemana;
		this.horaDesde = horaDesde;
		this.horaHasta = horaHasta;
		Intervalo = intervalo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDiaSemana() {
		return diaSemana;
	}
	public void setDiaSemana(int diaSemana) {
		this.diaSemana = diaSemana;
	}
	public LocalTime getHoraDesde() {
		return horaDesde;
	}
	public void setHoraDesde(LocalTime horaDesde) {
		this.horaDesde = horaDesde;
	}
	public LocalTime getHoraHasta() {
		return horaHasta;
	}
	public void setHoraHasta(LocalTime horaHasta) {
		this.horaHasta = horaHasta;
	}
	public int getIntervalo() {
		return Intervalo;
	}
	public void setIntervalo(int intervalo) {
		Intervalo = intervalo;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DiaRetiro other = (DiaRetiro) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "DiaRetiro [id=" + id + ", diaSemana=" + diaSemana + ", horaDesde=" + horaDesde + ", horaHasta="
				+ horaHasta + ", Intervalo=" + Intervalo + "]";
	}
	
	
}
