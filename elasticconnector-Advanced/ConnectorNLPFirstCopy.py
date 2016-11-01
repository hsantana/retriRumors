#Importing libaray and Api
import sys
from Cleaner import strip_links, strip_all_entities
from NegativePositiveWords import Opinions
from Vulgarwordsfeature import VulgarFilter
from elasticsearch import Elasticsearch

# create instance of elasticsearch
es = Elasticsearch()

res = es.search(index="tweets", doc_type='rumors',body={"query": {"match_all": {}}, "size": 2000})
for hit in res['hits']['hits']:
	if 'text' in hit['_source']:
		print strip_all_entities(strip_links(hit['_source']['text'].encode("utf-8")))
		if 'retweeted_status' in hit['_source']:
			print strip_all_entities(strip_links(hit['_source']['retweeted_status']['text'].encode("utf-8")))
			if 'user' in hit['_source']['retweeted_status']:
				if 'description' in hit['_source']['retweeted_status']:
					print strip_all_entities(strip_links(hit['_source']['retweeted_status']['user']['description'].encode("utf-8")))