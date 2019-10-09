/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.ControlMode;
// Import Talon SRX and Victor SPX
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

//Import AnalogPotentiometer/
import edu.wpi.first.wpilibj.AnalogPotentiometer;

import edu.wpi.first.wpilibj.DigitalInput;
/**
 * Add your docs here.
 */
public class GPM extends Subsystem {
  
  public static GPM INSTANCE = new GPM();

  public static GPM getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new GPM();
    }
    return INSTANCE;
  }

  //Define Motors for GPM/
  public WPI_VictorSPX DorsalMotor1;
  public WPI_TalonSRX DorsalMotor2;
  public WPI_VictorSPX FourBarMotor1;
  public WPI_VictorSPX FourBarMotor2;

  //Define Limit Switches for GPM/
  public DigitalInput DorsalLimitUp, DorsalLimitDown;
  public DigitalInput FourBarFwd, FourBarBack;

  //Define Pot/
  public AnalogPotentiometer DorsalPot, FourBarPot;

  //Define Line Sensor/
  public DigitalInput LineSensorLeft, LineSensorCenter, LineSensorRight;

  public GPM(){
    //Initialize Motors for GPM/
    DorsalMotor1 = new WPI_VictorSPX(16);
    DorsalMotor2 = new WPI_TalonSRX(32);
    FourBarMotor1 = new WPI_VictorSPX(15);
    FourBarMotor2 = new WPI_VictorSPX(14);

    //Initialize Limit Switches for GPM/
    DorsalLimitUp = new DigitalInput(0);
    DorsalLimitDown = new DigitalInput(1);
    FourBarFwd = new DigitalInput(2);
    FourBarBack = new DigitalInput(3);

    //Initialize Potentiometers for GPM/
    DorsalPot = new AnalogPotentiometer(0);
    FourBarPot = new AnalogPotentiometer(1);

    //Initialize Line Sensors/
    LineSensorLeft = new DigitalInput(7);
    LineSensorCenter = new DigitalInput(8);
    LineSensorRight = new DigitalInput(9);

    // Make sure everything is default config settings
    DorsalMotor1.configFactoryDefault();
    DorsalMotor2.configFactoryDefault();
    FourBarMotor1.configFactoryDefault();
    FourBarMotor2.configFactoryDefault();

  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());

  }
  
  public void MoveFWD(double val){
   //Negative moves 4Bar fwd
    this.FourBarMotor1.set(ControlMode.PercentOutput, -val);
  }
  public void MoveBack(double val){
    this.FourBarMotor1.set(ControlMode.PercentOutput, val);
    this.FourBarMotor2.set(ControlMode.PercentOutput, val);
  }
  public void MoveUp(double val){
    this.DorsalMotor1.set(ControlMode.PercentOutput, val);
    this.DorsalMotor2.set(ControlMode.PercentOutput, val);
  }
  //Negative moves dorsal down
  public void MoveDown(double val){
    this.DorsalMotor1.set(ControlMode.PercentOutput, -val);
    this.DorsalMotor2.set(ControlMode.PercentOutput, -val);
  }
  public boolean isUp(){
    // Dorsal up limit is mechanical so invert get() value
    return !(this.DorsalLimitUp.get());
  }
  public boolean isDown(){
    //If dorsal limit switch is down or pot is less than down pot value
    //Dorsal down limit is mechanical so invert get() value
    return (!(this.DorsalLimitDown.get()) || (this.DorsalPot.get() <= 0.111));
  }
  public boolean isFwd(){
    // If fourbar fwd limit is set or pot is less than fwd pot value
    // Fourbar fwd is mechanical so invert get() value
    return ( !(this.FourBarFwd.get()) || (this.FourBarPot.get() <= 0.48)) ;
    
  }
  public boolean isBack(){
    // If fourbar back limit is set or fourbarpot value is greater than back pot value
    return (this.FourBarBack.get() || (this.FourBarPot.get() >= 1.38));
  }

  public void DorsalOff() {
    this.DorsalMotor1.set(ControlMode.PercentOutput, 0);
    this.DorsalMotor2.set(ControlMode.PercentOutput, 0);
  }

  public void FourBarOff() {
    this.FourBarMotor1.set(ControlMode.PercentOutput, 0);
    this.FourBarMotor2.set(ControlMode.PercentOutput, 0);
  }

  @Override
  public void periodic() {
    this.UpdateSmartDashboard();
  }

  public void UpdateSmartDashboard() {
    SmartDashboard.putBoolean("Dorsal Lim Up", GPM.getInstance().isUp());
    SmartDashboard.putBoolean("Dorsal Lim Down", GPM.getInstance().isDown());
    SmartDashboard.putBoolean("Fourbar Lim Fwd", GPM.getInstance().isFwd());
    SmartDashboard.putData("Fourbar Lim Back", FourBarBack);
    SmartDashboard.putData("Fourbar Pot", FourBarPot);
    SmartDashboard.putData("Dorsal Pot", DorsalPot);
    SmartDashboard.putData("Left Line", LineSensorLeft);
    SmartDashboard.putData("Center Line", LineSensorCenter);
    SmartDashboard.putData("RightLine", LineSensorRight);
  }
}
  
  /*Press a button (Y) = go hatch position
  Requires GPM
Execute  
set 4bar motor to go to hatch
set dorsal to go to hatch
Is finished
4bar potentiometer = forward limit value = true (limit)
dorsal potentiometer = down limit value = true (limit)
End
set dorsal motor to stay value
set 4bar motor to stay value
Press a button (B) = capture hatch
  Requires GPM
Execute
4bar potentiometer = down value
Dorsal potentiometer = down value
Is finished
4bar down limit = true
Dorsal down limit = true
End
Set dorsal motor to stay
Set 4bar motor to stay
Press button (X) = release hatch
  Requires GPM
Execute/is finished
4bar potentiometer = stay value = true (limit)
Dorsal potentiometer = stay value = true (limit)
End
Set dorsal motor to stay
Set 4bar to stay*/



