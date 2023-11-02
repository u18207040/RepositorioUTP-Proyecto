package Interface;

import java.util.List;

import Modelo.Existencia;

public interface CRUDExistencias {
	public List existencia();
    public boolean add(Existencia ex);
    public boolean edit(Existencia ex);
    public List buscarE(String texto);
    
    public boolean addx(Existencia ex);
    public List existenciam();
    public List buscarM(String texto);
    public boolean editP(Existencia ex);
}
