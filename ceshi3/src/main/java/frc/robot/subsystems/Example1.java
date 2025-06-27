package frc.robot.subsystems;
//一个点击两套pid 与电机电压配合控制
import com.ctre.phoenix.led.CANdle;
import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.controls.VelocityTorqueCurrentFOC;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.FeedbackSensorSourceValue;
import com.ctre.phoenix6.signals.SensorDirectionValue;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Example1 extends SubsystemBase {
    private final TalonFX m_test_motor = new TalonFX(1, "rio");
    private final CANcoder m_test_cancoder = new CANcoder(4, "rio");

    // set control mode for each motor
    private final MotionMagicVoltage m_motorPositionRequest = new MotionMagicVoltage(0.0)//MotionMagicVoltage之前
            .withSlot(0);
    private final MotionMagicVoltage m_motorPositionRequest2 = new MotionMagicVoltage(0.0)//MotionMagicVoltage之前
            .withSlot(1);

    private final VoltageOut m_motoroutRequest = new VoltageOut(0.0);//MotionMagicVoltage之前
           

    
    /**
     * Sets position.
     * or set voltage 
     * or set velocity
     * @param position
     */
    public void setmotormove(double position) {
        m_test_motor.setControl(m_motorPositionRequest.withPosition(position));//之前位置是withPosition(位置控制)。withVelocity（电压控制）
    }

    public void setmotormove2(double position) {
        m_test_motor.setControl(m_motorPositionRequest2.withPosition(position));//之前位置是withPosition(位置控制)。withVelocity（电压控制）
    }

    public void releasemotor(double Voltage) {
        m_test_motor.setControl(m_motoroutRequest.withOutput(Voltage));//之前位置是withPosition(位置控制)。withVelocity（电压控制）
    }

    public Example1() {

        //cancoder的配置
        var motorEncoderConfigs = new CANcoderConfiguration();

        motorEncoderConfigs.MagnetSensor.MagnetOffset=0.0;//使用传感器控制电机，使用
        motorEncoderConfigs.MagnetSensor.AbsoluteSensorDiscontinuityPoint=0.5;
        motorEncoderConfigs.MagnetSensor.SensorDirection=SensorDirectionValue.Clockwise_Positive;

        m_test_cancoder.getConfigurator().apply(motorEncoderConfigs);



        //motor1电机的配置
        var mtestmotorConfigs = new TalonFXConfiguration();

        mtestmotorConfigs.Slot0.kS = 0.25;
        mtestmotorConfigs.Slot0.kV = 0;
        mtestmotorConfigs.Slot0.kA = 0;
        mtestmotorConfigs.Slot0.kP = 20;
        mtestmotorConfigs.Slot0.kI = 0;
        mtestmotorConfigs.Slot0.kD = 0.5;

        ///////pid2
        mtestmotorConfigs.Slot1.kS = 0.25;
        mtestmotorConfigs.Slot1.kV = 0;
        mtestmotorConfigs.Slot1.kA = 0;
        mtestmotorConfigs.Slot1.kP = 5;
        mtestmotorConfigs.Slot1.kI = 0;
        mtestmotorConfigs.Slot1.kD = 0.5;

        mtestmotorConfigs.MotionMagic.MotionMagicAcceleration = 100; // Acceleration is around 40 rps/s
        mtestmotorConfigs.MotionMagic.MotionMagicCruiseVelocity = 200; // Unlimited cruise velocity
        mtestmotorConfigs.MotionMagic.MotionMagicExpo_kV = 0.12; // kV is around 0.12 V/rps
        mtestmotorConfigs.MotionMagic.MotionMagicExpo_kA = 0.1; // Use a slower kA of 0.1 V/(rps/s)
        mtestmotorConfigs.MotionMagic.MotionMagicJerk = 0; // Jerk is around 0

        mtestmotorConfigs.Feedback.FeedbackRemoteSensorID=m_test_cancoder.getDeviceID();//将cancoder的id返回到电机进行控制,绑定
        mtestmotorConfigs.Feedback.FeedbackSensorSource = FeedbackSensorSourceValue.FusedCANcoder;
        mtestmotorConfigs.Feedback.RotorToSensorRatio = 13; //轮子转向的传动比

        m_test_motor.getConfigurator().apply(mtestmotorConfigs);



    }


    

    /**
     * Runs the claw wheel outtake.
     * 
     * @return
     */
    public Command motorTurn1() {

        return run(
                () -> {
               
                    setmotormove(0);
                }
                );
    }
    public Command motorTurn2() {

        return run(
                () -> {
                    
                    setmotormove2(2);
        }

        );  
    }

    public Command motorvoltage() {

        return run(
                () -> {
                    
                    releasemotor(0.5);
        }

        );  
    }

   public double testmotorrun(){
    return m_test_motor.getVelocity().getValueAsDouble();//自己命名的电机.检测电机的位置.以Double的方法返回精度很高的数值getPosition().getValueAsDouble()
   }

}