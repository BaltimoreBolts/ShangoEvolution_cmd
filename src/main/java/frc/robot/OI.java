/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;

//Import Hand + Joystick control/
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  private static OI INSTANCE;

  public static OI getInstance() {

    if (INSTANCE == null) {
      INSTANCE = new OI();
    }
    return INSTANCE;
  }
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());

  //Define Xbox Joystick/
  //public XboxController driver;
  //public XboxController operator;
  public Joystick driver, operator;

   //XBox Controller1 Buttons/
   public Button TriggerDr,SideBtnDr,Joystick7Dr,Joystick8Dr, Joystick9Dr,Joystick11Dr, Joystick12Dr;
   public Button AOp,BOp,XOp,YOp, LeftBumpOp, RightBumpOp, LeftLogoOp, RightLogoOp;


  public OI() {
    
    //Initialize Xbox Controller or Joystick/
    // Driver is joystick 0
    // Operator is joystick 1
    driver = new Joystick(0);
    operator = new Joystick(1);

    /* We should make a map, but until then I took these from triplehelix
	//XboxOne Joysticks
	public static final int LEFT_STICK_X = 0;
	public static final int LEFT_STICK_Y = 1;
	public static final int LEFT_TRIGGER = 2;
	public static final int RIGHT_TRIGGER = 3;
	public static final int RIGHT_STICK_X = 4;
	public static final int RIGHT_STICK_Y = 5;
	
	//XboxOne Buttons
	public static final int A = 1;
	public static final int B = 2;
	public static final int X = 3;
	public static final int Y = 4;
	public static final int LB = 5;
	public static final int RB = 6;
	public static final int LOGO_LEFT = 7;
	public static final int LOGO_RIGHT = 8;
	public static final int LEFT_STICK_BUTTON = 9;
  public static final int RIGHT_STICK_BUTTON = 10;
  */


    AOp = new JoystickButton(operator,1);
    BOp = new JoystickButton(operator,2);
    XOp = new JoystickButton(operator,3);
    YOp = new JoystickButton(operator,4);
    //LeftLogoOp = new JoystickButton(operator,7);
    //RightLogoOp = new JoystickButton(operator, 8);
    TriggerDr = new JoystickButton(driver, 1);
    SideBtnDr = new JoystickButton(driver, 2);
    Joystick7Dr = new JoystickButton(driver, 7);
    Joystick8Dr = new JoystickButton(driver, 8);
    Joystick9Dr = new JoystickButton(driver,9);
    Joystick11Dr = new JoystickButton(driver, 11);
    Joystick12Dr = new JoystickButton(driver, 12);


    //LeftBumpOp = new JoystickButton(operator, 5);
   // RightBumpOp= new JoystickButton(operator, 6);


    //Added starburst off to test button functionality without having to trigger limit switches
    //BOp.whenPressed(new StarBurstOpen());
    //AOp.whenPressed(new StarBurstClosed());
    //XOp.whenPressed(new StarBurstOff());
    AOp.whenPressed(new GoHatch());
    BOp.whenPressed(new GoHome());

    //ADr.whenPressed(new GoHome());
    // Starburst open/close run for 1.0 seconds
    TriggerDr.whenPressed(new StarBurstClosed(1));
    SideBtnDr.whenPressed(new StarBurstOpen(1));
    //Intake functions
    XOp.whenPressed(new EatCargo(1));
    YOp.whenPressed(new SpitCargo(2));

    //Gyro reset
    //Joystick7Dr.whenPressed(Robot.ShangoDT.resetGyro());
    // Have no idea if this will work
    Joystick8Dr.whenPressed(new ShangoDrive());
    Joystick9Dr.whenPressed(new ShangoFieldDrive());
  }

  public double getDriveX() {
    // For some reason getButton() doesn't like when you give it a button ... so Raw button 
    // is just giving it the value of Joystick7Dr (DRRM)
    if (this.driver.getRawButtonPressed(7)) {
      Robot.ShangoDT.resetGyro();
    }
    return driver.getX();
  }

  public double getDriveY() {

    return -driver.getY();
  }

  public double getDriveZ() {
    return driver.getZ();
  }
 
  public boolean LockX() {
    return this.Joystick12Dr.get();
  }
  
  public boolean LockY() {
    return this.Joystick11Dr.get();
  }
 
  

  
  public double getYaw() {
    // Prefer to do it this way so we're accessing ShangoDT object gyro DRRM
    return Robot.ShangoDT.RobotGyro.getAngle();
  }


}
