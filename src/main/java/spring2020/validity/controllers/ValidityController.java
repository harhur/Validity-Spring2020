package spring2020.validity.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring2020.validity.ValidityApplication;

// @PostMapping, @PutMapping, @DeleteMapping, @PatchMapping

@RestController
public class ValidityController {
  @GetMapping("/dashboard")
  public String showDashboard() {
    ValidityApplication application = new ValidityApplication();
    return application.output();
  }

  @RequestMapping("/home")
  public String home() {
    return "redirect:/templates/index.html";
  }
}
