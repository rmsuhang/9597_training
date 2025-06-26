package frc.robot.subsystems;

//wpi package
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj2.command.Commands;

//ctre package
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVelocityTorqueCurrentFOC;
import com.ctre.phoenix6.controls.VelocityTorqueCurrentFOC;
import com.ctre.phoenix6.hardware.TalonFX;

// my package
import frc.robot.Configs;

public class sub_motorControl_VTC extends SubsystemBase{
    private final TalonFX m_motor_1 = new TalonFX(Configs.subsys.MOTOR_1.CAN_ID,Configs.subsys.MOTOR_1.CAN_BUS);
    private final VelocityTorqueCurrentFOC m_motor_1_VelocityRequest = new VelocityTorqueCurrentFOC(0.0).withSlot(0);

    public sub_motorControl_VTC(){
        var m_motor_1_Configs = new TalonFXConfiguration();
        m_motor_1_Configs.Slot0.kS = 0.18;
        m_motor_1_Configs.Slot0.kV = 0.11;
        m_motor_1_Configs.Slot0.kA = 0.0;
        m_motor_1_Configs.Slot0.kP = 0.3;
        m_motor_1_Configs.Slot0.kI = 0.0;
        m_motor_1_Configs.Slot0.kD = 0.0;
        m_motor_1_Configs.MotionMagic.MotionMagicAcceleration = 100;
        m_motor_1_Configs.MotionMagic.MotionMagicCruiseVelocity = 200;
        m_motor_1_Configs.MotionMagic.MotionMagicExpo_kV = 0.12; 
        m_motor_1_Configs.MotionMagic.MotionMagicExpo_kA = 0;
        m_motor_1_Configs.MotionMagic.MotionMagicJerk = 0;

        m_motor_1.getConfigurator().apply(m_motor_1_Configs);
    }

    /**
     * Command: set the motor to velocity of 10
     * @param velocity
     */
    public Command cmd_motor_1_setVelocity_10(){
        return startEnd(
            ()->{m_motor_1_setVelocity(10);},
            ()->{m_motor_1_setVelocity(0);}
        );
    }
    /**
     * Command: set the motor to velocity of -10
     * @param velocity
     */
    public Command cmd_motor_1_setVelocity_neg10(){
        return startEnd(
            ()->{m_motor_1_setVelocity(-10);},
            ()->{m_motor_1_setVelocity(0);}
        );
    }
    /** 
     * method: set the motor to a target velocity(speed)
     * @param double velocity: target velocity
     * @return no return
     */
    public void m_motor_1_setVelocity(double velocity){
        m_motor_1.setControl(m_motor_1_VelocityRequest.withVelocity(velocity));
    }
    
}
