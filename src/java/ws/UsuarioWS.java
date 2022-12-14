/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
    
    @Path("obtenerUsuarios") 
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> obtenerUsuarios(){
        List<Usuario> listaUsuarios = null;
        SqlSession conexionbd = MyBatisUtils.getSession();
        if(conexionbd != null){
            try {
                listaUsuarios = conexionbd.selectList("usuarios.obtenerUsuarios");
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                conexionbd.close();
            }
        }
        return listaUsuarios;
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
            @FormParam("idUsuario") Integer idUsuario,
            @FormParam("nombre") String nombre,
            @FormParam("apellidoPaterno") String apellidoPaterno,
            @FormParam("apellidoMaterno") String apellidoMaterno,
            @FormParam("telefono") String telefono,
            @FormParam("calle") String calle,
            @FormParam("numero") String numero,
            @FormParam("fechaNacimiento") String fechaNacimiento,
            @FormParam("password") String password
            ){
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(idUsuario);
        usuario.setNombre(nombre);
        usuario.setApellidoPaterno(apellidoPaterno);
        usuario.setApellidoMaterno(apellidoMaterno);
        usuario.setTelefono(telefono);
        usuario.setCalle(calle);
        usuario.setNumero(numero);
        usuario.setFechaNacimiento(fechaNacimiento);
        usuario.setPassword(password);
        
        
        Respuesta respuesta = new Respuesta();
        respuesta.setError(true);
        
        SqlSession conexionBD = MyBatisUtils.getSession();
        
        if(conexionBD !=null){
            try {
                int resultado = conexionBD.update("usuarios.modificarUsuario", usuario);
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
    
    @Path("eliminarUsuario")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta eliminarUsuario(
            @FormParam("idUsuario") Integer idUsuario
            ){
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MyBatisUtils.getSession();
        if(conexionBD != null){
            try {
                int resultado = conexionBD.delete("usuarios.eliminarUsuario", idUsuario);
                conexionBD.commit();
                if(resultado > 0){
                    respuesta.setError(false);
                    respuesta.setMensaje(MensajeRespuesta.registroEliminado);
                }else{
                    respuesta.setError(true);
                    respuesta.setMensaje(MensajeRespuesta.errorRegistroEliminado);
                }
            } catch (Exception e) {
                respuesta.setError(true);
                respuesta.setMensaje(e.getMessage());
            }finally{
                conexionBD.close();
            }
        }else{
            respuesta.setError(true);
            respuesta.setMensaje(MensajeRespuesta.errorConexion);
        }
        return respuesta;
    }


    
}
