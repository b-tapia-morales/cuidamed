package com.bairontapia.projects.cuidamed;

import com.bairontapia.projects.cuidamed.person.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CuidaMedApplicationTests {

  @Autowired
  PersonRepository personRepository;

  @Test
  void contextLoads() {
    personRepository.findAll();
    personRepository.findAllNative().forEach(System.out::println);
  }
}
