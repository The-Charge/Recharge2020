// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package frc.robot;

import frc.robot.commands.*;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());


    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
public JoystickButton shiftHighBtn;
public JoystickButton shiftLowBtn;
public JoystickButton quarterSpeedBtn;
public JoystickButton halfSpeedBtn;
public Joystick rightJoystick;
public JoystickButton toggleLockStraightBtn;
public Joystick leftJoystick;
public JoystickButton shootBtn;
public JoystickButton manualElevation;
public Joystick buttonBox;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public OI() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

buttonBox = new Joystick(2);

manualElevation = new JoystickButton(buttonBox, 6);
manualElevation.whileHeld(new ManualTurretElevation(0));
shootBtn = new JoystickButton(buttonBox, 1);
shootBtn.whileHeld(new Shoot(0));
leftJoystick = new Joystick(1);

toggleLockStraightBtn = new JoystickButton(leftJoystick, 1);
toggleLockStraightBtn.whileHeld(new ToggleLockStraight());
rightJoystick = new Joystick(0);

halfSpeedBtn = new JoystickButton(rightJoystick, 5);
halfSpeedBtn.whileHeld(new HalfSpeed());
quarterSpeedBtn = new JoystickButton(rightJoystick, 4);
quarterSpeedBtn.whileHeld(new QuarterSpeed());
shiftLowBtn = new JoystickButton(rightJoystick, 2);
shiftLowBtn.whenPressed(new ShiftLow());
shiftHighBtn = new JoystickButton(rightJoystick, 1);
shiftHighBtn.whenPressed(new ShiftHigh());


        // SmartDashboard Buttons
        SmartDashboard.putData("Autonomous Command", new AutonomousCommand());
        SmartDashboard.putData("ShiftHigh", new ShiftHigh());
        SmartDashboard.putData("ShiftLow", new ShiftLow());
        SmartDashboard.putData("Shoot: default", new Shoot(0.4));
        SmartDashboard.putData("TurretCommand", new TurretCommand());
        SmartDashboard.putData("RunIntake: default", new RunIntake(0.4));
        SmartDashboard.putData("DriveXFeetMM: default", new DriveXFeetMM(0, 0, 30));
        SmartDashboard.putData("TurnNDegreesAbsolute: default", new TurnNDegreesAbsolute(180));
        SmartDashboard.putData("InvertDrive", new InvertDrive());
        SmartDashboard.putData("QuarterSpeed", new QuarterSpeed());
        SmartDashboard.putData("RotationControl", new RotationControl());
        SmartDashboard.putData("PositionControl", new PositionControl());
        SmartDashboard.putData("Index: default", new Index(0.4));
        SmartDashboard.putData("HalfSpeed", new HalfSpeed());
        SmartDashboard.putData("ManualTurretElevation: default", new ManualTurretElevation(0));
        SmartDashboard.putData("ManualTurretElevationDegrees: default", new ManualTurretElevationDegrees(0));
		SmartDashboard.putData("ToggleLockStraight", new ToggleLockStraight());
		SmartDashboard.putData("RunTurretVision", new RunTurretVision());
        SmartDashboard.putData("RunTurretManual", new RunTurretManual());
	//	SmartDashboard.putData("RightTrenchRun", new RightTrenchRun());
      //  SmartDashboard.putData("MiddleTrenchRun", new MiddleTrenchRun());
      //  SmartDashboard.putData("LeftTrenchRun", new LeftTrenchRun());
		
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        SmartDashboard.putNumber("Degrees:", 0);
        SmartDashboard.putNumber("TurnPID P:", 0.05);
        SmartDashboard.putNumber("TurnPID I:", 0.00004);
        SmartDashboard.putNumber("TurnPID D:", 0.0025);

        SmartDashboard.putData("Reinitialize PIDController:", new ReinitializePIDController());
        
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
public Joystick getRightJoystick() {
        return rightJoystick;
    }

public Joystick getLeftJoystick() {
        return leftJoystick;
    }

public Joystick getButtonBox() {
        return buttonBox;
    }


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
}

