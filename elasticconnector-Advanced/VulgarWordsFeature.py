import sys
from VulgarWords import *

#If founds any bad words in sentence marking as negative or (-1) rating 
def VulgarFilter(tweets):
	Rating = 0
	brokenStr1 = tweets.split()
	for word in brokenStr1:
		if word in arrBad:
			Rating = -1
			return Rating

# # For Testing Purposes
# print VulgarFilter('You had best unfuck yourself and start shitting me Tiffany cufflinks or I will definitely fuck you up')