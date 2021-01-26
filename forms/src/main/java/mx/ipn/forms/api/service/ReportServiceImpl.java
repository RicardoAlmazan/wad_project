package mx.ipn.forms.api.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import mx.ipn.forms.api.dao.ReportsRepository;
import mx.ipn.forms.api.dao.TipoProblemaRepository;
import mx.ipn.forms.api.dao.UsersRepository;
import mx.ipn.forms.api.exception.ResourceNotFoundException;
import mx.ipn.forms.api.model.Report;
import mx.ipn.forms.api.model.TipoProblema;
import mx.ipn.forms.api.model.Users;
import mx.ipn.forms.utils.DocumentException;
import mx.ipn.forms.utils.Documentos;
import mx.ipn.forms.utils.Mail;
import mx.ipn.forms.utils.Utils;

@Service
public class ReportServiceImpl implements ReportService {

  @Autowired
  UsersRepository usersRepository;

  @Autowired
  ReportsRepository reportsRepository;

  @Autowired
  TipoProblemaRepository tipoProblemaRepository;

  // @Value("${app.url}")
  // private String baseUrl;
  @Value("${mail.smtp.host}")
  private String host;
  @Value("${mail.smtp.user}")
  private String from;
  @Value("${mail.smtp.password}")
  private String password;
  @Value("${mail.smtp.port}")
  private String port;

  Mail mail;

  @Override
  public Report registrarReporte(Report reporte) {
    Report nuevo = reportsRepository.save(reporte);
    Users usr = usersRepository.findById(reporte.getIdSolicitante())
        .orElseThrow(() -> new ResourceNotFoundException("User", "id", reporte.getIdSolicitante()));
    mailConfig();

    TipoProblema tipoProblema = tipoProblemaRepository.findById(reporte.getTipoProblema())
        .orElseThrow(() -> new ResourceNotFoundException("Tipo de problema", "id", reporte.getTipoProblema()));
    mail.sendEmail(usr.getEmail(), "Registro completado!",
        Mail.MAIL_SOLICITUD(nuevo.getNumeroSolicitud(), nuevo.getNombresOwner() + " " + nuevo.getApellidosOwner(),
            tipoProblema.getDescripcion(), Utils.toISO(nuevo.getFechaSolicitud())));
    return nuevo;
  }

  public void mailConfig() {
    mail = new Mail(host, from, password, port);
  }

  @Override
  public File generarDocumento(Integer id) {
    Report existe = reportsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Report", "id", id));

    Users usr = usersRepository.findById(existe.getIdSolicitante())
        .orElseThrow(() -> new ResourceNotFoundException("User", "id", existe.getIdSolicitante()));
    mailConfig();

    TipoProblema tipoProblema = tipoProblemaRepository.findById(existe.getTipoProblema())
        .orElseThrow(() -> new ResourceNotFoundException("Tipo de problema", "id", existe.getTipoProblema()));

    Map<String, Object> variables = new HashMap<>();
    variables.put("numeroSolicitud", existe.getNumeroSolicitud());
    variables.put("fechaSolicitud", existe.getFechaSolicitud());
    variables.put("motivoSolicitud", tipoProblema.getDescripcion());
    variables.put("nombreSolicitante", usr.getNombres() + " " + usr.getApellidos());
    variables.put("nombreOwner", existe.getNombresOwner() + " " + existe.getNombresOwner());

    try {
      return Documentos.transformTemplate(new File("src\\resources\\plantilla.docx"), variables);
    } catch (DocumentException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;

  }
}
