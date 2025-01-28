// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.cameraserver.CameraServer;
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
 
  private final SparkMax m_leadMotorleft = new SparkMax(3, MotorType.kBrushed);
  //private final SparkMax m_arm = new SparkMax(9, MotorType.kBrushless);
  private final SparkMax m_followMotorleft = new SparkMax(4, MotorType.kBrushed);
  private final SparkMax m_leadMotorright = new SparkMax(2, MotorType.kBrushed);
  private final SparkMax m_followMotorright = new SparkMax(1, MotorType.kBrushed);
  //private final SparkMax m_liftMotor = new SparkMax(6, MotorType.kBrushless);
  private final DifferentialDrive m_robotDrive = new DifferentialDrive(m_leadMotorleft, m_leadMotorright);
  //Joystick controller
  private final Joystick m_controller = new Joystick(0);
  //logitech controller
  private final XboxController xbox_controller = new XboxController(0);

  //Camera Thread
  Thread m_visionThread;
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {


   m_visionThread = new Thread(
        () -> {
          var camera = CameraServer.startAutomaticCapture();

          var cameraWidth = 640;
          var cameraHeight = 480;

          camera.setResolution(cameraWidth, cameraHeight);        
        });
    m_visionThread.setDaemon(true);
    m_visionThread.start(); 

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

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    // Syncs the left side of the motors with one another
    m_followMotorleft.set(m_leadMotorleft.get());

   // Syncs the right side of the motors with one another
   m_followMotorright.set(m_leadMotorright.get());
    
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
