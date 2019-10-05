package spring2020.validity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import info.debatty.java.stringsimilarity.*;
import com.github.vickumar1981.stringdistance.util.*;
import org.apache.commons.codec.language.DoubleMetaphone;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SpringBootApplication
public class ValidityApplication {
  public static void main(String[] args) throws IOException {
    String csvFilepath = "./lib/normal.csv";
    BufferedReader csvReader = new BufferedReader(new FileReader(csvFilepath));

    // Use Metaphone and Levenshtein algorithms
    LinkedHashMap<String, List<String>> metaphone_map = new LinkedHashMap<>();
    DoubleMetaphone mp = new DoubleMetaphone();

    String metaphone_words = "";
    String levenshtein = "";

    String row = csvReader.readLine(); // skip headers
    while ((row = csvReader.readLine()) != null) {
      String[] data = row.split(",");
      String[] c_data = new String[data.length];

      int clean_index = 0;
      for (int i=1; i<data.length - 1; i++) { // start 1 to ignore ID
        if (!data[i].equals("")) {
          c_data[clean_index++] = data[i];
        }
      }

      metaphone_words = c_data[1] + c_data[2] + c_data[3];
      levenshtein = c_data[4] + c_data[5] + c_data[6] + c_data[7] + c_data[10] + c_data[11];

      String mp_score = mp.doubleMetaphone(metaphone_words);
      List<String> similar_words;
      if (!metaphone_map.containsKey(mp_score)) {
        similar_words = new ArrayList<>();
      } else {
        similar_words = metaphone_map.get(mp_score);
      }
      String properties = "";
      for (String property : c_data) {
        if (property != null) {
          properties += property;
        }
      }
      similar_words.add(properties);
      metaphone_map.put(mp_score, similar_words);
    }

    LinkedHashSet<String> non_duplicates = new LinkedHashSet<>();
    // new duplicates map to prevent concurrent modification exception
    LinkedHashSet<String> duplicates = new LinkedHashSet<>();

    // Levenshtein algoirhtm
    Levenshtein lv = new Levenshtein();
    for (Map.Entry<String, List<String>> entry : metaphone_map.entrySet()) {
      List<String> value = entry.getValue();
      if (value.size() >= 2) {
        String comparison = value.get(0); // compare rest of list with the first
        for (int i=1; i<value.size() ; i++) {
          if (lv.distance(comparison, value.get(i)) <= 12.0) { // most likely not a duplicate
            duplicates.addAll(value);
          }
        }
      } else {
        // Likely not duplicates
        non_duplicates.addAll(value);
      }
    }
/*
    // Test output for DUPLICATES
    System.out.println("Duplicate Values:");
    for (String s : duplicates) {
        System.out.println(s);
    }

    System.out.println("Non-duplicate values");
    for (String s : non_duplicates) {
      System.out.println(s);
    }*/


    SpringApplication.run(ValidityApplication.class, args);
  }
}
