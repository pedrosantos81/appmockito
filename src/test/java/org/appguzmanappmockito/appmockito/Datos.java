package org.appguzmanappmockito.appmockito;

import java.util.Arrays;
import java.util.List;

import org.appguzmanappmockito.appmockito.model.Examen;

public class Datos {

	public final static List<Examen> EXAMENES =Arrays.asList(
			new Examen(5L,"Matematicas"),
			new Examen(6L,"Lenguaje"),
			new Examen(7L,"Historia"),
			new Examen(8L,"Espanol"));
	
	public final static List<Examen> EXAMENES_ID_NULL =Arrays.asList(
			new Examen(null,"Matematicas"),
			new Examen(null,"Lenguaje"),
			new Examen(null,"Historia"),
			new Examen(null,"Espanol"));
	
	public final static List<Examen> EXAMENES_ID_NEGATIVOS =Arrays.asList(
			new Examen(-5L,"Matematicas"),
			new Examen(-6L,"Lenguaje"),
			new Examen(null,"Historia"),
			new Examen(-8L,"Espanol"));
	
	public final static List<String> PREGUNTAS = Arrays.asList("aritmetica","integrales",
			"derivadas","trigonometria","geometria");
	//public final static Examen EXAMEN = new Examen(8L,"Fisica");
	public final static Examen EXAMEN = new Examen(null,"Fisica"); //este es para simular id automatico como si fuera jpa o hibernate.
	
}
