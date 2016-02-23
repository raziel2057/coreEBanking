/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.distri.servicios;

import javax.ejb.Remote;
import javax.jms.JMSException;

/**
 *
 * @author Freddy
 */
@Remote
public interface ProductorSLSBRemote {
    void enviaMensajeJMS(String mensaje) throws JMSException;
}
