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
import com.ctre.phoenix.ILoopable;
import com.ctre.phoenix.CANifier;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class Lights extends Subsystem implements ILoopable{

    public static CANifier canifier = new CANifier(0);
    private float hue;
    public float saturation;
    public float value;
    private static float rgb[] = new float[3];

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public Lights() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

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
    public void onStart(){}

    public void onStop(){}

    public boolean isDone(){return false;}

    public void onLoop()
    {
        hue += 1;
        if (hue >= 360){
            hue = 0;
        }
        if (saturation > 1){
            saturation = 1;
        }
        if (saturation < 0){
            saturation = 0;
        }

        if (value > 1){
            value = 1;
        }
        if (value < 0){
            value = 0;
        }
         
        rgb = HsvToRgb(hue,saturation, value);   //How to convert this to RGB without a new "task"
          
        // rgb[0] = _averageR.process(rgb[0]);   //What is this
        // rgb[1] = _averageG.process(rgb[1]);
        // rgb[2] = _averageB.process(rgb[2]);
        canifier.setLEDOutput(rgb[0],CANifier.LEDChannel.LEDChannelA);
        canifier.setLEDOutput(rgb[1], CANifier.LEDChannel.LEDChannelB);
        canifier.setLEDOutput(rgb[2], CANifier.LEDChannel.LEDChannelC);
        //I think that this array is supposed to store the three RGB values...
        //But how to get it from HSV
        //and should we even be using HSV, should we just be using RGB values
    }
    public float[] HsvToRgb(float h, float s, float v)
    {
        //idk really know if this is right
        float[] RGB = new float[3];


        return null;
    }


}

