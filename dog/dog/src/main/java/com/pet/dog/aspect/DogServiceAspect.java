package com.pet.dog.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.pet.dog.entity.Dog;

@Aspect
@Component
public class DogServiceAspect {
	
	@Around("@annotation(com.pet.dog.aspect.TrackEntity) && (args(dog))")
	public void beforeAdvice(JoinPoint joinPoint, Dog dog) {  
		System.out.println("Before method:" + joinPoint.getSignature());  
		System.out.println("Saving Dog - " + dog);  
	}  
}
