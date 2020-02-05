// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 *
 */
public class TurnNDegreesAbsolute extends Command {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
    private double m_degrees;
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    private double drivespeed;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public TurnNDegreesAbsolute(double degrees) {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // m_degrees = degrees;

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        m_degrees = degrees;

        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.drivetrain);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        Robot.shifters.shiftLow();
        // Robot.drivetrain.setBrakeMode();

        setTimeout(5);
        Robot.drivetrain.pidController.setSetpoint(m_degrees);
        Robot.drivetrain.pidController.enableContinuousInput(-180, 180);
        Robot.drivetrain.pidController.setTolerance(1);
        

    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        
        drivespeed = Robot.drivetrain.pidController.calculate(Robot.drivetrain.getGyroYaw());
        // if(drivespeed < .25 && drivespeed >= .05)
        //     drivespeed = .25;
        // else if(drivespeed > -.25 && drivespeed <= -.05)
        //     drivespeed = -.25;

        Robot.drivetrain.run(-drivespeed, drivespeed);
        
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.shifters.shiftHigh();
        // Robot.drivetrain.setCoastMode();
        Robot.drivetrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }

    public double getCurrentOutput() {
        return drivespeed;
    }
}
