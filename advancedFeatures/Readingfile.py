import sys
import os
import textstatic
content_list = []
for content in os.listdir("/Users/vishalnayanshi/Sentiment/Input_files"):
    content_list.append(content)

for i in range(1, len(content_list)):
	input_file = "/Users/vishalnayanshi/Sentiment/Input_files/"+content_list[i]
	print input_file