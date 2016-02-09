/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.distri.servicios;

import ec.espe.distri.modelo.Cliente;
import ec.espe.distri.modelo.Usuario;
import ec.espe.distri.persistencia.PersistenceManager;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;
import org.apache.commons.codec.digest.DigestUtils;
import org.mongodb.morphia.query.Query;

/**
 *
 * @author RAUL
 */

public class UsuarioServicio {
    private PersistenceManager persistence;


    public UsuarioServicio() {
        persistence = new PersistenceManager();
    }
    
    public boolean buscarUsuario(String usuario)
    {
        
        Query<Usuario> q = persistence.context().createQuery(Usuario.class);
        q.field("usuario").equal(usuario);
        List<Usuario> usuarios = q.asList();
        if(usuarios.size()>0)
            return true;
        else
            return false;
    }
    
    public Usuario validarLogin(String usuario, String contrasenia)
    {
        
        Query<Usuario> q = persistence.context().createQuery(Usuario.class);
        q.field("usuario").equal(usuario);
        List<Usuario> usuarios = q.asList();
        if(usuarios.size()>0)
            if(usuarios.get(0).getContrasenia().equals(DigestUtils.md5Hex(contrasenia)))
            {
                //clienteServicio = new ClienteServicio();
                return usuarios.get(0);
            }
            else
                return null;
        else
            return null;
    }
    
    public boolean crearUsuario(Usuario usuario)
    {
        
        try
        {
            if(this.buscarUsuario(usuario.getUsuario()))
                return false;
            
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

