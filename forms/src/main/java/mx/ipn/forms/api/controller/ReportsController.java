package mx.ipn.forms.api.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.ipn.forms.api.model.Report;
import mx.ipn.forms.api.service.ReportService;

@RestController
@CrossOrigin
@RequestMapping("/reports")
public class ReportsController {
  
  @Autowired
  ReportService service;

  @PostMapping("")
  public Report registrarReporte(@RequestBody Report reporte){
    return service.registrarReporte(reporte);
  }

  @GetMapping("/{id}/documento")
  public File generarDocumento(@PathVariable Integer id){
    return service.generarDocumento(id);
  }
}
