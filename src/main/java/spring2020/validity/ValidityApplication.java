package spring2020.validity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@SpringBootApplication
public class ValidityApplication {
  public static void main(String[] args) throws IOException {
    String csvFilepath = "./lib/normal.csv";
    BufferedReader csvReader = new BufferedReader(new FileReader(csvFilepath));

    String row = csvReader.readLine(); // skip headers
    while ((row = csvReader.readLine()) != null) {
      String[] data = row.split(",");
      // TODO
    }

    SpringApplication.run(ValidityApplication.class, args);
  }
}
