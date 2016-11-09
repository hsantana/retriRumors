import sys
import os

content_list = []
count=0
for content in os.listdir("C:\Users\Himalini\Documents\MS\IR_DM\project\\basic_features\\all_rumors"): # "." means current directory
    content_list.append(content)

#print len(content_list)

for i in range(0, len(content_list)):
	#print i
	#print content_list[i]
	input_file = open("C:\Users\Himalini\Documents\MS\IR_DM\project\\basic_features\\all_rumors\\"+content_list[i], 'r').read().split('\n')
	#print len(file[0])
	#print input_file[2]


	#train_data_file_basic = open("Basicfeatures_output_file.txt",'w')

	#total number of tweets for rumor one i.e. given per keyword
	total_number_of_tweets = len(input_file) - 3

	#extract keyword from input_file
	keyword = input_file[0].replace(",","")
	print keyword

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
		# -: retweeted, //missing in the file
		# 7: followers_count_original, // useless
		# -: hashtags, //missing in file
		# 8: urls
		verified = tweet[1]

		followers = int(tweet[2])
		default_profile_image = tweet[3]
		retweeted = int(tweet[4])
		favourites = int(tweet[5])
		main_tweet_followers = tweet[7] 
		#print main_tweet_followers

		hashtag = tweet[9]
		url = tweet[8]

		if verified == "true":
			verified_count = verified_count + 1

		if followers != "null": 
			followers_count = followers_count + followers
			if followers >= 1000 and main_tweet_followers != "null" and int(main_tweet_followers) != 0 :
				ratio_of_retweet_to_mainTweet = float(followers)/int(main_tweet_followers)
				if(ratio_of_retweet_to_mainTweet > 2):
					count = count + 1	

		if default_profile_image == "true":
			default_profile_image_count = default_profile_image_count + 1

		if retweeted != "null":
			retweeted_count = retweeted_count + retweeted
		
		if favourites != "null":
			favourites_count = favourites_count + favourites

		if hashtag != "[]" and url != "" and url != " ":
		 	 hashtag_count = hashtag_count + (len(str(hashtag).split('{')) - 1)

		if url != "[]" and url != "" and url != " ":
			if url[0:3] == "[{d":
				url_count = url_count + (len(str(url).split('{')) - 1)
			

	verified_ratio = round((float(verified_count) / total_number_of_tweets),4)
	followers_ratio = round((float(followers_count) / total_number_of_tweets),4)
	default_profile_image_ratio = round((float(default_profile_image_count )/ total_number_of_tweets),4)
	retweeted_ratio = round((float(retweeted_count) / total_number_of_tweets),4)
	favourite_ratio = round((float(favourites_count) / total_number_of_tweets),4)
	hashtag_ratio = round((float(hashtag_count) / total_number_of_tweets),4)
	url_ratio = round((float(url_count) / total_number_of_tweets),4)
	low_to_high_ratio = round((float(count) / total_number_of_tweets),4)

	features = [str(keyword), verified_ratio, followers_ratio, default_profile_image_ratio, retweeted_ratio, favourite_ratio, hashtag_ratio, url_ratio, low_to_high_ratio]
#	print(str(features).replace("[","").replace("]","").replace("\'","")+"\n")

	f = open("Basicfeatures_output_file.txt", "a")
	f.write(str(features).replace("[","").replace("]","").replace("\'","")+"\n")
	f.close()
