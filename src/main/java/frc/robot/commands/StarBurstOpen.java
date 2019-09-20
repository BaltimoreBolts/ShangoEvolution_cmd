/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.StarBurst;

public class StarBurstOpen extends Command {
  private double m_timeout;
  /*public static StarBurstOpen INSTANCE = new StarBurstOpen();

  public static StarBurstOpen getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new StarBurstOpen();
    }
    return INSTANCE;

  }*/

  /* Call command with timeout value in seconds*/
  public StarBurstOpen(double timeout) {
    m_timeout = timeout;
    requires(StarBurst.getInstance());
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    //StarBurst.getInstance().StarBurstOpenCounter.reset();
    //this.setInterruptible(true);
    setTimeout(m_timeout);
    StarBurst.getInstance().setCurrentState(StarBurst.StarBurstState.OPEN);
    StarBurst.getInstance().open();
    StarBurst.getInstance().lightOn();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // Put the code to open the Starburst
    //Starburst potentiometer = open value/
    // Need something better than this, it's wonky at best DRRM
    
   /* if (StarBurst.getInstance().CurrentState == StarBurst.StarBurstState.CLOSE) {
      StarBurst.getInstance().setCurrentState(StarBurst.StarBurstState.OPEN);
      StarBurst.getInstance().open();
    } else {
      // turn the motor off? DRRM
    }*/
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
  //return StarBurst.getInstance().isOpen();
    return isTimedOut();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    StarBurst.getInstance().MotorOff();
    StarBurst.getInstance().lightOn();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    this.end();
  }
}
