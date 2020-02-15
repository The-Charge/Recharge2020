package frc.robot;
//package org.usfirst.frc2619.Recharge2020;  //This was the declaration from last year

import java.util.ArrayList;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
//import org.usfirst.frc2619.Plybot2020.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.util.Units;

public class MathUtil {

	public static double calcDirection(double current, double desired) {
		current = Math.toRadians(current);
		desired = Math.toRadians(desired);
		double current_x = Math.cos(current);
		double current_y = Math.sin(current);
		double desired_x = Math.cos(desired);
		double desired_y = Math.sin(desired);
		double direction = Math.signum(current_x * desired_y - desired_x * current_y);
		return direction;
	}	//return +1 if direction is CW, -1 if CCW - copied from SteamBot

	//limits a value to a max and min
	public static double clamp(double input, double min, double max){
		input = input < min ? min : input;
		input = input > max ? max : input;
		return input;
	}

	//calls deadband and delin
    public static double adjSpeed(double speed) {
    	double dB = 0.1;
    	double root = 1;
    	double power = 3;
    	speed = MathUtil.deadband(speed, dB);
    	speed = MathUtil.delin(speed, dB , root, power);
    	return speed;
    }
	
	//Prevents the robot from moving while the joysticks are close to the center
	public static double deadband(double speed, double dead){
		if (-dead < speed && speed < dead) 
			return 0;
		else
			return speed;
	}//checks if speed is inbetween -dB and +dB then it should be set to zero
	
	//Applies delinerization
	public static double delin(double speed, double dead, double root, double pwr){
		double evn = (pwr/root) % 2; // 1
		double invdB = Math.pow(1 - dead,-1); // 1/0.9
		double cons = pwr/root; // 3
		if (speed != 0) { //Makes sure deadband doesn't bypass the calculations
			if (speed > 0) //Speed is greater than zero and so there are no exceptions
				return Math.pow(invdB * (speed - dead), cons);
			else if (evn != 0) //Less than zero, checks for even power
				return Math.pow(invdB * (speed + dead), cons);
			else //To stay negative, a "-" must be put at the beginning to maintain negativity of speed
				return -Math.pow(invdB * (speed + dead), cons);
		}
		else return 0;
	}//

	//Maps a ranged value between 0 and 1
	public static double lerp(double input, double max, double min){
		return (input-min)/(max-min);
	}

	//Need to convert points to Pose2d objects and the way poionts prior to calling this method
	/*
	public Trajectory generateTrajectory(Pose2d startPos, Pose2d endPos, ArrayList<Translation2d> waypoints) {

	
	var sideStart = new Pose2d(Units.feetToMeters(1.54), Units.feetToMeters(23.23), Rotation2d.fromDegrees(-180));
    var crossScale = new Pose2d(Units.feetToMeters(23.7), Units.feetToMeters(6.8),
    Rotation2d.fromDegrees(-160));

    var interiorWaypoints = new ArrayList<Translation2d>();
    interiorWaypoints.add(new Translation2d(Units.feetToMeters(14.54), Units.feetToMeters(23.23)));
    interiorWaypoints.add(new Translation2d(Units.feetToMeters(21.04), Units.feetToMeters(18.23)));
	
    TrajectoryConfig config = new TrajectoryConfig(Units.feetToMeters(12), Units.feetToMeters(12));  //Need to input max velocity and accleration
    config.setReversed(true);

    return (TrajectoryGenerator.generateTrajectory(startPos, waypoints, endPos, config));

	}
	*/
}