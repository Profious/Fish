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

        System.out.println("Start");

        System.out.println("<--Pi4J--> GPIO Control Example ... started.");

        // create gpio controller
        final GpioController gpio = GpioFactory.getInstance();

        // provision gpio pin #01 as an output pin and turn on
        final GpioPinDigitalOutput pin1A = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "MyLED", PinState.HIGH);
        final GpioPinDigitalOutput pin1B = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, "MyLED", PinState.LOW);
        final GpioPinDigitalOutput pin1E = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06, "MyLED", PinState.HIGH);
        final GpioPinDigitalOutput pin2A = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_23, "MyLED", PinState.HIGH);
        final GpioPinDigitalOutput pin2B = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_24, "MyLED", PinState.LOW);
        final GpioPinDigitalOutput pin2E = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_25, "MyLED", PinState.HIGH);

        // set shutdown state for this pin
        pin1A.setShutdownOptions(true, PinState.LOW);
        pin1B.setShutdownOptions(true, PinState.LOW);
        pin1E.setShutdownOptions(true, PinState.LOW);
        pin2A.setShutdownOptions(true, PinState.LOW);
        pin2B.setShutdownOptions(true, PinState.LOW);
        pin2E.setShutdownOptions(true, PinState.LOW);

        System.out.println("--> GPIO state should be: ON");

        Thread.sleep(2000);

        // turn off gpio pin #01
        pin1A.low();
        pin2A.low();
        System.out.println("--> GPIO state should be: OFF");

        Thread.sleep(2000);

        // toggle the current state of gpio pin #01 (should turn on)
        pin1B.toggle();
        pin2B.toggle();
        System.out.println("--> GPIO state should be: ON backwards");

        Thread.sleep(5000);

        // toggle the current state of gpio pin #01  (should turn off)
        pin1B.toggle();
        pin2B.toggle();
        System.out.println("--> GPIO state should be: OFF");

        /*Thread.sleep(5000);

        // turn on gpio pin #01 for 1 second and then off
        System.out.println("--> GPIO state should be: ON for only 1 second");
        pinA.pulse(1000, true); // set second argument to 'true' use a blocking call*/

        // stop all GPIO activity/threads by shutting down the GPIO controller
        // (this method will forcefully shutdown all GPIO monitoring threads and scheduled tasks)
        gpio.shutdown();

        System.out.println("Exiting ControlGpioExample");

    }
}
