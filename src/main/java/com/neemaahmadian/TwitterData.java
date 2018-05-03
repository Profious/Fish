package com.neemaahmadian;

public class TwitterData {
    private String username;
    private String message;
    private String[] command;
    private int duration;
    private int speed;

    //constructor
    public TwitterData(String username, String message) {
        this.username = username;
        this.message = message;
    }

    public void generateCommand() {
        String original = getMessage().toLowerCase();
        String[] segments = original.split(" ");
        if (segments.length>2) {
            command = new String[segments.length - 2];
            for (int i = 0; i < command.length; i++) {
                command[i] = segments[i + 2];
            }
        } else {
            command = new String[segments.length];
            command = segments;
        }

    }

    public String[] getCommand() {
        return command;
    }

    public String getCommand(int i) {
        return command[i];
    }

    public int updateDuration(){
        for (int i = 0; i<command.length; i++){
            boolean isNumeric = true;
            try {
                //Double result = Double.valueOf(command[i]);
                //return result.doubleValue();
                Integer result = Integer.valueOf(command[i]);
                return result.intValue();
            } catch (NumberFormatException e) {
                System.out.println("No duration int: " + command[i]);
            }
        }
        return 0;
    }

    public String getUsername() {
        return username;
    }

    //returns message
    public String getMessage() {
        return message;
    }

    //updates message and username using the getLatestTweet() method from MyTwitter
    public void updateTweet() {
        MyTwitter tweet = new MyTwitter();
        message = tweet.getLatestTweet();
        username = tweet.getLatestUsername();
    }
}