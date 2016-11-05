#!/usr/bin/python
#Importing libaray and Api
from __future__ import division
import sys
import os
import csv
from Cleaner import strip_links, strip_all_entities
from NegativePositiveWordsFeature import Opinionschecker
from VulgarWordsFeature import profanitychecker
from EmojiFeatures import emoticons
from AbbreviationsFeature import Abbreviationschecker

# Setting up current path of directory
os.chdir('/Users/vishalnayanshi/rumorvenv/RumorAnalytics/elasticconnector-Advanced')

# Method for processing Tweets
def processingtweets():
	twitterCounter = 0
	NegativeCounts = 0
	PositiveCounts = 0
	NeutralCounts = 0
	Profanitycounts = 0
	EmoticonsCounts = 0
	AbbreviationsCounts = 0
	#Read the tweets one by one and process it
	with open('tweetsTextV1.txt', 'rU') as f:
		reader = csv.reader(f, delimiter=',')
		firstline = next(reader, None)
		keywords =''.join(firstline)
		for line in reader:
			twitterCounter = twitterCounter + 1
			if line != ['-----------------------------------------------------------']:
				# NegativePositiveWordsFeature
				Rating = Opinionschecker(strip_all_entities(strip_links(line[1])))
				if Rating == 'negative':
					NegativeCounts = NegativeCounts + 1
				if Rating == 'positive':
					PositiveCounts = PositiveCounts + 1
				if Rating == ' ':
					NeutralCounts = NeutralCounts + 1	
				# VulgarWordsFeature
				Rating = profanitychecker(strip_all_entities(strip_links(line[1])))
				if Rating == 'vulgar':
					Profanitycounts = Profanitycounts + 1
				# EmoticonsFeature(Not Working)
				Rating = emoticons(strip_all_entities(strip_links(line[1])))
				if Rating == 'detected':
					EmoticonsCounts = EmoticonsCounts + 1 
				# AbbreviationsFeature
				Rating = Abbreviationschecker(strip_all_entities(strip_links(line[1])))
				if Rating == 'abbr':
					AbbreviationsCounts = AbbreviationsCounts + 1
				# 	
		return keywords, '{0:.4}'.format(twitterCounter/NegativeCounts),'{0:.4}'.format(twitterCounter/PositiveCounts),'{0:.4}'.format(twitterCounter/NeutralCounts),'{0:.4}'.format(twitterCounter/Profanitycounts), '{0:.4}'.format(twitterCounter/EmoticonsCounts),'{0:.4}'.format(twitterCounter/AbbreviationsCounts)

with open('AdvancedFeatures_Output_File.txt', 'w') as f:
    f.write(str(processingtweets()))
