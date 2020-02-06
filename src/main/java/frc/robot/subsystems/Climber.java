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
    public static final int MIN_ENCODER_TICKS = 0;
    public static final int MAX_ENCODER_TICKS = 5000;

    private final static double P_CONSTANT = 0.1;
	private final static double I_CONSTANT = 0.001;
	private final static double D_CONSTANT = 0.0;
    private final static double F_CONSTANT = 0.0;
    
    private final double ROTATIONS_TO_CLIMB = 35;

    private double posP = P_CONSTANT;
    private double posI = I_CONSTANT;
    private double posD = D_CONSTANT;
    private double posF = F_CONSTANT;

    private final static int PID_SLOT_SPEED_MODE = 1;

    final int TIMEOUT_MS = 10;

    private static final int MAX_TICKS_PER_SEC = 934;

    public Climber() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
climberMotor = new WPI_TalonSRX(13);


        

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

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void climbUp(){
        climberMotor.set(0.5);
    }

    public void climbDown(){
        climberMotor.set(-0.5);
    }
    public void runManual(double percent)
    {
        int angle = climberMotor.getSelectedSensorPosition();
        if ((angle < MIN_ENCODER_TICKS && percent < 0) || (angle > MAX_ENCODER_TICKS)) //&& percent ??
            stop();
        else
            climberMotor.set(ControlMode.Position, MAX_TICKS_PER_SEC * percent );
    } 
    public boolean reached(boolean goingUp)
    {
        if (goingUp){
            return (climberMotor.getSelectedSensorPosition() < -ROTATIONS_TO_CLIMB);
        }else{
            return (climberMotor.getSelectedSensorPosition() > 0);
        }
    }

    public void stop(){
        climberMotor.set(0);
    }

    public void initSpeedMode(){
        climberMotor.selectProfileSlot(PID_SLOT_SPEED_MODE, 0);

        climberMotor.set(ControlMode.Velocity, 0);

        climberMotor.config_kP(PID_SLOT_SPEED_MODE, posP, TIMEOUT_MS);
    	climberMotor.config_kI(PID_SLOT_SPEED_MODE, posI, TIMEOUT_MS);
    	climberMotor.config_kD(PID_SLOT_SPEED_MODE, posD, TIMEOUT_MS);
    	climberMotor.config_kF(PID_SLOT_SPEED_MODE, posF, TIMEOUT_MS);
        
    }

    
    }

