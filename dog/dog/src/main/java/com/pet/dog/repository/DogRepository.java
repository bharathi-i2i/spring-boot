package com.pet.dog.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pet.dog.entity.Dog;

@Repository
public interface DogRepository extends CrudRepository <Dog, Integer> {

}
