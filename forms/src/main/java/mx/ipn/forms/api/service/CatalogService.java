package mx.ipn.forms.api.service;

import java.util.List;

import mx.ipn.forms.api.model.Estado;
import mx.ipn.forms.api.model.Municipio;

public interface CatalogService {
  
  List<Estado> getEstadosActivos();
  List<Municipio> getMunicipiosEstado(Integer id);
}
