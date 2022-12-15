package ws;

import java.util.Base64;
import java.util.HashMap;
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
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import mybatis.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import pojos.Empresa;
import pojos.MensajeRespuesta;
import pojos.Promocion;
import pojos.Respuesta;


@Path("promociones")
public class PromocionWS {

    @Context
    private UriInfo context;


    public PromocionWS() {
    }
    
     @Path("obtenerPromociones") 
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Promocion> obtenerPromociones(){
        List<Promocion> listaPromociones = null;
        SqlSession conexionbd = MyBatisUtils.getSession();
        if(conexionbd != null){
            try {
                listaPromociones = conexionbd.selectList("empresa.obtenerEmpresas");
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                conexionbd.close();
            }
        }
        return listaPromociones;
    }
    
    @Path("registrarPromocion") 
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta registrarPromocion(
            @FormParam("nombrePromocion") String nombrePromocion,
            @FormParam ("foto") byte[] foto,
            @FormParam("descripcion") String descripcion,
            @FormParam("fechaInicio") String fechaInicio,
            @FormParam("fechaTermino") String fechaTermino,
            @FormParam("restricciones") String restricciones,
            @FormParam("idTipoPromocion") Integer idTipoPromocion,
            @FormParam("porcentajeDescuento") Integer porcentajeDescuento,
            @FormParam("costoPromocion") Float costoPromocion,
            @FormParam("idCategoria") Integer idCategoria,
            @FormParam("idEstatus") Integer idEstatus,
            @FormParam("idSucursal") Integer idSucursal
            ){
        Promocion promocionNueva = new Promocion();
        promocionNueva.setNombrePromocion(nombrePromocion);
        promocionNueva.setDescripcion(descripcion);
        promocionNueva.setFechaInicio(fechaInicio);
        promocionNueva.setFechaTermino(fechaTermino);
        promocionNueva.setRestricciones(restricciones);
        promocionNueva.setIdTipoPromocion(idTipoPromocion);
        promocionNueva.setPorcentajeDescuento(porcentajeDescuento);
        promocionNueva.setCostoPromocion(costoPromocion);
        promocionNueva.setIdCategoria(idCategoria);
        promocionNueva.setIdEstatus(idEstatus);
        promocionNueva.setIdSucursal(idSucursal);
        
        Respuesta respuesta = new Respuesta();
        respuesta.setError(true);
        
        SqlSession conexionBD = MyBatisUtils.getSession();
        
        if(conexionBD !=null){
            try {
                int resultado = conexionBD.insert("promociones.registrarPromocion", promocionNueva);
                conexionBD.commit();
                if(resultado > 0){
                    if(subirFoto(foto, resultado)){
                        respuesta.setError(false);
                        respuesta.setMensaje(MensajeRespuesta.registroExistoso);
                    }else{
                        respuesta.setMensaje(MensajeRespuesta.errorRegistroRegistrado);
                    }
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
    
//    @Path("modificarPromocion") 
//    @PUT
//    @Produces(MediaType.APPLICATION_JSON)
//    public Respuesta modificarPromocion(
//            @FormParam("nombre") String nombre,
//            //@FormParam ("foto") byte[] foto,
//            @FormParam("nombreComercial") String nombreComercial,
//            @FormParam("nombreRepresentante") String nombreRepresentante,
//            @FormParam("correo") String correo,
//            @FormParam("calle") String calle,
//            @FormParam("numero") String numero,
//            @FormParam("codigoPostal") String codigoPostal,
//            @FormParam("ciudad") String ciudad,
//            @FormParam("telefono") String telefono,
//            @FormParam("paginaWeb") String paginaWeb,
//            @FormParam("idEstatus") Integer idEstatus
//            ){
//        Empresa empresa = new Empresa();
//        empresa.setNombre(nombre);
//        empresa.setNombreComercial(nombreComercial);
//        empresa.setNombreRepresentante(nombreRepresentante);
//        empresa.setCorreo(correo);
//        empresa.setCalle(calle);
//        empresa.setNumero(numero);
//        empresa.setCodigoPostal(codigoPostal);
//        empresa.setCiudad(ciudad);
//        empresa.setTelefono(telefono);
//        empresa.setPaginaWeb(paginaWeb);
//        empresa.setIdEstatus(idEstatus);
//        
//        Respuesta respuesta = new Respuesta();
//        respuesta.setError(true);
//        
//        SqlSession conexionBD = MyBatisUtils.getSession();
//        
//        if(conexionBD !=null){
//            try {
//                int resultado = conexionBD.update("empresa.modificarEmpresa", empresa);
//                System.out.println(resultado);
//                conexionBD.commit();
//                if(resultado > 0){
//                    respuesta.setError(false);
//                    respuesta.setMensaje(MensajeRespuesta.registroModificado);
//                }else{
//                    respuesta.setMensaje(MensajeRespuesta.errorRegistroModificado);
//                }
//            } catch (Exception e) {
//                respuesta.setMensaje(e.getMessage());
//            }finally{
//                conexionBD.close();
//            }
//        }else{
//            respuesta.setMensaje(MensajeRespuesta.errorConexion);
//        }
//        
//        return respuesta;
//    }
//    
//    @Path("eliminarPromocion")
//    @DELETE
//    @Produces(MediaType.APPLICATION_JSON)
//    public Respuesta eliminarPromocion(
//            @FormParam("idEmpresa") Integer idEmpresa
//            ){
//        Respuesta respuesta = new Respuesta();
//        SqlSession conexionBD = MyBatisUtils.getSession();
//        if(conexionBD != null){
//            try {
//                int resultado = conexionBD.delete("empresa.eliminarEmpresa", idEmpresa);
//                conexionBD.commit();
//                if(resultado > 0){
//                    respuesta.setError(false);
//                    respuesta.setMensaje(MensajeRespuesta.registroEliminado);
//                }else{
//                    respuesta.setError(true);
//                    respuesta.setMensaje(MensajeRespuesta.errorRegistroEliminado);
//                }
//            } catch (Exception e) {
//                respuesta.setError(true);
//                respuesta.setMensaje(e.getMessage());
//            }finally{
//                conexionBD.close();
//            }
//        }else{
//            respuesta.setError(true);
//            respuesta.setMensaje(MensajeRespuesta.errorConexion);
//        }
//        return respuesta;
//    }
//    
//
    public boolean subirFoto(byte[] foto, Integer idPromocion){
        boolean fotoSubida = false;
        SqlSession conexionBD = MyBatisUtils.getSession();
        if(conexionBD != null){
            try {
                HashMap<String, Object> parametros = new HashMap<>();
                parametros.put("foto", foto);
                parametros.put("idPromocion", idPromocion);
                int filasAfectadas = conexionBD.update("promociones.subirFotoPromocion", parametros);
                conexionBD.commit();
                if(filasAfectadas > 0){
                    fotoSubida = true;
                    return fotoSubida;
                }else{
                    return fotoSubida; 
                }
            } catch (Exception e) {
                return fotoSubida;
            }finally{
                conexionBD.close();
            }
        }else{
            return fotoSubida;     
        }
    }
    
//    @Path("obtenerFoto/{idMedico}")
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public Medico obtenerFoto(@PathParam("idMedico") Integer idMedico){
//        Medico medico = new Medico();
//        SqlSession conexionBD = MyBatisUtils.getSession();
//        if(conexionBD !=null){
//            try {
//                medico = conexionBD.selectOne("medicos.obtenerFoto", idMedico);
//            } catch (Exception e) {
//            
//            }finally{
//                conexionBD.close();
//            }
//        }
//        return medico;
//    }

}
