package spring2020.validity.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// @PostMapping, @PutMapping, @DeleteMapping, @PatchMapping

@RestController
public class ValidityController {
  @GetMapping("/dashboard")
  public String showDashboard() {
    return "This is the dashboard!";
  }
}
