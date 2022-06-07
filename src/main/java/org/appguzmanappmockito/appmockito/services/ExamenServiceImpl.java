package org.appguzmanappmockito.appmockito.services;

import java.util.List;
import java.util.Optional;

import org.appguzmanappmockito.appmockito.model.Examen;
import org.appguzmanappmockito.appmockito.repositories.ExamenRepository;
import org.appguzmanappmockito.appmockito.repositories.PreguntaRepository;

public class ExamenServiceImpl implements ExamenService{

	private ExamenRepository examenRepository;
	private PreguntaRepository preguntaRepository;
	
	public ExamenServiceImpl(ExamenRepository examenRepository,PreguntaRepository preguntaRepository) {
		this.examenRepository = examenRepository;
		this.preguntaRepository =  preguntaRepository;
	}

//	@Override
//	public Examen findExamenPorNombre(String nombre) {
//		// TODO Auto-generated method stub
//		Optional<Examen> examenOptional = examenRepository.findAll()
//				.stream()
//				.filter(e->e.getNombre()
//				.contains(nombre))
//				.findFirst();
//		Examen examen = null;
//		if(examenOptional.isPresent()) {
//			examen = examenOptional.orElseThrow(null);
//		}
//		return examen;
//	}
	
	@Override
	public Optional<Examen> findExamenPorNombre(String nombre) {
		// TODO Auto-generated method stub
		return examenRepository.findAll()
				.stream()
				.filter(e->e.getNombre()
				.contains(nombre))
				.findFirst();
		
		
	}

	@Override
	public Examen findExamenPorNombreConPreguntas(String nombre) {
		// TODO Auto-generated method stub
		Optional<Examen> examenOptional = findExamenPorNombre(nombre);
		Examen examen = null;
		if(examenOptional.isPresent()) {
			examen = examenOptional.orElseThrow(null);
			List<String> preguntas = preguntaRepository.findPreguntasPorExamenId(examen.getId());
			preguntaRepository.findPreguntasPorExamenId(examen.getId());
			examen.setPreguntas(preguntas);
		}
		return examen;
	}

	@Override
	public Examen guardar(Examen examen) {
		// TODO Auto-generated method stub
		if(!examen.getPreguntas().isEmpty()) {
			preguntaRepository.guardarVarias(examen.getPreguntas());
		}
		return examenRepository.guardar(examen);
	}

}
