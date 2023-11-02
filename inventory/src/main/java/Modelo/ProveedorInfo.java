package Modelo;

public class ProveedorInfo {
	private int id;
    private String nombre;
    private long diferenciaDias;

    public ProveedorInfo(int id, String nombre, long diferenciaDias) {
        this.id = id;
        this.nombre = nombre;
        this.diferenciaDias = diferenciaDias;
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

}
