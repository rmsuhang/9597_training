// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.VelocityTorqueCurrentFOC;

public class Motor extends SubsystemBase {

  //step1
  private final TalonFX m_test_motor = new TalonFX(1, "rio");
  private final VelocityTorqueCurrentFOC m_test_motor_request = new VelocityTorqueCurrentFOC(0.0).withSlot(0);

  //setp2
  public void setmotorVelocity(double velocity) {
    m_test_motor.setControl(m_test_motor_request.withVelocity(velocity));
  }

  //构造函数
  public Motor() {

      //step3
      var motorConfigs = new TalonFXConfiguration();

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

      m_test_motor.getConfigurator().apply(motorConfigs);

  }

  public Command Motor_Move_Positive(){
    return runOnce(()->{
      setmotorVelocity(10); // Set the motor to move at 1000 units per second
    });
  }

  public Command Motor_Move_Negetive(){
    return runOnce(()->{
      setmotorVelocity(-10); // Set the motor to move at 1000 units per second
    });
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
