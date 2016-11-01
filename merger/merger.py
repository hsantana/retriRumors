import sys

basic_features_raw = open(sys.argv[1], 'r')
advanced_features_raw = open(sys.argv[2], 'r')
merged_file = open(sys.argv[3],'w')

merged_features = {}

for line in basic_features_raw:
    line = line.rstrip()
    key_and_values = line.split(',',1)
    merged_features[key_and_values[0]] = {}
    merged_features[key_and_values[0]]['basic'] = key_and_values[1]

for line in advanced_features_raw:
    line = line.rstrip()
    key_and_values = line.split(',',1)
    merged_features[key_and_values[0]]['advanced'] = key_and_values[1]

for key in merged_features:
    merged_file.write(key + ',' + merged_features[key]['basic'] + ',' + merged_features[key]['advanced'] + "\n")


