/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.GPM;

public class GoHomeDorsal extends Command {
  public GoHomeDorsal() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(GPM.getInstance());
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    GPM.getInstance().MoveDown(0.1);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (!GPM.getInstance().isDown()) {
      GPM.getInstance().MoveDown(0.275);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return GPM.getInstance().isDown();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    GPM.getInstance().MoveDown(0.2);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
