import sys

input_file = open('tweetsBasicV1.csv', 'r').read().split('\n')

#train_data_file_basic = open("Basicfeatures_output_file.txt",'w')


#total number of tweets for rumor one i.e. given per keyword
total_number_of_tweets = len(input_file) - 3

#extract keyword from input_file
keyword = input_file[0].replace(",","")

verified = [0 for i in range(2, total_number_of_tweets+2)] 
followers = [0 for i in range(2, total_number_of_tweets)] 
default_profile_image = [0 for i in range(2, total_number_of_tweets)] 
retweeted = [0 for i in range(2, total_number_of_tweets)] 
retweeted_bool = [0 for i in range(2, total_number_of_tweets)] 
hashtag = [0 for i in range(2, total_number_of_tweets)] 
url = [0 for i in range(2, total_number_of_tweets)] 
favourites = [0 for i in range(2, total_number_of_tweets)] 

verified_count = 0
followers_count = 0
default_profile_image_count = 0
retweeted_count = 0
favourites_count = 0
retweeted_bool_count = 0
hashtag_count = 0
url_count = 0 

for i in range(2, total_number_of_tweets - 2):
	
	tweet = input_file[i].split(',')

	#protected[i] =  tweet[]
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

	if retweeted_bool == "true":
		retweeted_bool_count = retweeted_bool_count + 1
	
	if favourites[i] != "null":
		favourites_count = favourites_count + 1
	if hashtag[i] != "[]":
		hashtag_count = len(str(hashtag).split('{')) - 1
	if hashtag[i] != "[]":
		url_count = len(str(url).split('{')) - 1


verified_ratio = verified_count / total_number_of_tweets
followers_ratio = followers_count / total_number_of_tweets
default_profile_image_ratio = default_profile_image_count / total_number_of_tweets
retweeted_count_ratio = retweeted_count / total_number_of_tweets
favourite_ratio = favourites_count / total_number_of_tweets
retweeted_bool_ratio = retweeted_bool_count / total_number_of_tweets
hashtag_ratio = hashtag_count / total_number_of_tweets
url_ratio = url_count / total_number_of_tweets

print("Verified ratio: \t\t\t\t" + str(verified_ratio))
print("Followers ratio: \t\t\t\t" + str(followers_ratio))
print("Default profile image ratio: \t" + str(default_profile_image_ratio))
print("Retweeted count ratio: \t\t\t" + str(retweeted_count_ratio))
print("Retweeted true/false: \t\t\t" + str(retweeted_bool_ratio))
print("Favourite ratio: \t\t\t\t" + str(favourite_ratio))
print("Hashtag ratio: \t\t\t\t\t" + str(hashtag_ratio))
print("Url ratio: \t\t\t\t\t\t" + str(url_ratio))

print("-------------")

features = 	[keyword, verified_ratio, followers_ratio, default_profile_image_ratio, 
			retweeted_count_ratio, retweeted_bool_ratio, favourite_ratio, hashtag_ratio, url_ratio]

s = open('output.txt', 'w')
s.write(str(features).replace("[", "").replace("]", "").replace("\'", "") + "\n")
s.close()
