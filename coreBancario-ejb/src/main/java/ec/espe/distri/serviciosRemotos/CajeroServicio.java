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
public class CajeroServicio implements CajeroServicioRemote 
{

    @Override
    public boolean deposito(String identificador,String cuenta,float monto) {
        if(monto>0)
        {
            return true;
        }
        return false;
    }

    /**
     * Metodo que me permite validar si el usuario y clave del cajero son correctas
     * @return 
     */
    @Override
    public boolean logueo(String usuario, String clave) 
    {
        System.out.println("logueo("+usuario+","+clave+")");
        if(usuario.equals("carlos") && clave.equals("123"))
        {
            return true;
        }
        return false;
    }

    @Override
    public float getSaldo(String identificador,String cuenta) 
    {
        return 100000;
    }

    @Override
    public boolean retiro(String identificador,String cuenta, float monto) 
    {
        return true;
    }
}
