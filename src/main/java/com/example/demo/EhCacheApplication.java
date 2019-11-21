package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.boot.SpringApplication.*;

@SpringBootApplication
@EnableCaching
public class EhCacheApplication {


	public static void main(String[] args) {

		SpringApplication.run(EhCacheApplication.class, args);
	}


}
