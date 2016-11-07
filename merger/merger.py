import sys
#himalini is beautiful
basic_features_raw = open(sys.argv[1], 'r')
advanced_features_raw = open(sys.argv[2], 'r')
merged_file = open(sys.argv[3],'w')
labels_file = open('labels.csv','r')

labels = {}

for line in labels_file:
    line = line.rstrip()
    key_and_value = line.split(',')
    labels[key_and_value[0]] = key_and_value[1]

merged_features = {}

for line in basic_features_raw:
    line = line.rstrip()
    key_and_values = line.split(',',1)
    key = key_and_values[0]
    value = key_and_values[1]
    if key not in merged_features:
        merged_features[key] = {}
    merged_features[key]['basic'] = value

for line in advanced_features_raw:
    line = line.rstrip()
    line = line.replace(')','')
    line = line.replace('(','')
    line = line.replace("\'",'')
    key_and_values = line.split(',',1)
    key = key_and_values[0]
    value = key_and_values[1]
    if key not in merged_features:
        merged_features[key] = {}
    merged_features[key]['advanced'] = value

for key in merged_features:
    label = labels.get(key,'LABELMISSING')
    merged_file.write(key + ',' + merged_features[key].get('basic','BASICMISSING') + ',' + merged_features[key].get('advanced','ADVANCEDMISSING') + ',' + label + "\n")


