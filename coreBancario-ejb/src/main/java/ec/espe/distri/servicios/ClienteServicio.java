/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.distri.servicios;

import ec.espe.distri.dao.ClienteDAO;
import ec.espe.distri.dao.PersonaDAO;
import ec.espe.distri.modelo.Cliente;
import ec.espe.distri.modelo.Persona;
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
public class ClienteServicio {
     @EJB
    private ClienteDAO clienteDAO;
    public List<Cliente> obtenerTodas(){
        return this.clienteDAO.findAll();
        
    }
    public Cliente buscarPorCedula(String cedula)
    {
        Cliente c = new Cliente();
        c.setIdentificacion(cedula);
        List<Cliente> clientes = this.clienteDAO.findO(c);
        if(clientes.size()>0)
            return clientes.get(0);
        else
            return null;
    }
}
