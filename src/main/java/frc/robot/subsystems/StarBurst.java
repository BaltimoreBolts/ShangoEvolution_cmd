/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//Import Library for Star Burst Motor/
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.Counter;

import edu.wpi.first.wpilibj.DigitalInput;
/**
 * Add your docs here.
 */
public class StarBurst extends Subsystem {

  public static StarBurst INSTANCE = new StarBurst();

  public static StarBurst getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new StarBurst();
    }
    return INSTANCE;

  }

  //Define Motors for GPM/
  public VictorSP StarBurstMotor;

  //Define Limit Switches for GPM/
  public DigitalInput StarBurstLimitOpen, StarBurstLimitClose;

  public static enum StarBurstState {
    OPEN, CLOSE, MIDDLE
  };

  public StarBurstState CurrentState;
  
  public StarBurst(){
      //Initialize Motors for GPM/
    StarBurstMotor = new VictorSP(0);
    
    //Initialize Limit Switches for GPM/
    StarBurstLimitOpen = new DigitalInput(5);
    StarBurstLimitClose = new DigitalInput(6);

    if (StarBurstLimitOpen.get()) {
      CurrentState = StarBurstState.OPEN;
    } else if (StarBurstLimitClose.get()) {
      CurrentState = StarBurstState.CLOSE;
    } else {
      CurrentState = StarBurstState.MIDDLE;
    }

    //StarBurst SmartDashboard stuff/
    /*StarBurstLimitOpen.setName("Starburst", "OpenLimitSwitch");
    StarBurstLimitClose.setName("Starburst", "ClosedLimitSwitch"); 
    StarBurstMotor.setName("SBMotor", "SBMotor");*/
  }
   @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand())
  }
  public void open(){
    this.StarBurstMotor.set(0.2);
  }
  public void close(){
    this.StarBurstMotor.set(-0.2);
  }
  public void MotorOff(){
    this.StarBurstMotor.set(0);
  }
  public boolean isOpen(){
    return StarBurstLimitOpen.get();
  }
  public boolean isClosed(){
    return StarBurstLimitClose.get();
  }

  public void setCurrentState(StarBurstState newState) {
    this.CurrentState = newState;
  }

  @Override
  public void periodic() {
    this.UpdateSmartDashboard();
  }

  public void UpdateSmartDashboard() {
    SmartDashboard.putData("Starburst Open LS:", StarBurstLimitOpen);
    SmartDashboard.putData("Starburst Close LS:", StarBurstLimitClose);
    SmartDashboard.putString("Current StarBurst State: ", CurrentState.toString());
  }

}
