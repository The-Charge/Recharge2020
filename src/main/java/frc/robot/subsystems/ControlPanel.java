// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package frc.robot.subsystems;

import frc.robot.Robot;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;  //not sure if I need this. This is for the port
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.*;


// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class ControlPanel extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
private WPI_TalonSRX controlPanelMotor;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
   private Color currentColor;
  private Color temp;
  private int ctr = 0;
  
    public ControlPanel() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
controlPanelMotor = new WPI_TalonSRX(8);


        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }

 
    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
      


        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    @Override
    public void periodic() {


        // Put code here to be run every loop
  }
  public void run(double pow) {    	
    controlPanelMotor.set(pow);
}
public void stop(){
  controlPanelMotor.set(0);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             
}

public void rotateX(){
  SmartDashboard.putNumber("ControlPanel", ctr);
  currentColor = Robot.colorSensor.getColor();
  if (ctr == 0)
    temp = currentColor;
 
  //while (ctr<16){
    if (ctr<26)
    controlPanelMotor.set(.5);

    //everytime the color changes counter goes up
    if (currentColor!= temp) {
      ctr++;
      temp = currentColor;
    }
  ctr++;
  //}
}
public void rotateColor(Color desiredColor){
  Color currentColor = Robot.colorSensor.getColor();
  if(!desiredColor.equals(currentColor)){ //Dont know if this would work - changed while to if
    controlPanelMotor.set(.5);
  }
  
}
public int getCounter(){
  return ctr;
}


    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

}


