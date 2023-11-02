package Interface;
import Modelo.Movimientos;
import Modelo.Producto;
import java.util.List;

import Modelo.Usuario;

public interface CRUD {
	
	public Usuario validar(String user, String pass);

	public List<?> listar();

	public boolean add(Usuario u);

	public boolean edit(Usuario u);

	public boolean eliminar(int id, String tabla);

	public List<?> buscar(String texto);
	public boolean add(Movimientos mv);
}
