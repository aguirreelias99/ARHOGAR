
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import modelo.conexion;

/**
 *
 * @author Admin
 */
public interface miInterface {
    int imModo();
    void imGrabar();
    void imActualizar();
    void imBorrar();
    void imNuevo();
    void imBuscar();
    void imPrimero();
    void imSiguiente();
    void imAnterior();
    void imUltimo();
    void imImprimir();
    void imInsDet();
    void imDelDet();
    void imCerrar();
    void imFiltrar();
    int imBuscarUsuClave();
    boolean imAbierto();
    void imAbrir();
    void imConect(conexion coneccion);
    

}
