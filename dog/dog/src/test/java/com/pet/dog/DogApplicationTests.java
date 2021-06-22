package com.pet.dog;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.pet.dog.entity.Dog;
import com.pet.dog.repository.DogRepository;
import com.pet.dog.service.DogService;

public class DogApplicationTests {
	
    @InjectMocks
    DogService dogService;
     
    @Mock
    DogRepository dogRepository;
 
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
     
    @Test
    public void getAllDogsTest()
    {
        List<Dog> list = new ArrayList<Dog>();
        Dog empOne = new Dog(1, "John", 10);
        Dog empTwo = new Dog(2, "Alex", 20);
        Dog empThree = new Dog(3, "Steve", 14);
         
        list.add(empOne);
        list.add(empTwo);
        list.add(empThree);
         
        when(dogRepository.findAll()).thenReturn(list);
         
        //test
        List<Dog> empList = dogService.getAllDogs();
         
        assertEquals(list.size(), empList.size());
//        verify(dogRepository, times(1)).findAll();
    }

    @Test
    public void getDogByIdTest()
    {
    	Dog dog1 = new Dog(5, "bhara", 12);
    	when(dogRepository.save(dog1)).thenReturn(dog1);

        when(dogRepository.findById(5)).thenReturn(Optional.of(dog1));
         
        Dog dog = dogService.getDogById(5);
         
        assertEquals("bhara", dog.getName());
        assertEquals(12, dog.getAge());
    }
    
    @Test
    public void saveOrUpdateTest()
    {
    	Dog dog1 = new Dog(5, "bhara", 12);
//    	when(dogRepository.save(dog1)).thenReturn(dog1);

//        when(dogRepository.findById(5)).thenReturn(Optional.of(dog1));
         
         dogService.saveOrUpdate(dog1);
         
//        assertEquals("bhara", dog.getName());
//        assertEquals(12, dog.getAge());
    }
    
    @Test
    public void deleteByIdTest()
    {
//    	Dog dog1 = new Dog(5, "bhara", 12);
//    	when(dogRepository.save(dog1)).thenReturn(dog1);
//
//        when(dogRepository.findById(5)).thenReturn(Optional.of(dog1));
         
        dogService.deleteById(9);
         
//        assertEquals("bhara", dog.getName());
//        assertEquals(12, dog.getAge());
    }
}
