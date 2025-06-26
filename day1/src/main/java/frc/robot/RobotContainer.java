// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.CANdlePrintCommands;
import frc.robot.subsystems.CANdleSystem;
import frc.robot.subsystems.Example;
//import frc.robot.commands.AutoAlignToAprilTagCommand;

//import frc.robot.Commands.Getballcommand;


public class RobotContainer {

    //private final CommandXboxController joystick = new CommandXboxController(0);
    private final CommandXboxController m_driverJoystick = new CommandXboxController(0);//two xbox controller

    //subsystem
    public final Example testmotor = new Example();
    public final CANdleSystem m_candleSubsystem = new CANdleSystem();


    public RobotContainer() {

      //bind the all the commands
      configureBindings();
        
    }

    private void configureBindings() {

        //************************************************************ (driver) *******************************************************

        //motor
        //m_driverJoystick.leftBumper().whileTrue(testmotor.motor_Move2position());

        //candle
        m_driverJoystick.a().onTrue(Commands.runOnce(m_candleSubsystem::setOff, m_candleSubsystem));
        m_driverJoystick.x().onTrue(Commands.runOnce(m_candleSubsystem::incrementAnimation, m_candleSubsystem));  
        m_driverJoystick.y().onTrue(Commands.runOnce(m_candleSubsystem::decrementAnimation, m_candleSubsystem));  
        m_driverJoystick.b().onTrue(new CANdlePrintCommands.PrintCurrent(m_candleSubsystem));

        //together motor and candle
        //m_driverJoystick.leftBumper().whileTrue(new ParallelCommandGroup(testmotor.motor_Move2position(),
        //m_candleSubsystem.setFireWithMotor()));
        m_driverJoystick.leftBumper().onTrue(testmotor.motor_Move2position(Constants.Motor.MotorPosition1)
                                      .andThen(m_candleSubsystem::setFire,m_candleSubsystem));

        m_driverJoystick.rightBumper().onTrue(testmotor.motor_Move2position(Constants.Motor.MotorPosition2)
                                      .andThen(m_candleSubsystem::setColorFlow,m_candleSubsystem));
    }

  }
