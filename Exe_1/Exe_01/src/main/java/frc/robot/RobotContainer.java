// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

//my package
import frc.robot.subsystems.sub_motorControl_VTC;
import frc.robot.subsystems.sub_CANdle;

public class RobotContainer {
  private final CommandXboxController m_XboxCtler = new CommandXboxController(0);
  public final sub_motorControl_VTC m_motor= new sub_motorControl_VTC();
  public final sub_CANdle m_candle = new sub_CANdle();
  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    /*---------------(Driver)---------------------------- */
    m_XboxCtler.leftBumper().whileTrue(m_motor.cmd_motor_1_setVelocity_10().alongWith(Commands.startEnd(m_candle::setFire,m_candle::setAll,m_candle)));
    m_XboxCtler.rightBumper().whileTrue(m_motor.cmd_motor_1_setVelocity_neg10().alongWith(Commands.startEnd(m_candle::setRainbow,m_candle::setAll,m_candle)));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
