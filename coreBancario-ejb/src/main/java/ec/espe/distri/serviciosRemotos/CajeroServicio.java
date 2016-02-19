/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.distri.serviciosRemotos;

import javax.ejb.Stateless;

/**
 *
 * @author RAUL
 */
@Stateless
public class CajeroServicio implements CajeroServicioRemote {

    @Override
    public boolean deposito() {
        System.out.println("ec.espe.distri.serviciosRemotos.CajeroServicio.deposito()");
        return true; //To change body of generated methods, choose Tools | Templates.
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
