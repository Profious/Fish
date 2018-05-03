package com.neemaahmadian;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

/**
 * Created by Neema on 4/29/2018.
 */

public class Motor {
    private String name;

    final GpioController gpio;
    final GpioPinDigitalOutput pin1A;
    final GpioPinDigitalOutput pin1B;
    final GpioPinDigitalOutput pin1E;
    final GpioPinDigitalOutput pin2A;
    final GpioPinDigitalOutput pin2B;
    final GpioPinDigitalOutput pin2E;

    //constructor
    public Motor(String name) {
        this.name = name;
        gpio = GpioFactory.getInstance();

        // provision gpio pins
        pin1A = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "Motor1 Forwards", PinState.HIGH);
        pin1B = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, "Motor1 Backwards", PinState.LOW);
        pin1E = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06, "Motor1 Enable", PinState.HIGH);
        pin2A = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_23, "Motor2 Forwards", PinState.HIGH);
        pin2B = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_24, "Motor2 Backwards", PinState.LOW);
        pin2E = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_25, "Motor2 Enable", PinState.HIGH);

        // set shutdown states
        pin1A.setShutdownOptions(true, PinState.LOW);
        pin1B.setShutdownOptions(true, PinState.LOW);
        pin1E.setShutdownOptions(true, PinState.LOW);
        pin2A.setShutdownOptions(true, PinState.LOW);
        pin2B.setShutdownOptions(true, PinState.LOW);
        pin2E.setShutdownOptions(true, PinState.LOW);

    }

    //turns the motors off
    public void off() {
        pin1A.low();
        pin1B.low();
        pin2A.low();
        pin2B.low();

        System.out.println("off");
    }

    public void on(int direction) {
        if (direction>0){
            pin1A.high();
            pin2A.high();
            pin1B.low();
            pin2B.low();
        } else {
            pin1B.high();
            pin2B.high();
            pin1A.low();
            pin2A.low();
        }
        System.out.println("on");
    }

    //turns the motor on for a certain amount of time
    public void on(int direciton, int milliseconds) {
        on(direciton);
        try {
            Thread.sleep(milliseconds); //sleep amount of milliseconds
            off();
        } catch (InterruptedException e) {
            System.out.println("got interrupted!");
        }
    }

    //didn't end up using this PWM method because I found out that pi4j has a built in one that works better
    public void myPWM(double dutyCycle, int duration, int direction){
        int ratio = ((int)((dutyCycle+0.05)*10));
        int resolution = 10;
        System.out.println(ratio);
        int numbOfLoops = duration*(1000/resolution);
        for(int i=0; i<numbOfLoops; i++){
            if (i<ratio){
                System.out.println("ONIT");
                on(direction);
                try{ Thread.sleep(resolution);} catch(InterruptedException e){ System.out.println("Interrupted");}
            } else {
                System.out.println("OFFIT");
                off();
                try{ Thread.sleep(resolution);} catch(InterruptedException e){ System.out.println("Interrupted");}
            }
        }

    }
}