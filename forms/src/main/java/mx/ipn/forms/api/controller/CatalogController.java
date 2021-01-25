package mx.ipn.forms.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.ipn.forms.api.model.Estado;
import mx.ipn.forms.api.model.Municipio;
import mx.ipn.forms.api.service.CatalogService;

@RestController
@CrossOrigin
@RequestMapping("/catalog")
public class CatalogController {
  @Autowired
  private CatalogService service;

  @GetMapping("/estados")
  public List<Estado> getEstadosActivos() {
    return service.getEstadosActivos();
  }

  @GetMapping("/estados/{id}/municipios")
  public List<Municipio> getMunicipiosEstado(@PathVariable Integer id) {
    return service.getMunicipiosEstado(id);
  }
}
