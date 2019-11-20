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

//Import PID libraries
import edu.wpi.first.wpilibj.PIDController;

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

  // Define PID 
  // TODO: Default values for now. Probably want to set these up as configurable on the screen.
  public static final double kP = 1;
  public static final double kI = 0.01;
  public static final double kD = 0.1;
  public PIDController m_pidFourBar, m_pidDorsal;
  public double fourbarPIDVal;


  public GPM(){
    //Initialize Motors for GPM/
    DorsalMotor1 = new WPI_VictorSPX(16);
    DorsalMotor2 = new WPI_TalonSRX(32);
    FourBarMotor1 = new WPI_VictorSPX(15);
    FourBarMotor2 = new WPI_VictorSPX(14);

    //Initialize Limit Switches for GPM/
    DorsalLimitUp = new DigitalInput(0);
    DorsalLimitDown = new DigitalInput(1);
    FourBarFwd = new DigitalInput(5);// Used to be 2
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

    DorsalMotor2.follow(DorsalMotor1);
    FourBarMotor2.follow(FourBarMotor1);

    //Initalize PID controller
    m_pidFourBar = new PIDController(kP, kI, kD, FourBarPot, FourBarMotor1);
    m_pidFourBar.setInputRange(0, 5); //TODO: idk what to set these ranges to. Need to check.
    m_pidFourBar.setPercentTolerance(2.5); // 2.5%? DRRM
  
    m_pidDorsal = new PIDController(kP, kI, kD, DorsalPot, DorsalMotor1);
    m_pidDorsal.setInputRange(2,20);
    m_pidDorsal.setPercentTolerance(3.5); 
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());

  }
  
  public void MoveFWD(double val){
   //Negative moves 4Bar fwd
    this.FourBarMotor1.set(ControlMode.PercentOutput, -val);
    //this.FourBarMotor2.set(ControlMode.PercentOutput, -val);
  }
  public void MoveBack(double val){
    this.FourBarMotor1.set(ControlMode.PercentOutput, val);
    //this.FourBarMotor2.set(ControlMode.PercentOutput, val);
  }
  public void MoveUp(double val){
    this.DorsalMotor1.set(ControlMode.PercentOutput, val);
    //this.DorsalMotor2.set(ControlMode.PercentOutput, val);
  }
  //Negative moves dorsal down
  public void MoveDown(double val){
    this.DorsalMotor1.set(ControlMode.PercentOutput, -val);
    //this.DorsalMotor2.set(ControlMode.PercentOutput, -val);
  }

  public void goToShoot(double val){
    m_pidFourBar.setSetpoint(val);
  }

  public void goToShootDorsal(double val){
    m_pidDorsal.setSetpoint(val);
  }
  public boolean isUp(){
    // Dorsal up limit is mechanical so invert get() value
    return !(this.DorsalLimitUp.get());
  }
  public boolean isDown(){
    //If dorsal limit switch is down or pot is less than down pot value
    //Dorsal down limit is mechanical so invert get() value
    return (!(this.DorsalLimitDown.get()));
  }
  public boolean isFwd(){
    // If fourbar fwd limit is set or pot is less than fwd pot value
    // Fourbar fwd is mechanical so invert get() value
    //return ( !(this.FourBarFwd.get()) || (this.FourBarPot.get() <= 0.5)) ;
    return ( (this.FourBarPot.get() <= 0.11)) ;
    
  }
  public boolean isBack(){
    // If fourbar back limit is set or fourbarpot value is greater than back pot value
    //return (this.FourBarBack.get() || (this.FourBarPot.get() >= 0.252));
    return (this.FourBarPot.get() >= 0.22); // DRRM 11/1/2019 during Ramp Riot Calibration  
  }

  public void DorsalOff() {
    this.DorsalMotor1.set(ControlMode.PercentOutput, 0);
    //this.DorsalMotor2.set(ControlMode.PercentOutput, 0);
  }

  public void FourBarOff() {
    this.FourBarMotor1.set(ControlMode.PercentOutput, 0);
    //this.FourBarMotor2.set(ControlMode.PercentOutput, 0);
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
    SmartDashboard.putNumber("Fourbar Pot", FourBarPot.get());
    SmartDashboard.putNumber("Dorsal Pot", DorsalPot.get());
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



