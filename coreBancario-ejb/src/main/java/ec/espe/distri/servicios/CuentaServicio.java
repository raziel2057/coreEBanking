/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.distri.servicios;

import ec.espe.distri.dao.CuentaDAO;
import ec.espe.distri.modelo.Cuenta;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author RAUL
 */
@LocalBean
@Stateless
public class CuentaServicio {
    @EJB
    private CuentaDAO cuentaDAO;
    public List<Cuenta> obtenerTodas()
    {
        return this.cuentaDAO.findAll();
    }
    public List<Cuenta> consolidado(Integer codigoCliente)
    {
        Cuenta cuenta = new Cuenta();
        cuenta.setCodigoCliente(codigoCliente);
        return this.cuentaDAO.findO(cuenta);
    }
    
    
}
