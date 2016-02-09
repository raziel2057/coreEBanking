/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.distri.web;

import ec.espe.distri.modelo.Movimiento;
import ec.espe.distri.servicios.MovimientoServicio;
import java.io.Serializable;
import java.util.Collections;
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
    
    @PostConstruct
    public void inicializar()
    {
        this.movimientos = this.getDatosLogin().getCuentaSelected().getMovimientos();
        //Collections.reverse(this.movimientos);
    }
}
