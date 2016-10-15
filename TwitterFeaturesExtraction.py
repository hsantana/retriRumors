#Importing libaray and Api
import os
import json
import csv
from tweepy import Stream
from tweepy import OAuthHandler
from tweepy.streaming import StreamListener

#Making current path in operating system make changes for Window / Mac / Ubuntu
os.chdir('/Users/vishalnayanshi/rumorvenv/')

#consumer key, consumer secret, access token, access secret.
access_token="4313209397-xLb7abokbrIRxcaDHl9Oj46ROwLbPXKoIqM7VvX"
access_token_secret="22QJTofAFcmuoN2eeF14CG0a7tQXCKBLpzaqYtqpOvPRA"
consumer_key="otfO9yCq38AbSeTpWm9T03WQB"
consumer_secret="c1NzhXnXfyMr36Hdswt78ENAOGNgvtQ8XHwDAThcBLvk8YEN6I"

#Class Listner for streaming twitter data 
class listener(StreamListener):
    def on_data(self, data):
        feeds = json.loads(data) 
        features={"author": feeds["user"]["screen_name"],
                   "date": feeds["created_at"],
                   "message": feeds["text"],
                   " original Tweets author":feeds['in_reply_to_screen_name'],
                   "tweet language": feeds['lang'],
                   "location": feeds['place'],
                   "retweet count": feeds['retweet_count']}

        f = open('TwitterFeatures.csv', 'a')
        f.write(str(features))
        #Make it True for multiple rows or keep it False for one row           
        return False

    def on_error(self, status):
        print status

#Twitter Api consumer Key and Api
auth = OAuthHandler(consumer_key, consumer_secret)
auth.set_access_token(access_token, access_token_secret)

#Tweepy filters for tracking tweets 
twitterStream = Stream(auth, listener())
twitterStream.filter(track=["NewYork","Bombing"])