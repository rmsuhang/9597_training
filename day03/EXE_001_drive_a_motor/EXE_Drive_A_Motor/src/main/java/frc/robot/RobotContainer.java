// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot;

import java.util.Set;
import com.ctre.phoenix6.swerve.SwerveModule.DriveRequestType;
import com.ctre.phoenix6.swerve.SwerveRequest;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;

import static edu.wpi.first.units.Units.MetersPerSecond;
import static edu.wpi.first.units.Units.RadiansPerSecond;
import static edu.wpi.first.units.Units.RotationsPerSecond;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.commands.CANdlePrintCommands;
import frc.robot.subsystems.CANdleSystem;
import frc.robot.subsystems.ExampleSubsystem;

public class RobotContainer {
    private final CommandXboxController m_driverJoystick = new CommandXboxController(0);//two xbox controller
    public final ExampleSubsystem m_test_motor = new ExampleSubsystem();
    public final CANdleSystem m_candle = new CANdleSystem();
    public RobotContainer() {
        configureBindings();
        
    }

    private void configureBindings() {

        //************************************************************ (driver) *******************************************************
        //set the direction of heading
        //motor
        m_driverJoystick.leftBumper().whileTrue(m_test_motor.motorSetPosition().alongWith(Commands.runOnce(m_candle::incrementAnimation, m_candle)));
        m_driverJoystick.rightBumper().onTrue(m_test_motor.cmd_motor_SetPositon().andThen(m_candle::setFire,m_candle));
        m_driverJoystick.rightTrigger().onTrue(m_test_motor.cmd_motor_SetPositon().alongWith(Commands.runOnce(m_candle::setRgbFade,m_candle)).andThen(m_candle::setFire,m_candle));
        //candle
        m_driverJoystick.a().onTrue(Commands.runOnce(m_candle::setColors, m_candle));
        m_driverJoystick.x().onTrue(Commands.runOnce(m_candle::incrementAnimation, m_candle));
        m_driverJoystick.y().onTrue(Commands.runOnce(m_candle::decrementAnimation, m_candle));
        m_driverJoystick.b().onTrue(new CANdlePrintCommands.Print5V(m_candle));

        //new JoystickButton(joy, Constants.BlockButton).onTrue(new RunCommand(m_candleSubsystem::setColors, m_candleSubsystem));
        //new JoystickButton(joy, Constants.IncrementAnimButton).onTrue(Commands.runOnce RunCommand(m_candleSubsystem::incrementAnimation, m_candleSubsystem));
        //new JoystickButton(joy, Constants.DecrementAnimButton).onTrue(Commands.runOnce(m_candleSubsystem::decrementAnimation, m_candleSubsystem));

        //new POVButton(joy, Constants.MaxBrightnessAngle).onTrue(new CANdleConfigCommands.ConfigBrightness(m_candleSubsystem, 1.0));
        //new POVButton(joy, Constants.MidBrightnessAngle).onTrue(new CANdleConfigCommands.ConfigBrightness(m_candleSubsystem, 0.3));
        //new POVButton(joy, Constants.ZeroBrightnessAngle).onTrue(new CANdleConfigCommands.ConfigBrightness(m_candleSubsystem, 0));

        //new JoystickButton(joy, Constants.VbatButton).onTrue(new CANdlePrintCommands.PrintVBat(m_candleSubsystem));
        //new JoystickButton(joy, Constants.V5Button).onTrue(new CANdlePrintCommands.Print5V(m_candleSubsystem));
        //new JoystickButton(joy, Constants.CurrentButton).onTrue(new CANdlePrintCommands.PrintCurrent(m_candleSubsystem));
        //new JoystickButton(joy, Constants.TemperatureButton).onTrue(new CANdlePrintCommands.PrintTemperature(m_candleSubsystem));
    }
}
