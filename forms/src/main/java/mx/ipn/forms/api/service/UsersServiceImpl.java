package mx.ipn.forms.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.ipn.forms.api.dao.UsersRepository;
import mx.ipn.forms.api.exception.DuplicateResourceException;
import mx.ipn.forms.api.model.Users;

@Service
public class UsersServiceImpl implements UsersService {

  @Autowired
  UsersRepository usersRepository;

  @Override
  public Users validatePassword(Users usuario) {
    return usersRepository.validatePassword(usuario.getEmail(), usuario.getContrasenia());
  }

  @Override
  public Users registerUser(Users usr) {
    List<Users> usuarios = usersRepository.findByEmail(usr.getEmail());
    if (usuarios.size() == 0) {
      return usersRepository.save(usr);
    } else {
      throw new DuplicateResourceException("User", "email", usr.getEmail());
    }
  }

}
