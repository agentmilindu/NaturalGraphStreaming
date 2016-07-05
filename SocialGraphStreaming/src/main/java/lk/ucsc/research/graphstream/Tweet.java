/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ucsc.research.graphstream;

/**
 *
 * @author agentmilindu
 */
public  class Tweet {
    public  String created_at;
    public  long id;
    public  String id_str;
    public  String text;
    public  String source;
    public  boolean truncated;
    public  In_reply_to_status_id in_reply_to_status_id;
    public  In_reply_to_status_id_str in_reply_to_status_id_str;
    public  In_reply_to_user_id in_reply_to_user_id;
    public  In_reply_to_user_id_str in_reply_to_user_id_str;
    public  In_reply_to_screen_name in_reply_to_screen_name;
    public  User user;
    public  Geo geo;
    public  Coordinates coordinates;
    public  Place place;
    public  Contributors contributors;
    public  boolean is_quote_status;
    public  long retweet_count;
    public  long favorite_count;
    public  Entities entities;
    public  boolean favorited;
    public  boolean retweeted;
    public  boolean possibly_sensitive;
    public  String filter_level;
    public  String lang;
    public  String timestamp_ms;

    public Tweet(){
    
    }
    
    public Tweet(String created_at, long id, String id_str, String text, String source, boolean truncated, In_reply_to_status_id in_reply_to_status_id, In_reply_to_status_id_str in_reply_to_status_id_str, In_reply_to_user_id in_reply_to_user_id, In_reply_to_user_id_str in_reply_to_user_id_str, In_reply_to_screen_name in_reply_to_screen_name, User user, Geo geo, Coordinates coordinates, Place place, Contributors contributors, boolean is_quote_status, long retweet_count, long favorite_count, Entities entities, boolean favorited, boolean retweeted, boolean possibly_sensitive, String filter_level, String lang, String timestamp_ms){
        this.created_at = created_at;
        this.id = id;
        this.id_str = id_str;
        this.text = text;
        this.source = source;
        this.truncated = truncated;
        this.in_reply_to_status_id = in_reply_to_status_id;
        this.in_reply_to_status_id_str = in_reply_to_status_id_str;
        this.in_reply_to_user_id = in_reply_to_user_id;
        this.in_reply_to_user_id_str = in_reply_to_user_id_str;
        this.in_reply_to_screen_name = in_reply_to_screen_name;
        this.user = user;
        this.geo = geo;
        this.coordinates = coordinates;
        this.place = place;
        this.contributors = contributors;
        this.is_quote_status = is_quote_status;
        this.retweet_count = retweet_count;
        this.favorite_count = favorite_count;
        this.entities = entities;
        this.favorited = favorited;
        this.retweeted = retweeted;
        this.possibly_sensitive = possibly_sensitive;
        this.filter_level = filter_level;
        this.lang = lang;
        this.timestamp_ms = timestamp_ms;
    }

    public static  class In_reply_to_status_id {

        public In_reply_to_status_id(){
        }
    }

    public static  class In_reply_to_status_id_str {

        public In_reply_to_status_id_str(){
        }
    }

    public static  class In_reply_to_user_id {

        public In_reply_to_user_id(){
        }
    }

    public static  class In_reply_to_user_id_str {

        public In_reply_to_user_id_str(){
        }
    }

    public static  class In_reply_to_screen_name {

        public In_reply_to_screen_name(){
        }
    }

    public static  class User {
        public  long id;
        public  String id_str;
        public  String name;
        public  String screen_name;
        public  String location;
        public  String url;
        public  Description description;
        public  boolean protected_tweet;
        public  boolean verified;
        public  long followers_count;
        public  long friends_count;
        public  long listed_count;
        public  long favourites_count;
        public  long statuses_count;
        public  String created_at;
        public  Utc_offset utc_offset;
        public  Time_zone time_zone;
        public  boolean geo_enabled;
        public  String lang;
        public  boolean contributors_enabled;
        public  boolean is_translator;
        public  String profile_background_color;
        public  String profile_background_image_url;
        public  String profile_background_image_url_https;
        public  boolean profile_background_tile;
        public  String profile_link_color;
        public  String profile_sidebar_border_color;
        public  String profile_sidebar_fill_color;
        public  String profile_text_color;
        public  boolean profile_use_background_image;
        public  String profile_image_url;
        public  String profile_image_url_https;
        public  String profile_banner_url;
        public  boolean default_profile;
        public  boolean default_profile_image;
        public  Following following;
        public  Follow_request_sent follow_request_sent;
        public  Notifications notifications;

