import sys
from os import listdir

for f in listdir("D:/tweets"):
    input = open("D:/tweets/" + f, 'r')
    output = open("D:/tweets_fixed/" + f,'w')
    for line in input:
        newline = line.replace("}}{","}}\n{")
        output.write(newline)
    output.close()
