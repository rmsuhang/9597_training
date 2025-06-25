package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

public class Example extends SubsystemBase {

    private final TalonFX m_test_motor = new TalonFX(Constants.Motor.Motorid, "rio");

    // set control mode for each motor
    private final MotionMagicVoltage m_motorPositionRequest = new MotionMagicVoltage(0.0)
            .withSlot(0);

    public Example() {

        // test motor configs
        var testMotorConfigs = new TalonFXConfiguration();

        testMotorConfigs.Slot0.kS = 4.7;
        testMotorConfigs.Slot0.kV = 0.09;
        testMotorConfigs.Slot0.kA = 1;
        testMotorConfigs.Slot0.kP = 4;
        testMotorConfigs.Slot0.kI = 0;
        testMotorConfigs.Slot0.kD = 0.25;
        testMotorConfigs.MotionMagic.MotionMagicAcceleration = 100; // Acceleration is around 40 rps/s
        testMotorConfigs.MotionMagic.MotionMagicCruiseVelocity = 200; // Unlimited cruise velocity
        testMotorConfigs.MotionMagic.MotionMagicExpo_kV = 0.12; // kV is around 0.12 V/rps
        testMotorConfigs.MotionMagic.MotionMagicExpo_kA = 0.1; // Use a slower kA of 0.1 V/(rps/s)
        testMotorConfigs.MotionMagic.MotionMagicJerk = 0; // Jerk is around 0

        m_test_motor.getConfigurator().apply(testMotorConfigs);


    }

    
    /**
     * Sets the motor position.
     * 
     * @param position
     */
    public void setMotorPosition(double position) {
        m_test_motor.setControl(m_motorPositionRequest.withPosition(position));
    }


    /**
     * Set the motor to a specific position.
     * 
     * @return
     */
    public Command motor_Move2position() {

        return startEnd(
                () ->{
                    setMotorPosition(50);
                },
                () ->{
                    setMotorPosition(0);
                }

        );

    }

 
}