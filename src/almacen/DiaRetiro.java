package almacen;

import java.time.*;

import almacen.DiaRetiro;

public class DiaRetiro {
	private int id;
	private int diaSemana;
	private LocalTime horaDesde;
	private LocalTime horaHasta;
	private int intervalo;

	public DiaRetiro(int id, int diaSemana, LocalTime horaDesde, LocalTime horaHasta, int intervalo) {
		this.id = id;
		this.diaSemana = diaSemana;
		this.horaDesde = horaDesde;
		this.horaHasta = horaHasta;
		this.intervalo = intervalo;
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
		return intervalo;
	}

	public void setIntervalo(int intervalo) {
		this.intervalo = intervalo;
	}

	@Override
	public boolean equals(Object obj) {
		return this.diaSemana == ((DiaRetiro) obj).getDiaSemana();
	}

	@Override
	public String toString() {
		return "DiaRetiro [id=" + id + ", diaSemana=" + diaSemana + ", horaDesde=" + horaDesde + ", horaHasta="
				+ horaHasta + ", intervalo=" + intervalo + "]";
	}

}
