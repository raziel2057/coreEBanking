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
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author RAUL
 */
@ManagedBean
@ViewScoped
public class RecargasBean implements Serializable{
    private String telefono;
    private BigDecimal monto;
    @ManagedProperty(value = "#{loginBean}")
    private LoginBean datosLogin;
    
    @EJB
    private MovimientoServicio movimientoServicio;
    
    @EJB
    private ProductorSLSBRemote productor;
    
    @EJB
    private CuentaServicio cuentaServicio;
    private List<Cuenta> consolidado;
    private Cuenta cuentaSelected;
    private Integer codigoCuentaSelected;

    public Integer getCodigoCuentaSelected() {
        return codigoCuentaSelected;
    }

    public void setCodigoCuentaSelected(Integer codigoCuentaSelected) {
        this.codigoCuentaSelected = codigoCuentaSelected;
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
    
    

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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
    
    @PostConstruct
    public void inicializar()
    {
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
    
    public void imprimirDatos()
    {
        System.out.println(this.telefono + "  "+this.monto);
    }
    public void asignarDatos()
    {
        RequestContext context = RequestContext.getCurrentInstance();
        context.addCallbackParam("telf", this.telefono);
        context.addCallbackParam("mont", this.monto);
        boolean continuar = true;
        for(Cuenta c : consolidado)
        {
            if(c.getNumero().equals(this.cuentaSelected.getNumero()))
            {
                if(c.getSaldo().doubleValue()<this.monto.doubleValue())
                    continuar=false;
            }
        }
        context.addCallbackParam("continuar", continuar);
        if(!continuar)
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Saldo insuficiente"));

        
        
    }
    
    public void recargaExitosa()
    {
        
        try
        {
            productor.enviaMensajeJMS(this.getDatosLogin().getCliente().getCorreoElectronico()+"/Aviso recarga celular/Se ha recargado "+this.monto+"$ al número "+this.telefono+" desde la cuenta "+this.cuentaSelected.getNumero());
            this.movimientoServicio.retiro(monto, Integer.parseInt(this.cuentaSelected.getNumero()), "Recarga número: "+this.telefono+" Monto: "+this.monto);
            System.out.println("si entra exitosa");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Recarga exitosa"));

        }
        catch(Exception e)
        {
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al realizar el débito"));

        }
        
       
    }
    public void recargaFallida()
    {
        System.out.println("si entra fallida");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No existe el número"));
    }
    
}
