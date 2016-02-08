/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.distri.servicios;

import ec.espe.distri.dao.EmpleadoDAO;
import ec.espe.distri.modelo.Empleado;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author RAUL
 */
@LocalBean
@Stateless
public class EmpleadoServicio {
    @EJB
    private EmpleadoDAO empreadoDAO;
    
    public Empleado buscarPorCodigo(Integer codigo)
    {
        return this.empreadoDAO.findById(codigo, false);
    }
    
    public boolean autentificacion(String contrasenia, Integer codigoEmpleado)
    {
        Empleado empleado = this.buscarPorCodigo(codigoEmpleado);
        if(empleado!=null)
        {
            if(empleado.getContrasenia().equals(DigestUtils.md5Hex(contrasenia)))
                return true;
        }
        return false;
    }
    
    public void registrarEmpleado(Empleado empleado)
    {
        this.empreadoDAO.insert(empleado);
    }
    public void crearUsuarios()
    {
        List<Empleado> empleados = new ArrayList<>();
        String pass = "12345";
        empleados.add(new Empleado(null,"Jose Almendariz", "9876543210", "rdne18@yahoo.com", "cajero1", DigestUtils.md5Hex(pass)));
        empleados.add(new Empleado(null,"Luis Guerrero", "9876543211", "rdne18@yahoo.com", "cajero2", DigestUtils.md5Hex(pass)));
        empleados.add(new Empleado(null,"Fernanda Cordova", "9876543212", "rdne18@yahoo.com", "cajero3", DigestUtils.md5Hex(pass)));
        empleados.add(new Empleado(null,"Daniela Suarez", "9876543213", "rdne18@yahoo.com", "cajero4", DigestUtils.md5Hex(pass)));
        empleados.add(new Empleado(null,"Flavio Garcia", "9876543214", "rdne18@yahoo.com", "cajero5", DigestUtils.md5Hex(pass)));
        for(Empleado e: empleados)
            this.registrarEmpleado(e);
    }
}
