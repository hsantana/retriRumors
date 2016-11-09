
import json

from pyreadability.readability import Readability

text_sample = 'There were a king with a large jaw and a queen with a plain face, on the throne of England; there were a king with a large jaw and a queen with a fair face, on the throne of France. In both countries it was clearer than crystal to the lords of the State preserves of loaves and fishes, that things in general were settled for ever.'

r = Readability(text_sample)

print(r.result)

# show just the smog score
print(r.smog)


# Method for processing Tweets
test_data = "ONE MORNING I SHOT AN ELEPHANT IN MY PAJAMAS. HOW HE GOT INTO MY PAJAMAS I'LL NEVER KNOW."

ComplexSentence = 0
print textstat.flesch_kincaid_grade(test_data)
if textstat.flesch_kincaid_grade(from Cleaner import strip_links, strip_all_entities(test_data) < 30:
	ComplexSentence = ComplexSentence + 1
print ComplexSentence

fk = FleschKincaid(text_sample)
dc = DaleChall((text_sample), simplewordlist=awordlist)

print dc.min_age
print textstat.flesch_reading_ease(test_data)
print textstat.smog_index(test_data)
print textstat.coleman_liau_index(test_data)
print textstat.automated_readability_index(test_data)
print textstat.dale_chall_readability_score(test_data)
print 'dale_chall->'+str(textstat.dale_chall_readability_score(test_data))
print textstat.difficult_words(test_data)
print textstat.linsear_write_formula(test_data)
print textstat.gunning_fog(test_data)
print textstat.text_standard(test_data)

import grammar_check
import textblob
tool = grammar_check.LanguageTool('en-GB')
text = "Everyone needs to bring their pencil"
matches = tool.check(text.decode('ascii', errors="ignore"))
print matches
print len(matches)




test_data
Grammatically incorrect sentences
"I go to the store and I bought milk. Go is a present tense verb. Bought is a past tense verb. Bought should be buy milk since these two events both occur at the same time."
Confusing Sentence
"ONE MORNING I SHOT AN ELEPHANT IN MY PAJAMAS. HOW HE GOT INTO MY PAJAMAS I'LL NEVER KNOW."
"THE HORSE RACED PAST THE BARN FELL"
"THE COMPLEX HOUSES MARRIED AND SINGLE SOLDIERS AND THEIR FAMILIES"
"THE RAT THE CAT THE DOG CHASED KILLED ATE THE MALT"
"ANYONE WHO FEELS THAT IF SO MANY MORE STUDENTS WHOM WE HAVEN’T ACTUALLY ADMITTED ARE SITTING IN ON THE COURSE THAN ONES WE HAVE THAT THE ROOM HAD TO BE CHANGED, THEN PROBABLY AUDITORS WILL HAVE TO BE EXCLUDED, IS LIKELY TO AGREE THAT THE CURRICULUM NEEDS REVISION"
"HIS EXCEEDING TRIFLING WITLING, CONSIDERING RANTING CRITICIZING CONCERNING ADOPTING FITTING WORDING BEING EXHIBITING TRANSCENDING LEARNING, WAS DISPLAYING, NOTWITHSTANDING RIDICULING, SURPASSING BOASTING SWELLING REASONING, RESPECTING CORRECTING ERRING WRITING, AND TOUCHING DETECTING DECEIVING ARGUING DURING DEBATING"
