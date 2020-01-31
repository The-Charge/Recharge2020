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
import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class Turret extends Subsystem {
    private static final double DEGREES_PER_TICK = 0.0;  // how many degrees traveled per encoder tick
    private static final double TICKS_PER_DEGREE = 1 / DEGREES_PER_TICK;
    private static final int MIN_ENCODER_TICKS = 5000;  // used to stop turret from rotating past ends
    private static final int MAX_ENCODER_TICKS = -MIN_ENCODER_TICKS;
    private static final double MIN_ANGLE = MIN_ENCODER_TICKS * DEGREES_PER_TICK;
    private static final double MAX_ANGLE = MAX_ENCODER_TICKS * DEGREES_PER_TICK;
    private static final double ANGLE_ACCURACY = 5;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
private WPI_TalonSRX turretMotor;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public Turret() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
turretMotor = new WPI_TalonSRX(9);


        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        // turretMotor.set(ControlMode.Position, 0);
        turretMotor.setSelectedSensorPosition(0);
        turretMotor.setNeutralMode(NeutralMode.Brake);
    }

    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new RunTurretManual());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
    }

    @Override
    public void periodic() {}

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    private void setAngleAbsolute(double setpoint) {
        double ticks = turretMotor.getSelectedSensorPosition();
        if ((setpoint < MIN_ANGLE || setpoint > MAX_ANGLE) || (ticks < MIN_ENCODER_TICKS || ticks > MAX_ENCODER_TICKS)) {
            stop();
        } else {
            turretMotor.set(ControlMode.Position, setpoint * TICKS_PER_DEGREE);
        }
        // other teams used a rate limiter to stabilize the setpoint
    }

    private void stop() {
        turretMotor.set(ControlMode.PercentOutput, 0);
    }

    private double getAngle() {
        return turretMotor.getSelectedSensorPosition() * DEGREES_PER_TICK;
    }

    public boolean atAngle(double angle) {
        double current = getAngle();
        return (angle - ANGLE_ACCURACY < current) && (current < angle + ANGLE_ACCURACY);
    }

    public void setAngleRelative(double setpoint) {
        setAngleAbsolute(setpoint + getAngle());
    }

    public void runManual(double percent) {
        int ticks = turretMotor.getSelectedSensorPosition();
        if ((ticks < MIN_ENCODER_TICKS && percent < 0) || (ticks > MAX_ENCODER_TICKS && percent > 0)) {
            stop();
        } else {
            turretMotor.set(ControlMode.PercentOutput, percent);
        }
    }
}

