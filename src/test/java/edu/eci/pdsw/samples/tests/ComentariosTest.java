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
import edu.eci.pdsw.samples.services.ServiciosForo;
import edu.eci.pdsw.samples.services.ServiciosForoStub;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

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
    public void registroPacienteTest(){
        
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
        assertEquals(actuales.size(),3);
    }
    
    
}
