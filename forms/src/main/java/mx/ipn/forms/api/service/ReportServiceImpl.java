package mx.ipn.forms.api.service;

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

}
