package com.neemaahmadian;

/**
 * Created by Neema
 * Drives the project and keeps looping to check for new tweets
 */

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Motor myRover = new Motor();
        MyTwitter myTwitter = new MyTwitter();
        TwitterData myData = new TwitterData(myTwitter.getLatestTweet(), myTwitter.getLatestUsername());

        String oldTweet = myData.getMessage();

        for (int i=0; i<1000; i++) {
            myData.updateInfo();
            if (!myData.getMessage().equals(oldTweet)) {
                oldTweet = myData.getMessage();
                if (myData.getAction().equals("move")){
                    myRover.on(myData.getSpeed(), (myData.getDuration()*1000));
                } else if (myData.getAction().equals("turn")){
                    myRover.turn(myData.getDirection(), (myData.getDuration()*1000));
                }
            }

            //makes the loop run once every 7 seconds so that twitter doesn't lock up my access key for spamming their servers
            try {
                Thread.sleep(7000); //sleep amount of milliseconds
            } catch (InterruptedException e) {
                System.out.println("got interrupted!");
            }
        }
    }
}
