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
public Joystick rightJoystick;
public Joystick leftJoystick;
public JoystickButton shootBtn;
public Joystick buttonBox;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public OI() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

buttonBox = new Joystick(2);

shootBtn = new JoystickButton(buttonBox, 1);
shootBtn.whileHeld(new Shoot(0));
leftJoystick = new Joystick(1);

rightJoystick = new Joystick(0);

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
        SmartDashboard.putData("TurretTurn", new TurretTurn());
        SmartDashboard.putData("RunIntake: default", new RunIntake(0.4));
        SmartDashboard.putData("DriveXFeetMM: default", new DriveXFeetMM(0, 0, 30));

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

