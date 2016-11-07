from os import listdir

for f in listdir("D:/new_tweets_windows3"):
    print("Recoding: " + f)
    input = open("D:/new_tweets_windows3/" + f, 'r')
    output = open("D:/new_tweets_utf8/" + f,'wb')
    for line in input:
        newline = line.encode('utf-8')
        output.write(newline)
    output.close()