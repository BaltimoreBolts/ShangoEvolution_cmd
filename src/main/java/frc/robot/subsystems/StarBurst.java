/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.StarBurstClosed;
import frc.robot.commands.StarBurstOpen;
//Import Library for Star Burst Motor/
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.hal.InterruptJNI;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.Relay;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.InterruptHandlerFunction;
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
  public DigitalInput StarBurstLimitOpen, StarBurstLimitClosed;
  public Relay CargoLight;

  public static enum StarBurstState {
    OPEN, CLOSE, MIDDLE
  };

  public StarBurstState CurrentState;
  
  public StarBurst(){
    //Initialize Motors for GPM/
    StarBurstMotor = new VictorSP(0);
    
    //Initialize Limit Switches for GPM/
    StarBurstLimitOpen = new DigitalInput(4);
    StarBurstLimitClosed = new DigitalInput(6);
    CargoLight = new Relay(2, Relay.Direction.kForward);

    //this.SetupInterrupts();

    if (StarBurstLimitOpen.get()) {
      CurrentState = StarBurstState.OPEN;
    } else if (StarBurstLimitClosed.get()) {
      CurrentState = StarBurstState.CLOSE;
    } else {
      CurrentState = StarBurstState.MIDDLE;
    }

  }
   @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand())
  }

  /*public void SetupInterrupts() {

    StarBurstLimitOpen.requestInterrupts(new InterruptHandlerFunction<Object>() {
			
			@Override // This is the function that will actually be called when the Open LS is hit
			public void interruptFired(int interruptAssertedMask, Object param) {
        StarBurstOpen.getInstance().cancel();
        //StarBurst.StarBurstOpenCMD.Cancel();

      }});

      StarBurstLimitClosed.requestInterrupts(new InterruptHandlerFunction<Object>() {
			
        @Override // This is the function that will actually be called when the Open LS is hit
        public void interruptFired(int interruptAssertedMask, Object param) {
          StarBurstClosed.getInstance().cancel();
          //StarBurst.StarBurstOpenCMD.Cancel();
  
        }});
      
		
		StarBurstLimitOpen.setUpSourceEdge(true, false); // Sets up for rising edge, might need to flip it for falling edge I forget how the LS work
		StarBurstLimitClosed.setUpSourceEdge(true, false);
		
		StarBurstLimitOpen.enableInterrupts();
		StarBurstLimitClosed.enableInterrupts();
  }*/
  
  public void open(){
    this.StarBurstMotor.set(0.2);
  }
  public void close(){
    this.StarBurstMotor.set(-0.2);
  }
  public void MotorOff(){
    this.StarBurstMotor.set(0);
  }

  public void lightOn() {
    CargoLight.set(Value.kOn);
  }

  public void lightOff() {
    CargoLight.set(Value.kOff);
  }

  public boolean isOpen(){
    return StarBurstLimitOpen.get();
  }
  public boolean isClosed(){
    return StarBurstLimitClosed.get();
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
    SmartDashboard.putData("Starburst Close LS:", StarBurstLimitClosed);
    SmartDashboard.putString("Current StarBurst State: ", CurrentState.toString());
  }

}
