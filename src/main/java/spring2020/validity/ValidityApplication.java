package spring2020.validity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import info.debatty.java.stringsimilarity.*;
import com.github.vickumar1981.stringdistance.util.*;
import org.apache.commons.codec.language.DoubleMetaphone;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

@SpringBootApplication
public class ValidityApplication {
  public static void main(String[] args) throws IOException {
    String csvFilepath = "./lib/normal.csv";
    BufferedReader csvReader = new BufferedReader(new FileReader(csvFilepath));

    // Use Metaphone and Levenshtein algorithms
    LinkedHashMap<String, Set<String>> metaphone_map = new LinkedHashMap<>();
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
      Set<String> similar_words;
      if (!metaphone_map.containsKey(mp_score)) {
        similar_words = new LinkedHashSet<>();
      } else {
        similar_words = metaphone_map.get(mp_score);
      }
      similar_words.add(metaphone_words);
      metaphone_map.put(mp_score, similar_words);
    }

/*    for (Map.Entry<String, Set<String>> entry : metaphone_map.entrySet()) {
      String key = entry.getKey();
      Set<String> value = entry.getValue();
      System.out.println("Key: " + key);
      System.out.println("Values:");
      for (String v : value) {
        System.out.println(v);
      }
      System.out.println("-------------------------------------------------------");
    }*/
    SpringApplication.run(ValidityApplication.class, args);
  }
}
