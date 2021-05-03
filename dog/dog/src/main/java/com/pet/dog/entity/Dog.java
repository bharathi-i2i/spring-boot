package com.pet.dog.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Dog {
	
	@Id
	private int id;
	private String name;
	private int age;
	
	public Dog() {}

}
