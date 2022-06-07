package org.appguzmanappmockito.appmockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.appguzmanappmockito.appmockito.utils.Parseador;
import org.junit.jupiter.api.Test;

public class ParseadorTest {

	private Parseador parseador = new Parseador();
	
	@Test
	void obtenerParte1Unidades() {
		String ecuacion = "2x-1=0";
		int resultado = parseador.obtenerParte1(ecuacion);
		
		assertEquals(2,resultado);
	}
	
	@Test
	void obtenerParte2Suma() {
		String ecuacion = "2x+1=0";
		int resultado = parseador.obtenerParte2(ecuacion);
		assertEquals(1,resultado);
	}
	
	@Test
	void obtenerParte3Positivo() {
		String ecuacion = "2x+1=3";
		int resultado = parseador.obtenerParte3(ecuacion);
		assertEquals(3,resultado);
	}
	
	@Test
	void obtenerOperadorSuma() {
		String ecuacion = "2x+1=3";
		String operador = parseador.obtenerOperador(ecuacion);
		assertEquals("+",operador);
		
	}
}
