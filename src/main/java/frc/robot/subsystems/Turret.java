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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class Turret extends Subsystem {
    private static final double H_DEGREES_PER_TICK = 0.0;  // how many degrees traveled per encoder tick
    private static final double H_TICKS_PER_DEGREE = 1 / H_DEGREES_PER_TICK;
    private static final int H_MIN_ENCODER_TICKS = 5000;  // used to stop turret from rotating past ends
    private static final int H_MAX_ENCODER_TICKS = -H_MIN_ENCODER_TICKS;
    private static final double H_MIN_ANGLE = H_MIN_ENCODER_TICKS * H_DEGREES_PER_TICK;
    private static final double H_MAX_ANGLE = H_MAX_ENCODER_TICKS * H_DEGREES_PER_TICK;
    private static final double H_TOLERANCE = 5;

    //Constants aquired from CAD team used for trig calculations (millimeters):
    public static final double TURRET_SIDE_A = 244.475;
    public static final double TURRET_SIDE_B = 369.4176;
    private final double V_MIN_ANGLE = 34.4;
    private final double V_MAX_ANGLE = 57.4;
    private final double V_TOLERANCE = 0.01;

    private static final double H_PID_P_CONSTANT = 0.25;
    private static final double H_PID_I_CONSTANT = 0.0001;
    private static final double H_PID_D_CONSTANT = 0.0;
    private static final double H_PID_F_CONSTANT = 0.12;
    private static final int TIMEOUT_MS = 10;

    private double actuatorDistance;
    public String status;
    public double horizontalError;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
private WPI_TalonSRX turretMotor;
private Servo elevationServo;
private DigitalInput leftLimitSwitch;
private DigitalInput limitSwitch2;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public Turret() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
turretMotor = new WPI_TalonSRX(9);


        
elevationServo = new Servo(0);
addChild("ElevationServo",elevationServo);

        
leftLimitSwitch = new DigitalInput(0);
addChild("Left Limit Switch",leftLimitSwitch);

        
limitSwitch2 = new DigitalInput(1);
addChild("Limit Switch 2",limitSwitch2);

        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        turretMotor.config_kP(1, H_PID_P_CONSTANT, TIMEOUT_MS);
        turretMotor.config_kI(1, H_PID_I_CONSTANT, TIMEOUT_MS);
        turretMotor.config_kD(1, H_PID_D_CONSTANT, TIMEOUT_MS);
        turretMotor.config_kF(1, H_PID_F_CONSTANT, TIMEOUT_MS);
        turretMotor.set(ControlMode.Position, 0);
        turretMotor.setSelectedSensorPosition(0);
        turretMotor.setNeutralMode(NeutralMode.Brake);

        status = "";
    }

    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
    }

    @Override
    public void periodic() {}

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void setHorizontalAngleAbsolute(double setpoint) {
        double ticks = turretMotor.getSelectedSensorPosition();
        if ((setpoint < H_MIN_ANGLE || setpoint > H_MAX_ANGLE) || (ticks < H_MIN_ENCODER_TICKS || ticks > H_MAX_ENCODER_TICKS)) {
            stopHorizontal();
            if (setpoint < 0) {
                horizontalError = setpoint - H_MIN_ANGLE;
            } else {
                horizontalError = setpoint - H_MAX_ANGLE;
            }
            SmartDashboard.putBoolean("Vision/error", true);
        } else {
            turretMotor.set(ControlMode.Position, setpoint * H_TICKS_PER_DEGREE);
            horizontalError = 0;
            SmartDashboard.putBoolean("Vision/error", false);
        }
        SmartDashboard.putNumber("Vision/angular_distance", setpoint);
        checkHorizontalLimitSwitches();
    }

    private double calcActuatorDistance(double angle) {
        // Running law of cosines on the turret
        double d = Math.sqrt(Math.pow(Turret.TURRET_SIDE_A, 2) + Math.pow(Turret.TURRET_SIDE_B, 2) - 2 * Turret.TURRET_SIDE_A * Turret.TURRET_SIDE_B * Math.cos(Math.toRadians(94.4 - angle)));
    
        // This line subtracts the length of the actuator while not extended
        d -= 218;  // 218 is what the actuator blueprints says is the "Closed Length (hole to hole)"
        // This line changes the normalization from 0-140 to 0-1
        d /= 140;  // 140 is what the actuator blueprints says is the max the actuator can extend from the base
        return d;
    }

    public void setVerticalAngle(double setpoint) {
        if(setpoint > V_MAX_ANGLE) {
            setpoint = V_MAX_ANGLE;
        } else if(setpoint < V_MIN_ANGLE) {
            setpoint = V_MIN_ANGLE;
        }
        
        actuatorDistance = calcActuatorDistance(setpoint);
        elevationServo.set(actuatorDistance);
    }

    public void checkHorizontalLimitSwitches() {
        if (turretMotor.getSensorCollection().isRevLimitSwitchClosed()) {
            turretMotor.setSelectedSensorPosition(H_MIN_ENCODER_TICKS, 0, TIMEOUT_MS);
        } else if (turretMotor.getSensorCollection().isFwdLimitSwitchClosed()) {
            turretMotor.setSelectedSensorPosition(H_MAX_ENCODER_TICKS, 0, TIMEOUT_MS);
        }
    }

    public void setRawVertical(double setpoint) {
        setpoint *= 0.6;
        setpoint += 0.2;
        elevationServo.set(setpoint);
    }

    public boolean atVerticalAngle(double angle) {
        return (Math.abs(elevationServo.get() - calcActuatorDistance(angle)) <= V_TOLERANCE);
    }

    public void stopHorizontal() {
        turretMotor.set(ControlMode.PercentOutput, 0);
    }

    private double getHorizontalAngle() {
        return turretMotor.getSelectedSensorPosition() * H_DEGREES_PER_TICK;
    }

    public boolean atHorizontalAngle(double angle) {
        double current = getHorizontalAngle();
        return (angle - H_TOLERANCE < current) && (current < angle + H_TOLERANCE);
    }

	public void setHorizontalAngleRelative(double setpoint) {
        setHorizontalAngleAbsolute(setpoint + getHorizontalAngle());
    }
    
    public void runHorizontalManual(double percent) {
        int ticks = turretMotor.getSelectedSensorPosition();
        if ((ticks < H_MIN_ENCODER_TICKS && percent < 0) || (ticks > H_MAX_ENCODER_TICKS && percent > 0)) {
            stopHorizontal();
        } else {
            turretMotor.set(ControlMode.PercentOutput, percent);
        }
        checkHorizontalLimitSwitches();
    }
}

