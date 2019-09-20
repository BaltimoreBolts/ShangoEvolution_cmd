/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.nio.channels.InterruptibleChannel;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.StarBurst;

public class StarBurstClosed extends Command {

  private double m_timeout;
  /*public static StarBurstClosed INSTANCE = new StarBurstClosed();

  public static StarBurstClosed getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new StarBurstClosed();
    }
    return INSTANCE;

  }*/
  public StarBurstClosed(double timeout) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    m_timeout = timeout;
    requires(StarBurst.getInstance());
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    //this.setInterruptible(true);
    //StarBurst.getInstance().StarBurstCloseCounter.reset();
    setTimeout(m_timeout);
    StarBurst.getInstance().setCurrentState(StarBurst.StarBurstState.CLOSE);
    StarBurst.getInstance().close();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // Code to close the Starburst 
    // Need something better than this, it's wonky at best DRRM
    /*if (StarBurst.getInstance().CurrentState == StarBurst.StarBurstState.OPEN) {
      StarBurst.getInstance().setCurrentState(StarBurst.StarBurstState.CLOSE);
      StarBurst.getInstance().close();
    } else {
      // Turn the motor off? DRRM
    }*/

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    //return StarBurst.getInstance().isClosed();
    return isTimedOut();
  }
  
  // Called once after isFinished returns true
  @Override
  protected void end() {
    StarBurst.getInstance().MotorOff();
    StarBurst.getInstance().lightOff();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    this.end();
  }
}
