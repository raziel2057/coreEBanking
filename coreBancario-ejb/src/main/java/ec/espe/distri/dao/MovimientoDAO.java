/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.distri.dao;

import ec.espe.distri.common.dao.DefaultGenericDAOImple;
import ec.espe.distri.modelo.Movimiento;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author RAUL
 */
@LocalBean
@Stateless 
public class MovimientoDAO extends DefaultGenericDAOImple<Movimiento,Integer> {
    public MovimientoDAO() {
        super(Movimiento.class);
    }
}
