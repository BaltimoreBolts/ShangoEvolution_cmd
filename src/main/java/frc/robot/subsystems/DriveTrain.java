/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.commands.*;
//import com.analog.adis16470.frc.ADIS16470_IMU;

/**
 * Need to make drivetrain a static instance. For after BoB
 */
public class DriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  // Define mecanumdrive variables
  private MecanumDrive RobotDT;
  private WPI_TalonSRX LeftFront;
  private WPI_TalonSRX RightFront;
  private WPI_TalonSRX LeftBack;
  private WPI_TalonSRX RightBack;

  //Define IMU for Robot Centric Drive/
  // Currently getting a checksum error, my bet is it's from the gyro DRRM
  //public static final ADIS16470_IMU RobotGyro = new ADIS16470_IMU(); 

  //
  public DriveTrain() {
    //Initialize Drive Train Motors
    LeftFront = new WPI_TalonSRX(11);
    RightFront = new WPI_TalonSRX(13);
    LeftBack = new WPI_TalonSRX(10);
    RightBack = new WPI_TalonSRX(12);
    RobotDT = new MecanumDrive(LeftFront, LeftBack, RightFront, RightBack);
    //RobotGyro.reset(); 
        
  }

  public void DriveShango(double x, double y, double z, double yaw){
    RobotDT.driveCartesian(x, y, z, yaw);
  }

  /*public void DriveShangoGyro(double x, double y, double z) {
    RobotDT.driveCartesian(x, y, z, RobotGyro.getAngleZ());
  }*/

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    this.setDefaultCommand(new ShangoDrive());

  }

  public void periodic() {
    //this.UpdateSmartDashboard();
  }

  public MecanumDrive GetShangoDT() {
    return this.RobotDT;
  }

  /*public void UpdateSmartDashboard() {
    SmartDashboard.putNumber("Yaw", RobotGyro.getAngleZ());
  }*/

}
