/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
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
import pojos.RespuestaLogin;


@Path("acceso")
public class AccesoWS {

    @Context
    private UriInfo context;


    @Path("escritorio")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaLogin validarCredencialesEscritorio(
            @FormParam("correo") String correo,
            @FormParam("password")String password
            ){
        RespuestaLogin respuesta = new RespuestaLogin();
        Empleado empleadoSesion = new Empleado();
        empleadoSesion.setCorreo(correo);
        empleadoSesion.setPassword(password);
        
        SqlSession conexionBD = MyBatisUtils.getSession();

        if(conexionBD != null){
            try {
                Empleado empleado = conexionBD.selectOne("empleados.validarCredencialesEscritorio", empleadoSesion);
                conexionBD.commit();
                if(empleado != null){
                    respuesta.setError(false);
                    respuesta.setMensaje(MensajeRespuesta.credencialesCorrectas);
                    respuesta.setNombre(empleado.getNombre());
                    respuesta.setApellidoPaterno(empleado.getApellidoPaterno());
                }else{
                    respuesta.setError(true);
                    respuesta.setMensaje(MensajeRespuesta.credencialesIncorrectas);
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
    
//    @Path("movil")
//    @POST
//    public RespuestaLogin validarCredencialesMovil(
//            @FormParam("usuario") String usuario,
//            @FormParam("password")String password
//            ){
//        RespuestaLogin respuesta = new RespuestaLogin();
//        Paciente pacienteNuevo = new Paciente();
//        pacienteNuevo.setUsuario(usuario);
//        pacienteNuevo.setPassword(password);
//        
//        SqlSession conexionBD = MyBatisUtils.getSession();
//
//        if(conexionBD != null){
//            try {
//                Paciente paciente = conexionBD.selectOne("pacientes.validarCredenciales", pacienteNuevo);
//                conexionBD.commit();
//                if(paciente != null){
//                    respuesta.setError(false);
//                    respuesta.setMensaje("Usuario correcto...");
//                    respuesta.setNombre(paciente.getNombre());
//                    respuesta.setApellidoPaterno(paciente.getApellidoPaterno());
//                }else{
//                    respuesta.setError(true);
//                    respuesta.setMensaje("Credenciales de acceso incorrectas");
//                }
//            } catch (Exception e) {
//                respuesta.setError(true);
//                respuesta.setMensaje(e.getMessage());
//            }finally{
//                conexionBD.close();
//            }
//        }else{
//            respuesta.setError(true);
//            respuesta.setMensaje("Servicio no disponible, intentelo más tarde");
//        }
//        return respuesta; 
//       
//    
//    }


   
}
