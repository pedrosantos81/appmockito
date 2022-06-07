package org.appguzmanappmockito.appmockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.appguzmanappmockito.appmockito.ejemplos.EcuacionPrimerGrado;
import org.junit.jupiter.api.Test;

public class EcuacionPrimerGradoIntegrationTest {

	EcuacionPrimerGrado ecuacion = new EcuacionPrimerGrado();
	
	@Test
	public void solucionEcuacionConMenos() {
		Double result = ecuacion.obtenerResultado("2x-1=0");
		Double valueExpected = 0.5;
		assertEquals(valueExpected,result);
	}
	
	@Test
	public void solucionEcuacionConMas() {
		Double result = ecuacion.obtenerResultado("2x+1=0");
		Double valueExpected = -0.5;
		assertEquals(valueExpected,result);
	}
	
	@Test
	public void solucionEcuacionConParte3Mayor0() {
		Double result = ecuacion.obtenerResultado("2x+1=10");
		Double valueExpected = 4.5;
		assertEquals(valueExpected,result);
	}
}
