/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.distri.servicios;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 *
 * @author Freddy
 */
@LocalBean
@Stateless
public class Productor implements ProductorSLSBRemote{

    @Resource(mappedName  = "jms/ConnectionFactory")
    private ConnectionFactory connectionFactory;
    @Resource(mappedName  = "jms/Queue")
    private Queue queue;

    public void enviaMensajeJMS(String mensaje) throws JMSException {
        Connection connection = null;
        Session session = null;
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(true, 0);
            TextMessage tm = session.createTextMessage(mensaje);
            MessageProducer messageProducer = session.createProducer(queue);
            messageProducer.send(tm);
        } finally {
            if (session != null) {
                session.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
