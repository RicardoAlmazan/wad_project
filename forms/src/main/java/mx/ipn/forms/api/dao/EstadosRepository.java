package mx.ipn.forms.api.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mx.ipn.forms.api.model.Estado;

public interface EstadosRepository extends JpaRepository<Estado, Integer>{
  
  @Query(value = "SELECT * from Estados WHERE activo = 1", nativeQuery = true)
  List<Estado> getEstadosActivos();
}
