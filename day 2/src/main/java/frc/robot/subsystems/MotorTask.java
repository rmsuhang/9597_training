// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.VelocityTorqueCurrentFOC;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class MotorTask extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  //Step 1
     private final TalonFX m_testmotor1 = new TalonFX(1, "rio");
    private final VelocityTorqueCurrentFOC m_motor_request=new VelocityTorqueCurrentFOC(0.0).withSlot(0);
  //Step 2
    public void setmotorVelocity(double velocity) {
        m_testmotor1.setControl(m_motor_request.withVelocity(velocity));
    }
    public MotorTask() {
  // This method will be called once when the command is scheduled.
 // Step 3
  var motorConfigs=new TalonFXConfiguration();

  motorConfigs.Slot0.kS = 0.18;
  motorConfigs.Slot0.kV = 0.11;
  motorConfigs.Slot0.kA = 0;
  motorConfigs.Slot0.kP = 0.3;
  motorConfigs.Slot0.kI = 0;
  motorConfigs.Slot0.kD = 0;
  motorConfigs.MotionMagic.MotionMagicAcceleration = 100; // Acceleration is around 40 rps/s
  motorConfigs.MotionMagic.MotionMagicCruiseVelocity = 200; // Unlimited cruise velocity
  motorConfigs.MotionMagic.MotionMagicExpo_kV = 0.12; // kV is around 0.12 V/rps
  motorConfigs.MotionMagic.MotionMagicExpo_kA = 0.1; // Use a slower kA of 0.1 V/(rps/s)
  motorConfigs.MotionMagic.MotionMagicJerk = 0; // Jerk is around 0


  m_testmotor1.getConfigurator().apply(motorConfigs);
  }

public Command motor_Move_PositiveCommand() {
    return runOnce(() -> setmotorVelocity(10.0));
  }
public Command motor_Move_NegtiveCommand() {
    return runOnce(() -> setmotorVelocity(-10.0));
  }






  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
