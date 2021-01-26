package mx.ipn.forms.api.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Solicitud")
public class Report {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(updatable = false, nullable = false)
  private Integer id;

  private String numeroSolicitud;
  private Integer idSolicitante;
  private String nombresOwner;
  private String apellidosOwner;
  private Date fechaSolicitud;
  private Integer tipoProblema;
  private Integer estado;
  private Integer municipio;
  private String direccion;
  private String comentarios;

  public Report() {
  }

  public Report(Integer id, String numeroSoliciud, Integer idSolicitante, String nombresOwner, String apellidosOwner,
      Date fechaSolicitud, Integer tipoProblema, Integer estado, Integer municipio, String direccion,
      String comentarios) {
    this.id = id;
    this.numeroSolicitud = numeroSoliciud;
    this.idSolicitante = idSolicitante;
    this.nombresOwner = nombresOwner;
    this.apellidosOwner = apellidosOwner;
    this.fechaSolicitud = fechaSolicitud;
    this.tipoProblema = tipoProblema;
    this.estado = estado;
    this.municipio = municipio;
    this.direccion = direccion;
    this.comentarios = comentarios;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getNumeroSolicitud() {
    return numeroSolicitud;
  }

  public void setNumeroSolicitud(String numeroSolicitud) {
    this.numeroSolicitud = numeroSolicitud;
  }

  public Integer getIdSolicitante() {
    return idSolicitante;
  }

  public void setIdSolicitante(Integer idSolicitante) {
    this.idSolicitante = idSolicitante;
  }

  public String getNombresOwner() {
    return nombresOwner;
  }

  public void setNombresOwner(String nombresOwner) {
    this.nombresOwner = nombresOwner;
  }

  public String getApellidosOwner() {
    return apellidosOwner;
  }

  public void setApellidosOwner(String apellidosOwner) {
    this.apellidosOwner = apellidosOwner;
  }

  public Date getFechaSolicitud() {
    return fechaSolicitud;
  }

  public void setFechaSolicitud(Date fechaSolicitud) {
    this.fechaSolicitud = fechaSolicitud;
  }

  public Integer getTipoProblema() {
    return tipoProblema;
  }

  public void setTipoProblema(Integer tipoProblema) {
    this.tipoProblema = tipoProblema;
  }

  public Integer getEstado() {
    return estado;
  }

  public void setEstado(Integer estado) {
    this.estado = estado;
  }

  public Integer getMunicipio() {
    return municipio;
  }

  public void setMunicipio(Integer municipio) {
    this.municipio = municipio;
  }

  public String getDireccion() {
    return direccion;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  public String getComentarios() {
    return comentarios;
  }

  public void setComentarios(String comentarios) {
    this.comentarios = comentarios;
  }
}
