import sys

input_file = open('tweetsBasicV2.csv', 'r').read().split('\n')

train_data_file_basic = open("Basicfeatures_output_file.txt",'w')

#total number of tweets for rumor one i.e. given per keyword
total_number_of_tweets = len(input_file) - 3

#extract keyword from input_file
keyword = input_file[0].replace(",","")

main_tweet_followers_count = [0 for i in range(2, total_number_of_tweets + 2)] 
retweet_followers_count = [0 for i in range(2, total_number_of_tweets + 2)] 
ratio_of_retweet_to_mainTweet = [0 for i in range(2, total_number_of_tweets + 2)] 
retweet_followers = [0 for i in range(2, total_number_of_tweets + 2)] 
main_tweet_followers = [0 for i in range(2, total_number_of_tweets + 2)]

# initialize counters
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

for i in range(2, total_number_of_tweets + 2):
	
	tweet = input_file[i].split(',')

	# 0: id, 
	# 1: verified,
	# 2: followers_count, 
	# 3: default_profile_image,
	# 4: retweet_count,
	# 5: favorites_count,
	# 6: retweeted,
	# 7: followers_count_original, // useless
	# -: hashtags, //missing in file
	# 8: urls
	verified = tweet[1]
	followers = int(tweet[2])
	retweet_followers = followers + 100 # replace with actual followers!!!
	default_profile_image = tweet[3]
	retweeted = int(tweet[4])
	favourites = int(tweet[5])
	retweeted_bool = tweet[6]
	#hashtag = tweet[7]
	url = tweet[8]

	if verified == "true":
		verified_count = verified_count + 1

	if followers != "null": 
		followers_count = followers_count + followers
		if followers > 0:
			ratio_of_retweet_to_mainTweet = float(retweet_followers)/followers
			if(ratio_of_retweet_to_mainTweet > 3):
				count = count + 1	

	if default_profile_image == "true":
		default_profile_image_count = default_profile_image_count + 1

	if retweeted != "null":
		retweeted_count = retweeted_count + retweeted
	
	if favourites != "null":
		favourites_count = favourites_count + favourites

	if retweeted_bool == "true":
		retweeted_bool_count = retweeted_bool_count + 1

	# if hashtag != "[]" and url != "" and url != " ":
	# 	 hashtag_count = len(str(hashtag).split('{')) - 1

	if url != "[]" and url != "" and url != " ":
		if url[0:3] == "[{d":
			url_count = url_count + (len(str(url).split('{')) - 1)
		

verified_ratio = float(verified_count) / total_number_of_tweets
followers_ratio = float(followers_count) / total_number_of_tweets
default_profile_image_ratio = float(default_profile_image_count )/ total_number_of_tweets
retweeted_ratio = float(retweeted_count) / total_number_of_tweets
favourite_ratio = float(favourites_count) / total_number_of_tweets
retweeted_bool_ratio = float(retweeted_bool_count) / total_number_of_tweets
#hashtag_ratio = float(hashtag_count) / total_number_of_tweets
url_ratio = float(url_count) / total_number_of_tweets
low_to_high_ratio = float(count) / total_number_of_tweets

# add hashtag_ratio back when present!!!
features = [str(keyword), verified_ratio, followers_ratio, default_profile_image_ratio, retweeted_ratio, favourite_ratio, retweeted_bool_ratio, url_ratio, low_to_high_ratio]
print(str(features).replace("[","").replace("]","").replace("\'","")+"\n")

f = open("Basicfeatures_output_file.txt", "w")
f.write(str(features).replace("[","").replace("]","").replace("\'","")+"\n")
f.close() 