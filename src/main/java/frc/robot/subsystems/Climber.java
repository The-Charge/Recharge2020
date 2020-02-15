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

import frc.robot.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class Climber extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
private WPI_TalonSRX climberMotor;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    private final static double P_CONSTANT = 1;   //needs tuning
    private final static double I_CONSTANT = 0.001;  //needs tuning
	private final static double D_CONSTANT = 0.0;   //needs tuning
    private final static double F_CONSTANT = 10;   //needs tuning
      
    private final static int PID_SLOT_SPEED_MODE = 0;

    final int TIMEOUT_MS = 10;   //needs tuning
    private final static int MAX_TICKS_PER_SEC = 934;
   

    public Climber() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
climberMotor = new WPI_TalonSRX(2);



        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    climberMotor.setInverted(true);
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

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
   
   

   
    public void stop(){
        climberMotor.set(ControlMode.PercentOutput,0);
    }

    public void initSpeedMode(){
        climberMotor.set(ControlMode.Velocity, 0);
        // climberMotor.selectProfileSlot(PID_SLOT_SPEED_MODE, 0);

        climberMotor.config_kP(PID_SLOT_SPEED_MODE, P_CONSTANT, TIMEOUT_MS);
    	climberMotor.config_kI(PID_SLOT_SPEED_MODE, I_CONSTANT, TIMEOUT_MS);
    	climberMotor.config_kD(PID_SLOT_SPEED_MODE, D_CONSTANT, TIMEOUT_MS);
        climberMotor.config_kF(PID_SLOT_SPEED_MODE, F_CONSTANT, TIMEOUT_MS);

        // climberMotor.configNominalOutputForward(1, TIMEOUT_MS);
		// climberMotor.configNominalOutputReverse(1, TIMEOUT_MS);
        
    }

    public void set(double percentSpeed)
    {
        climberMotor.set(ControlMode.Velocity, 10 * percentSpeed);
    }
    public void setPercentVBus()
    {
        climberMotor.set(ControlMode.PercentOutput, 0);
    }
    public void manual (double pow)
    {
        climberMotor.set(ControlMode.PercentOutput, pow);
    }

    }

