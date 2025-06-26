// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.Motor;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {

  //joysick
  private final CommandXboxController m_driverJoystick = new CommandXboxController(0);//two xbox controller
  
  //subsystems
  public final Motor motor = new Motor();


  //构造函数
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }


  private void configureBindings() {
    //电机正转
    m_driverJoystick.leftBumper().onTrue(motor.Motor_Move_Positive());

    //电机反转
    m_driverJoystick.rightBumper().onTrue(motor.Motor_Move_Negetive());
    
  }


}
