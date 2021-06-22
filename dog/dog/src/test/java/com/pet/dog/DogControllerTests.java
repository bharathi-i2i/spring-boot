package com.pet.dog;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.pet.dog.controller.DogController;
import com.pet.dog.entity.Dog;
import com.pet.dog.repository.DogRepository;
import com.pet.dog.service.DogService;

@WebMvcTest(DogController.class)
public class DogControllerTests {
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private DogController dogController;
	
	@Mock
	private DogService dogService;
	
	@Mock
	private DogRepository dogRepository;
	
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(dogController).build();
    }

	
	@Test
	public void getAllDogsTest() throws Exception {
		
		List<Dog> allDogs = new ArrayList<Dog>();
		allDogs.add(new Dog(1, "bhara" , 12));
		allDogs.add(new Dog(2, "meera" , 10));

		Mockito.when(dogService.getAllDogs()).thenReturn(allDogs);
		
		mockMvc.perform(get("/dogs"))
		.andExpect(status().isOk());
	}
	
	@Test
	public void getDogByIdTest() throws Exception {
		
		Dog dog = new Dog(1, "dupo", 10);
    	when(dogRepository.save(dog)).thenReturn(dog);
        when(dogRepository.findById(1)).thenReturn(Optional.of(dog));
        Dog dog1 = dogService.getDogById(1);
		mockMvc.perform(get("/dog/1"))
		.andExpect(status().isOk());
	}
	
	@Test
	public void saveDogTest() throws Exception {
		
		Dog dog = new Dog(1, "begu", 12);
    	when(dogRepository.save(dog)).thenReturn(dog);
        dogService.saveOrUpdate(dog);
        Mockito.doNothing().when(dogService).saveOrUpdate(dog);
		mockMvc.perform(post("/dog"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(jsonPath("$.name").value("begu"))
		.andExpect(jsonPath("$.id").value(1))
		.andExpect(jsonPath("$.age").value(12));
	}
	
	@Test
	public void updateDogTest() throws Exception {
		
		Dog dog = new Dog(1, "peidu", 12);
    	when(dogRepository.save(dog)).thenReturn(dog);
        dogService.saveOrUpdate(dog);
        Mockito.doNothing().when(dogService).saveOrUpdate(dog);
		mockMvc.perform(put("/dog"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(jsonPath("$.name").value("peidu"))
		.andExpect(jsonPath("$.id").value(1))
		.andExpect(jsonPath("$.age").value(12));
	}

	@Test
	public void deleteDogTest() throws Exception {
		
		Dog dog = new Dog(1, "kadpoq", 7);
    	when(dogRepository.save(dog)).thenReturn(dog);
        Mockito.doNothing().when(dogRepository).deleteById(1);
        dogService.deleteById(1);
		mockMvc.perform(delete("/dog/1"))
		.andExpect(status().isOk());
	}
	
}
