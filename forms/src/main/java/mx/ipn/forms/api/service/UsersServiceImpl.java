package mx.ipn.forms.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import mx.ipn.forms.api.dao.UsersRepository;
import mx.ipn.forms.api.exception.DuplicateResourceException;
import mx.ipn.forms.api.exception.ResourceNotFoundException;
import mx.ipn.forms.api.model.Users;
import mx.ipn.forms.utils.Mail;

@Service
public class UsersServiceImpl implements UsersService {

  @Autowired
  UsersRepository usersRepository;

  @Value("${app.url}")
  private String baseUrl;
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
  public Users validatePassword(Users usuario) {
    try {
      return usersRepository.validatePassword(usuario.getEmail(), usuario.getContrasenia());
    } catch (Exception e) {
      throw new ResourceNotFoundException("User", "email", usuario.getEmail());
    }
  }

  @Override
  public Users registerUser(Users usr) {
    List<Users> usuarios = usersRepository.findByEmail(usr.getEmail());
    if (usuarios.size() == 0) {
      Users nuevo = usersRepository.save(usr);
      mailConfig();
      mail.sendEmail(usr.getEmail(), "Registro completado!", Mail.MAIL_REGISTRO_USR(baseUrl + "/login"));
      return nuevo;
    } else {
      throw new DuplicateResourceException("User", "email", usr.getEmail());
    }
  }

  // @Override
  // public String levantarReporte(String request) {
  //   // TODO Auto-generated method stub
  //   return null;
  // }

  public void mailConfig() {
    mail = new Mail(host, from, password, port);
  }
}
