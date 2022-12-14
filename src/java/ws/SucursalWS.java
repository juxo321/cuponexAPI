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
import pojos.Empresa;
import pojos.MensajeRespuesta;
import pojos.Respuesta;
import pojos.Sucursal;


@Path("sucursales")
public class SucursalWS {

    @Context
    private UriInfo context;


    public SucursalWS() {
    }
    
    @Path("obtenerSucursales") 
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Sucursal> obtenerSucursales(){
        List<Sucursal> listaSucursales = null;
        SqlSession conexionbd = MyBatisUtils.getSession();
        if(conexionbd != null){
            try {
                listaSucursales = conexionbd.selectList("sucursales.obtenerSucursales");
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                conexionbd.close();
            }
        }
        return listaSucursales;
    }
    
    @Path("registrarSucursal") 
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta registrarSucursal(
            @FormParam("nombre") String nombre,
            @FormParam("calle") String calle,
            @FormParam("numero") String numero,
            @FormParam("codigoPostal") String codigoPostal,
            @FormParam("colonia") String colonia,
            @FormParam("ciudad") String ciudad,
            @FormParam("telefono") String telefono,
            @FormParam("latitud") String latitud,
            @FormParam("longitud") String longitud,
            @FormParam("nombreEncargado") String nombreEncargado,
            @FormParam("idEmpresa") Integer idEmpresa
            ){
        Sucursal sucursal = new Sucursal();
        sucursal.setNombre(nombre);
        sucursal.setCalle(calle);
        sucursal.setNumero(numero);
        sucursal.setCodigoPostal(codigoPostal);
        sucursal.setColonia(colonia);
        sucursal.setCiudad(ciudad);
        sucursal.setTelefono(telefono);
        sucursal.setLatitud(latitud);
        sucursal.setLongitud(longitud);
        sucursal.setNombreEncargado(nombreEncargado);
        sucursal.setIdEmpresa(idEmpresa);
        
        Respuesta respuesta = new Respuesta();
        respuesta.setError(true);
        
        SqlSession conexionBD = MyBatisUtils.getSession();
        
        if(conexionBD !=null){
            try {
                int resultado = conexionBD.insert("sucursales.registrarSucursal", sucursal);
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
    
    @Path("modificarSucursal") 
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta modificarSucursal(
            @FormParam("idSucursal") Integer idSucursal,
            @FormParam("nombre") String nombre,
            @FormParam("calle") String calle,
            @FormParam("numero") String numero,
            @FormParam("codigoPostal") String codigoPostal,
            @FormParam("colonia") String colonia,
            @FormParam("ciudad") String ciudad,
            @FormParam("telefono") String telefono,
            @FormParam("latitud") String latitud,
            @FormParam("longitud") String longitud,
            @FormParam("nombreEncargado") String nombreEncargado,
            @FormParam("idEmpresa") Integer idEmpresa
            ){
        Sucursal sucursal = new Sucursal();
        sucursal.setIdSucursal(idSucursal);
        sucursal.setNombre(nombre);
        sucursal.setCalle(calle);
        sucursal.setNumero(numero);
        sucursal.setCodigoPostal(codigoPostal);
        sucursal.setColonia(colonia);
        sucursal.setCiudad(ciudad);
        sucursal.setTelefono(telefono);
        sucursal.setLatitud(latitud);
        sucursal.setLongitud(longitud);
        sucursal.setNombreEncargado(nombreEncargado);
        sucursal.setIdEmpresa(idEmpresa);
        
        Respuesta respuesta = new Respuesta();
        respuesta.setError(true);
        
        SqlSession conexionBD = MyBatisUtils.getSession();
        
        if(conexionBD !=null){
            try {
                int resultado = conexionBD.update("sucursales.modificarSucursal", sucursal);
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
    
    @Path("eliminarSucursal")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta eliminarSucursal(
            @FormParam("idSucursal") Integer idSucursal
            ){
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MyBatisUtils.getSession();
        if(conexionBD != null){
            try {
                int resultado = conexionBD.delete("sucursales.eliminarSucursal", idSucursal);
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
