package robot.model;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.utility.Delay;

//import lejos.ev3.UltrasonicSensor;

public class EV3Bot
{
	private String botMessage;
	private int xPosition;
	private int yPosition;
	private long waitTime;
	
	private int Default;
	private int Distance;
	
	private MovePilot botPilot;
	private EV3UltrasonicSensor distanceSensor;
	private float [] ultrasonicSamples;
	
	public EV3Bot()
	{
		this.botMessage = "You know where it is";
		this.xPosition = 50;
		this.yPosition = 50;
		this.waitTime = 4000;
		Default = 5;
		Distance = 10;
		
		distanceSensor = new EV3UltrasonicSensor(LocalEV3.get().getPort("S1"));
		distanceSensor.enable();
		setupPilot();
	}
	
	public void driveRoom()
	{
		ultrasonicSamples = new float [distanceSensor.sampleSize()];
		distanceSensor.fetchSample(ultrasonicSamples, 0);
		displayMessage();
		
		
		
		if(ultrasonicSamples[0] > 10.5)
		{
			botPilot.travel(5000);
			botPilot.rotate(90);
		}
		else
		{
			botPilot.rotate(-360);
		}
		if(ultrasonicSamples[0] > 10.5)
		{
			botPilot.travel(5830.2475);
			botPilot.rotate(-90);
		}
		else
		{
			botPilot.rotate(360);
		}		
		if(ultrasonicSamples[0] > 6.5)
		{
			botPilot.travel(1250.25);
			botPilot.rotate(90);
		}
		else
		{
			botPilot.rotate(360);
		}
		if(ultrasonicSamples[0] > 10.5)
		{
			botPilot.travel(500);
			botPilot.rotate(360);
		}
		else
		{
			botPilot.rotate(-360);
		}
	}
	
	public void displayMessage()
	{
		LCD.drawString(botMessage, xPosition, yPosition);
		Delay.msDelay(waitTime);
	}
	private void displyMessage(String message)
	{
		LCD.drawString(message, xPosition, yPosition);
		Delay.msDelay(waitTime);
	}
	
	private void setupPilot()
	{
		Wheel leftWheel = WheeledChassis.modelWheel(Motor.A, 43.2).offset(-72);
		Wheel rightWheel = WheeledChassis.modelWheel(Motor.B, 43.2).offset(72);
		Chassis baseChassis = new WheeledChassis(new Wheel []{leftWheel, rightWheel}, WheeledChassis.TYPE_DIFFERENTIAL);
		botPilot = new MovePilot(baseChassis);
	}
	
	public void Delta()
	{
		Motor.A.setSpeed(900);
		Motor.B.setSpeed(900);
		Motor.A.forward();
		Motor.B.forward();
		if(Distance < Default)
		{
			Delay.msDelay(3000);
		}
		else if(Distance > Default)
		{
			Delay.msDelay(4000);
			Motor.A.stop();
			Motor.B.stop();
			Motor.B.rotate(360);
			Motor.A.forward();
			Motor.B.forward();
			Motor.A.setSpeed(900);
			Motor.B.setSpeed(500);
			Delay.msDelay(2000);
			Motor.B.setSpeed(900);
			Delay.msDelay(4000);
			Motor.A.stop();
			Motor.B.stop();
		}
		else
		{
			Delay.msDelay(6000);
			Motor.A.stop();
			Motor.B.stop();
		}

	}
	
	private void Alpha()
	{
		Motor.A.setSpeed(-900);
		Motor.B.setSpeed(900);
		Delay.msDelay(3000);
		Motor.A.stop();
		Motor.B.stop();
	}
}

