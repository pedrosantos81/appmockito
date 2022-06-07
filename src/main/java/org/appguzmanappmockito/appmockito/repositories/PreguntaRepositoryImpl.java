package org.appguzmanappmockito.appmockito.repositories;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.appguzmanappmockito.appmockito.ejemplos.Datos;

public class PreguntaRepositoryImpl implements PreguntaRepository{

	@Override
	public List<String> findPreguntasPorExamenId(Long id) {
		// TODO Auto-generated method stub
		System.out.println("PreguntaRepositoryImpl.findPreguntasPorExamenId");
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Datos.PREGUNTAS;
	}

	@Override
	public void guardarVarias(List<String> preguntas) {
		// TODO Auto-generated method stub
		System.out.println("PreguntaRepositoryImpl.guardarVarias");
	}

}
