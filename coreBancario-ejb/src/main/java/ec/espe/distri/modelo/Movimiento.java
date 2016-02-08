/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.distri.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author RAUL
 */
@Entity
@Table(name = "MOVIMIENTO")
public class Movimiento implements Serializable {
    @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)    
     @Column(name = "CODIGO_MOVIMIENTO", nullable = false)
     private Integer codigo;
    

    
    @Column(name = "CODIGO_CUENTA", nullable = false)
    private Integer codigoCuenta;
    
    @JoinColumn(name = "CODIGO_CUENTA", referencedColumnName = "CODIGO_CUENTA", insertable = false, updatable = false)
     @ManyToOne(optional = false)
     private Cuenta cuenta;
    
    @Column(name = "TIPO_MOVIMIENTO", nullable = false)
     private String tipo;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_Y_HORA", nullable = false)
    private java.util.Date fecha;
    
    @Column(name = "MONTO", nullable = false)
     private BigDecimal monto;
    
    @Column(name = "SALDO", nullable = false)
     private BigDecimal saldo;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigoCuenta() {
        return codigoCuenta;
    }

    public void setCodigoCuenta(Integer codigoCuenta) {
        this.codigoCuenta = codigoCuenta;
    }

    

    
    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.codigo);
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
        final Movimiento other = (Movimiento) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Movimiento{" + "codigo=" + codigo + ", codigoCuenta=" + codigoCuenta + ", cuenta=" + cuenta + ", tipo=" + tipo + ", fecha=" + fecha + ", monto=" + monto + ", saldo=" + saldo + '}';
    }

 
    
    
}
