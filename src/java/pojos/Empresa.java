
package pojos;

public class Empresa {
    private Integer idEmpresa;
    private String nombre;
    private String nombreComercial;
    private String nombreRepresentante;
    private String correo;
    private String calle;
    private String numero;
    private String codigoPostal;
    private String ciudad;
    private String telefono;
    private String paginaWeb;
    private String rfc;
    private Integer IdEstatus;

    public Empresa() {
    }

    public Empresa(Integer idEmpresa, String nombre, String nombreComercial, String nombreRepresentante, String correo, String calle, String numero, String codigoPostal, String ciudad, String telefono, String paginaWeb, String rfc, Integer IdEstatus) {
        this.idEmpresa = idEmpresa;
        this.nombre = nombre;
        this.nombreComercial = nombreComercial;
        this.nombreRepresentante = nombreRepresentante;
        this.correo = correo;
        this.calle = calle;
        this.numero = numero;
        this.codigoPostal = codigoPostal;
        this.ciudad = ciudad;
        this.telefono = telefono;
        this.paginaWeb = paginaWeb;
        this.rfc = rfc;
        this.IdEstatus = IdEstatus;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getNombreRepresentante() {
        return nombreRepresentante;
    }

    public void setNombreRepresentante(String nombreRepresentante) {
        this.nombreRepresentante = nombreRepresentante;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }

    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public Integer getIdEstatus() {
        return IdEstatus;
    }

    public void setIdEstatus(Integer IdEstatus) {
        this.IdEstatus = IdEstatus;
    }
    
    
}
