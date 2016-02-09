/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.distri.web;

import ec.espe.distri.modelo.Cliente;
import ec.espe.distri.modelo.Cuenta;
import ec.espe.distri.modelo.Persona;
import ec.espe.distri.modelo.Usuario;
import ec.espe.distri.servicios.ClienteServicio;
import ec.espe.distri.servicios.CuentaServicio;
import ec.espe.distri.servicios.EmpleadoServicio;
import ec.espe.distri.servicios.MovimientoServicio;
import ec.espe.distri.servicios.PersonaServicio;
import ec.espe.distri.servicios.UsuarioServicio;
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
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author RAUL
 */
@ViewScoped
@ManagedBean
public class ClienteBean implements Serializable {
    @EJB
    private ClienteServicio clienteServicio;
    private List<Cliente> clientes;
    private Cliente cliente;
    private String cedula;
    private String usuario;
    private String contrasenia;
    private String correo;
       
    @EJB
    private CuentaServicio cuentaServicio;
    private List<Cuenta> consolidado;
    
    private UsuarioServicio usuarioServicio;
    
    @EJB
    private MovimientoServicio movimientoServicio;
    @EJB
    private EmpleadoServicio empleadoServicio;
    
    @ManagedProperty(value = "#{loginBean}")
    private LoginBean datosLogin;

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public List<Cuenta> getConsolidado() {
        return consolidado;
    }

    public void setConsolidado(List<Cuenta> consolidado) {
        this.consolidado = consolidado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
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
        this.usuarioServicio = new UsuarioServicio();
        this.posicionConsolidada();
        /*//this.movimientoServicio.retiro(BigDecimal.valueOf(50.52d), 1,"Retiro, Cajero 1: Jose Almendariz");
        this.movimientoServicio.transferencia(BigDecimal.valueOf(50.52d), 1, 2);
        this.clientes = this.clienteServicio.obtenerTodas();
        //this.consolidado = this.cuentaServicio.consolidado(this.clientes.get(0).getCodigo());
        this.consolidado = this.cuentaServicio.obtenerTodas();
        //this.consolidado = this.cuentaServicio.obtenerTodas();
        //empleadoServicio.crearUsuarios();*/
    }
    
    public void posicionConsolidada()
    {
        if(this.getDatosLogin().getCliente()!=null)
            this.consolidado = this.cuentaServicio.consolidado(this.getDatosLogin().getCliente().getCodigo());
    }
    
    public void buscarCliente()
    {
        this.cliente = this.clienteServicio.buscarPorCedula(cedula);
    }
    public boolean existeCliente()
    {
        if(this.cliente!=null)
            return true;
        return false;
    }
    public void crearCliente()
    {
        Usuario usuario = new Usuario(this.cliente.getCodigo(), 0.0d, "S",DigestUtils.md5Hex(contrasenia), this.usuario, this.correo);
        if(this.usuarioServicio.crearUsuario(usuario))
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "El usuario se creó correctamente"));
            reset();
        }
            else
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El usuario ya existe"));
         
    }
    public void reset()
    {
       this.cliente = null;
       this.usuario="";
       this.contrasenia="";
       this.correo="";
    }
}
