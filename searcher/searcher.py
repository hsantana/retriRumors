from elasticsearch import Elasticsearch
es = Elasticsearch()
results_raw = open('results_desc.csv')

results = {}

for line in results_raw:
    line = line.rstrip()
    columns = line.split(';')
    results[columns[0]] = {}
    results[columns[0]]['real label'] = columns[1]
    results[columns[0]]['predicted label'] = columns[2]
    results[columns[0]]['confidence'] = columns[3]
    results[columns[0]]['description'] = columns[4]

es.indices.refresh(index="retrirumors")

while(True):
    keyword = input("Enter your keyword: ")
    max_tweets = input("Enter the maximum number of retrieved tweets for each rumor: ")

    found = False
    for key in results:
        if keyword in key:
            found = True
            print("Retrieved rumor: " + key)
            print("\tReal label: " + results[key]['real label'])
            print("\tPredicted label: " + results[key]['predicted label'])
            print("\tConfidence: " + results[key]['confidence'][0:5]) #chunk
            print("\tDescription: " + results[key]['description'])
            body={
                    "query": {
                        "bool": {
                            "must": []
                        }
                    }
                }
            for word in key.split(' '):
                body['query']['bool']['must'].append({"term" : { "text": word }})
            res = es.search(index="retrirumors", body=body, size=max_tweets, from_=12)
            #print("\tNumber of related tweets: " + str(res['hits']['total']))
            print("\tSamples:")
            for hit in res['hits']['hits']:
                print("\t\t" + hit['_source']['text'])

    if not found:
        print("There are no correspoding rumors in the database")

    print("\n\n")