package Interface;

import java.util.List;

import Modelo.Cliente;
import Modelo.Producto;

public interface CRUDProducto {
	public List listarP();
    public boolean add(Producto p);
    public boolean edit(Producto pro);
    public List buscarP(String texto);
    
    public List listarC();
    public boolean add(Cliente c);
    public boolean edit(Cliente cli);
    public List buscarC(String texto);
}