        public User(long id, String id_str, String name, String screen_name, String location, String url, Description description, boolean protected_tweet, boolean verified, long followers_count, long friends_count, long listed_count, long favourites_count, long statuses_count, String created_at, Utc_offset utc_offset, Time_zone time_zone, boolean geo_enabled, String lang, boolean contributors_enabled, boolean is_translator, String profile_background_color, String profile_background_image_url, String profile_background_image_url_https, boolean profile_background_tile, String profile_link_color, String profile_sidebar_border_color, String profile_sidebar_fill_color, String profile_text_color, boolean profile_use_background_image, String profile_image_url, String profile_image_url_https, String profile_banner_url, boolean default_profile, boolean default_profile_image, Following following, Follow_request_sent follow_request_sent, Notifications notifications){
            this.id = id;
            this.id_str = id_str;
            this.name = name;
            this.screen_name = screen_name;
            this.location = location;
            this.url = url;
            this.description = description;
            this.protected_tweet = protected_tweet;
            this.verified = verified;
            this.followers_count = followers_count;
            this.friends_count = friends_count;
            this.listed_count = listed_count;
            this.favourites_count = favourites_count;
            this.statuses_count = statuses_count;
            this.created_at = created_at;
            this.utc_offset = utc_offset;
            this.time_zone = time_zone;
            this.geo_enabled = geo_enabled;
            this.lang = lang;
            this.contributors_enabled = contributors_enabled;
            this.is_translator = is_translator;
            this.profile_background_color = profile_background_color;
            this.profile_background_image_url = profile_background_image_url;
            this.profile_background_image_url_https = profile_background_image_url_https;
            this.profile_background_tile = profile_background_tile;
            this.profile_link_color = profile_link_color;
            this.profile_sidebar_border_color = profile_sidebar_border_color;
            this.profile_sidebar_fill_color = profile_sidebar_fill_color;
            this.profile_text_color = profile_text_color;
            this.profile_use_background_image = profile_use_background_image;
            this.profile_image_url = profile_image_url;
            this.profile_image_url_https = profile_image_url_https;
            this.profile_banner_url = profile_banner_url;
            this.default_profile = default_profile;
            this.default_profile_image = default_profile_image;
            this.following = following;
            this.follow_request_sent = follow_request_sent;
            this.notifications = notifications;
        }

        public static  class Description {
    
            public Description(){
            }
        }

        public static  class Utc_offset {
    
            public Utc_offset(){
            }
        }

        public static  class Time_zone {
    
            public Time_zone(){
            }
        }

        public static  class Following {
    
            public Following(){
            }
        }

        public static  class Follow_request_sent {
    
            public Follow_request_sent(){
            }
        }

        public static  class Notifications {
    
            public Notifications(){
            }
        }
    }

    public static  class Geo {

        public Geo(){
        }
    }

    public static  class Coordinates {

        public Coordinates(){
        }
    }

    public static  class Place {

        public Place(){
        }
    }

    public static  class Contributors {

        public Contributors(){
        }
    }

    public static  class Entities {
        public  Hashtag hashtags[];
        public  Url urls[];
        public  User_mention user_mentions[];
        public  Symbol symbols[];

        public Entities(Hashtag[] hashtags, Url[] urls, User_mention[] user_mentions, Symbol[] symbols){
            this.hashtags = hashtags;
            this.urls = urls;
            this.user_mentions = user_mentions;
            this.symbols = symbols;
        }

        public static  class Hashtag {
            public  String text;
            public  int[] indices;
    
            public Hashtag(String text, int[] indices){
                this.text = text;
                this.indices = indices;
            }
        }

        public static  class Url {
            public  String url;
            public  String expanded_url;
            public  String display_url;
            public  int[] indices;
    
            public Url(String url, String expanded_url, String display_url, int[] indices){
                this.url = url;
                this.expanded_url = expanded_url;
                this.display_url = display_url;
                this.indices = indices;
            }
        }

        public static  class User_mention {
    
            public User_mention(){
            }
        }

        public static  class Symbol {
    
            public Symbol(){
            }
        }
    }
}
