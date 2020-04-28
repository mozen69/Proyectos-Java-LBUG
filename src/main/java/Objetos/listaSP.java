/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

/**
 *
 * @author Jorge Videla Araya
 */
public class listaSP {
    private int item;
    private String codigo;
    private int cantidad;
    private String unidad;
    private String descripcion;
    private String area;
    private String prioridad;
    
    public listaSP(int item,String codigo,int cantidad,String unidad, String descripcion,String area, String prioridad){
        this.item = item;
        this.codigo = codigo;
        this.cantidad = cantidad;
        this.unidad = unidad;
        this.descripcion = descripcion;
        this.area = area;
        this.prioridad = prioridad;   
    }

    /**
     * @return the item
     */
    public int getItem() {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem(int item) {
        this.item = item;
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    /**
     * @return the cantidad
     */
    public String getUnidad() {
        return unidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setUnidad(String cantidad) {
        this.unidad = unidad;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    
    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

     /**
     * @return the area
     */
    public String getArea() {
        return area;
    }

    /**
     * @param area the codigo to set
     */
    public void setArea(String area) {
        this.area = area;
    }
    /**
     * @return the prioridad
     */
    public String getPrioridad() {
        return prioridad;
    }

    /**
     * @param prioridad the prioridad to set
     */
    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }
        
    
    
    }

    