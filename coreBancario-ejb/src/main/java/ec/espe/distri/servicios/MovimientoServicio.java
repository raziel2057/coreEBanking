/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.distri.servicios;

import ec.espe.distri.common.dao.Order;
import ec.espe.distri.dao.CuentaDAO;
import ec.espe.distri.dao.MovimientoDAO;
import ec.espe.distri.modelo.Cuenta;
import ec.espe.distri.modelo.Movimiento;
import java.math.BigDecimal;
import java.util.Date;
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
public class MovimientoServicio {
    @EJB
    private MovimientoDAO movimientoDAO;
    @EJB
    private CuentaDAO cuentaDAO;
    public List<Movimiento> obtenerTodos()
    {
        return this.movimientoDAO.findAll();
    }
    public List<Movimiento> consolidado(Integer codigoCuenta)
    {
        Movimiento movimiento = new Movimiento();
        
        movimiento.setCodigoCuenta(codigoCuenta);
        return this.movimientoDAO.findO(movimiento,Order.descendente("fecha"));
    }
    public void deposito(BigDecimal monto,Integer codigoCuenta,String descripcion)
    {
        Cuenta cuenta = this.cuentaDAO.findById(codigoCuenta, false);
        Movimiento movimiento = new Movimiento();
        movimiento.setCodigoCuenta(codigoCuenta);
        movimiento.setFecha(new Date());
        movimiento.setMonto(monto);
        movimiento.setTipo("DE");
        movimiento.setDescripcion(descripcion);
        movimiento.setSaldo(monto.add(cuenta.getSaldo()));
        cuenta.setSaldo(monto.add(cuenta.getSaldo()));
        this.cuentaDAO.update(cuenta);
        this.movimientoDAO.insert(movimiento);
    }
    
    public void retiro(BigDecimal monto,Integer codigoCuenta,String descripcion)
    {
        Cuenta cuenta = this.cuentaDAO.findById(codigoCuenta, false);
        Movimiento movimiento = new Movimiento();
        movimiento.setCodigoCuenta(codigoCuenta);
        movimiento.setFecha(new Date());
        movimiento.setMonto(monto);
        movimiento.setTipo("RE");
        movimiento.setDescripcion(descripcion);
        movimiento.setSaldo(cuenta.getSaldo().subtract(monto));
        cuenta.setSaldo(cuenta.getSaldo().subtract(monto));
        this.cuentaDAO.update(cuenta);
        this.movimientoDAO.insert(movimiento);
    }
    
    public void transferencia(BigDecimal monto,Integer codigoCuentaOrigen,Integer codigoCuentaDestino)
    {
        //deposito
        Cuenta cuentaOrigen = this.cuentaDAO.findById(codigoCuentaOrigen, false);
        Cuenta cuentaDestino = this.cuentaDAO.findById(codigoCuentaDestino, false);
        this.deposito(monto, codigoCuentaDestino, "Transferencia de "
                                                    +cuentaOrigen.getCliente().getNombre()
                                                    +" "
                                                    +cuentaOrigen.getCliente().getApellido());
        
        this.retiro(monto, codigoCuentaOrigen, "Transferencia a "
                                                    +cuentaDestino.getCliente().getNombre()
                                                    +" "
                                                    +cuentaDestino.getCliente().getApellido());
       
    }
}
