package io.thewokecoder.example.controller;

import io.thewokecoder.example.api.EmployeeApi;
import io.thewokecoder.example.model.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController implements EmployeeApi {
  @Override
  public ResponseEntity<Employee> getEmployees() {
    return ResponseEntity.ok(new Employee().firstName("John").lastName("Lim").age(28));
  }
}
