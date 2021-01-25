package mx.ipn.forms.api.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mx.ipn.forms.api.model.Users;

public interface UsersRepository extends JpaRepository<Users, Integer> {

  @Query(value = "SELECT * from Pais WHERE idEstatusCatalogo = 1", nativeQuery = true)
  Users getInfo();

  @Query(value = "CALL validateUsr(:mail, :pass)", nativeQuery = true)
  Users validatePassword(@Param("mail") String mail, @Param("pass") String pass);

  List<Users> findByEmail(String email);
}
