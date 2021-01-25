package mx.ipn.forms.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Estados")
public class Estado {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(updatable = false, nullable = false)
  private Integer id;
  private String abrev;
  private String nombre;
  private Integer activo;

  public Estado() {
  }

  public Estado(Integer id, String abrev, String nombre, Integer activo) {
    this.id = id;
    this.abrev = abrev;
    this.nombre = nombre;
    this.activo = activo;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getAbrev() {
    return abrev;
  }

  public void setAbrev(String abrev) {
    this.abrev = abrev;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public Integer getActivo() {
    return activo;
  }

  public void setActivo(Integer activo) {
    this.activo = activo;
  }
  
}
