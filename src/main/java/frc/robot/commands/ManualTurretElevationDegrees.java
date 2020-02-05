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
import frc.robot.subsystems.Turret;

/**
 *
 */
public class ManualTurretElevationDegrees extends Command {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
    private double m_targetAngle;
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    private double actuatorDistance;

    private final double MIN_ANGLE = 37;
    private final double MAX_ANGLE = 60;
    private final double TOLERANCE = 0.01;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public ManualTurretElevationDegrees(double targetAngle) {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        m_targetAngle = targetAngle;

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.turret);

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {

        //This makes sure the sent angle is within doable range
        if(m_targetAngle > MAX_ANGLE)
            m_targetAngle = MAX_ANGLE;
        else if(m_targetAngle < MIN_ANGLE)
            m_targetAngle = MIN_ANGLE;

        
        // This line uses the Law of Cosines to find the total length
        //  of the actuator based on some other values from the CAD team:
        //  c^2 = a^2 + b^2 - 2abCos(C):
        actuatorDistance = Math.sqrt(Math.pow(Turret.TURRET_SIDE_A, 2) + Math.pow(Turret.TURRET_SIDE_B, 2) - 2 * Turret.TURRET_SIDE_A * Turret.TURRET_SIDE_B * Math.cos(94.4 - m_targetAngle));
        
        Robot.turret.getElevationServo().set(actuatorDistance);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        double elevation = Robot.turret.getElevationServo().get();
        if(Math.abs(elevation - actuatorDistance) <= TOLERANCE) 
            return true;
        else
            return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
}
