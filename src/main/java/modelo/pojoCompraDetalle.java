/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Admin
 */


public class pojoCompraDetalle {
	
    private int compraid;
    private String cod_barra;
    private String descripcion;
    private double precio_neto;
    private double cantidad;
    private double descuento;
    private double bonificado;
    private double total;


    public pojoCompraDetalle(){

    }

    public pojoCompraDetalle(int compraid, String cod_barra, String descripcion, 
            double precio_neto, double cantidad, double descuento, double bonificado, double total) {
            super();
            this.compraid = compraid;
            this.cod_barra = cod_barra;
            this.descripcion = descripcion;
            this.precio_neto = precio_neto;
            this.cantidad = cantidad;
            this.descuento = descuento;
            this.bonificado = bonificado;
            this.total = total;
    }

    //===================== GETTERS =======================================
    public String getString(String arg){
        switch(arg){
            case "cod_barra" :
                return this.cod_barra;
                //break;
            case "descripcion" :
                return this.descripcion;
                //break;
            default :
                return "";
        }
    }

    public int getInteger(String arg){
        switch(arg){
            case "compraid" :
                return this.compraid;
                //break;
            default :
                return 0;
        }
    }

    public double getDouble(String arg){
        switch(arg){
            case "precio_neto" :
                return this.precio_neto;
                //break;
            case "cantidad" :
                return this.cantidad;
                //break;
            case "descuento" :
                return this.descuento;
                //break;
            case "bonificado" :
                return this.bonificado;
                //break;
            case "total" :
                return this.total;
                //break;
            default :
                return 0;
        }
    }

    //===================== SETTERS =======================================
    public void setString(String attribute, String value){
        switch(attribute){
            case "cod_barra" :
                this.cod_barra = value;
                //break;
            case "descripcion" :
                this.descripcion = value;
                //break;
        }
    }

    public void setInteger(String attribute, int value){
        switch(attribute){
            case "compraid" :
                this.compraid = value;
                //break;
        }
    }

    public void setDouble(String attribute, double value){
        switch(attribute){
            case "precio_neto" :
                this.precio_neto = value;
                //break;
            case "cantidad" :
                this.cantidad = value;
                //break;
            case "descuento" :
                this.descuento = value;
                //break;
            case "bonificado" :
                this.bonificado = value;
                //break;
            case "total" :
                this.total = value;
        }
    }
    //=============================================

}