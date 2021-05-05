package com.pet.dog.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import com.pet.dog.aspect.TrackEntity;
import com.pet.dog.entity.Dog;
import com.pet.dog.repository.DogRepository;

//defining business logic
@Service
public class DogService {
	
	private final DogRepository dogRepository;
	
	@Autowired
	public DogService(DogRepository dogRepository) {
		this.dogRepository = dogRepository;
	}

	@TrackEntity
	public void saveOrUpdate(Dog dog) {
		dogRepository.save(dog);
	}
	
	public List<Dog> getAllDogs(){
		List<Dog> dogs = new ArrayList<Dog>();
		dogRepository.findAll().forEach(dog -> dogs.add(dog));
		return dogs;
	}
	
	public Dog getDogById(int id) {
		return dogRepository.findById(id).get();
	}
	
	public void deleteById(int id) {
		dogRepository.deleteById(id);
	}
	
    @JmsListener(destination = "java2blog.queue")
    public void receiveQueue(String text) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Message Received: This entity is saved - "+text);
    }

}
