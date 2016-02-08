/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.distri.modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author RAUL
 */
@Entity
@Table(name = "EMPLEADO")
public class Empleado implements Serializable {
    @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)    
     @Column(name = "CODIGO_EMPLEADO", nullable = false)
     private Integer codigo;
    @Column(name = "NOMBRES", nullable = false)
    private String nombres;
    @Column(name = "IDENTIFICACION", nullable = false)
    private String identificacion;
    @Column(name = "CORREO_ELECTRONICO", nullable = false)
    private String correo;
    @Column(name = "NOMBRE_USUARIO", nullable = false)
    private String usuario;
    @Column(name = "CONTRASENIA", nullable = false)
    private String contrasenia;

    public Empleado() {
    }

    public Empleado(Integer codigo, String nombres, String identificacion, String correo, String usuario, String contrasenia) {
        this.codigo = codigo;
        this.nombres = nombres;
        this.identificacion = identificacion;
        this.correo = correo;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

 

    
    


    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.codigo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Empleado other = (Empleado) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Empleado{" + "codigo=" + codigo + ", nombres=" + nombres + ", identificacion=" + identificacion + ", correo=" + correo + ", usuario=" + usuario + ", contrasenia=" + contrasenia + '}';
    }
    
    
}
