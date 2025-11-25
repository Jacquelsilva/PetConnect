package com.example.petconnect.service;

import com.example.petconnect.repository.AnimalRepository;
import com.example.petconnect.model.Animal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class AnimalServiceTest {

    @MockBean
    private AnimalRepository repository;

    @Autowired
    private AnimalService service;

    @Test
    void testarDelete() {
        String id = "123";

        doNothing().when(repository).deleteById(id);

        service.deletar(id);

        verify(repository, times(1)).deleteById(id);
    }
   
    @Test
    void testarSalvar() {
        Animal animal = new Animal();
        animal.setId("123");
        animal.setNome("Mingau");

    
        when(repository.save(animal)).thenReturn(animal);

        Animal resultado = service.salvar(animal);


        verify(repository, times(1)).save(animal);

   
        assertEquals("123", resultado.getId());
        assertEquals("Mingau", resultado.getNome());
}
@Test
void testarListarTodos() {
    Animal a1 = new Animal();
    a1.setId("1");
    a1.setNome("Mingau");

    Animal a2 = new Animal();
    a2.setId("2");
    a2.setNome("Bolinha");

    List<Animal> listaMock = Arrays.asList(a1, a2);

    
    when(repository.findAll()).thenReturn(listaMock);

    
    List<Animal> resultado = service.listarTodos();

   
    verify(repository, times(1)).findAll();

   
    assertEquals(2, resultado.size());
    assertEquals("Mingau", resultado.get(0).getNome());
    assertEquals("Bolinha", resultado.get(1).getNome());
}

}




