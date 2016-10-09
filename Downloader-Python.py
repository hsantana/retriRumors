#Importing libaray and Api
import os
import json
from tweepy import Stream
from tweepy import OAuthHandler
from tweepy.streaming import StreamListener
from elasticsearch import Elasticsearch

# create instance of elasticsearch
es = Elasticsearch()

#Making current path in operating system
os.chdir('/Users/vishalnayanshi/rumorvenv/')

#consumer key, consumer secret, access token, access secret.
access_token="4313209397-xLb7abokbrIRxcaDHl9Oj46ROwLbPXKoIqM7VvX"
access_token_secret="22QJTofAFcmuoN2eeF14CG0a7tQXCKBLpzaqYtqpOvPRA"
consumer_key="otfO9yCq38AbSeTpWm9T03WQB"
consumer_secret="c1NzhXnXfyMr36Hdswt78ENAOGNgvtQ8XHwDAThcBLvk8YEN6I"

#Class Listner for streaming twitter data 
class listener(StreamListener):
    def on_data(self, data):
        tweets = json.loads(data)
    	f = open('vishal-tweets.txt', 'a')
        f.write(str(tweets))

        return True

    def on_error(self, status):
        print status

#Twitter Api consumer Key and Api
auth = OAuthHandler(consumer_key, consumer_secret)
auth.set_access_token(access_token, access_token_secret)

#Tweepy filters for tracking tweets 
twitterStream = Stream(auth, listener())
twitterStream.filter(track=["Newyork","Bombing"])
