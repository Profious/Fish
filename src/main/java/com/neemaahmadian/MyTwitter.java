package com.neemaahmadian;

/**
 * Created by Neema
 * Used to directly access twitter
 */

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;
import java.util.*;

public class MyTwitter{
    private int counter = 0;
    private TwitterData myData;
    private String latestUsername;

    public String getLatestTweet() {
        //System.out.println("Get Latest Tweet Counter: " + counter);
        //counter++;
        ConfigurationBuilder cb = new ConfigurationBuilder();
        Twitter twitterInstance;
        Query query4Twitter;

        cb.setOAuthConsumerKey("0aKiWf9V25gvp7NLEusPAgTu2");
        cb.setOAuthConsumerSecret("f2Z4XlCl23wyTjwg2Xx3971rqePCcRhDtgkPbcy8RkWo4HbDpA");
        cb.setOAuthAccessToken("962397751614099457-GASkR2zpKt6IVqai1J0xv6Dg1ommEgP");
        cb.setOAuthAccessTokenSecret("l33lf3XxscvwSXiCcW4mhw8G8S7etM4uDLvLHn9Lr3MFr");

        twitterInstance = new TwitterFactory(cb.build()).getInstance();

        query4Twitter = new Query("#TORCS");

        try {
            int index = 0;
            //boolean finished = false;
            QueryResult result = twitterInstance.search(query4Twitter);
            Status t;
            while(true) {
                t = (Status) result.getTweets().get(index);
                String username = t.getUser().getName();
                String latestUsername = username;
                String msg = t.getText();
                if (msg.contains("@TORCS_Rover")) {
                    latestUsername = username;
                    return(msg);
                } else {
                    index++;
                    System.out.println("invalid message contents(" + index + ") :  " + username + " -------- " + msg);
                }

            }
        } catch (TwitterException te) {
            System.out.println("Couldn't connect: " + te);
        }
        return "";
    }

    public String getLatestUsername() {
        return latestUsername;
    }
}