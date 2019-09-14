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
  public StarBurstOpen() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    //requires(Robot.ShangoStarBurst);
    requires(StarBurst.getInstance());
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    //StarBurst.getInstance().StarBurstOpenCounter.reset();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // Put the code to open the Starburst
    //Starburst potentiometer = open value/
    if (StarBurst.getInstance().CurrentState == StarBurst.StarBurstState.CLOSE) {
      StarBurst.getInstance().setCurrentState(StarBurst.StarBurstState.OPEN);
      StarBurst.getInstance().open();
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return StarBurst.getInstance().isOpen();
    
    //Starburst open limit = true/
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    StarBurst.getInstance().MotorOff();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
