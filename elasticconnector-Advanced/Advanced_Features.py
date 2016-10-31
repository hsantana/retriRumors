#!/usr/bin/python
#Importing libaray and Api
import sys
import os
from Cleaner import strip_links, strip_all_entities
from NegativePositiveWordsFeature import Opinionschecker
from VulgarWordsFeature import profanitychecker
from EmoticonsFeature import Emoticonschecker
from AbbreviationsFeature import Abbreviationschecker

# Setting up current path of directory
os.chdir('/Users/vishalnayanshi/rumorvenv/RumorAnalytics/elasticconnector-Advanced')

# Method for processing Tweets
def processingtweets():
	#Read the tweets one by one and process it
	Twitter_text_file = open('tweetsTextV1.txt', 'r')
	line = Twitter_text_file.readline()

	train_data_file = open("output_file.txt",'w')

	while line:
		Rating = " "
		if line != " ":

			# NegativePositiveWordsFeature
			Rating = Opinionschecker(strip_all_entities(strip_links(line)))
			# VulgarWordsFeature
			if Rating != 'negative' or ' ':
				Rating = profanitychecker(strip_all_entities(strip_links(line)))
			# EmoticonsFeature
			elif Rating != 'negative' or ' ':
				Rating = Emoticonschecker(strip_all_entities(strip_links(line)))
			# AbbreviationsFeature
			elif Rating != 'negative' or ' ':
				Rating = Abbreviationschecker(strip_all_entities(strip_links(line)))

		# Writing train data file
		print Rating
		train_data_file.write(line+' '+Rating+'\n')
		line = Twitter_text_file.readline()

	Twitter_text_file.close()
	train_data_file.close()

processingtweets()