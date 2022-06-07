package org.appguzmanappmockito.appmockito.repositories;

import java.io.PrintStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.appguzmanappmockito.appmockito.ejemplos.Datos;
import org.appguzmanappmockito.appmockito.model.Examen;

public class ExamenRepositoryImpl implements ExamenRepository{

	public List<Examen> findAll()
	{
		System.out.println("ExamenRepositoryImpl.findAll");
	    try
	    {
	      //System.out.println("ExamenRepositoryOtro");
	      TimeUnit.SECONDS.sleep(2L);
	    }
	    catch (InterruptedException e)
	    {
	      e.printStackTrace();
	    }
	    return Datos.EXAMENES;
	}
	  
	public Examen guardar(Examen examen)
	{
		System.out.println("ExamenRepositoryImpl.guardar");
	    return Datos.EXAMEN;
	}
}
