/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;
import frc.robot.Robot;
import frc.robot.OI;

import edu.wpi.first.wpilibj.command.Command;


public class ShangoFieldDrive extends Command {

  // Need an intial angle for when you start doing field centric drive
  private double yaw0;

  public ShangoFieldDrive() {
    requires(Robot.ShangoDT);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

    // Get gyro angle of this point
    //yaw0 = Robot.ShangoDT.RobotGyro.getAngle();
    yaw0 = 0;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double xDir, yDir, zDir;

    xDir = OI.getInstance().getDriveX();
    yDir = OI.getInstance().getDriveY();
    zDir = OI.getInstance().getDriveZ();

    Robot.ShangoDT.DriveShangoGyro(xDir, yDir, zDir, yaw0);
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
    this.end();
  }
}
