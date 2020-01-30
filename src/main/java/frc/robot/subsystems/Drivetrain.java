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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.controller.PIDController;

import com.kauailabs.navx.frc.AHRS;

import com.ctre.phoenix.motorcontrol.ControlMode;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class Drivetrain extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
private WPI_TalonFX rightFrontMotor;
private WPI_TalonFX rightMidMotor;
private WPI_TalonFX rightBackMotor;
private WPI_TalonFX leftFrontMotor;
private WPI_TalonFX leftMidMotor;
private WPI_TalonFX leftBackMotor;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    //These need to be turned
    private static final double PIDTURN_P = 0.1;
    private static final double PIDTURN_I = 0.0;
    private static final double PIDTURN_D = 0.00;

    public PIDController pidController;

    private static final AHRS ahrs = new AHRS(Port.kMXP);

    public Drivetrain() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
rightFrontMotor = new WPI_TalonFX(0);


        
rightMidMotor = new WPI_TalonFX(99999);


        
rightBackMotor = new WPI_TalonFX(1);


        
leftFrontMotor = new WPI_TalonFX(15);


        
leftMidMotor = new WPI_TalonFX(99999);


        
leftBackMotor = new WPI_TalonFX(14);


        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

    /**Motors just to test on Plybot
    rightFrontMotor = new WPI_TalonSRX(14);
    rightMidMotor = new WPI_TalonSRX(2);
    rightBackMotor = new WPI_TalonSRX(15);

    leftFrontMotor = new WPI_TalonSRX(0);
    leftMidMotor = new WPI_TalonSRX(5);
    leftBackMotor = new WPI_TalonSRX(1); */


    leftFrontMotor.setInverted(true);
    leftMidMotor.setInverted(true);
    leftBackMotor.setInverted(true);

    leftMidMotor.follow(leftFrontMotor);
    leftBackMotor.follow(leftFrontMotor);
    rightMidMotor.follow(rightFrontMotor);
    rightBackMotor.follow(rightFrontMotor);

    
        pidController = new PIDController(PIDTURN_P, PIDTURN_I, PIDTURN_D);
    
    }

    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.S
        setDefaultCommand(new TankDrive());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop

    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    public void run(double l, double r)
    {
        double leftSpeed = l;
        double rightSpeed = r;

        leftFrontMotor.set(leftSpeed);
        rightFrontMotor.set(rightSpeed);

    }

    public void stop()
    {
        leftFrontMotor.set(0);
        rightFrontMotor.set(0);
    }

    public void setPercentVBus()
    {
        leftFrontMotor.set(ControlMode.PercentOutput, 0);
        rightFrontMotor.set(ControlMode.PercentOutput, 0);
    }

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    public void setControlMode(ControlMode mode) {
        leftFrontMotor.set(mode, 0);
        rightFrontMotor.set(mode, 0);
    }

    public ControlMode getControlMode() {
        return leftFrontMotor.getControlMode();
    }

    public static AHRS getGyro() {
        return ahrs;
    }

    public double getGyroYaw() {
        return ahrs.getYaw();
    }

    public static double getGyroPID(){
		return ahrs.pidGet();
	}

    public void writePIDs(double output){
		leftFrontMotor.pidWrite(output);
		rightFrontMotor.pidWrite(-output);
    }
    

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

}

