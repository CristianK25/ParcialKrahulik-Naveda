
package dao;

import java.util.List;


public interface GenericDAO <E>{
    boolean insertar(E e);
    E buscar(String x,int y);
    List<E> buscarTodos();
}
