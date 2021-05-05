package com.pet.dog.controller;

import java.util.List;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pet.dog.entity.Dog;
import com.pet.dog.service.DogService;

@RestController
public class DogController {

	private final DogService dogService;
		
	@Autowired
	public DogController(DogService dogService) {
		this.dogService = dogService;
	}
	
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
 
    @Autowired
    private Queue queue;
 
//    public void run(String... arg0) throws Exception {
//                // This will put text message to queue
//        this.jmsMessagingTemplate.convertAndSend(this.queue, "Hello Java2blog!!");
//        System.out.println("Message has been put to queue by sender");
//    }
	
	/**
	 * save the dog by invoking dogService method.
	 * 
	 * @param dog - which is going to save.
	 * @return int - which is id of saved dog.
	 * @throws JsonProcessingException 
	 */
	@PostMapping("/dog")
	private int saveDog(@RequestBody Dog dog) throws JsonProcessingException {
		dogService.saveOrUpdate(dog);
		ObjectMapper mapper = new ObjectMapper();
		String dogAsJson = mapper.writeValueAsString(dog);
        this.jmsMessagingTemplate.convertAndSend(this.queue, dogAsJson);
        System.out.println("Message has been put to queue by sender");
		return dog.getId();
	}
	
	/**
	 * get all saved dogs by invoking dogService method.
	 *  
	 * @return List<Dog> - list of dogs saved.
	 */
	@GetMapping("/dogs")
	private List<Dog> getAllDogs(){
		return dogService.getAllDogs();
	}
	
	/**
	 * get one dog by its id.
	 * 
	 * @param dogid -  which is dog unique id.
	 * @return Dog - which is the dog we get.
	 */
	@GetMapping("/dog/{dogid}")
	private Dog getDogById(@PathVariable("dogid") int dogid) {
		return dogService.getDogById(dogid);
	}
	
	/**
	 * update a dog by invoking dogService method.
	 * 
	 * @param dog - which is going to update. 
	 * @return Dog - which is updated dog.
	 */
	@PutMapping("/dog")
	private Dog updateDog(@RequestBody Dog dog) {
		dogService.saveOrUpdate(dog);
		return dog;
	}
	
	/**
	 * delete a dog by invoking dogService method.
	 * 
	 * @param id - which is dog unique id.
	 */
	@DeleteMapping("/dog/{dogid}")
	private void deleteById(@PathVariable ("dogid") int id) {
		dogService.deleteById(id);
	}
}
