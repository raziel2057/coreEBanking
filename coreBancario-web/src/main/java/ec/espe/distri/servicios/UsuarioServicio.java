/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.distri.servicios;

import ec.espe.distri.modelo.Usuario;
import ec.espe.distri.persistencia.PersistenceManager;

/**
 *
 * @author RAUL
 */
public class UsuarioServicio {
    private PersistenceManager persistence;

    public UsuarioServicio() {
        persistence = new PersistenceManager();
    }
    
    public boolean crearUsuario(Usuario usuario)
    {
        
        try
        {
            persistence.context().save(usuario);
            return true;
        }
        catch(Exception e)
        {   
            e.printStackTrace();
        }
        return false;
    }
}
