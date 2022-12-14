/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import mybatis.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import pojos.Empleado;
import pojos.MensajeRespuesta;
import pojos.Respuesta;
import pojos.Usuario;


@Path("usuarios")
public class UsuarioWS {

    @Context
    private UriInfo context;


    public UsuarioWS() {
    }
    
    @Path("registrarUsuario") 
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta registrarUsuario(
            @FormParam("nombre") String nombre,
            @FormParam("apellidoPaterno") String apellidoPaterno,
            @FormParam("apellidoMaterno") String apellidoMaterno,
            @FormParam("telefono") String telefono,
            @FormParam("correo") String correo,
            @FormParam("calle") String calle,
            @FormParam("numero") String numero,
            @FormParam("fechaNacimiento") String fechaNacimiento,
            @FormParam("password") String password
            ){
        Usuario usuarioNuevo = new Usuario();
        usuarioNuevo.setNombre(nombre);
        usuarioNuevo.setApellidoPaterno(apellidoPaterno);
        usuarioNuevo.setApellidoMaterno(apellidoMaterno);
        usuarioNuevo.setTelefono(telefono);
        usuarioNuevo.setCorreo(correo);
        usuarioNuevo.setCalle(calle);
        usuarioNuevo.setNumero(numero);
        usuarioNuevo.setFechaNacimiento(fechaNacimiento);
        usuarioNuevo.setPassword(password);
        
        Respuesta respuesta = new Respuesta();
        respuesta.setError(true);
        
        SqlSession conexionBD = MyBatisUtils.getSession();
        
        if(conexionBD !=null){
            try {
                int resultado = conexionBD.insert("usuarios.registrarUsuario", usuarioNuevo);
                conexionBD.commit();
                if(resultado > 0){
                    respuesta.setError(false);
                    respuesta.setMensaje(MensajeRespuesta.registroExistoso);
                }else{
                    respuesta.setMensaje(MensajeRespuesta.errorRegistroRegistrado);
                }
            } catch (Exception e) {
                respuesta.setMensaje(e.getMessage());
            }finally{
                conexionBD.close();
            }
        }else{
            respuesta.setMensaje(MensajeRespuesta.errorConexion);
        }
        
        return respuesta;
    }
    
    @Path("modificarUsuario") 
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta modificarUsuario(
            @FormParam("idEmpleado") Integer idEmpleado,
            @FormParam("nombre") String nombre,
            @FormParam("apellidoPaterno") String apellidoPaterno,
            @FormParam("apellidoMaterno") String apellidoMaterno,
            @FormParam("password") String password
            ){
        Empleado empleado = new Empleado();
        empleado.setIdEmpleado(idEmpleado);
        empleado.setNombre(nombre);
        empleado.setApellidoPaterno(apellidoPaterno);
        empleado.setApellidoMaterno(apellidoMaterno);
        empleado.setPassword(password);
        
        Respuesta respuesta = new Respuesta();
        respuesta.setError(true);
        
        SqlSession conexionBD = MyBatisUtils.getSession();
        
        if(conexionBD !=null){
            try {
                int resultado = conexionBD.update("empleados.modificarEmpleado", empleado);
                conexionBD.commit();
                if(resultado > 0){
                    respuesta.setError(false);
                    respuesta.setMensaje(MensajeRespuesta.registroModificado);
                }else{
                    respuesta.setMensaje(MensajeRespuesta.errorRegistroModificado);
                }
            } catch (Exception e) {
                respuesta.setMensaje(e.getMessage());
            }finally{
                conexionBD.close();
            }
        }else{
            respuesta.setMensaje(MensajeRespuesta.errorConexion);
        }
        
        return respuesta;
    }


    
}
