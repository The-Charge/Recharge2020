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

    //FIXME: Add a quick javadoc comment for degrees saying whether +90 is clockwise or counter clockwise
    // Should also add a note whether the units are [-180,180] or [0,360]
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
        Robot.drivetrain.turnPIDController.setSetpoint(m_degrees);
        Robot.drivetrain.turnPIDController.enableContinuousInput(-180, 180);
        Robot.drivetrain.turnPIDController.setTolerance(1);
        

    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        //FIXME: Not seeing any code to clamp the pidcontroller's output, so there's a possibility it'll try to go full speed....
        drivespeed = Robot.drivetrain.turnPIDController.calculate(Robot.drivetrain.getGyroYaw());
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
        // FIXME: Would recommend at initialize() seeing what the shifting state was, and setting it back to that, rather than assuming you need high gear.
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

    //FIXME: Not seeing any calls to this, can remove
    public double getCurrentOutput() {
        return drivespeed;
    }
}
