#Importing libaray and Api
import sys
from Cleaner import strip_links, strip_all_entities
from NegativePositiveWords import Opinions
from Vulgarwordsfeature import VulgarFilter
from elasticsearch import Elasticsearch

# create instance of elasticsearch
es = Elasticsearch()

def features():

	Rating = 0
	counter = 0
	Negativetweets = 0
	Positivetweets = 0

	res = es.search(index="tweets", doc_type='rumors',body={"query": {"match_all": {}}, "size": 5})
	for hit in res['hits']['hits']:
		counter = counter + 1
		if 'text' in hit['_source']:
			tweets = strip_all_entities(strip_links(hit['_source']['text'].encode("utf-8")))
			print Opinions(tweets)
			if Rating == -1:
				Negativetweets = Negativetweets + 1
			elif Rating == 1:
				Positivetweets = Positivetweets + 1 
			if 'retweeted_status' in hit['_source']:
				re_tweets = strip_all_entities(strip_links(hit['_source']['retweeted_status']['text'].encode("utf-8")))
				print Opinions(re_tweets)
	return counter, Negativetweets, Positivetweets

print features()