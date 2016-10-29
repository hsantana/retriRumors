import sys
from BadWord import *

#If founds any bad words in sentence marking as negative or (-1) rating 
def VulgarFilter(text):
	Rating = 0
	brokenStr1 = text.split()
	for word in brokenStr1:
		if word in arrBad:
			Rating = -1
			return Rating