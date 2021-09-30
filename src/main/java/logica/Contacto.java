
package logica;

import persistencia.Conexion;
import java.sql.*;
import java.util.*;

public class Contacto {
    private int identificacion;
    private String nombre;
    private String apellido;
    private String genero;
    private String tipoIdentificacion;
    private String telefono;
    private String dirección;
    private String correo;

    
    public int getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }


    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the dirección
     */
    public String getDirección() {
        return dirección;
    }

    /**
     * @param dirección the dirección to set
     */
    public void setDirección(String dirección) {
        this.dirección = dirección;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    public boolean guardarContacto(){
        Conexion conexion = new Conexion();
        
        String sentencia = "INSERT INTO contactos(identificadion, nombre, apellido, genero, tipoIdentificaion, telefono, direccion, correo"
                +"VALUES ( '" + this.identificacion + "','" + this.nombre + "',"
                + "'" + this.apellido + "','" + this.genero + "','" + this.tipoIdentificacion + "',"
                + "'" + this.telefono + "','" + this.dirección + "','" + this.correo + "');";
        if (conexion.setAutoCommitBD(false)){
            if (conexion.insertarBD(sentencia)){
                conexion.commitBD();
                conexion.cerrarConexion();
                return true;
            }else{
                conexion.rollbackBD();
                conexion.cerrarConexion();
                return false;
            }
        }else{
            conexion.cerrarConexion();
            return false;
        }
    }
    
    public boolean borrarContacto(int identificacion){
        String sentencia = "DELETE FROM contactos WHERE identificacion ='" + identificacion + "'";
        Conexion conexion = new Conexion();
        if (conexion.setAutoCommitBD(false)){
            if (conexion.actualizarBD(sentencia)){
                conexion.commitBD();
                conexion.cerrarConexion();
                return true;
            }else{
                conexion.rollbackBD();
                conexion.cerrarConexion();
                return false;
            }
        }else{
            conexion.cerrarConexion();
            return false;
        }
    }
    
    public boolean actualizarContacto(){
    Conexion conexion = new Conexion();
    String sentencia = "UPDATE contactos SET nombre='" + this.nombre + "',apellido='" + this.apellido + "',genero='" + this.genero
            + "',tipoIdentificacion='" + this.tipoIdentificacion + "',telefono='" + this.telefono + "',direccion='" + this.dirección
            +"' WHERE identificacion='" + this.identificacion + ";"; 
        if (conexion.setAutoCommitBD(false)){
            if (conexion.actualizarBD(sentencia)){
                conexion.commitBD();
                conexion.cerrarConexion();
                return true;
            }else{
                conexion.rollbackBD();
                conexion.cerrarConexion();
                return false;
            }
        }else{
            conexion.cerrarConexion();
            return false;
        }
    }    
    
    public List<Contacto> listarContactos() throws SQLException{
        Conexion conexion = new Conexion();
        List<Contacto> listaContactos = new ArrayList<>();
        String sql = "select * from contactos order by identificacion asc";
        ResultSet rs = conexion.consultarBD(sql);
        Contacto c;
        while(rs.next()){
            c = new Contacto();
            c.setIdentificacion(rs.getInt("identificacion"));
            c.setNombre(rs.getString("nombre"));
            c.setApellido(rs.getString("apellido"));
            c.setGenero(rs.getString("genero"));
            c.setTipoIdentificacion(rs.getString("tipoIdentificacion"));
            c.setTelefono(rs.getString("telefono"));
            c.setDirección(rs.getString("direccion"));
            c.setCorreo(rs.getString("correo"));
            listaContactos.add(c); 
        }
        conexion.cerrarConexion();
        return listaContactos;
    }
        
    
}
