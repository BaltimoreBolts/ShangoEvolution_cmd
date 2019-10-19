/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.commands.*;

// Imports related to navx gyro
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.DriverStation;

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
  public AHRS RobotGyro; 

  //
  public DriveTrain() {
    //Initialize Drive Train Motors
    LeftFront = new WPI_TalonSRX(11);
    RightFront = new WPI_TalonSRX(13);
    LeftBack = new WPI_TalonSRX(10);
    RightBack = new WPI_TalonSRX(12);
    
    RobotDT = new MecanumDrive(LeftFront, LeftBack, RightFront, RightBack);

    try {
			/***********************************************************************
			 * navX-MXP:
			 * - Communication via RoboRIO MXP (SPI, I2C, TTL UART) and USB.            
			 * - See http://navx-mxp.kauailabs.com/guidance/selecting-an-interface.
			 * 
			 * navX-Micro:
			 * - Communication via I2C (RoboRIO MXP or Onboard) and USB.
			 * - See http://navx-micro.kauailabs.com/guidance/selecting-an-interface.
			 * 
			 * Multiple navX-model devices on a single robot are supported.
			 ************************************************************************/
            RobotGyro = new AHRS(SPI.Port.kMXP); 
            //RobotGyro = new AHRS();
        } catch (RuntimeException ex ) {
            DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
        }
  }
     
        

  public void DriveShango(double x, double y, double z, double yaw){
    RobotDT.driveCartesian(x, y, z, yaw);
  }

  public void DriveShangoGyro(double x, double y, double z, double yaw0) {
    RobotDT.driveCartesian(x, y, z, RobotGyro.getAngle()-yaw0);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    this.setDefaultCommand(new ShangoDrive());

  }

  public void periodic() {
    this.UpdateSmartDashboard();
  }

  public MecanumDrive GetShangoDT() {
    return this.RobotDT;
  }

  public void resetGyro() {
    RobotGyro.reset();
  }

  public void UpdateSmartDashboard() {
    SmartDashboard.putNumber("Yaw", RobotGyro.getAngle());
    //SmartDashboard.putNumber("z", RobotGyro.getAngleZ());
  }

}
