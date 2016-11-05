import sys
from emojilist import *
 
 #If founds emoticons in sentence returns detected 
def emoticons(tweets):
	Rating = 0
 	brokenStr1 = tweets.split()
 	for word in brokenStr1:
 		if word in emojis:
 			Rating = 'detected'
 			return Rating