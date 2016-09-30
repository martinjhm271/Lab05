/*
 * Copyright (C) 2015 hcadavid
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

import org.junit.Before;
import org.junit.Test;

import edu.eci.pdsw.samples.entities.EntradaForo;
import edu.eci.pdsw.samples.entities.Usuario;
import edu.eci.pdsw.samples.services.ExcepcionServiciosForos;
import edu.eci.pdsw.samples.services.ServiciosForoStub;

import static org.junit.Assert.*;

import java.util.List;

/**
 *
 * @author hcadavid
 */



/**
 * Pruebas de Equivalencia
 * 
 * Clase 1 = Enviarle un foro con un identificador no valido id<0 o id >999999.
 * 
 * Clase 2 = Enviarle un foro con un titulo y comentario nulo o vacio (titulo="" || comentario="" || titulo=null || comentario=null)
 * 
 * Clase 3 = Enviarle un foro con un uuario que tenga de email y nombre nulo o vacio (titulo="" || comentario="" || titulo=null || comentario=null) 
 *
 */
public class EntradasForoTest {
    
    public EntradasForoTest() {
    }
    
    @Before
    public void setUp() {
    }
    
  
    @Test
    public void agregarforo1() throws ExcepcionServiciosForos{
    	ServiciosForoStub svf=new ServiciosForoStub();
    	EntradaForo ef=new EntradaForo(999999,new Usuario("martinjhm271@hotmail.es","Martin Hernandez") ,"Por que el agua moja?", "Super interrogante", java.sql.Date.valueOf("2000-01-01"));
    	svf.registrarNuevaEntradaForo(ef);
    	List<EntradaForo> temp =svf.consultarEntradasForo();
    	int id=-1;
    	boolean ban=true;
    	for(int i=0;i<temp.size() && ban;i++){if(temp.get(i).equals(ef)){id=temp.get(i).getIdentificador();ban=false;}}
    	assertNotEquals(999999,id);
    }
     @Test
    public void agregarforo2() throws ExcepcionServiciosForos{
    	ServiciosForoStub svf=new ServiciosForoStub();
    	EntradaForo ef=new EntradaForo(999999,new Usuario("martinjhm271@hotmail.es","Martin Hernandez") ,"", "", java.sql.Date.valueOf("2000-01-01"));
    	svf.registrarNuevaEntradaForo(ef);
    	List<EntradaForo> temp =svf.consultarEntradasForo();
    	assertEquals("Deberia agregar el nuevo foro con un titulo y comentario vacio",temp.size(),2);
    }
    @Test
    public void agregarforo3() throws ExcepcionServiciosForos{
    	ServiciosForoStub svf=new ServiciosForoStub();
    	EntradaForo ef=new EntradaForo(999999,new Usuario("","") ,"Por que el agua moja?", "Super interrogante", java.sql.Date.valueOf("2000-01-01"));
    	svf.registrarNuevaEntradaForo(ef);
    	List<EntradaForo> temp =svf.consultarEntradasForo();
    	assertEquals("Deberia agregar el nuevo foro con un usuario que tiene nombre y email vacio",temp.size(),2);
    }
    
}
