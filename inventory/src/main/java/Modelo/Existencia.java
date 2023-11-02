package Modelo;

import java.math.BigDecimal;
import java.sql.Date;

public class Existencia {
	private int idInventario;
	private int existenciaInicial;
    private BigDecimal precioCompra;
    private Date fechaEntrada;
    private Date fechaSalida;
    private String estado;
    private String ingresadoPor;
    private int idCliente;
    private int idProducto;
    private String nombreProducto;
    private String nombreCliente;
    private int existenciaFinal;
    private String nombreMaterial;
    private String nombreProveedor;
    private String tipoMovimiento;
    private int cantidadMovimiento;
    private int idMaterial; 
    private int idProveedor;
    private int idUsuario;
    public Existencia() {
    	
    }
    public int getIdInventario() {
		return idInventario;
	}
	public void setIdInventario(int idInventario) {
		this.idInventario = idInventario;
	}
	public int getExistenciaInicial() {
		return existenciaInicial;
	}
	public void setExistenciaInicial(int existenciaInicial) {
		this.existenciaInicial = existenciaInicial;
	}
	public BigDecimal getPrecioCompra() {
		return precioCompra;
	}
	public void setPrecioCompra(BigDecimal precioCompra) {
		this.precioCompra = precioCompra;
	}
	public Date getFechaEntrada() {
		return fechaEntrada;
	}
	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}
	public Date getFechaSalida() {
		return fechaSalida;
	}
	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getIngresadoPor() {
		return ingresadoPor;
	}
	public void setIngresadoPor(String ingresadoPor) {
		this.ingresadoPor = ingresadoPor;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public int getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	public String getNombreProducto() {
		return nombreProducto;
	}
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public int getExistenciaFinal() {
		return existenciaFinal;
	}
	public void setExistenciaFinal(int existenciaFinal) {
		this.existenciaFinal = existenciaFinal;
	}
	public String getNombreMaterial() {
		return nombreMaterial;
	}
	public void setNombreMaterial(String nombreMaterial) {
		this.nombreMaterial = nombreMaterial;
	}
	public String getNombreProveedor() {
		return nombreProveedor;
	}
	public void setNombreProveedor(String nombreProveedor) {
		this.nombreProveedor = nombreProveedor;
	}
	public String getTipoMovimiento() {
		return tipoMovimiento;
	}
	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}
	public int getCantidadMovimiento() {
		return cantidadMovimiento;
	}
	public void setCantidadMovimiento(int cantidadMovimiento) {
		this.cantidadMovimiento = cantidadMovimiento;
	}
	public int getIdMaterial() {
		return idMaterial;
	}
	public void setIdMaterial(int idMaterial) {
		this.idMaterial = idMaterial;
	}
	public int getIdProveedor() {
		return idProveedor;
	}
	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	
}
