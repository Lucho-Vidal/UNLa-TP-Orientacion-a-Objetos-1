package almacen;
import java.time.*;

public abstract class Entrega {
	protected int id;
	protected LocalDate fecha;
	protected boolean efectivo;
	
	public Entrega(int id, LocalDate fecha, boolean efectivo) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.efectivo = efectivo;
	}
	
	
	
	
	
}
