/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.distri.modelo;

import java.math.BigDecimal;
import org.mongodb.morphia.annotations.Entity;

/**
 *
 * @author RAUL
 */
@Entity(value="persona")
public class Usuario {
    private Integer codigoCliente;
    private double montoMaximo;
    private String activo;
    private String contrasenia;
    private String usuario;
    private String correo;

    public Usuario() {
    }

    public Usuario(Integer codigoCliente, double montoMaximo, String activo, String contrasenia, String usuario, String correo) {
        this.codigoCliente = codigoCliente;
        this.montoMaximo = montoMaximo;
        this.activo = activo;
        this.contrasenia = contrasenia;
        this.usuario = usuario;
        this.correo = correo;
    }

   

    public Integer getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(Integer codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public double getMontoMaximo() {
        return montoMaximo;
    }

    public void setMontoMaximo(double montoMaximo) {
        this.montoMaximo = montoMaximo;
    }

    

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return "Usuario{" + "codigoCliente=" + codigoCliente + ", montoMaximo=" + montoMaximo + ", activo=" + activo + ", contrasenia=" + contrasenia + ", usuario=" + usuario + ", correo=" + correo + '}';
    }
    
    
}
