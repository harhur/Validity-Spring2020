<h3>Takeaways and Challenges</h3>

* I had a lot of fun working with string similarity algorithms, as I hadn't done anything quite like this before. The idea of indexing English words to their pronunciation is really interesting and I had to determine which properties to include in determining a row's metaphone score. 

* I had difficulty getting the data to appear in a bullet-format on the Spring server, and it appears that the server terminates immediately after starting up. For convenience I output the likely duplicate/non-duplicate rows to the console.

<h3>How to Compile and Run</h3>

1. `gradle clean`

2. `gradle uberJar` uberJar is a custom task in the `build.gradle` file that generates a JAR with its dependencies.

3. In ./build/libs, move `all-in-one-jar-1.0.jar` to root folder

4. Run via `java -jar all-in-one-jar-1.0.jar`

<h3>My Process:</h3>
1. Categorize overall strings by metaphone score, which is comprised of first_name, last_name, company.

2. For each overall string, check if LV distance to the original string (first one in list) <= 12. If it is, add it to duplicate list. Otherwise, add it to non-dupes.

<h3>String Similarity Algorithms</h3>
<b>Attribute name, Index in c_data</b>

Metaphone (phonetic):

* first_name; 1
* last_name; 2
* company; 3

City and States, even when spelled wrong, are distinct so do NOT include.

Levenshtein (spelling):
* email; 4
* address1; 5
* address2; 6
* zip; 7
* state (acronym); 10 
* phone; 11

If the LV distance <= 12 (2 per above categories, but use for the overall string) then it is likely a duplicate.
