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
public class Intake extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
private WPI_TalonSRX intakeMotor;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
     private final static double SPEED_P_CONSTANT = 0.1;
	private final static double SPEED_I_CONSTANT = 0.00001;
	private final static double SPEED_D_CONSTANT = 0.0;
	private final static double SPEED_F_CONSTANT = 0.0;
    
    public double speedP = SPEED_P_CONSTANT;
	public double speedI = SPEED_I_CONSTANT;
	public double speedD = SPEED_D_CONSTANT;
    public double speedF = SPEED_F_CONSTANT;
    
    public final static int PID_SLOT_SPEED_MODE = 1;
    
    public double SHOOTER_INWARD_MULTIPLIER = 1;
    public double SHOOTER_OUTWARD_MULTIPLIER = 1;

    private final int TIMEOUT_MS = 10;
    private static final int MAX_TICKS_PER_SEC = 9000;


    public Intake() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
intakeMotor = new WPI_TalonSRX(30);


        

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

    public void run (double setPower) {
        intakeMotor.set(setPower);
	}

    public void initSpeedMode() {    	
    	intakeMotor.set(ControlMode.Velocity, 0);
        
        intakeMotor.config_kP(PID_SLOT_SPEED_MODE, speedP, TIMEOUT_MS);
    	intakeMotor.config_kI(PID_SLOT_SPEED_MODE, speedI, TIMEOUT_MS);
    	intakeMotor.config_kD(PID_SLOT_SPEED_MODE, speedD, TIMEOUT_MS);
    	intakeMotor.config_kF(PID_SLOT_SPEED_MODE, speedF, TIMEOUT_MS);

    	intakeMotor.selectProfileSlot(PID_SLOT_SPEED_MODE, 0);
    }

	public void stop() {
        intakeMotor.set(0);
	}

    public void setPercentSpeedPID(double setSpeed) {
		intakeMotor.set(ControlMode.Velocity, MAX_TICKS_PER_SEC * setSpeed);
    }

	public void setPercentVBus() {
        intakeMotor.set(ControlMode.PercentOutput, 0);
	}

    public int getTicksPerSecond(){
        return intakeMotor.getSelectedSensorVelocity();
    }
}

