import sys

input_file = open('tweetsBasicV1.csv', 'r').read().split('\n')

train_data_file_basic = open("Basicfeatures_output_file.txt",'w')

#total number of tweets for rumor one i.e. given per keyword
total_number_of_tweets = len(input_file) - 3

#extract keyword from input_file
keyword = input_file[0].replace(",","")

verified = [0 for i in range(2, total_number_of_tweets+2)] 
followers = [0 for i in range(2, total_number_of_tweets+2)] 
default_profile_image = [0 for i in range(2, total_number_of_tweets+2)] 
retweeted = [0 for i in range(2, total_number_of_tweets+2)] 
retweeted_bool = [0 for i in range(2, total_number_of_tweets+2)] 
hashtag = [0 for i in range(2, total_number_of_tweets+2)] 
url = [0 for i in range(2, total_number_of_tweets+2)] 
favourites = [0 for i in range(2, total_number_of_tweets+2)] 

main_tweet_followers_count = [0 for i in range(2, total_number_of_tweets+2)] 
retweet_followers_count = [0 for i in range(2, total_number_of_tweets+2)] 
ratio_of_retweet_to_mainTweet = [0 for i in range(2, total_number_of_tweets+2)] 
retweet_followers = [0 for i in range(2, total_number_of_tweets+2)] 
main_tweet_followers = [0 for i in range(2, total_number_of_tweets+2)]

verified_count = 0
followers_count = 0
default_profile_image_count = 0
retweeted_count = 0
favourites_count = 0
retweeted_bool_count = 0
hashtag_count = 0
url_count = 0 

#count is for counting the ratios that are greater than 5 
count = 0
low_to_high_ratio = 0

for i in range(2, total_number_of_tweets - 2):
	
	tweet = input_file[i].split(',')

	verified[i] = tweet[1]
	followers[i] = int(tweet[2])
	default_profile_image[i] = tweet[3]
	retweeted[i] = tweet[4]
	favourites[i] = tweet[5]
	retweeted_bool[i] = tweet[6]
	hashtag[i] = tweet[7]
	url[i] = tweet[8]

	if verified[i] == "true":
		verified_count = verified_count + 1

	if followers[i] != "null": 
		followers_count = followers_count + followers[i]

	if default_profile_image[i] == "true":
		default_profile_image_count = default_profile_image_count + 1

	if retweeted[i] != "null":
		retweeted_count = retweeted_count + int(retweeted[i])
	
	if favourites[i] != "null":
		favourites_count = favourites_count + 1

	if retweeted_bool == "false":
		retweeted_bool_count = retweeted_bool_count + 1

	if hashtag[i] != "[]":
		hashtag_count = len(str(hashtag).split('{')) - 1

	if hashtag[i] != "[]":
		url_count = len(str(url).split('{')) - 1

verified_ratio = float(verified_count) / total_number_of_tweets
followers_ratio = float(followers_count) / total_number_of_tweets
default_profile_image_ratio = float(default_profile_image_count )/ total_number_of_tweets
retweeted_ratio = float(retweeted_count) / total_number_of_tweets
favourite_ratio = float(favourites_count) / total_number_of_tweets
retweeted_bool_ratio = float(retweeted_bool_count) / total_number_of_tweets
hashtag_ratio = float(hashtag_count) / total_number_of_tweets
url_ratio = float(url_count) / total_number_of_tweets

#low to high diffusion feature
for i in range(2,total_number_of_tweets):
	
	tweet = input_file[i].split(',')

	main_tweet_followers[i] = int(tweet[2])
	retweet_followers[i] = int(tweet[2]) + 100
	
	if(main_tweet_followers[i] != 0):
		ratio_of_retweet_to_mainTweet[i] = float(retweet_followers[i])/main_tweet_followers[i]

	if(ratio_of_retweet_to_mainTweet[i] > 3 ):
		count = count+1
	
low_to_high_ratio = float(count) / total_number_of_tweets

features = [str(keyword), verified_ratio, followers_ratio, default_profile_image_ratio, retweeted_ratio, favourite_ratio, retweeted_bool_ratio, hashtag_ratio, url_ratio, low_to_high_ratio]
print features

f = open("Basicfeatures_output_file.txt", "w")
f.write(str(features).replace("[","").replace("]","").replace("\'","")+"\n")
f.close() 