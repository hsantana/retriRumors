import sys
import os
import csv
import nltk
from nltk.tokenize import sent_tokenize, word_tokenize

#Making current path in operating system
os.chdir('/Users/vishalnayanshi/Sentiment/')

def Abbreviationschecker(para):
    sentense = word_tokenize(para)
    word_features = []

    for i,j in nltk.pos_tag(sentense):
        if j in ['NN','NNP', 'CC', 'VBG']: 
            word_features.append(i)

    Rating = " "

    for i in word_features:
        with open('Abbreviations.txt', 'rU') as f:
            reader = csv.reader(f, delimiter=',')
            for row in reader:
                if i == row[0]:
                    if row[1] == 'abbr':
                        Rating = 'abbr'                
    return Rating
