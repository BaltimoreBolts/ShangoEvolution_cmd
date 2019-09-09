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
   public Button A1,B1,X1,Y1;
   public Button A2,B2,X2,Y2;


  public OI() {
    
    //Initialize Xbox Controller or Joystick/
    driver = new Joystick(0);
    operator = new Joystick(1);

    A2 = new JoystickButton(operator,1);
    B2 = new JoystickButton(operator,2);
    X2 = new JoystickButton(operator,3);


    B2.whenPressed(new StarBurstOpen());
    A2.whenPressed(new StarBurstClosed());
    X2.whenPressed(new StarBurstOff());
    
  }

  public double getDriveX() {

    return driver.getX(Hand.kLeft);
  }

  public double getDriveY() {

    return -driver.getY(Hand.kLeft);
}
  


}
