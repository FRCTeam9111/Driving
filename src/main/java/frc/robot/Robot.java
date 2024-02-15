// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;


/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
 
  private final CANSparkMax m_leadMotorleft = new CANSparkMax(3, MotorType.kBrushed);
  //private final CANSparkMax m_arm = new CANSparkMax(9, MotorType.kBrushless);
  private final CANSparkMax m_followMotorleft = new CANSparkMax(4, MotorType.kBrushed);
  private final CANSparkMax m_leadMotorright = new CANSparkMax(2, MotorType.kBrushed);
  private final CANSparkMax m_followMotorright = new CANSparkMax(1, MotorType.kBrushed);
  //private final CANSparkMax m_liftMotor = new CANSparkMax(6, MotorType.kBrushless);
  private final DifferentialDrive m_robotDrive = new DifferentialDrive(m_leadMotorleft, m_leadMotorright);
  //Joystick controller
  private final Joystick m_controller = new Joystick(0);
  //logitech controller
  private final XboxController xbox_controller = new XboxController(0);
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
   // Syncs the left side of the motors with one another
   m_followMotorleft.follow(m_leadMotorleft);

   // Syncs the right side of the motors with one another
   m_followMotorright.follow(m_leadMotorright);

  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
 
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
 
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    //XBox Controller
    m_robotDrive.arcadeDrive(-xbox_controller.getLeftX() * .8, -xbox_controller.getLeftY() * .8);
    // joystick 
    m_robotDrive.arcadeDrive(-m_controller.getRawAxis(0) * .8, -m_controller.getRawAxis(1) * .8);
   
  }

  @Override
  public void testInit() {
 
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}
