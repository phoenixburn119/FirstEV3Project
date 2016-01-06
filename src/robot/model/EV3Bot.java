package robot.model;

import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
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
	
	public EV3Bot()
	{
		//UltrasonicSensor sonic = new UltrasonicSensor(SensorPort.S4);
		this.botMessage = "You know where it is";
		this.xPosition = 50;
		this.yPosition = 50;
		this.waitTime = 4000;
		Default = 5;
		Distance = 10;
		
		setupPilot();
		displayMessage();
	}
	
	public void driveRoom()
	{
		displayMessage();
		botPilot.travel(254.12);
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
	
	private void Delta()
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

