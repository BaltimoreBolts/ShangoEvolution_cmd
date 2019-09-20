/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.OI;

public class ShangoDrive extends Command {
  public ShangoDrive() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.ShangoDT);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  
  @Override
  protected void execute() {
    double xDir = OI.getInstance().getDriveX();
    double yDir = OI.getInstance().getDriveY();
    double zDir = OI.getInstance().getDriveZ();
    //double Yaw = OI.getInstance().getYaw();

    if (RobotState.isAutonomous()) { // When we're in Autonomous don't use robo-centric
      Robot.ShangoDT.DriveShango(xDir, yDir, zDir, 0);
    } else { // When we're in teleop we want to use robo-centric
      Robot.ShangoDT.DriveShangoGyro(xDir, yDir, zDir); 
    }   
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
