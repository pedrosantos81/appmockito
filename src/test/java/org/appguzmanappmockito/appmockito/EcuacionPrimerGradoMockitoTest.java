package org.appguzmanappmockito.appmockito;

import org.appguzmanappmockito.appmockito.ejemplos.EcuacionPrimerGrado;
import org.appguzmanappmockito.appmockito.utils.Parseador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class EcuacionPrimerGradoMockitoTest {

	@InjectMocks
	private EcuacionPrimerGrado ecuacionPrimerGrado;
	
	@Mock
	private Parseador parseador;
	
	@BeforeEach
	public void inicializaMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void solucionEcuacionConMenos() {
		String ecuacion = "2x-1=0";
		
		when(parseador.obtenerParte1(ecuacion)).thenReturn(2);
		when(parseador.obtenerParte2(ecuacion)).thenReturn(-1);
		when(parseador.obtenerParte3(ecuacion)).thenReturn(0);
		
		Double resultado = ecuacionPrimerGrado.obtenerResultado(ecuacion);
		
		Double valueExpected = 0.5;
		
		assertEquals(valueExpected, resultado);

	}
	
	@Test
	public void solucionEcuacionConMas() {
		String ecuacion = "2x+1=0";
		
		when(parseador.obtenerParte1(ecuacion)).thenReturn(2);
		when(parseador.obtenerParte2(ecuacion)).thenReturn(1);
		when(parseador.obtenerParte3(ecuacion)).thenReturn(0);
		
		Double resultado = ecuacionPrimerGrado.obtenerResultado(ecuacion);
		
		Double valueExpected = -0.5;
		
		assertEquals(valueExpected, resultado);

	}
}
