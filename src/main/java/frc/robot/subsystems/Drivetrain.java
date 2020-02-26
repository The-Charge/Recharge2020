/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.I2C.Port;
//import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


import frc.robot.Constants.DriveConstants;

public class Drivetrain extends SubsystemBase {
  // The motors on the left side of the drive.
  public WPI_TalonSRX leftFrontMotor = new WPI_TalonSRX(DriveConstants.kLeftMotor1Port);
  public WPI_TalonSRX rightFrontMotor = new WPI_TalonSRX(DriveConstants.kRightMotor1Port);

  private  WPI_TalonSRX leftBackMotor = new WPI_TalonSRX(DriveConstants.kLeftMotor2Port);

  private WPI_TalonSRX rightBackMotor = new WPI_TalonSRX(DriveConstants.kRightMotor2Port);
  
 
  private final SpeedControllerGroup m_leftMotors =
      new SpeedControllerGroup(leftFrontMotor,
                               leftBackMotor);

  // The motors on the right side of the drive.
  private final SpeedControllerGroup m_rightMotors =
      new SpeedControllerGroup(rightFrontMotor,
                            rightBackMotor);
                               

  // The robot's drive
  private final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotors, m_rightMotors);

  /*
    // The left-side drive encoder
    private final Encoder m_leftEncoder =
    new Encoder(DriveConstants.kLeftEncoderPorts[0], DriveConstants.kLeftEncoderPorts[1],
                DriveConstants.kLeftEncoderReversed);

// The right-side drive encoder
    private final Encoder m_rightEncoder =
    new Encoder(DriveConstants.kRightEncoderPorts[0], DriveConstants.kRightEncoderPorts[1],
                DriveConstants.kRightEncoderReversed);
                */

  // The gyro sensor
      private final AHRS m_gyro = new AHRS(Port.kMXP);

  // Odometry class for tracking robot pose
      public final DifferentialDriveOdometry m_odometry;

  /**
   * Creates a new DriveSubsystem.
   */
  public Drivetrain() {
    // Sets the distance per pulse for the encoders
    // Sets the distance per pulse for the encoders
    //m_leftEncoder.setDistancePerPulse(DriveConstants.kEncoderDistancePerPulse);
    //m_rightEncoder.setDistancePerPulse(DriveConstants.kEncoderDistancePerPulse);
    //leftFrontMotor.setInverted(false);
    //leftFrontMotor.setSensorPhase(false);
    //leftFrontMotor.setNeutralMode(NeutralMode.Brake);
    //rightFrontMotor.setInverted(false);
    //rightFrontMotor.setSensorPhase(true);
    //rightFrontMotor.setNeutralMode(NeutralMode.Brake);
    //leftBackMotor.setInverted(false);
    //leftBackMotor.follow(leftFrontMotor);
   //leftBackMotor.setNeutralMode(NeutralMode.Brake);
    //rightBackMotor.setInverted(false);
    //rightBackMotor.follow(rightFrontMotor);
    //rightBackMotor.setNeutralMode(NeutralMode.Brake);
    
    resetEncoders();

    m_odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getHeading()));
  }

  @Override
  public void periodic() {
    // Update the odometry in the periodic block
    //SmartDashboard.putNumber("Left Encoder", leftFrontMotor.getSelectedSensorPosition(0));
    m_odometry.update(Rotation2d.fromDegrees(getHeading()),leftFrontMotor.getSelectedSensorPosition(0)*DriveConstants.kEncoderDistancePerPulse,
    -rightFrontMotor.getSelectedSensorPosition(0)*DriveConstants.kEncoderDistancePerPulse);
  }

  /**
   * Returns the currently-estimated pose of the robot.
   *
   * @return The pose.
   */
  public Pose2d getPose() {
    return m_odometry.getPoseMeters();
  }

  /**
   * Returns the current wheel speeds of the robot.
   *
   * @return The current wheel speeds.
   */
  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(leftFrontMotor.getSelectedSensorVelocity()*DriveConstants.kEncoderDistancePerPulse, -rightFrontMotor.getSelectedSensorVelocity()*DriveConstants.kEncoderDistancePerPulse);
  }

  /**
   * Resets the odometry to the specified pose.
   *
   * @param pose The pose to which to set the odometry.
   */
  public void resetOdometry(Pose2d pose) {
    resetEncoders();
    m_odometry.resetPosition(pose, Rotation2d.fromDegrees(getHeading()));
  }

  /**
   * Drives the robot using arcade controls.
   *
   * @param fwd the commanded forward movement
   * @param rot the commanded rotation
   */
  public void arcadeDrive(double fwd, double rot) {
    m_drive.arcadeDrive(fwd, rot);
  }

  /**
   * Controls the left and right sides of the drive directly with voltages.
   *
   * @param leftVolts  the commanded left output
   * @param rightVolts the commanded right output
   */
  public void tankDriveVolts(double leftVolts, double rightVolts) {
    m_leftMotors.setVoltage(leftVolts);
    m_rightMotors.setVoltage(-rightVolts);
    m_drive.feed();
  }

  /**
   * Resets the drive encoders to currently read a position of 0.
   */
  public void resetEncoders() {
    leftFrontMotor.setSelectedSensorPosition(0);
    rightFrontMotor.setSelectedSensorPosition(0);
  }

  public void setEncoders(int left, int right) {
    leftFrontMotor.setSelectedSensorPosition(left);
    rightFrontMotor.setSelectedSensorPosition(right);
  }

  /**
   * Gets the average distance of the two encoders.
   *
   * @return the average of the two encoder readings
   */
  public double getAverageEncoderDistance() {

    return ((leftFrontMotor.getSelectedSensorPosition(0)*DriveConstants.kEncoderDistancePerPulse - rightFrontMotor.getSelectedSensorPosition(0)*DriveConstants.kEncoderDistancePerPulse) / 2.0);
  }

  /**
   * Gets the left drive encoder.
   *
   * @return the left drive encoder
   */
  /*
  public Encoder getLeftEncoder() {
    return leftFrontMotor.getSelectedSensorPosition(0);
  }
  */

  /**
   * Gets the right drive encoder.
   *
   * @return the right drive encoder
   */
  /*
  public Encoder getRightEncoder() {
    return m_rightEncoder;
  }
  */

  /**
   * Sets the max output of the drive.  Useful for scaling the drive to drive more slowly.
   *
   * @param maxOutput the maximum output to which the drive will be constrained
   */
  public void setMaxOutput(double maxOutput) {
    m_drive.setMaxOutput(maxOutput);
  }

  /**
   * Zeroes the heading of the robot.
   */
  public void zeroHeading() {
    m_gyro.reset();
  }

  /**
   * Returns the heading of the robot.
   *
   * @return the robot's heading in degrees, from -180 to 180
   */
  public double getHeading() {
    return Math.IEEEremainder(m_gyro.getAngle(), 360) * (DriveConstants.kGyroReversed ? -1.0 : 1.0);
  }

  /**
   * Returns the turn rate of the robot.
   *
   * @return The turn rate of the robot, in degrees per second
   */
  public double getTurnRate() {
    return m_gyro.getRate() * (DriveConstants.kGyroReversed ? -1.0 : 1.0);
  }
}