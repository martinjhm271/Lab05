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
package edu.eci.pdsw.samples.tests;

import edu.eci.pdsw.samples.entities.Comentario;
import edu.eci.pdsw.samples.entities.EntradaForo;
import edu.eci.pdsw.samples.entities.Usuario;
import edu.eci.pdsw.samples.services.ExcepcionServiciosForos;
import edu.eci.pdsw.samples.services.ServiciosForo;
import edu.eci.pdsw.samples.services.ServiciosForoStub;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Pruebas de Equivalencia
 * 
 * Clase 1 = Enviarle un identificador no valido al metodo setrespuestaforo id<0 o id >999999.
 * 
 * Clase 2 = Enviarle un comentario que tenga como autor nulo o vacio (autor="" || autor=null)
 *
 */
public class ComentariosTest {
    
    public ComentariosTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @Test
    public void registrarcomentario() throws ExcepcionServiciosForos{
		ServiciosForo svf=new ServiciosForoStub();
        Usuario u = svf.consultarUsuario("juan.perez@gmail.com");
        Comentario c=new Comentario(u,"Buen post", new Date(java.util.Calendar.getInstance().getTime().getTime()));
        svf.agregarRespuestaForo(0,c);
        EntradaForo e=svf.consultarEntradaForo(0);
        assertEquals("el numero de respuestas del foro con id=0 es igual 1",e.getRespuestas().size(),1);
    }
    @Test
    public void registrarcomentario2() throws ExcepcionServiciosForos{
		ServiciosForo svf=new ServiciosForoStub();
        Usuario u = svf.consultarUsuario("juan.perez@gmail.com");int ans=-1;
		for(EntradaForo e:svf.consultarEntradasForo()){ans=e.getIdentificador();}
        Comentario c=new Comentario(u,"Buen post", new Date(java.util.Calendar.getInstance().getTime().getTime()));
        Comentario c2=new Comentario(u,"Me gusto mucho tu foro espero que subas mas", new Date(java.util.Calendar.getInstance().getTime().getTime()));
        Comentario c3=new Comentario(u,"Creo que puedes mejorarlo", new Date(java.util.Calendar.getInstance().getTime().getTime()));
        svf.agregarRespuestaForo(ans,c); svf.agregarRespuestaForo(ans,c2); svf.agregarRespuestaForo(ans,c3);EntradaForo e1=new EntradaForo();
        for(EntradaForo e:svf.consultarEntradasForo()){e1=e;}
        assertEquals("el numero de respuestas del foro con id=0 es igual e debido al agregar 3 comentarios",e1.getRespuestas().size(),3);
    }
 
    
}
