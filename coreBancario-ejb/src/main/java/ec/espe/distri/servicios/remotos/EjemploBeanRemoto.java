/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.distri.servicios.remotos;

import javax.ejb.Stateless;

/**
 *
 * @author carlo
 */
@Stateless
public class EjemploBeanRemoto implements EjemploBeanRemotoRemote {

    @Override
    public String getNameSystem() {
        return "Sistema Bancario";
    }
}
