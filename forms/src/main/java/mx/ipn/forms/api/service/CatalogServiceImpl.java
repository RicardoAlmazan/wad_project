package mx.ipn.forms.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.ipn.forms.api.dao.EstadosRepository;
import mx.ipn.forms.api.dao.MunicipiosRepository;
import mx.ipn.forms.api.exception.ResourceNotFoundException;
import mx.ipn.forms.api.model.Estado;
import mx.ipn.forms.api.model.Municipio;

@Service
public class CatalogServiceImpl implements CatalogService {

  @Autowired
  EstadosRepository estadosRepository;

  @Autowired
  MunicipiosRepository municipiosRepository;

  @Override
  public List<Estado> getEstadosActivos() {
    return estadosRepository.getEstadosActivos();
  }

  @Override
  public List<Municipio> getMunicipiosEstado(Integer id) {
    estadosRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Estado", "id", id));

    return municipiosRepository.findMunicipiosActivosDelEstado(id);
  }

}
