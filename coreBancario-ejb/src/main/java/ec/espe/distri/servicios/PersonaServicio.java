/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.distri.servicios;

import ec.espe.distri.dao.PersonaDAO;
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
public class PersonaServicio {
    @EJB
    private PersonaDAO personaDAO;
    public List<Persona> obtenerTodas(){
        return this.personaDAO.findAll();
        
    }
}
