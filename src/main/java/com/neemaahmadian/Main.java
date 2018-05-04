package com.neemaahmadian;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Motor myRover = new Motor();
        MyTwitter myTwitter = new MyTwitter();
        TwitterData myData = new TwitterData(myTwitter.getLatestTweet(), myTwitter.getLatestUsername());


        myData.updateInfo();



        System.out.println("Username: " + myData.getUsername());
        System.out.println("Message: " + myData.getMessage());
        System.out.println("Duration: " + myData.getMessage());
        System.out.println("Speed: " + myData.getMessage());
        System.out.println("Direction: " + myData.getMessage());

        /*myRover.on(1);

        try {
            Thread.sleep(2000); //sleep amount of milliseconds
        } catch (InterruptedException e) {
            System.out.println("got interrupted!");
        }

        myRover.off();
        myRover.on(-1, 2000);
        myRover.on(1, 2000);

        myRover.turn("R", 3000);
        myRover.turn("L", 3000);*/


    }
}
