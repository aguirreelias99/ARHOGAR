/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.conexion;
import vistas.miInterface;
import vistas.miInterfaceCon;

public class Usuario implements miInterface {

    //Atributos
    private int id_usuario;
    private String nombre_usuario;
    private String contraseña_usuario;
    private int id_rol;
    private String rol;
    //Variables
    public ResultSet rs_abm;
    conexion con = null;

    public void setDatos(int id, String n, String ps, int r) {
        this.id_usuario = id;
        this.nombre_usuario = n;
        this.contraseña_usuario = ps;
        this.id_rol = r;
    }

    public String getDato(String att) {
        String rt = "0";
        if (att.equals("id_usuario")) {
            rt = Integer.toString(this.id_usuario);
        }
        if (att.equals("nombre_usuario")) {
            rt = this.nombre_usuario;
        }
        if (att.equals("contraseña_usuario")) {
            rt = this.contraseña_usuario;
        }
        if (att.equals("id_rol")) {
            rt = Integer.toString(this.id_rol);
        }
        if (att.equals("rol")) {
            rt = this.rol;
        }
        return rt;
    }

    @Override
    public int imModo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void imGrabar() {
        int li_grabados, li_act, li_ultimo = 0;
        String dpto, ls_det;
        li_act = this.id_usuario;
        try {
            rs_abm = con.ejecuteSQL("SELECT max(id_usuario) as id_usuario FROM usuario ORDER BY id_usuario");
            li_ultimo = this.id_usuario;
            if (li_act == 0) {
                if (rs_abm.last()) {
                    li_ultimo = rs_abm.getInt("id_usuario") + 1;
                } else {
                    li_ultimo = li_ultimo + 1;
                }
            } else {
                imActualizar();
                return;
            }
            String sqlinsert = "INSERT INTO USUARIO (id_usuario, id_rol, nombre_usuario, contraseña_usuario ) values ("
                    + li_ultimo + ","
                    + this.id_rol + ",'"
                    + this.nombre_usuario + "','"
                    + this.contraseña_usuario + "')";
            li_grabados = con.ejecuteUPD(sqlinsert);
            if (li_grabados > 0) {
                JOptionPane.showMessageDialog(null, "Usuario guardado con éxito");
            } else {
                JOptionPane.showMessageDialog(null, "Se ha producido un problema al grabar");
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "ERROR al intentar grabar el registro: " + erro);
        }
    }

    @Override
    public void imActualizar() {
        int li_grabados = 0;
        String sqlinsert = "UPDATE USUARIO SET nombre = '" + this.nombre_usuario
                + "', clave = '" + this.contraseña_usuario
                + "', id_rol = " + this.id_rol
                + " WHERE id_usuario = " + this.id_usuario;
        li_grabados = con.ejecuteUPD(sqlinsert);
        if (li_grabados > 0) {
            JOptionPane.showMessageDialog(null, "Datos modificados con éxito!");
        } else {
            JOptionPane.showMessageDialog(null, "Se ha producido un problema al modificar");
        }

    }

    @Override
    public void imBorrar() {
        int li_grabados = 0;
        String sqlinsert = "DELETE FROM usuario "
                + " WHERE id_usuario = " + this.id_usuario;
        li_grabados = con.ejecuteUPD(sqlinsert);
        if (li_grabados > 0) {
            JOptionPane.showMessageDialog(null, "Usuario eliminado con éxito!");
        } else {
            JOptionPane.showMessageDialog(null, "Se ha producido un problema al borrar");
        }
    }

    @Override
    public void imNuevo() {
        setDatos(0, "", "", 0);
    }

    @Override
    public void imBuscar() {
        String msg = "";
        try {
            rs_abm = con.ejecuteSQL("select * from usuario where id_usuario =" + this.id_usuario);
            if (rs_abm.first()) {
                setDatos(rs_abm.getInt("id_usuario"), rs_abm.getString("nombre"), rs_abm.getString("clave"), rs_abm.getInt("id_rol"));
            } else {
                JOptionPane.showMessageDialog(null, "Usuario " + this.id_usuario + " no existe");
                setDatos(0, "", "", 0);
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, msg + " - \nERROR: " + erro);
        }

    }
     public int imBuscarUsuClave() {
        String msg = "";
        int rows = 0;
        try{
            rs_abm = con.ejecuteSQL("SELECT * FROM usuario WHERE nombre_usuario ='"+this.nombre_usuario+"' AND contraseña_usuario ='"+this.contraseña_usuario+"'");
//            rs_abm = con.ejecuteSQL("select * from usuario where id_usuario ="+this.id_usuario);
            if (rs_abm.first()){
                setDatos(rs_abm.getInt("id_usuario"), rs_abm.getString("nombre_usuario"), rs_abm.getString("contraseña_usuario"), rs_abm.getInt("id_rol"));
                rows = 1;
            }else{
                setDatos(0, "", "", 0);
                rows = 0;
            }
        } catch(SQLException erro){
            JOptionPane.showMessageDialog(null,  msg+" - \nERROR: "+erro);
            rows =0;
        }
        return rows;
    }
    @Override
    public void imPrimero() {
        try {
            rs_abm = con.ejecuteSQL("select * from usuario ORDER BY id_usuario ASC LIMIT 1");
            if (rs_abm.first()) {
                setDatos(rs_abm.getInt("id_usuario"), rs_abm.getString("nombre_usuario"), rs_abm.getString("contraseña_usuario"), rs_abm.getInt("id_rol"));
            } else {
                setDatos(0, "", "", 0);
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, " - \nERROR: " + erro);
        }
    }

    @Override
    public void imSiguiente() {
        try {
            rs_abm = con.ejecuteSQL("select * from usuario WHERE id_usuario > " + this.id_usuario + " ORDER BY id_usuario ASC LIMIT 1");
            if (rs_abm.first()) {
                setDatos(rs_abm.getInt("id_usuario"), rs_abm.getString("nombre_usuario"), rs_abm.getString("contraseña_usuario"), rs_abm.getInt("id_rol"));
            } else {
                setDatos(0, "", "", 0);
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, " - \nERROR: " + erro);
        }
    }

    @Override
    public void imAnterior() {
        try {
            rs_abm = con.ejecuteSQL("select * from usuario WHERE id_usuario < " + this.id_usuario + " ORDER BY id_usuario DESC LIMIT 1");
            if (rs_abm.first()) {
                setDatos(rs_abm.getInt("id_usuario"), rs_abm.getString("nombre_usuario"), rs_abm.getString("contraseña_usuario"), rs_abm.getInt("id_rol"));
            } else {
                setDatos(0, "", "", 0);
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, " - \nERROR: " + erro);
        }
    }

    @Override
    public void imUltimo() {
        try {
            rs_abm = con.ejecuteSQL("select * from usuario ORDER BY id_usuario DESC LIMIT 1");
            if (rs_abm.first()) {
                setDatos(rs_abm.getInt("id_usuario"), rs_abm.getString("nombre_usuario"), rs_abm.getString("contraseña_usuario"), rs_abm.getInt("id_rol"));
            } else {
                setDatos(0, "", "", 0);
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, " - \nERROR: " + erro);
        }
    }

    @Override
    public void imImprimir() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void imInsDet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void imDelDet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void imCerrar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean imAbierto() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void imConect(conexion coneccion) {
        con = coneccion;
    }

    @Override
    public void imAbrir() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void imFiltrar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
