/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.GPM;

public class AutoHome extends Command {
  public AutoHome() {
    // Use requires() here to declare subsystem dependencies
    requires(GPM.getInstance());
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    GPM.getInstance().MoveDown(0.25);
    GPM.getInstance().MoveBack(0.1);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true

  //Don't turn motors off because we always need some oomph to keep dorsal 
  // and fourback back if we are driving around
  @Override
  protected void end() {
    //GPM.getInstance().DorsalOff();
    //GPM.getInstance().FourBarOff();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    //sthis.end();
  }
}
