package org.appguzmanappmockito.appmockito.services;

import java.util.Optional;

import org.appguzmanappmockito.appmockito.model.Examen;

public interface ExamenService {
	//Examen findExamenPorNombre(String nombre);
	Optional<Examen> findExamenPorNombre(String nombre);
	Examen findExamenPorNombreConPreguntas(String nombre);
	Examen guardar(Examen examen);
}
