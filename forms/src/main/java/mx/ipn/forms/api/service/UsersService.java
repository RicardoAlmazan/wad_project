package mx.ipn.forms.api.service;

import mx.ipn.forms.api.model.Users;

public interface UsersService {
  
  Users validatePassword(Users usuario);
  Users registerUser(Users usr);

}
