package mx.ipn.forms.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.ipn.forms.api.model.TipoProblema;

public interface TipoProblemaRepository extends JpaRepository<TipoProblema, Integer>{
  
}
