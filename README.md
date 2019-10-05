Metaphone (phonetic):
first_name                  1 (index in c_data)
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

If the LV distance <= 12 (2 per above categories, but for the overall string) then it is likely a duplicate.

Process:
1. Categorize overall strings by metaphone score, which is comprised of first_name, last_name, company.
2. For each overall string, check if LV distance to the original string (first one in list) <= 12. If it is, keep it.
