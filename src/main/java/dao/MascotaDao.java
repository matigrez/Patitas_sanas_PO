package dao;

import cl.POO.patitassanas.model.Mascota;

import java.util.List;


public interface MascotaDao {
    void agregar(Mascota mascota) throws PersistenciaException;
    void editar(Mascota mascota) throws PersistenciaException;
    void eliminar(String codigo) throws PersistenciaException;
    List<Mascota> obtenerTodos() throws PersistenciaException;
}
