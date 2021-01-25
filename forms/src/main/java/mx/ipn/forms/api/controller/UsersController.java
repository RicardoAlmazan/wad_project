package mx.ipn.forms.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.ipn.forms.api.model.Users;
import mx.ipn.forms.api.service.UsersService;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UsersController {
  @Autowired
  private UsersService service;

  @PostMapping("")
  public Users registrarUsuario(@RequestBody Users usr){
    return service.registerUser(usr);
  }

  @PostMapping("/login")
  public Users validarCredenciales(@RequestBody Users usuario){
    return service.validatePassword(usuario);
  }

}
