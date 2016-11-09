#!/usr/bin/python
#Importing libaray and Api
from __future__ import division
import sys
import os
import csv
from textstat.textstat import textstat
from Cleaner import strip_links, strip_all_entities
from NegativePositiveWordsFeature import Opinionschecker
from VulgarWordsFeature import profanitychecker
from EmojiFeatures import emoticons
from AbbreviationsFeature import Abbreviationschecker

# Reading Number of Files in Folder
content_list = []
for content in os.listdir("/Users/vishalnayanshi/Sentiment/Input_files"):
	content_list.append(content)

# Method for processing Tweets
def processingtweets(file_path):
	print file_path
	twitterCounter = 0.0
	NegativeCounts = 0.0
	PositiveCounts = 0.0
	NeutralCounts = 0.0
	Profanitycounts = 0.0
	EmoticonsCounts = 0.0
	AbbreviationsCounts = 0.0
	ComplexSentence = 0.0
	GrammerSentence = 0.0
	returnStatement = ""
	#Read the tweets one by one and process it
	with open(file_path, 'rU') as f:
		reader = csv.reader(f, delimiter=',')
		firstline = next(reader, None)
		keywords =''.join(firstline)
		for line in reader:
			twitterCounter = twitterCounter + 1
			if line != ['-----------------------------------------------------------']:
				# Negative Positive Words Feature
				Rating = Opinionschecker(strip_all_entities(strip_links(line[1])))
				if Rating == 'negative':
					NegativeCounts = NegativeCounts + 1
				if Rating == 'positive':
					PositiveCounts = PositiveCounts + 1
				if Rating == ' ':
					NeutralCounts = NeutralCounts + 1	
				# VulgarWords Feature
				Rating = profanitychecker(strip_all_entities(strip_links(line[1])))
				if Rating == 'vulgar':
					Profanitycounts = Profanitycounts + 1
				# Emoticons Feature
				Rating = emoticons(strip_all_entities(strip_links(line[1])))
				if Rating == 'detected':
					EmoticonsCounts = EmoticonsCounts + 1 
				# Abbreviations Feature
				Rating = Abbreviationschecker(strip_all_entities(strip_links(line[1])))
				if Rating == 'abbr':
					AbbreviationsCounts = AbbreviationsCounts + 1
				#Sentence Complexity Feature
				if textstat.dale_chall_readability_score((strip_all_entities(strip_links(line[1])))) < 25:
					ComplexSentence = ComplexSentence + 1
				# Grammatical Complexity Feature
				# tool = grammar_check.LanguageTool('en-GB')
				# matches = tool.check(strip_all_entities(strip_links(line[1].decode('ascii', errors="ignore"))))
				# if matches > 5:
				# 	GrammerComplexity = GrammerComplexity + 1
				returnStatement = keywords, '{0:.4}'.format(NegativeCounts/twitterCounter),'{0:.4}'.format(PositiveCounts/twitterCounter),'{0:.4}'.format(NeutralCounts/twitterCounter),'{0:.4}'.format(Profanitycounts/twitterCounter), '{0:.4}'.format(EmoticonsCounts/twitterCounter),'{0:.4}'.format(AbbreviationsCounts/twitterCounter), '{0:.4}'.format(ComplexSentence/twitterCounter)

		return returnStatement
with open('AdvancedFeatures_Output_File.txt', 'a') as f:
	for i in range(0, len(content_list)):
		f.write("\n")
		input_file = "/Users/vishalnayanshi/Sentiment/Input_files/"+content_list[i]
		if input_file != "/Users/vishalnayanshi/Sentiment/Input_files/.DS_Store":
			f.write(str(processingtweets(input_file)))
