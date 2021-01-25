package mx.ipn.forms.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.ipn.forms.api.model.Users;
import mx.ipn.forms.api.service.UsersService;
import mx.ipn.forms.utils.Mail;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UsersController {
  @Value("${mail.smtp.host}")
  private String host;
  @Value("${mail.smtp.user}")
  private String from;
  @Value("${mail.smtp.password}")
  private String password;
  @Value("${mail.smtp.port}")
  private String port;

  @Autowired
  private UsersService service;

  Mail m = new Mail(host, from, password, port);

  @PostMapping("")
  public Users registrarUsuario(@RequestBody Users usr) {
    return service.registerUser(usr);
  }

  @PostMapping("/login")
  public Users validarCredenciales(@RequestBody Users usuario) {
    m.sendEmail("ricardo.almazan.trejo@iikt.com.mx", "Prueba", "Sí funciona :D");
    return service.validatePassword(usuario);
  }

}
