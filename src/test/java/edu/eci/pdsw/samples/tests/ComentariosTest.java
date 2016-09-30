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
 *
* CLASES DE EQUIVALENCIA
 * 
 * CONDICION DE ENTRADA  |      TIPO     |           CLASE DE EQUIVALENCIA VALIDA         |             CLASE DE EQUIVALENCIA INVALIDA
 *  ID (Identificadior)  |     Entero    |           0<=ID<=Cantidad de foros             |       ID<0 o ID>Cantidad de foros o un ID no numerico
 *    Entrada al foro    |  EntradaForo  |  Un usuario asociado a la entrada del for o    |        Usario no existente a la entrada del foro
 *      Comentario       |     String    |  El comentario debe estar asociado a un        |      -Usuario no existente para el comentario asociado
 *                       |               |              usuario existente                 |                     -Cadena en blanco
 */
public class ComentariosTest {
    
    public ComentariosTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @Test
    public void registroNuevaEntradaAlForoValidaUnaEntradaForoJuanPerez(){
        List<EntradaForo> actuales = null;
        ServiciosForo sf = ServiciosForo.getInstance();
        EntradaForo ef=new EntradaForo(4, new Usuario("carlosescuelaing@gmail.com","carlos"),"Mejores juegos MMORPG 2016", "Los mejores videojuegos", java.sql.Date.valueOf("2016-09-26"));
        EntradaForo ef2=new EntradaForo(6, new Usuario("yenny@hotmail.com","yenny"),"Tutorial HTML", "Lenguajes de programacion", java.sql.Date.valueOf("2015-03-12"));
        try{
            sf.registrarNuevaEntradaForo(ef);
            sf.registrarNuevaEntradaForo(ef2);
            actuales = sf.consultarEntradasForo();
        }catch(Exception ex){
            Logger.getLogger(ServiciosForoStub.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        assertEquals("El tamaño actual de la lista de entradas foros es 3.",actuales.size(),3);
    }
    
    @Test 
    public void consultarEntradaForoYaCReadoAdemasdeDatosEstaticos(){
        List<EntradaForo> actuales = null;
        ServiciosForo sf = ServiciosForo.getInstance();
        try{
            EntradaForo ef = new EntradaForo(10,sf.consultarUsuario("luisa.perez@gmail.com"),"Que clase de mundo es este?", "Super INterrogante",java.sql.Date.valueOf("2016-09-03"));
            EntradaForo evaluar = sf.consultarEntradaForo(3);
            actuales = sf.consultarEntradasForo();
            assertEquals("La entrada al foro se encuentra registrado una vez creado",evaluar.getIdentificador(),actuales.get(2).getIdentificador());
        }catch(Exception e){
            Logger.getLogger(ServiciosForoStub.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }
    
    @Test
    public void registrarEntradaForoAdemasdeDatosEstaticos(){
        List<EntradaForo> actuales = null;
        ServiciosForo sf = ServiciosForo.getInstance();
        try {
            EntradaForo ef1 = new EntradaForo(10,sf.consultarUsuario("luisa.perez@gmail.com"),"Que clase de mundo es este?", "Super Intterrogante",java.sql.Date.valueOf("2016-09-03"));
            EntradaForo ef2 = new EntradaForo(11,sf.consultarUsuario("yenny@hotmail.com"),"Que clase de mundo es este?", "Super INterrogante",java.sql.Date.valueOf("2016-09-03"));
            sf.registrarNuevaEntradaForo(ef1);
            sf.registrarNuevaEntradaForo(ef2);
            actuales = sf.consultarEntradasForo();
            assertEquals("Se registraron 2 nuevas EntradasForo ademas de las del contructor y las de las pruebas anteriores",actuales.size(),5);
        } catch (ExcepcionServiciosForos ex) {
            Logger.getLogger(ComentariosTest.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    
}
