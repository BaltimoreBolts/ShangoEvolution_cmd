/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
/**
 * Add your docs here.
 */
public class Intake extends Subsystem {

  public static Intake INSTANCE = new Intake();

  public static Intake getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new Intake();
    }
    return INSTANCE;
  }

  public WPI_TalonSRX CargoIntakeMotor;
  public DigitalInput CargoSensor;
  
  public Intake(){
    CargoIntakeMotor = new WPI_TalonSRX(17); 
    //Initialize Cargo/Sensor Components for GPM/
    CargoSensor = new DigitalInput(4); 

    CargoSensor.setName("CargoSensor", "Sensor");
  }

   @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    
  }
  public void intake(){
    this.CargoIntakeMotor.set(0.5);
  }
  public void outtake(){
    this.CargoIntakeMotor.set(-0.5);
  }
  public void MotorOff(){
    this.CargoIntakeMotor.set(0);
  }
  public boolean hasCargo(){
    return this.CargoSensor.get();
  }
}
