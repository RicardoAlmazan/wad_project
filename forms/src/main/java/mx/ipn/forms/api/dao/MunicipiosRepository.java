package mx.ipn.forms.api.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mx.ipn.forms.api.model.Municipio;

public interface MunicipiosRepository extends JpaRepository<Municipio, Integer>{
  
  @Query(value = "SELECT * FROM Municipios WHERE estado=:idEstado AND activo=1", nativeQuery = true)
  List<Municipio> findMunicipiosActivosDelEstado(@Param("idEstado") Integer idEstado);
}
