/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Operaciones;

import Ventanas.Escritorio;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jorge Videla Araya
 */
public class Conector {

    public Connection connect;//solo se agrego public

    public void connect() {
       
        try {
           
            connect = DriverManager.getConnection("jdbc:sqlite:Bodega.db");
            if (connect != null) {
                System.out.println("Conectado");
            }
        } catch (SQLException ex) {
            System.err.println("No se ha podido conectar a la base de datos\n" + ex.getMessage());
        }
    }

    public void close() {
        try {
            connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void guardarSesion(int id) {
        
        int Id = id;
        DateFormat dfDateShort = DateFormat.getDateInstance(DateFormat.SHORT);
        java.util.Date fecha = new Date();
        String fechadata = dfDateShort.format(fecha);
        String sql = "Insert into Sesion (Fecha,Id_usuario) Values ('" + fechadata + "','" + Id + "')";
        try {
            PreparedStatement st = connect.prepareStatement(sql);
            st.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connect != null) {
                try {
                    connect.close();
                } catch (SQLException e) {

                }

            }
        }
    }

    public boolean validaUsuario(String sql) {
        ResultSet resultado = null;
        boolean valor = false;
        PreparedStatement st;
        try {
            st = connect.prepareStatement(sql);
            resultado = st.executeQuery();
            if (resultado.next()) {
                valor = true;
                int id = resultado.getInt(1);
                guardarSesion(id);
                Escritorio.idData[1] = id;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connect != null) {
                try {
                    connect.close();
                } catch (SQLException e) {

                }

            }
        }

        return valor;

    }
   
    public ResultSet buscarId(String codigo) {
        ResultSet resultado = null;
        PreparedStatement st;
        try {
            st = connect.prepareStatement("SELECT Id FROM Maestro WHERE Codigo = '"+codigo+"'");
            resultado = st.executeQuery();
           
        } catch (SQLException ex) {
            Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return resultado;
    }
    
    

    public ResultSet buscar(String sql) {
        ResultSet resultado = null;
        PreparedStatement st;
        try {
            st = connect.prepareStatement(sql);
            resultado = st.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    public void guardarDatos(String sql) {

        try {
            PreparedStatement st = connect.prepareStatement(sql);

            st.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connect != null) {
                try {

                    connect.close();
                } catch (SQLException e) {

                }

            }
        }
    }
}
