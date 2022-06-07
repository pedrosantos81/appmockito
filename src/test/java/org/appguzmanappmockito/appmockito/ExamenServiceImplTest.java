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
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verifyNoInteractions;

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
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

//@ExtendWith(MockitoExtension.class)
public class ExamenServiceImplTest {

	@Mock
	ExamenRepositoryImpl repository;
	@Mock
	PreguntaRepositoryImpl preguntaRepository;
	
	@InjectMocks
	ExamenServiceImpl examenService;
	
	@Captor
	ArgumentCaptor<Long> captor;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this); //esto es para habilitar las anotaciones o se puede usar @ExtendWith(MockitoExtension.class) al inicio de la clase
		//los comentarizamos porque lo vamos hacer por injection de dependencia con mock
//		repository = mock(ExamenRepository.class);
//		preguntaRepository = mock(PreguntaRepository.class);
//		examenService = new ExamenServiceImpl(repository,preguntaRepository);
	}
	
	@Test
	void findExamenPorNombre() {
		//ExamenRepository repository = new ExamenRepositoryImpl();//ya no usamos examenrepository,ahora usamos mock como abajo
		//ExamenRepository repository = mock(ExamenRepository.class); //aqui usamos la interface
		//ExamenRepository repository = mock(ExamenRepositoryOtro.class); //no es el objeto la instancia real de examen repositoriootro,aqui usamos la clase que implementa,creamos mock de la implementacion
		
		//ExamenRepository repository = mock(ExamenRepository.class); esta en setup beforeeach
		//ExamenService examenService = new ExamenServiceImpl(repository);
//		List<Examen> datos =Arrays.asList(new Examen(5L,"Matematicas"),new Examen(6L,"Lenguaje"),
//				new Examen(7L,"Espanol"));
		
		when(repository.findAll()).thenReturn(Datos.EXAMENES);
		//Examen examen = examenService.findExamenPorNombre("Matematicas");
		Optional <Examen> examen = examenService.findExamenPorNombre("Matematicas");
		
		
		
		assertNotNull(examen);
		assertTrue(examen.isPresent());
		assertEquals(5L, examen.orElseThrow(null).getId());
		assertEquals("Matematicas", examen.get().getNombre());
		
	}
	
	@Test
	void findExamenPorNombreListaVacia() {
		//ExamenRepository repository = new ExamenRepositoryImpl();//ya no usamos examenrepository,ahora usamos mock como abajo
		//ExamenRepository repository = mock(ExamenRepository.class); //aqui usamos la interface
		//ExamenRepository repository = mock(ExamenRepositoryOtro.class); //no es el objeto la instancia real de examen repositoriootro,aqui usamos la clase que implementa,creamos mock de la implementacion
		//ExamenRepository repository = mock(ExamenRepository.class);//estan en el beforeeach
		//ExamenService examenService = new ExamenServiceImpl(repository);//estan en el beforeeach
		List<Examen> datos = Collections.emptyList();
		
		when(repository.findAll()).thenReturn(datos);
		//Examen examen = examenService.findExamenPorNombre("Matematicas");
		Optional <Examen> examen = examenService.findExamenPorNombre("Matematicas");
		
		
		
		assertNotNull(examen);
		//assertTrue(examen.isPresent());
		assertFalse(examen.isPresent());
		//assertEquals(5L, examen.orElseThrow(null).getId());
		//assertEquals("Matematicas", examen.get().getNombre());
		
	}
	
	@Test
	void testPreguntasExamen() {
		when(repository.findAll()).thenReturn(Datos.EXAMENES);
		when(preguntaRepository.findPreguntasPorExamenId(7L)).thenReturn(Datos.PREGUNTAS);
		when(preguntaRepository.findPreguntasPorExamenId(anyLong())).thenReturn(Datos.PREGUNTAS);
		
		Examen examen = examenService.findExamenPorNombreConPreguntas("Historia");
		assertEquals(5,examen.getPreguntas().size());
		assertTrue(examen.getPreguntas().contains("aritmetica"));
	}
	
	@Test
	void testPreguntasVerify() {
		when(repository.findAll()).thenReturn(Datos.EXAMENES);
		when(preguntaRepository.findPreguntasPorExamenId(7L)).thenReturn(Datos.PREGUNTAS);
		when(preguntaRepository.findPreguntasPorExamenId(anyLong())).thenReturn(Datos.PREGUNTAS);
		
		Examen examen = examenService.findExamenPorNombreConPreguntas("Matematicas");
		assertEquals(5,examen.getPreguntas().size());
		assertTrue(examen.getPreguntas().contains("aritmetica"));
		
		verify(repository).findAll();
		verify(preguntaRepository).findPreguntasPorExamenId(4L);
	}
	
	@Test
	void testNoExisteExamenVerify() {
		//Given
		//when(repository.findAll()).thenReturn(Datos.EXAMENES);
		when(repository.findAll()).thenReturn(Collections.emptyList());
		when(preguntaRepository.findPreguntasPorExamenId(7L)).thenReturn(Datos.PREGUNTAS);
		
		//When
		Examen examen = examenService.findExamenPorNombreConPreguntas("Matematicas2");
		
		//then
		assertNull(examen);
		verify(repository).findAll();
		verify(preguntaRepository).findPreguntasPorExamenId(5L);
	}
	
	@Test
	void testGuardarExamen() {
		//Given
		Examen newExamen = Datos.EXAMEN; 
		newExamen.setPreguntas(Datos.PREGUNTAS);
		
		//when(repository.guardar(any(Examen.class))).thenReturn(Datos.EXAMEN); usa el de 8L comentarizado de Datos.Examen
		
		when(repository.guardar(any(Examen.class))).then(new Answer<Examen>() {

			Long secuence = 8L;
			@Override
			public Examen answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				Examen examen = invocation.getArgument(0);
				examen.setId(secuence++);
				return examen;
			}
			
		});
		
		//When
		//Examen examen = examenService.guardar(Datos.EXAMEN); //no pasa porque no tiene preguntas
		Examen examen = examenService.guardar(newExamen);
		
		//Then
		assertNotNull(examen.getId());
		assertEquals(8L,examen.getId());
		assertEquals("Fisica",examen.getNombre());
		verify(repository).guardar(any(Examen.class));
		verify(preguntaRepository).guardarVarias(anyList());
	}
	
	@Test
	void testManejoException() {
		when(repository.findAll()).thenReturn(Datos.EXAMENES_ID_NULL);
		when(preguntaRepository.findPreguntasPorExamenId(isNull())).thenThrow(IllegalArgumentException.class);
//		assertThrows(IllegalArgumentException.class,()->{
//			//examenService.findExamenPorNombre("Matematicas"); //falla porque findexamenpornombre no interactua con preguntasporexamen
//			examenService.findExamenPorNombreConPreguntas("Matematicas");
//		});
		
		Exception exception = assertThrows(IllegalArgumentException.class,()->{
			//examenService.findExamenPorNombre("Matematicas"); //falla porque findexamenpornombre no interactua con preguntasporexamen
			examenService.findExamenPorNombreConPreguntas("Matematicas");
		});
		
		assertEquals(IllegalArgumentException.class,exception.getClass());
		
		verify(repository).findAll();
		verify(preguntaRepository).findPreguntasPorExamenId(isNull());
	}
	
	@Test
	void testArgumentsMatchers() {
		when(repository.findAll()).thenReturn(Datos.EXAMENES);
		when(preguntaRepository.findPreguntasPorExamenId(anyLong())).thenReturn(Datos.PREGUNTAS);
		
		examenService.findExamenPorNombreConPreguntas("Matematicas");
		verify(repository).findAll();
		//verify(preguntaRepository).findPreguntasPorExamenId(argThat(arg ->arg!=null && arg.equals(5L)));
		verify(preguntaRepository).findPreguntasPorExamenId(argThat(arg ->arg!=null && arg>=5L));
		//verify(preguntaRepository).findPreguntasPorExamenId(eq(5L));
	}
	
	@Test
	void testArgumentsMatchers2() {
		when(repository.findAll()).thenReturn(Datos.EXAMENES_ID_NEGATIVOS);
		when(preguntaRepository.findPreguntasPorExamenId(anyLong())).thenReturn(Datos.PREGUNTAS);
		
		examenService.findExamenPorNombreConPreguntas("Matematicas");
		verify(repository).findAll();
		verify(preguntaRepository).findPreguntasPorExamenId(argThat(new MiArgsMatcher()));
		
		
	}
	
	@Test
	void testArgumentsMatchers3() {
		when(repository.findAll()).thenReturn(Datos.EXAMENES_ID_NEGATIVOS);
		when(preguntaRepository.findPreguntasPorExamenId(anyLong())).thenReturn(Datos.PREGUNTAS);
		
		examenService.findExamenPorNombreConPreguntas("Matematicas");
		verify(repository).findAll();
		verify(preguntaRepository).findPreguntasPorExamenId(argThat((argument)->argument !=null && argument >0));
		
		
	}
	
	public static class MiArgsMatcher implements ArgumentMatcher<Long>{

		private Long argument;
		
		@Override
		public boolean matches(Long argument) {
			// TODO Auto-generated method stub
			this.argument=argument;
			return argument !=null && argument >0;
		}

		@Override
		public String toString() {
			return "es para un mensaje personalizado de error "
				 + "que imprime mockito en caso de que falle el test "
				 + argument + " debe ser un entero positivo"	;
		}
		
		
	}
	
	@Test
	void testArgumentCaptor() {
		when(repository.findAll()).thenReturn(Datos.EXAMENES);
		when(preguntaRepository.findPreguntasPorExamenId(anyLong())).thenReturn(Datos.PREGUNTAS);
		examenService.findExamenPorNombreConPreguntas("Matematicas");
		
		//ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);//esto lo popdemos hacer con anotaciones
		verify(preguntaRepository).findPreguntasPorExamenId(captor.capture());
		
		assertEquals(5L,captor.getValue());
	}
	
	@Test
	void testDoThrow() {
		Examen examen = Datos.EXAMEN;
		examen.setPreguntas(Datos.PREGUNTAS);
		doThrow(IllegalArgumentException.class).when(preguntaRepository).guardarVarias(anyList());
		
		assertThrows(IllegalArgumentException.class,()->{
			examenService.guardar(examen);
		});
	}
	
	@Test
	void testDoAnswer() {
		when(repository.findAll()).thenReturn(Datos.EXAMENES);
		//when(preguntaRepository.findPreguntasPorExamenId(anyLong())).thenReturn(Datos.PREGUNTAS);
		doAnswer(invocation->{
			Long id = invocation.getArgument(0);
			return id == 5L?Datos.PREGUNTAS:Collections.emptyList();
		}).when(preguntaRepository).findPreguntasPorExamenId(anyLong());
		
		Examen examen = examenService.findExamenPorNombreConPreguntas("Matematicas");
		assertEquals(5,examen.getPreguntas().size());
		assertTrue(examen.getPreguntas().contains("geometria"));
		assertEquals(5L,examen.getId());
		assertEquals("Matematicas",examen.getNombre());
		
		verify(preguntaRepository).findPreguntasPorExamenId(anyLong());
	}
	
	@Test
	void testDoAnswerGuardarExamen() {
		//Given
		Examen newExamen = Datos.EXAMEN; 
		newExamen.setPreguntas(Datos.PREGUNTAS);
		
		//when(repository.guardar(any(Examen.class))).thenReturn(Datos.EXAMEN); usa el de 8L comentarizado de Datos.Examen
		
		
		doAnswer(new Answer<Examen>() {

			Long secuence = 8L;
			@Override
			public Examen answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				Examen examen = invocation.getArgument(0);
				examen.setId(secuence++);
				return examen;
			}
			
		}).when(repository).guardar(any(Examen.class));
		
		//When
		//Examen examen = examenService.guardar(Datos.EXAMEN); //no pasa porque no tiene preguntas
		Examen examen = examenService.guardar(newExamen);
		
		//Then
		assertNotNull(examen.getId());
		assertEquals(8L,examen.getId());
		assertEquals("Fisica",examen.getNombre());
		verify(repository).guardar(any(Examen.class));
		verify(preguntaRepository).guardarVarias(anyList());
	}
	
	@Test
	void testDoCallRealMethod()	{
		when(repository.findAll()).thenReturn(Datos.EXAMENES);
		//when(preguntaRepository.findPreguntasPorExamenId(anyLong())).thenReturn(Datos.PREGUNTAS);
		doCallRealMethod().when(preguntaRepository).findPreguntasPorExamenId(anyLong());
		
		Examen examen = examenService.findExamenPorNombreConPreguntas("Matematicas");
		assertEquals(5L,examen.getId());
		assertEquals("Matematicas",examen.getNombre());
		
	}
	
	@Test
	void testSpy() {
		ExamenRepository examenRepository = spy(ExamenRepositoryImpl.class);
		PreguntaRepository preguntaRepository = spy(PreguntaRepositoryImpl.class);
		
		ExamenService examenService = new ExamenServiceImpl(examenRepository,preguntaRepository);
		
		List<String> preguntas = Arrays.asList("aritmetica");
		//when(preguntaRepository.findPreguntasPorExamenId(anyLong())).thenReturn(preguntas);
		doReturn(preguntas).when(preguntaRepository).findPreguntasPorExamenId(anyLong());
		
		Examen examen = examenService.findExamenPorNombreConPreguntas("Matematicas");
		assertEquals(5,examen.getId());
		assertEquals("Matematicas",examen.getNombre());
		assertEquals(1,examen.getPreguntas().size());
		assertTrue(examen.getPreguntas().contains("aritmetica"));
		
		verify(examenRepository).findAll();
		verify(preguntaRepository).findPreguntasPorExamenId(anyLong());
	}
	
	@Test
	void testOrdenDeInvocaciones() {
		when(repository.findAll()).thenReturn(Datos.EXAMENES);
		
		examenService.findExamenPorNombreConPreguntas("Matematicas");
		examenService.findExamenPorNombreConPreguntas("Lenguaje");
		
		InOrder inOrder = inOrder(preguntaRepository);
		inOrder.verify(preguntaRepository).findPreguntasPorExamenId(5L);
		inOrder.verify(preguntaRepository).findPreguntasPorExamenId(6L);
		
	}
	
	@Test
	void testOrdenDeInvocaciones2() {
		when(repository.findAll()).thenReturn(Datos.EXAMENES);
		
		examenService.findExamenPorNombreConPreguntas("Matematicas");
		examenService.findExamenPorNombreConPreguntas("Lenguaje");
		
		InOrder inOrder = inOrder(repository,preguntaRepository);
		inOrder.verify(repository).findAll();
		
		inOrder.verify(preguntaRepository).findPreguntasPorExamenId(5L);
		inOrder.verify(repository).findAll();
		inOrder.verify(preguntaRepository).findPreguntasPorExamenId(6L);
		
	}
	
	@Test
	void testNumeroDeInvocaciones() {
		when(repository.findAll()).thenReturn(Datos.EXAMENES);
		examenService.findExamenPorNombreConPreguntas("Matematicas");
		
		verify(preguntaRepository).findPreguntasPorExamenId(5L);
		verify(preguntaRepository,times(1)).findPreguntasPorExamenId(5L);
		verify(preguntaRepository,atLeast(1)).findPreguntasPorExamenId(5L);
		verify(preguntaRepository,atLeastOnce()).findPreguntasPorExamenId(5L);
		verify(preguntaRepository,atMost(1)).findPreguntasPorExamenId(5L);
		verify(preguntaRepository,atMostOnce()).findPreguntasPorExamenId(5L);
	}
	
	@Test
	void testNumeroDeInvocaciones2() {
		when(repository.findAll()).thenReturn(Datos.EXAMENES);
		examenService.findExamenPorNombreConPreguntas("Matematicas");
		
		//verify(preguntaRepository).findPreguntasPorExamenId(5L);
		verify(preguntaRepository,times(2)).findPreguntasPorExamenId(5L);
		verify(preguntaRepository,atLeast(2)).findPreguntasPorExamenId(5L);
		verify(preguntaRepository,atLeastOnce()).findPreguntasPorExamenId(5L);
		verify(preguntaRepository,atMost(20)).findPreguntasPorExamenId(5L);
		//verify(preguntaRepository,atMostOnce()).findPreguntasPorExamenId(5L);
		
		
	}
	
	@Test
	void testNumeroDeInvocaciones3() {
		when(repository.findAll()).thenReturn(Collections.emptyList());
		examenService.findExamenPorNombreConPreguntas("Matematicas");
		
		verify(preguntaRepository,never()).findPreguntasPorExamenId(5L);
		verifyNoInteractions(preguntaRepository);
	
		verify(repository).findAll();
		verify(repository,times(1)).findAll();
		verify(repository,atLeast(1)).findAll();
		verify(repository,atLeastOnce()).findAll();
		verify(repository,atMost(10)).findAll();
		verify(repository,atMostOnce()).findAll();
		
	}
}
