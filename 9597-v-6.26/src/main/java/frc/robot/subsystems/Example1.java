package frc.robot.subsystems;

import com.ctre.phoenix.led.CANdle;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.controls.VelocityTorqueCurrentFOC;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Example1 extends SubsystemBase {
    private final TalonFX m_test_motor = new TalonFX(2, "rio");
    // set control mode for each motor
    
    private final VelocityTorqueCurrentFOC m_motorPositionRequest = new VelocityTorqueCurrentFOC(0.0)//MotionMagicVoltage之前
            .withSlot(0);


    /**
     * Sets the claw pitch position.
     * 
     * @param position
     */
    public void setmotormove(double velocity) {
        m_test_motor.setControl(m_motorPositionRequest.withVelocity(velocity));//之前位置是withPosition
    }


    public Example1() {
     
        var mtestmotorConfigs = new TalonFXConfiguration();

        mtestmotorConfigs.Slot0.kS = 0.18;
        mtestmotorConfigs.Slot0.kV = 0.11;
        mtestmotorConfigs.Slot0.kA = 0;
        mtestmotorConfigs.Slot0.kP = 0.3;
        mtestmotorConfigs.Slot0.kI = 0;
        mtestmotorConfigs.Slot0.kD = 0;
        mtestmotorConfigs.MotionMagic.MotionMagicAcceleration = 100; // Acceleration is around 40 rps/s
        mtestmotorConfigs.MotionMagic.MotionMagicCruiseVelocity = 200; // Unlimited cruise velocity
        mtestmotorConfigs.MotionMagic.MotionMagicExpo_kV = 0.12; // kV is around 0.12 V/rps
        mtestmotorConfigs.MotionMagic.MotionMagicExpo_kA = 0.1; // Use a slower kA of 0.1 V/(rps/s)
        mtestmotorConfigs.MotionMagic.MotionMagicJerk = 0; // Jerk is around 0


        m_test_motor.getConfigurator().apply(mtestmotorConfigs);


    }


    

    /**
     * Runs the claw wheel outtake.
     * 
     * @return
     */
    public Command motormove1() {

        return runEnd(
                () -> {setmotormove(10);},
                () ->{setmotormove(0);}
                );
    }
    public Command motormove2() {

        return runOnce(
                () -> {setmotormove(-10);
        }

        );

    }


   public double testmotorrun(){
    return m_test_motor.getVelocity().getValueAsDouble();//自己命名的电机.检测电机的位置.以Double的方法返回精度很高的数值getPosition().getValueAsDouble()
   }

}