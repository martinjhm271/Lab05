/*
 * Copyright (C) 2016 hcadavid
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.pdsw.samples.managedbeans;


import edu.eci.pdsw.samples.entities.Comentario;
import edu.eci.pdsw.samples.entities.EntradaForo;
import edu.eci.pdsw.samples.entities.Usuario;
import edu.eci.pdsw.samples.services.ExcepcionServiciosForos;
import edu.eci.pdsw.samples.services.ServiciosForo;
import edu.eci.pdsw.samples.services.ServiciosForoStub;
import java.io.Serializable;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 *
 * @author hcadavid
 */
@ManagedBean(name="RegistroForo")
@SessionScoped
public class RegistroForosBean implements Serializable{
    
    ServiciosForo sp=ServiciosForo.getInstance();
    EntradaForo selectForo;
    
    private String email="";
    private String nombre="";
    private Usuario autor;
    private String comentario="";
    private Set<Comentario> respuestas;
    private String titulo="";
    private Date fechayHora;
    
    public List<EntradaForo> getEntradasForo() throws ExcepcionServiciosForos{
        return sp.consultarEntradasForo();
    }

    public EntradaForo getSelectForo() {
        return selectForo;
    }

    public void setSelectForo(EntradaForo selectForo) {
        this.selectForo = selectForo;
    }
    
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setEntradasForo(){
        if(!this.email.equals("") && !this.nombre.equals("") && !this.comentario.equals("") && !this.titulo.equals("")){
            Usuario u=new Usuario(email,nombre);
            EntradaForo ef=new EntradaForo(0,u, getComentario(),  getTitulo(),java.sql.Date.valueOf("2000-01-01"));
            try{
            sp.registrarNuevaEntradaForo(ef);
            } catch (ExcepcionServiciosForos ex) {
            Logger.getLogger(ServiciosForoStub.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
	this.email="";this.nombre="";this.comentario="";this.titulo="";
        }
    }




    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Set<Comentario> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(Set<Comentario> respuestas) {
        this.respuestas = respuestas;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFechayHora() {
        return fechayHora;
    }

    public void setFechayHora(Date fechayHora) {
        this.fechayHora = fechayHora;
    }
    
    
}
