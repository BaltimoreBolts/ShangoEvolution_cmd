/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Intake;

public class EatCargo extends Command {

  private double m_timeout;

  public EatCargo(double timeout) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    m_timeout = timeout;
    requires(Intake.getInstance());
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    setTimeout(m_timeout);
    Intake.getInstance().intake();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return isTimedOut();
    // Should put this back after testing. Stop intake when we have cargo? 
    //return Intake.getInstance().hasCargo();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Intake.getInstance().MotorOff();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
