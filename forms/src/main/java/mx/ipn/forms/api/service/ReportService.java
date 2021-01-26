package mx.ipn.forms.api.service;

import java.io.File;

import mx.ipn.forms.api.model.Report;

public interface ReportService {

	Report registrarReporte(Report reporte);

	File generarDocumento(Integer id);
  
}
