package Modelo;

public class Usuario {
	private int id;
	private String dni; 
	private String nom;
    private String tel;
    private String estado;
    private String user;
    private String varLinea;
    private String ultimoAcceso;
    private String horaUltimoAcceso;
    private String permisos;
    private String email;
    
    public Usuario() {
    }
    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUltimoAcceso() {
		return ultimoAcceso;
	}

	public void setUltimoAcceso(String ultimoAcceso) {
		this.ultimoAcceso = ultimoAcceso;
	}

    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}

	public String getVarLinea() {
		return varLinea;
	}

	public void setVarLinea(String varLinea) {
		this.varLinea = varLinea;
	}

	public String getHoraUltimoAcceso() {
		return horaUltimoAcceso;
	}

	public void setHoraUltimoAcceso(String horaUltimoAcceso) {
		this.horaUltimoAcceso = horaUltimoAcceso;
	}
	public String getPermisos() {
		return permisos;
	}
	public void setPermisos(String permisos) {
		this.permisos = permisos;
	}
}
