
package dao;

import java.util.List;


public interface GenericDAO <E>{
    void insertar(E e);
    E buscarPorNombre();
    List<E> buscarTodos();
}
