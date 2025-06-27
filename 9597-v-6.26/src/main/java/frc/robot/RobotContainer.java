// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot;

import static edu.wpi.first.units.Units.Newton;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.commands.CANdleConfigCommands;
import frc.robot.commands.CANdlePrintCommands;
import frc.robot.subsystems.CANdleSystem;
import frc.robot.subsystems.Example1;
/////////////////////////////
import edu.wpi.first.wpilibj2.command.RunCommand;
//import frc.robot.commands.AutoAlignToAprilTagCommand;

//import frc.robot.Commands.Getballcommand;


public class RobotContainer {

    private final CommandXboxController m_driverJoystick = new CommandXboxController(0);//two xbox controller
    
    //private final CommandXboxController m_driverJoystick2= new CommandXboxController(0);//Constants.JoystickId
    //subsystem
    public final Example1 motor = new Example1();
    //public final CANdleSystem Led = new CANdleSystem();
    public final CANdleSystem m_candleSubsystem = new CANdleSystem();

    public RobotContainer() {

        configureBindings();
       
        
    }

    private void configureBindings() {

        //set the direction of heading 
        //motor
        //m_driverJoystick.leftBumper().whileTrue();
        //m_driverJoystick.leftBumper().onTrue(motor.motormove1()
        //                            .andThen(m_candleSubsystem::red,m_candleSubsystem));
                        
        //m_driverJoystick.rightBumper().onTrue(motor.motormove2()
        //                        .andThen(m_candleSubsystem::blue,m_candleSubsystem));

        //m_driverJoystick.a().onTrue(Commands.runOnce(m_candleSubsystem::setOff, m_candleSubsystem));
        //m_driverJoystick.b().onTrue(Commands.runOnce(m_candleSubsystem::red, m_candleSubsystem));
        m_driverJoystick.b().onTrue(Commands.runOnce(m_candleSubsystem::red, m_candleSubsystem));
        m_driverJoystick.a().onTrue(Commands.runOnce(m_candleSubsystem::blue, m_candleSubsystem));
        
        
        m_driverJoystick.b().onTrue(motor.motormove1());
        m_driverJoystick.a().onTrue(motor.motormove2());
    }
                                
}
