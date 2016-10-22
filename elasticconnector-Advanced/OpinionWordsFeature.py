import sys
import os
import csv
import nltk
from nltk.tokenize import sent_tokenize, word_tokenize

#Making current path in operating system
os.chdir('/Users/vishalnayanshi/rumorvenv/RumorAnalytics')

def Opinions(para):
    sentense = word_tokenize(para)
    word_features = []

    for i,j in nltk.pos_tag(sentense):
        if j in ['JJ', 'JJR', 'JJS', 'RB', 'RBR', 'RBS']: 
            word_features.append(i)

    rating = 0

    for i in word_features:
        with open('words.txt', 'rU') as f:
            reader = csv.reader(f, delimiter=',')
            for row in reader:
                if i == row[0]:
                    print i, row[1]
                    if row[1] == 'pos':
                        rating = rating + 1
                    elif row[1] == 'neg':
                        rating = rating - 1
    return rating

print (Opinions(para="I am bad man I am good man"))
