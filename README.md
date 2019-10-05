Metaphone (phonetic):
first_name                  1
last_name                   2
company                     3

City and States, even when spelled wrong, are distinct so do NOT include.

Levenshtein (spelling):
email                       4
address1                    5
address2                    6
zip                         7
state (acronym)             10 
phone                       11

If the LV distance <= 12 (2 per above categories) then it is likely a duplicate.

Process:
Categorize overall strings by metaphone score, which is comprised of first_name, last_name, company.
For each overall string, check if LV distance to the original string (first one in list) <= 12.
  - If it is, keep it.