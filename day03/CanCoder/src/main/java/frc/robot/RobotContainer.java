// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.sub_CANcoder;

public class RobotContainer {
  private final CommandXboxController m_xbox = new CommandXboxController(0);
  public sub_CANcoder CANcoder_motor = new sub_CANcoder();

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    m_xbox.leftBumper().whileTrue(CANcoder_motor.cmd_Motor_10());
    m_xbox.rightBumper().whileTrue(CANcoder_motor.cmd_Motor_neg10());
    m_xbox.rightTrigger().onTrue(CANcoder_motor.cmd_Motor_neg());
    m_xbox.leftTrigger().onTrue(CANcoder_motor.cmd_Motor_v_out());
    m_xbox.a().onTrue(CANcoder_motor.cmd_Motor_v_out_release());
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
