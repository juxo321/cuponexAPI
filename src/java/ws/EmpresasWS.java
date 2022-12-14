
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
import pojos.Empresa;
import pojos.MensajeRespuesta;
import pojos.Respuesta;


@Path("empresas")
public class EmpresasWS {

    @Context
    private UriInfo context;


    public EmpresasWS() {
    }
    
    @Path("obtenerEmpresas") 
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Empresa> obtenerEmpresas(){
        List<Empresa> listaEmpresas = null;
        SqlSession conexionbd = MyBatisUtils.getSession();
        if(conexionbd != null){
            try {
                listaEmpresas = conexionbd.selectList("empresa.obtenerEmpresas");
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                conexionbd.close();
            }
        }
        return listaEmpresas;
    }
    
    @Path("registrarEmpresa") 
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta registrarEmpresa(
            @FormParam("nombre") String nombre,
            @FormParam("nombreComercial") String nombreComercial,
            @FormParam("nombreRepresentante") String nombreRepresentante,
            @FormParam("correo") String correo,
            @FormParam("calle") String calle,
            @FormParam("numero") String numero,
            @FormParam("codigoPostal") String codigoPostal,
            @FormParam("ciudad") String ciudad,
            @FormParam("telefono") String telefono,
            @FormParam("paginaWeb") String paginaWeb,
            @FormParam("rfc") String rfc,
            @FormParam("idEstatus") Integer idEstatus
            ){
        Empresa empresaNueva = new Empresa();
        empresaNueva.setNombre(nombre);
        empresaNueva.setNombreComercial(nombreComercial);
        empresaNueva.setNombreRepresentante(nombreRepresentante);
        empresaNueva.setCorreo(correo);
        empresaNueva.setCalle(calle);
        empresaNueva.setNumero(numero);
        empresaNueva.setCodigoPostal(codigoPostal);
        empresaNueva.setCiudad(ciudad);
        empresaNueva.setTelefono(telefono);
        empresaNueva.setPaginaWeb(paginaWeb);
        empresaNueva.setRfc(rfc);
        empresaNueva.setIdEstatus(idEstatus);
        
        Respuesta respuesta = new Respuesta();
        respuesta.setError(true);
        
        SqlSession conexionBD = MyBatisUtils.getSession();
        
        if(conexionBD !=null){
            try {
                int resultado = conexionBD.insert("empresa.registrarEmpresa", empresaNueva);
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
    
    @Path("modificarEmpresa") 
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta modificarEmpresa(
            @FormParam("nombre") String nombre,
            @FormParam("nombreComercial") String nombreComercial,
            @FormParam("nombreRepresentante") String nombreRepresentante,
            @FormParam("correo") String correo,
            @FormParam("calle") String calle,
            @FormParam("numero") String numero,
            @FormParam("codigoPostal") String codigoPostal,
            @FormParam("ciudad") String ciudad,
            @FormParam("telefono") String telefono,
            @FormParam("paginaWeb") String paginaWeb,
            @FormParam("idEstatus") Integer idEstatus
            ){
        Empresa empresa = new Empresa();
        empresa.setNombre(nombre);
        empresa.setNombreComercial(nombreComercial);
        empresa.setNombreRepresentante(nombreRepresentante);
        empresa.setCorreo(correo);
        empresa.setCalle(calle);
        empresa.setNumero(numero);
        empresa.setCodigoPostal(codigoPostal);
        empresa.setCiudad(ciudad);
        empresa.setTelefono(telefono);
        empresa.setPaginaWeb(paginaWeb);
        empresa.setIdEstatus(idEstatus);
        
        Respuesta respuesta = new Respuesta();
        respuesta.setError(true);
        
        SqlSession conexionBD = MyBatisUtils.getSession();
        
        if(conexionBD !=null){
            try {
                int resultado = conexionBD.update("empresa.modificarEmpresa", empresa);
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
    
    @Path("eliminarEmpresa")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta eliminarEmpresa(
            @FormParam("idEmpresa") Integer idEmpresa
            ){
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MyBatisUtils.getSession();
        if(conexionBD != null){
            try {
                int resultado = conexionBD.delete("empresa.eliminarEmpresa", idEmpresa);
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
