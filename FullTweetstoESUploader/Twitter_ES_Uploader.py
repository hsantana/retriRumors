#Importing libaray and Api
import sys
import os
import json
from TokenKeys import *
from tweepy import Stream
from tweepy import OAuthHandler
from tweepy.streaming import StreamListener
from textwrap import TextWrapper 
from elasticsearch import Elasticsearch

# create instance of elasticsearch
es = Elasticsearch()

#Making current path in operating system
os.chdir('/Users/vishalnayanshi/rumorvenv/')

#Class Listner for streaming twitter data 
class listener(StreamListener):
  status_wrapper = TextWrapper(width=60, initial_indent='    ', subsequent_indent='    ')

  def on_status(self, status):
    try:
      json_data = status._json
      # Push rumors and details information to elasticsearch
      es.create(index="tweets",doc_type="rumors",body=json_data)

    except Exception, e:

      print e
      pass

  def on_error(self, status):
    print (status)

#Twitter Api consumer Key and Api
auth = OAuthHandler(consumer_key, consumer_secret)
auth.set_access_token(access_token, access_token_secret)

#Tweepy filters for tracking tweets 
twitterStream = Stream(auth, listener())
twitterStream.filter(track=["Newyork","Bombing"])