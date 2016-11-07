from os import listdir
from subprocess import check_output

input_dir = "/Users/Hugo/Documents/all_utf8_tweets"
output_dir = "/Users/Hugo/Documents/logElasticSearch"

for f in listdir(input_dir):
    out = open(output_dir + '/' + f, 'w')
    command = "curl -XPOST 'localhost:9200/retrirumorstest/tweetstest/_bulk?pretty' --data-binary \"@" + input_dir + "/" + f + "\""  
    print("Running: " + command)
    output_text = check_output(command,shell=True)
    out.write(output_text.decode("utf-8"))
    out.close()
    print(f + " output file created")

