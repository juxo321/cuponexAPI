/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
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


@Path("empleados")
public class EmpleadoWS {

    @Context
    private UriInfo context;


    public EmpleadoWS() {
    }

    
    @Path("obtenerEmpleados") 
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Empleado> obtenerEmpleados(){
        List<Empleado> listaEmpleados = null;
        SqlSession conexionbd = MyBatisUtils.getSession();
        if(conexionbd != null){
            try {
                listaEmpleados = conexionbd.selectList("empleados.obtenerEmpleados");
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                conexionbd.close();
            }
        }
        return listaEmpleados;
    }
    
    @Path("registrarEmpleado") 
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta registrarEmpleado(
            @FormParam("nombre") String nombre,
            @FormParam("apellidoPaterno") String apellidoPaterno,
            @FormParam("apellidoMaterno") String apellidoMaterno,
            @FormParam("correo") String correo,
            @FormParam("password") String password
            ){
        Empleado empleadoNuevo = new Empleado();
        empleadoNuevo.setNombre(nombre);
        empleadoNuevo.setApellidoPaterno(apellidoPaterno);
        empleadoNuevo.setApellidoMaterno(apellidoMaterno);
        empleadoNuevo.setCorreo(correo);
        empleadoNuevo.setPassword(password);
        
        Respuesta respuesta = new Respuesta();
        respuesta.setError(true);
        
        SqlSession conexionBD = MyBatisUtils.getSession();
        
        if(conexionBD !=null){
            try {
                int resultado = conexionBD.insert("empleados.registrarEmpleado", empleadoNuevo);
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
    
    @Path("modificarEmpleado") 
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta modificarEmpleado(
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
    
    @Path("eliminarEmpleado")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta eliminarEmpleado(
            @FormParam("idEmpleado") Integer idEmpleado
            ){
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MyBatisUtils.getSession();
        if(conexionBD != null){
            try {
                int resultado = conexionBD.delete("empleados.eliminarEmpleado", idEmpleado);
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
