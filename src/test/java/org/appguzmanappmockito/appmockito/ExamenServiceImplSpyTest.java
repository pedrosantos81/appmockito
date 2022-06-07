package org.appguzmanappmockito.appmockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.appguzmanappmockito.appmockito.model.Examen;
import org.appguzmanappmockito.appmockito.repositories.ExamenRepository;
import org.appguzmanappmockito.appmockito.repositories.ExamenRepositoryImpl;
import org.appguzmanappmockito.appmockito.repositories.PreguntaRepository;
import org.appguzmanappmockito.appmockito.repositories.PreguntaRepositoryImpl;
import org.appguzmanappmockito.appmockito.services.ExamenService;
import org.appguzmanappmockito.appmockito.services.ExamenServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

@ExtendWith(MockitoExtension.class)
public class ExamenServiceImplSpyTest {

	@Spy
	ExamenRepositoryImpl repository;
	@Spy
	PreguntaRepositoryImpl preguntaRepository;
	
	@InjectMocks
	ExamenServiceImpl service;
	
	
	
	@Test
	void testSpy() {
		
		
		List<String> preguntas = Arrays.asList("aritmetica");
		//when(preguntaRepository.findPreguntasPorExamenId(anyLong())).thenReturn(preguntas);
		doReturn(preguntas).when(preguntaRepository).findPreguntasPorExamenId(anyLong());
		
		Examen examen = service.findExamenPorNombreConPreguntas("Matematicas");
		assertEquals(5,examen.getId());
		assertEquals("Matematicas",examen.getNombre());
		assertEquals(1,examen.getPreguntas().size());
		assertTrue(examen.getPreguntas().contains("aritmetica"));
		
		verify(repository).findAll();
		verify(preguntaRepository).findPreguntasPorExamenId(anyLong());
	}
}
