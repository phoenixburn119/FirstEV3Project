package robot.model;

import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.utility.Delay;

public class EV3Bot
{
	private String botMessage;
	private int xPosition;
	private int yPosition;
	private long waitTime;
	
	public EV3Bot()
	{
		this.botMessage = "You know where it is";
		this.xPosition = 50;
		this.yPosition = 50;
		this.waitTime = 4000;
	}
	
	public void driveRoom()
	{
		displayMessage();
		Delta();
		Alpha();
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
	
	private void Delta()
	{
		Motor.A.setSpeed(900);
		Motor.B.setSpeed(900);
		Delay.msDelay(3000);
		Motor.A.stop();
		Motor.B.stop();
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

