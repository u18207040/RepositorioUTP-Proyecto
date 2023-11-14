package Modelo;

import java.sql.Date;

public class ProveedorInfo {
	private int id;
    private String nombre;
    private long diferenciaDias;
    private Date fecha;

    public ProveedorInfo(int id, String nombre, long diferenciaDias, Date fecha) {
        this.id = id;
        this.nombre = nombre;
        this.diferenciaDias = diferenciaDias;
        this.fecha=fecha;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public long getDiferenciaDias() {
		return diferenciaDias;
	}

	public void setDiferenciaDias(long diferenciaDias) {
		this.diferenciaDias = diferenciaDias;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}
