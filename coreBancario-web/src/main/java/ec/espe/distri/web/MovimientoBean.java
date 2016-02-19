/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.distri.web;

import ec.espe.distri.modelo.Movimiento;
import ec.espe.distri.servicios.MovimientoServicio;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author RAUL
 */
@ViewScoped
@ManagedBean
public class MovimientoBean implements Serializable {
    @EJB
    private MovimientoServicio movimientoServicio;
    private List<Movimiento> movimientos;
    private Integer cantidad;
    private Date fechaInicio;
    private Date fechaFin;
    private String seleccion;
    @ManagedProperty(value = "#{loginBean}")
    private LoginBean datosLogin;

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }

    public LoginBean getDatosLogin() {
        return datosLogin;
    }

    public void setDatosLogin(LoginBean datosLogin) {
        this.datosLogin = datosLogin;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getSeleccion() {
        return seleccion;
    }

    public void setSeleccion(String seleccion) {
        this.seleccion = seleccion;
    }
    
    
    
    @PostConstruct
    public void inicializar()
    {
        this.cantidad =10;
        this.fechaFin = new Date();
        this.fechaInicio = new Date();
        this.seleccion="Movimientos";
        cargarNumeroMovimientos();
        
        //Collections.reverse(this.movimientos);
    }
    
    public void cargarMovimientos()
    {
        System.out.println(this.seleccion);
        if(this.seleccion.equals("Fechas"))
            this.cargarFechasMovimientos();
        else
            this.cargarNumeroMovimientos();
    }
    
    public void cargarFechasMovimientos()
    {
        this.movimientos = new ArrayList<>();
        for(Movimiento m: this.getDatosLogin().getCuentaSelected().getMovimientos())
        {
            this.movimientos.add(m);
        }
                
        
        for(int i=0;i<this.movimientos.size();i++)
        {
            if(!((this.movimientos.get(i).getFecha().before(fechaFin)||this.movimientos.get(i).getFecha().equals(fechaFin))
                   &&(this.movimientos.get(i).getFecha().after(fechaInicio)||this.movimientos.get(i).getFecha().equals(fechaInicio))))
            
           // if(this.movimientos.get(i).getFecha().after(fechaFin) || this.movimientos.get(i).getFecha().before(fechaInicio))
            {
                this.movimientos.remove(i);
                i--;
            }
        }
    }
    
    public void cargarNumeroMovimientos()
    {
        this.movimientos = new ArrayList<>();
        for(Movimiento m: this.getDatosLogin().getCuentaSelected().getMovimientos())
        {
            this.movimientos.add(m);
        }
                
        
        for(int i=0;i<this.movimientos.size();i++)
        {
            if(i>=this.cantidad)
            {
                this.movimientos.remove(i);
                i--;
            }
        }
    }
    
    
}
