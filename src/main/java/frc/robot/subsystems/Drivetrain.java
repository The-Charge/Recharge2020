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

import frc.robot.MathUtil;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.controller.PIDController;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.kauailabs.navx.frc.AHRS;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.ControlMode;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.Robot;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class Drivetrain extends Subsystem {

/*
private WPI_TalonFX rightFrontMotor;
private WPI_TalonFX rightMidMotor;
private WPI_TalonFX rightBackMotor;
private WPI_TalonFX leftFrontMotor;
private WPI_TalonFX leftMidMotor;
private WPI_TalonFX leftBackMotor;

*/


    //Motors
 	private WPI_TalonSRX rightFrontMotor;
    private WPI_TalonSRX rightMidMotor;
    private WPI_TalonSRX rightBackMotor;
    private WPI_TalonSRX leftFrontMotor;
    private WPI_TalonSRX leftMidMotor;
    private WPI_TalonSRX leftBackMotor;
	
    //PID Constants (all values still need to be changed, these are values for plybot)
    private static final double SPEED_P_CONSTANT = 0.25;
    private static final double SPEED_I_CONSTANT = 0.0001;   //lowered
    private static final double SPEED_D_CONSTANT = 0.0;
    private static final double SPEED_F_CONSTANT = 0.12;

    private static final int TIMEOUT_MS = 10;
    private static final int MAX_TICKS_PER_SECOND = 9000;
    private static final int TICKS_PER_FOOT = 5270;    

    //Motion Magic (all values still need to be changed, these are values for plybot)
    public double MotionMagicP = 1; //gain tuned...
    public double MotionMagicI = 0.0;
    public double MotionMagicD = 0.001;
    public double MotionMagicF = 0.65;
    public int MotionMagicAcceleration = 2500;  //Tune between 2500-5000
    public int MotionMagicVelocity = 5000;
    public int MotionMagicPIDIndex = 0;
    public int MotionMagicPIDSlot = 0;
    public double MotionMagicDistance;
    //public double correctionR = 1.02;




    
    /* Decent PID Values for Resistance:
            P: 0.05
            I: 0.00004
            D: 0.0025
    */
    //These need to be tuned
    private static double PIDTURN_P = 0.05;
    private static double PIDTURN_I = 0.00004;
    private static double PIDTURN_D = 0.0025;

    public PIDController pidController;

    private static final AHRS ahrs = new AHRS(Port.kMXP);

    private static boolean isReversed = false;
    private static boolean Arcade = false;

    public Drivetrain() {
/*
rightFrontMotor = new WPI_TalonFX(8);


        
rightMidMotor = new WPI_TalonFX(5);


        
rightBackMotor = new WPI_TalonFX(7);


        
leftFrontMotor = new WPI_TalonFX(3);


        
leftMidMotor = new WPI_TalonFX(10);


        
leftBackMotor = new WPI_TalonFX(2);


*/
        



    //Motors just to test on Plybot
    rightFrontMotor = new WPI_TalonSRX(7);
    rightMidMotor = new WPI_TalonSRX(0);
    rightBackMotor = new WPI_TalonSRX(8);

    leftFrontMotor = new WPI_TalonSRX(2);
    leftMidMotor = new WPI_TalonSRX(5);
    leftBackMotor = new WPI_TalonSRX(3); 

    rightFrontMotor.setInverted(true);
    rightMidMotor.setInverted(true);
    rightBackMotor.setInverted(true);

    rightFrontMotor.setSensorPhase(false);    //inverts right encoder on Talon SRX

    leftMidMotor.follow(leftFrontMotor);
    leftBackMotor.follow(leftFrontMotor);
    rightMidMotor.follow(rightFrontMotor);
    rightBackMotor.follow(rightFrontMotor);

    
        pidController = new PIDController(PIDTURN_P, PIDTURN_I, PIDTURN_D);
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

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void run(double l, double r)
    {
        double leftSpeed = l;
        double rightSpeed = r;


        if(isReversed){
            leftFrontMotor.set(-1*leftSpeed);
            rightFrontMotor.set(-1*rightSpeed);
        }
        if(Arcade){
            
            double left = rightSpeed + Robot.oi.rightJoystick.getX();
            double right = rightSpeed - Robot.oi.rightJoystick.getX();

            leftFrontMotor.set(left);
            rightFrontMotor.set(right);

            }
        else{
	  	leftFrontMotor.set(leftSpeed);
        rightFrontMotor.set(rightSpeed);
        }
     
    
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
    

    public void initSpeedMode()
    {
	        leftFrontMotor.set(ControlMode.Velocity, 0);
        rightFrontMotor.set(ControlMode.Velocity, 0);

        //Assigned PID constants to the motors.
        leftFrontMotor.config_kP(1, SPEED_P_CONSTANT, TIMEOUT_MS);
        leftFrontMotor.config_kI(1, SPEED_I_CONSTANT, TIMEOUT_MS);
        leftFrontMotor.config_kD(1, SPEED_D_CONSTANT, TIMEOUT_MS);
        leftFrontMotor.config_kF(1, SPEED_F_CONSTANT, TIMEOUT_MS);

        rightFrontMotor.config_kP(1, SPEED_P_CONSTANT, TIMEOUT_MS);
        rightFrontMotor.config_kI(1, SPEED_I_CONSTANT, TIMEOUT_MS);
        rightFrontMotor.config_kD(1, SPEED_D_CONSTANT, TIMEOUT_MS);
        rightFrontMotor.config_kF(1, SPEED_F_CONSTANT, TIMEOUT_MS);
        
	}
    public void setControlMode(ControlMode mode) {
 		leftFrontMotor.set(mode, 0);
        rightFrontMotor.set(mode, 0);
    }
	 public ControlMode getControlMode() {
        return leftFrontMotor.getControlMode();
    }
    public static AHRS getGyro() {
	return ahrs;
	}
		 public double getGyroYaw() {
	        return ahrs.getYaw();   
	 }

	 public static double getGyroPID(){
		return ahrs.pidGet();
		}
	 
	 public void setPercentSpeedPID (double setSpeedL, double setSpeedR)
    {
        setSpeedR = MathUtil.clamp(setSpeedR, -1, 1);
		 setSpeedL = MathUtil.clamp(setSpeedL, -1, 1);
        leftFrontMotor.set(ControlMode.Velocity, MAX_TICKS_PER_SECOND * setSpeedL);
        rightFrontMotor.set(ControlMode.Velocity, MAX_TICKS_PER_SECOND * setSpeedR);
    }

	public boolean getReversed(){
        return isReversed;
    }
    
    public void setReversed(boolean r){
        isReversed = r;
    }
	 public void writePIDs(double output){
		leftFrontMotor.pidWrite(output);
		rightFrontMotor.pidWrite(-output);
    }
    
    public void setCoastMode() {
		leftFrontMotor.setNeutralMode(NeutralMode.Coast);
        rightFrontMotor.setNeutralMode(NeutralMode.Coast);
    }
    	 //Motion Magic for DriveXFeet command
    public void MotionMagicInit(double distance) {
    	//rightFrontMotor.follow(leftFrontMotor);
		leftFrontMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, MotionMagicPIDIndex, TIMEOUT_MS);
    	rightFrontMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, MotionMagicPIDIndex, TIMEOUT_MS);
        

        //Setting min and max outputs (new code)
        leftFrontMotor.configNominalOutputForward(0, TIMEOUT_MS);
        leftFrontMotor.configNominalOutputReverse(0, TIMEOUT_MS);
        leftFrontMotor.configPeakOutputForward(1, TIMEOUT_MS);
        leftFrontMotor.configPeakOutputReverse(-1, TIMEOUT_MS);
    
        rightFrontMotor.configNominalOutputForward(0, TIMEOUT_MS);
        rightFrontMotor.configNominalOutputReverse(0, TIMEOUT_MS);
        rightFrontMotor.configPeakOutputForward(1, TIMEOUT_MS);
        rightFrontMotor.configPeakOutputReverse(-1, TIMEOUT_MS);
		
		leftFrontMotor.selectProfileSlot(MotionMagicPIDSlot, MotionMagicPIDIndex);
    	rightFrontMotor.selectProfileSlot(MotionMagicPIDSlot, MotionMagicPIDIndex);
    	
    	leftFrontMotor.config_kP(0, MotionMagicP, TIMEOUT_MS);
    	leftFrontMotor.config_kI(0, MotionMagicI, TIMEOUT_MS);
    	leftFrontMotor.config_kD(0, MotionMagicD, TIMEOUT_MS);
    	leftFrontMotor.config_kF(0, MotionMagicF, TIMEOUT_MS);
    	
    	rightFrontMotor.config_kP(0, MotionMagicP, TIMEOUT_MS);
    	rightFrontMotor.config_kI(0, MotionMagicI, TIMEOUT_MS);
    	rightFrontMotor.config_kD(0, MotionMagicD, TIMEOUT_MS);
    	rightFrontMotor.config_kF(0, MotionMagicF, TIMEOUT_MS);
    	
    	leftFrontMotor.configMotionAcceleration(MotionMagicAcceleration, TIMEOUT_MS);
        leftFrontMotor.configMotionCruiseVelocity(MotionMagicVelocity, TIMEOUT_MS);
        
    	//rightFrontMotor.configMotionAcceleration((int)(correctionR*MotionMagicAcceleration), TIMEOUT_MS);
    	rightFrontMotor.configMotionAcceleration(MotionMagicAcceleration, TIMEOUT_MS);
    	rightFrontMotor.configMotionCruiseVelocity(MotionMagicVelocity, TIMEOUT_MS);
        
    	leftFrontMotor.setSelectedSensorPosition(0, MotionMagicPIDIndex, TIMEOUT_MS);
    	rightFrontMotor.setSelectedSensorPosition(0, MotionMagicPIDIndex, TIMEOUT_MS);
    	
    	MotionMagicDistance = distance * TICKS_PER_FOOT;
        leftFrontMotor.set(ControlMode.MotionMagic, MotionMagicDistance);
        rightFrontMotor.set(ControlMode.MotionMagic, MotionMagicDistance);

    	//rightFrontMotor.set(ControlMode.MotionMagic, correctionR*MotionMagicDistance);
    }
		
		public void setBrakeMode() {
        leftFrontMotor.setNeutralMode(NeutralMode.Brake);
        rightFrontMotor.setNeutralMode(NeutralMode.Brake);
    }

    public void reInitializePIDController() {
        PIDTURN_P = SmartDashboard.getNumber("TurnPID P:", 0);
        PIDTURN_I = SmartDashboard.getNumber("TurnPID I:", 0);
        PIDTURN_D = SmartDashboard.getNumber("TurnPID D:", 0);
        pidController = new PIDController(PIDTURN_P, PIDTURN_I, PIDTURN_D);
    }

    	

    public boolean isAtPIDDestination() {
		return (Math.abs(this.leftFrontMotor.getSelectedSensorPosition(MotionMagicPIDIndex) - MotionMagicDistance) < 500) || (Math.abs(this.rightFrontMotor.getSelectedSensorPosition(MotionMagicPIDIndex) - MotionMagicDistance) < 500);// || this.leftFrontMotor.getSelectedSensorPosition(MotionMagicPIDIndex) < -MotionMagicDistance + 6000)
	}

    
    public void ResestEncoder()
    {
        leftFrontMotor.setSelectedSensorPosition(0, 0, TIMEOUT_MS);
    	rightFrontMotor.setSelectedSensorPosition(0, 0, TIMEOUT_MS);
    }

/*
    public void MotionMagicInit(double distance, int backVelocity, int backAcceleration) {
    	MotionMagicDistance = distance;
    	leftFrontMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, MotionMagicPIDIndex, TIMEOUT_MS);
    	rightFrontMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, MotionMagicPIDIndex, TIMEOUT_MS);
    	
    	leftFrontMotor.selectProfileSlot(MotionMagicPIDSlot, MotionMagicPIDIndex);
    	rightFrontMotor.selectProfileSlot(MotionMagicPIDSlot, MotionMagicPIDIndex);
    	
    	leftFrontMotor.config_kP(0, MotionMagicP, TIMEOUT_MS);
    	leftFrontMotor.config_kI(0, MotionMagicI, TIMEOUT_MS);
    	leftFrontMotor.config_kD(0, MotionMagicD, TIMEOUT_MS);
    	leftFrontMotor.config_kF(0, MotionMagicF, TIMEOUT_MS);
    	
    	rightFrontMotor.config_kP(0, MotionMagicP, TIMEOUT_MS);
    	rightFrontMotor.config_kI(0, MotionMagicI, TIMEOUT_MS);
    	rightFrontMotor.config_kD(0, MotionMagicD, TIMEOUT_MS);
    	rightFrontMotor.config_kF(0, MotionMagicF, TIMEOUT_MS);
    	
    	leftFrontMotor.configMotionAcceleration(backAcceleration, TIMEOUT_MS);
    	leftFrontMotor.configMotionCruiseVelocity(backVelocity, TIMEOUT_MS);
    	
    	rightFrontMotor.configMotionAcceleration((int)(correctionR*backAcceleration), TIMEOUT_MS);
    	rightFrontMotor.configMotionCruiseVelocity((int)(correctionR*backVelocity), TIMEOUT_MS);
        
        //Do we need to reset encoders here?
    	//leftFrontMotor.setSelectedSensorPosition(0, MotionMagicPIDIndex, TIMEOUT_MS);
    	//rightFrontMotor.setSelectedSensorPosition(0, MotionMagicPIDIndex, TIMEOUT_MS);
    	
    	MotionMagicDistance *= TICKS_PER_FOOT;
    	leftFrontMotor.set(ControlMode.MotionMagic, MotionMagicDistance);
    	rightFrontMotor.set(ControlMode.MotionMagic, correctionR*MotionMagicDistance);
    }
    */
    public boolean getAcradeDrive(){
        return Arcade;
    }
    
    public void setArcadeDrive(boolean r){
        Arcade = r;
    }

    

}
