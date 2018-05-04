package com.neemaahmadian;

public class TwitterData {
    private String username;
    private String message;
    private String[] command;
    private String direction;
    private int duration;
    private double speed;
    private String action;

    public TwitterData(String message, String username) {
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

    //updates message and username using the getLatestTweet() method from MyTwitter
    public void updateInfo() {
        MyTwitter tweet = new MyTwitter();
        message = tweet.getLatestTweet();
        username = tweet.getLatestUsername();
        generateCommand();
        updateDuration();
        updateDirection();
        updateSpeed();
        updateAction();
    }

    public int updateDuration(){
        for (int i = 0; i<command.length; i++){
            try {
                Integer result = Integer.valueOf(command[i]);
                duration = result.intValue();
                return i;
            } catch (NumberFormatException e) {
                System.out.println("No duration int: " + command[i]);
            }
        }
        duration = 0;
        return -1;
    }

    public void updateSpeed(){
        boolean mySwitch = true;
        for (int i = 0; i<command.length; i++){
            try {
                Double result = Double.valueOf(command[i]);
                if (updateDuration()!=i) {
                    speed = result.doubleValue();
                    mySwitch = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("No duration int: " + command[i]);
            }
        }
        if (mySwitch) {
            speed = 0;
        }
    }

    public void updateDirection(){
        boolean mySwitch = true;
        for (int i = 0; i<command.length; i++){
            if (command[i].toLowerCase().equals("r")){
                direction = "r";
                mySwitch = false;
            } else if (command[i].toLowerCase().equals("l")){
                direction = "l";
                mySwitch = false;
            }
        }
        if (mySwitch) {
            direction = "none";
        }
    }

    public void updateAction(){
        for (int i = 0; i<command.length; i++){
            if (command[i].toLowerCase().equals("move")) {
                action = "move";
            }
            if (command[i].toLowerCase().equals("turn")) {
                action = "turn";
            }
        }
    }

    public int getDuration(){
        return duration;
    }

    public double getSpeed(){
        return speed;
    }

    public String getDirection(){
        return direction;
    }

    public String getCommand(int i) {
        return command[i];
    }

    public String getAction(){
        return action;
    }

    public String[] getCommand() {
        return command;
    }

    public String getUsername() {
        return username;
    }

    //returns message
    public String getMessage() {
        return message;
    }
}