from os import listdir
from subprocess import check_output

input_dir = "D:/tweets_utf8"
output_dir = "D:/elastic_output"

for f in listdir(input_dir):
    out = open(output_dir + '/' + f, 'w')
    command = "curl -XPOST localhost:9200/retrirumors/tweets/_bulk?pretty --data-binary \"@" + input_dir + "/" + f
    print("Running: " + command)
    output_text = check_output(command,shell=True)
    out.write(output_text.decode("utf-8"))
    out.close()
    print(f + " output file created")

