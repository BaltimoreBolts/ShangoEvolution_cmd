/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import frc.robot.commands.*;

/**
 * Add your docs here.
 */
public class DriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  // Define macanumdrive variables
  private MecanumDrive RobotDT;
  private WPI_TalonSRX LeftFront;
  private WPI_TalonSRX RightFront;
  private WPI_TalonSRX LeftBack;
  private WPI_TalonSRX RightBack;

  //
  public DriveTrain() {
    //Initialize Drive Train Motors
    LeftFront = new WPI_TalonSRX(11);
    RightFront = new WPI_TalonSRX(13);
    LeftBack = new WPI_TalonSRX(10);
    RightBack = new WPI_TalonSRX(12);
    RobotDT = new MecanumDrive(LeftFront, LeftBack, RightFront, RightBack);
        
  }

  public void DriveShango(double x, double y){
    RobotDT.driveCartesian(x, y, 0, 0);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    this.setDefaultCommand(new ShangoDrive());

  }

  public MecanumDrive GetShangoDT() {
    return this.RobotDT;
  }

}
