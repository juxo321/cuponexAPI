/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

/**
 *
 * @author jus_p
 */
public class Promocion {
    private Integer idPromocion;
    private String nombrePromocion;
    private String foto;
    private String descripcion;
    private String fechaInicio;
    private String fechaTermino;
    private String restricciones;
    private Integer idTipoPromocion;
    private Integer porcentajeDescuento;
    private Float costoPromocion;
    private Integer idCategoria;
    private Integer idEstatus;

    public Promocion() {
    }

    public Promocion(Integer idPromocion, String nombrePromocion, String foto, String descripcion, String fechaInicio, String fechaTermino, String restricciones, Integer idTipoPromocion, Integer porcentajeDescuento, Float costoPromocion, Integer idCategoria, Integer idEstatus) {
        this.idPromocion = idPromocion;
        this.nombrePromocion = nombrePromocion;
        this.foto = foto;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaTermino = fechaTermino;
        this.restricciones = restricciones;
        this.idTipoPromocion = idTipoPromocion;
        this.porcentajeDescuento = porcentajeDescuento;
        this.costoPromocion = costoPromocion;
        this.idCategoria = idCategoria;
        this.idEstatus = idEstatus;
    }

    public Integer getIdPromocion() {
        return idPromocion;
    }

    public void setIdPromocion(Integer idPromocion) {
        this.idPromocion = idPromocion;
    }

    public String getNombrePromocion() {
        return nombrePromocion;
    }

    public void setNombrePromocion(String nombrePromocion) {
        this.nombrePromocion = nombrePromocion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaTermino() {
        return fechaTermino;
    }

    public void setFechaTermino(String fechaTermino) {
        this.fechaTermino = fechaTermino;
    }

    public String getRestricciones() {
        return restricciones;
    }

    public void setRestricciones(String restricciones) {
        this.restricciones = restricciones;
    }

    public Integer getIdTipoPromocion() {
        return idTipoPromocion;
    }

    public void setIdTipoPromocion(Integer idTipoPromocion) {
        this.idTipoPromocion = idTipoPromocion;
    }

    public Integer getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(Integer porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public Float getCostoPromocion() {
        return costoPromocion;
    }

    public void setCostoPromocion(Float costoPromocion) {
        this.costoPromocion = costoPromocion;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Integer getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(Integer idEstatus) {
        this.idEstatus = idEstatus;
    }

    
    
    
}
