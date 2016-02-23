/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.distri.web;

import ec.espe.distri.modelo.Cuenta;
import ec.espe.distri.servicios.CuentaServicio;
import ec.espe.distri.servicios.MovimientoServicio;
import ec.espe.distri.servicios.ProductorSLSBRemote;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author RAUL
 */
@ViewScoped
@ManagedBean
public class TransferenciaBean implements Serializable{
    @EJB
    private MovimientoServicio movimientoServicio;
    
    @EJB
    private ProductorSLSBRemote productor;
    
    private Integer numeroCuentaOrigen;
    private Integer numeroCuentaDestino;
    private String numeroCuentaOrigenS;
    private String numeroCuentaDestinoS;
    private String numeroCuentaDestinoSajena;
    private BigDecimal monto;
    private String seleccion;
    
    @ManagedProperty(value = "#{loginBean}")
    private LoginBean datosLogin;
    
    @EJB
    private CuentaServicio cuentaServicio;
    private List<Cuenta> consolidado;
    private Cuenta cuentaSelected;
    private Integer codigoCuentaSelected;

    public String getNumeroCuentaDestinoSajena() {
        return numeroCuentaDestinoSajena;
    }

    public void setNumeroCuentaDestinoSajena(String numeroCuentaDestinoSajena) {
        this.numeroCuentaDestinoSajena = numeroCuentaDestinoSajena;
    }

    
    public String getSeleccion() {
        return seleccion;
    }

    public void setSeleccion(String seleccion) {
        this.seleccion = seleccion;
    }

    public String getNumeroCuentaOrigenS() {
        return numeroCuentaOrigenS;
    }

    public void setNumeroCuentaOrigenS(String numeroCuentaOrigenS) {
        this.numeroCuentaOrigenS = numeroCuentaOrigenS;
    }

    public String getNumeroCuentaDestinoS() {
        return numeroCuentaDestinoS;
    }

    public void setNumeroCuentaDestinoS(String numeroCuentaDestinoS) {
        this.numeroCuentaDestinoS = numeroCuentaDestinoS;
    }

    
    
    public Integer getNumeroCuentaOrigen() {
        return numeroCuentaOrigen;
    }

    public void setNumeroCuentaOrigen(Integer numeroCuentaOrigen) {
        this.numeroCuentaOrigen = numeroCuentaOrigen;
    }

    public Integer getNumeroCuentaDestino() {
        return numeroCuentaDestino;
    }

    public void setNumeroCuentaDestino(Integer numeroCuentaDestino) {
        this.numeroCuentaDestino = numeroCuentaDestino;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public LoginBean getDatosLogin() {
        return datosLogin;
    }

    public void setDatosLogin(LoginBean datosLogin) {
        this.datosLogin = datosLogin;
    }

    public List<Cuenta> getConsolidado() {
        return consolidado;
    }

    public void setConsolidado(List<Cuenta> consolidado) {
        this.consolidado = consolidado;
    }

    public Cuenta getCuentaSelected() {
        return cuentaSelected;
    }

    public void setCuentaSelected(Cuenta cuentaSelected) {
        this.cuentaSelected = cuentaSelected;
    }

    public Integer getCodigoCuentaSelected() {
        return codigoCuentaSelected;
    }

    public void setCodigoCuentaSelected(Integer codigoCuentaSelected) {
        this.codigoCuentaSelected = codigoCuentaSelected;
    }
    
    @PostConstruct
    public void inicializar()
    {
        this.seleccion="propia";
        this.cuentaSelected = new Cuenta();
        this.cargarCuentas();
    }
    
    
    public void cargarCuentas()
    {
        if(this.getDatosLogin().getCliente()!=null)
        {
            this.consolidado = this.cuentaServicio.consolidado(this.getDatosLogin().getCliente().getCodigo());
            if(consolidado.size()>0)
                this.cuentaSelected.setNumero(this.consolidado.get(0).getNumero());
        }
        
    }
    
    public void transferencia()
    {
        Cuenta cuentaDestino = null;
        Cuenta cuentaOrigen = null;
        String cuenta ="";
        if(this.seleccion.equals("ajena"))
        {
            this.numeroCuentaDestino = Integer.parseInt(this.numeroCuentaDestinoSajena);
            cuenta = numeroCuentaDestinoSajena;
        }
        else
        {
            this.numeroCuentaDestino = Integer.parseInt(this.numeroCuentaDestinoS);
            cuenta = numeroCuentaDestinoS;
        }
        
        this.numeroCuentaOrigen = Integer.parseInt(this.numeroCuentaOrigenS);
        List<Cuenta> cuentasT = this.cuentaServicio.obtenerTodas();
        for(Cuenta c: cuentasT)
        {
            if(c.getCodigo().equals(numeroCuentaOrigen))
            {
               cuentaOrigen = c;
            }
            if(c.getCodigo().equals(numeroCuentaDestino))
            {
               cuentaDestino = c;
            }
        }
        
        try
        {
            if(cuentaDestino!=null)
            {
                if(cuentaOrigen.getSaldo().doubleValue()>=this.monto.doubleValue())
                {
                    if(this.getDatosLogin().getUsuario().getMontoMaximo()>=this.monto.doubleValue())
                    {
                        productor.enviaMensajeJMS(this.getDatosLogin().getCliente().getCorreoElectronico()+"/Aviso transferencia/Se ha transferido "+this.monto+"$ de la cuenta "+this.numeroCuentaOrigenS+" a la cuenta "+cuenta);
                        this.movimientoServicio.transferencia(this.monto, numeroCuentaOrigen, numeroCuentaDestino);
                        this.inicializar();
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Transferencia realizada exitosamente"));
                    }
                    else
                    {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Monto máximo superado"));      
                    }
                }
                else
                {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Saldo insuficiente"));      
                }
            }
            else
            {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La cuenta de destinono existe"));      
            }
        }
        catch(Exception e)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Hubo un error en la transferencia"));
            e.printStackTrace();
        }
    }
    
}
