/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.distri.web;

import ec.espe.distri.modelo.Cliente;
import ec.espe.distri.modelo.Cuenta;
import ec.espe.distri.modelo.Persona;
import ec.espe.distri.servicios.ClienteServicio;
import ec.espe.distri.servicios.CuentaServicio;
import ec.espe.distri.servicios.EmpleadoServicio;
import ec.espe.distri.servicios.MovimientoServicio;
import ec.espe.distri.servicios.PersonaServicio;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author RAUL
 */
@ViewScoped
@ManagedBean
public class ClienteBean implements Serializable {
    @EJB
    private ClienteServicio clienteServicio;
    private List<Cliente> clientes;
    
    @EJB
    private CuentaServicio cuentaServicio;
    private List<Cuenta> consolidado;
    
    @EJB
    private MovimientoServicio movimientoServicio;
    @EJB
    private EmpleadoServicio empleadoServicio;

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public List<Cuenta> getConsolidado() {
        return consolidado;
    }

    public void setConsolidado(List<Cuenta> consolidado) {
        this.consolidado = consolidado;
    }
    

    
    @PostConstruct
    public void inicializar()
    {
        //this.movimientoServicio.retiro(BigDecimal.valueOf(50.52d), 1,"Retiro, Cajero 1: Jose Almendariz");
        this.movimientoServicio.transferencia(BigDecimal.valueOf(50.52d), 1, 2);
        this.clientes = this.clienteServicio.obtenerTodas();
        //this.consolidado = this.cuentaServicio.consolidado(this.clientes.get(0).getCodigo());
        this.consolidado = this.cuentaServicio.obtenerTodas();
        //this.consolidado = this.cuentaServicio.obtenerTodas();
        //empleadoServicio.crearUsuarios();
    }
}
