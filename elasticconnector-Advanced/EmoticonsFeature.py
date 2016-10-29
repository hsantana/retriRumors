import sys
import os
import csv
import nltk
from nltk.tokenize import sent_tokenize, word_tokenize

#Making current path in operating system
os.chdir('/Users/vishalnayanshi/rumorvenv/RumorAnalytics/elasticconnector-Advanced')

def Emoticons(tweets):
    sentense = word_tokenize(tweets)
    word_features = []

    for i,j in nltk.pos_tag(sentense):
        if j in []:  # already Checked Out this program making changes 
            word_features.append(i)

    Rating = 0

    for i in word_features:
        with open('Emoticon.txt', 'rU') as f:
            reader = csv.reader(f, delimiter=',')
            for row in reader:
                print row
                if i == row[0]:
                    print i, row[1]
                    if row[1] == 'pos':
                        Rating = Rating + 1
                    elif row[1] == 'neg':
                        Rating = Rating - 1                  
    # return Rating

# # For Testing Purpose
# print Emoticons("my girl bc thats ALL ME")
