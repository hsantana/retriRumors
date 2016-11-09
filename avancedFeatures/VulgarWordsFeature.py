import sys
import os
import csv
import nltk
from nltk.tokenize import sent_tokenize, word_tokenize

#Making current path in operating system
os.chdir('/Users/vishalnayanshi/Sentiment/')

def profanitychecker(para):
    sentense = word_tokenize(para)
    word_features = []

    for i,j in nltk.pos_tag(sentense):
        if j in ['NN','CD','NNS','JJ','DT','VBP','VBG','VBD','S','IN','RB','WRB','VB','PRP','P']: 
            word_features.append(i)

    Rating = " "
    for i in word_features:
        with open('VulgarWords.txt', 'rU') as f:
            reader = csv.reader(f, delimiter=',')
            for row in reader:
                if i == row[0]:
                    # print i, row[1]
                    if row[1] == 'Vulgar':
                        Rating = 'vulgar'                 
    return Rating
