/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.distri.web;

import ec.espe.distri.modelo.Cliente;
import ec.espe.distri.modelo.Cuenta;
import ec.espe.distri.modelo.Usuario;
import ec.espe.distri.servicios.ClienteServicio;
import ec.espe.distri.servicios.ProductorSLSBRemote;
import ec.espe.distri.servicios.UsuarioServicio;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import javax.jms.JMSException;

/**
 *
 * @author RAUL
 */
@SessionScoped
@ManagedBean
public class LoginBean implements Serializable{
    @EJB
    private ClienteServicio clienteServicio;
    @EJB
    private ProductorSLSBRemote productor;
    private String username;
    private String password;
    private Usuario usuario;
    private UsuarioServicio usuarioServicio;
    private Cliente cliente;
    private Cuenta cuentaSelected;
    
    
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cuenta getCuentaSelected() {
        return cuentaSelected;
    }

    public void setCuentaSelected(Cuenta cuentaSelected) {
        this.cuentaSelected = cuentaSelected;
        Collections.reverse(this.cuentaSelected.getMovimientos());
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
    @PostConstruct
    public void inicializar()
    {
        this.usuarioServicio = new UsuarioServicio();
    }
    
    public void login() throws MalformedURLException, IOException, JMSException
    {
        RequestContext context = RequestContext.getCurrentInstance();
        this.usuario = this.usuarioServicio.validarLogin(username, password);
        this.cliente = this.clienteServicio.buscarPorCodigo(this.usuario.getCodigoCliente());
        if(this.cliente==null)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Credenciales inválidas"));

            this.reset();
            context.addCallbackParam("estaLogeado", false);
            //context.addCallbackParam("view", "faces/index.xhtml");
        }
        else
        {
            URL whatismyip = new URL("http://checkip.amazonaws.com");
            BufferedReader in = new BufferedReader(new InputStreamReader(
                whatismyip.openStream()));

            String ip = in.readLine(); //you get the IP as a String
            System.out.println(ip);
            productor.enviaMensajeJMS(this.getCliente().getCorreoElectronico()+"/Aviso inicio de sesión/Se ha iniciado sesión desde la dirección IP "+ip);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Bienveni@"));

            context.addCallbackParam("estaLogeado", true);
            context.addCallbackParam("view", "posicionConsolidada.xhtml");
        }
    }
    
    public void reset()
    {
        this.cliente=null;
        this.username ="";
        this.password="";
    }
    
    public boolean estaLogeado() {
        if(this.cliente==null)
            return false;
        else
            return true;
    }
    
     public void logout() {
        
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.invalidate();
        this.reset();
        
    }
}
