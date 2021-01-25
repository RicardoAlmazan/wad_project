package mx.ipn.forms.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Municipios")
public class Municipio {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(updatable = false, nullable = false)
  private Integer id;

  private String abrev;
  private String nombre;
  private Integer estado;
  private Integer activo;

  public Municipio() {
  }

  public Municipio(Integer id, String abrev, String nombre, Integer estado, Integer activo) {
    this.id = id;
    this.abrev = abrev;
    this.nombre = nombre;
    this.estado = estado;
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

  public Integer getEstado() {
    return estado;
  }

  public void setEstado(Integer estado) {
    this.estado = estado;
  }

  public Integer getActivo() {
    return activo;
  }

  public void setActivo(Integer activo) {
    this.activo = activo;
  }

  
}
