package robot.controller;

import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;
import robot.model.EV3Bot;


public class BotController
{
	private String message;
	
	private int xPosition;
	private int yPosition;
	
	private long waitTime;
	
	private EV3Bot AdamBot;
	
	public BotController()
	{
		this.message = "Adam's Robot";
		this.xPosition = 10;
		this.yPosition = 10;
		this.waitTime = 4000;
		
		AdamBot = new EV3Bot();
	}
	
	public void strat()
	{
		LCD.drawString(message, xPosition, yPosition);
		Delay.msDelay(waitTime);
		
		AdamBot.driveRoom();
	}
}
