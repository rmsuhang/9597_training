package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix6.controls.MotionMagicVoltage;

public class MotorTask2 extends SubsystemBase {
    /** Creates a new ExampleSubsystem. */
    //Step 1
    private final TalonFX m_testmotor2 = new TalonFX(2, "rio");
    private final MotionMagicVoltage m_motor2_request =new MotionMagicVoltage(0.0).withSlot(0);
    //Step 2
      public void setmotorposition(double position) {
          m_testmotor2.setControl(m_motor2_request.withPosition(position));
      }
      public MotorTask2() {
        // This method will be called once when the command is scheduled.
       // Step 3
        var motor1Configs=new TalonFXConfiguration();
      
        motor1Configs.Slot0.kS = 0.2;
        motor1Configs.Slot0.kV = 0;
        motor1Configs.Slot0.kA = 0;
        motor1Configs.Slot0.kP = 3;
        motor1Configs.Slot0.kI = 0;
        motor1Configs.Slot0.kD = 0;
        motor1Configs.MotionMagic.MotionMagicAcceleration = 100; // Acceleration is around 40 rps/s
        motor1Configs.MotionMagic.MotionMagicCruiseVelocity = 200; // Unlimited cruise velocity
        motor1Configs.MotionMagic.MotionMagicExpo_kV = 0.12; // kV is around 0.12 V/rps
        motor1Configs.MotionMagic.MotionMagicExpo_kA = 0.1; // Use a slower kA of 0.1 V/(rps/s)
        motor1Configs.MotionMagic.MotionMagicJerk = 0; // Jerk is around 0
      
      
        m_testmotor2.getConfigurator().apply(motor1Configs);
        }
    public Command motor_Move_HighCommand() {
        return run(() -> setmotorposition(10.0));
    }
    public Command motor_Move_LowCommand() {
        return run(() -> setmotorposition(0.0));
    }
}