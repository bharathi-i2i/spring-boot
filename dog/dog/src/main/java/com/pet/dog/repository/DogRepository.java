package com.pet.dog.repository;

import org.springframework.data.repository.CrudRepository;

import com.pet.dog.entity.Dog;

public interface DogRepository extends CrudRepository <Dog, Integer> {

}
