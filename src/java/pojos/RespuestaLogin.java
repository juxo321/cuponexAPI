package pojos;


public class RespuestaLogin {
    private Boolean error;
    private String mensaje;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String token;

    public RespuestaLogin() {
    }

    public RespuestaLogin(Boolean error, String mensaje, String nombre, String apellidoPaterno, String apellidoMaterno, String token) {
        this.error = error;
        this.mensaje = mensaje;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.token = token;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "RespuestaLogin{" + "error=" + error + ", mensaje=" + mensaje + ", nombre=" + nombre + ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno + ", token=" + token + '}';
    }
    
    
}
