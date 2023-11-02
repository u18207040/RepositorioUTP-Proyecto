package Interface;

import java.util.List;

import Modelo.Material;
import Modelo.Proveedor;

public interface CRUDMaterial {
	public List listarm();
    public boolean add(Material m);
    public boolean edit(Material mat);
    public List buscarM(String texto);
    
    public List listarp();
    public boolean add(Proveedor p);
    public boolean edit(Proveedor pro);
    public List buscarP(String texto);
}
