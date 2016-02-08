/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.distri.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author RAUL
 */
@Entity
@Table(name = "CUENTA")
public class Cuenta implements Serializable {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)    
     @Column(name = "CODIGO_CUENTA", nullable = false)
     private Integer codigo;
     
     @Column(name = "CODIGO_CLIENTE", nullable = false)
     private Integer codigoCliente;
     
     @JoinColumn(name = "CODIGO_CLIENTE", referencedColumnName = "CODIGO_CLIENTE", insertable = false, updatable = false)
     @ManyToOne(optional = false)
     private Cliente cliente;
     
     @OneToMany(fetch = FetchType.EAGER, mappedBy = "cuenta")
     private List<Movimiento> movimientos;
     
     @Column(name = "NUMERO", nullable = false)
     private String numero;
     
     @Column(name = "SALDO", nullable = false)
     private BigDecimal saldo;
     
     @Column(name = "TIPO", nullable = false)
     private String tipo;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(Integer codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }
    
    

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + Objects.hashCode(this.codigo);
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
        final Cuenta other = (Cuenta) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Cuenta{" + "codigo=" + codigo + ", codigoCliente=" + codigoCliente + ", cliente=" + cliente + ", movimientos=" + movimientos + ", numero=" + numero + ", saldo=" + saldo + ", tipo=" + tipo + '}';
    }

    
     
     
    
}
