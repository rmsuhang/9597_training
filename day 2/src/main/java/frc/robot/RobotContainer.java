// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.MotorTask;
import frc.robot.subsystems.MotorTask2;

public class RobotContainer {
    private final CommandXboxController m_driverJoystick = new CommandXboxController(0);//two xbox controller

    public final MotorTask motor=new MotorTask();
    public final MotorTask2 motor2=new MotorTask2();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    

    configureBindings();
  }


  private void configureBindings() {
//电机正转
    m_driverJoystick.leftBumper().onTrue(motor.motor_Move_PositiveCommand());
//电机反转
    m_driverJoystick.rightBumper().onTrue(motor.motor_Move_NegtiveCommand());
    m_driverJoystick.a().onTrue(motor2.motor_Move_HighCommand());
    m_driverJoystick.b().onTrue(motor2.motor_Move_LowCommand());
  }


}
