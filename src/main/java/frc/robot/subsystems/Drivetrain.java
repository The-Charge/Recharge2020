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

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
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

    public Drivetrain() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
rightFrontMotor = new WPI_TalonFX(1);


        
rightMidMotor = new WPI_TalonFX(2);


        
rightBackMotor = new WPI_TalonFX(3);


        
leftFrontMotor = new WPI_TalonFX(4);


        
leftMidMotor = new WPI_TalonFX(5);


        
leftBackMotor = new WPI_TalonFX(6);


        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

    //Motors just to test on Plybot
    // rightFrontMotor = new WPI_TalonSRX(14);
    // rightMidMotor = new WPI_TalonSRX(2);
    // rightBackMotor = new WPI_TalonSRX(15);

    // leftFrontMotor = new WPI_TalonSRX(0);
    // leftMidMotor = new WPI_TalonSRX(5);
    // leftBackMotor = new WPI_TalonSRX(1);


    leftFrontMotor.setInverted(true);
    leftMidMotor.setInverted(true);
    leftBackMotor.setInverted(true);

    leftMidMotor.follow(leftFrontMotor);
    leftBackMotor.follow(leftFrontMotor);
    rightMidMotor.follow(rightFrontMotor);
    rightBackMotor.follow(rightFrontMotor);
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

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

}

