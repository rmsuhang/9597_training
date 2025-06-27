package frc.robot.subsystems;

//import WPI packages
import  edu.wpi.first.wpilibj2.command.Command;
import  edu.wpi.first.wpilibj2.command.SubsystemBase;

// import ctre Packages
// cancoder support
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.signals.FeedbackSensorSourceValue;
import com.ctre.phoenix6.signals.SensorDirectionValue;

// TalonFX motor support
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.controls.VoltageOut;

// import my packages

// main class
public class sub_CANcoder extends SubsystemBase{
    // instantiate motor and CANcoder
    private final CANcoder m_cancoder = new CANcoder(4,"rio");
    private final TalonFX m_motor = new TalonFX(1,"rio");

    // define ways to control the motor
    private final MotionMagicVoltage m_motor_Request = new MotionMagicVoltage(0.0).withSlot(0);
    private final VoltageOut m_motor_voltageOut_Request = new VoltageOut(0.0).withOutput(0);

    // constructure function, used to configure motor and encoder, bind them then.
    public sub_CANcoder(){
        // configure motor
        var motorConfigs = new TalonFXConfiguration();
        motorConfigs.Slot0.kS = 0.2;
        motorConfigs.Slot0.kV = 0.0;
        motorConfigs.Slot0.kA = 0.0;
        motorConfigs.Slot0.kP = 3;
        motorConfigs.Slot0.kI = 0.0;
        motorConfigs.Slot0.kD = 0.0;
        motorConfigs.MotionMagic.MotionMagicAcceleration = 200;
        motorConfigs.MotionMagic.MotionMagicCruiseVelocity = 50;
        motorConfigs.MotionMagic.MotionMagicExpo_kV = 0.12;
        motorConfigs.MotionMagic.MotionMagicExpo_kV = 0.1;
        motorConfigs.MotionMagic.MotionMagicJerk = 0;
        // following configs will change the motor to use a target encoder instead of its own's.
        motorConfigs.Feedback.FeedbackRemoteSensorID = m_cancoder.getDeviceID();// bind the CAN_bus ID
        motorConfigs.Feedback.FeedbackSensorSource = FeedbackSensorSourceValue.FusedCANcoder;
        motorConfigs.Feedback.RotorToSensorRatio = 13;
        m_motor.getConfigurator().apply(motorConfigs);

        // configure cancoder
        var m_cancoder_Configuration = new CANcoderConfiguration();
        m_cancoder_Configuration.MagnetSensor.MagnetOffset = 0.642977;
        m_cancoder_Configuration.MagnetSensor.AbsoluteSensorDiscontinuityPoint = 0.5;
        m_cancoder_Configuration.MagnetSensor.SensorDirection = SensorDirectionValue.Clockwise_Positive;


        m_cancoder.getConfigurator().apply(m_cancoder_Configuration);
    }
    /** set motor position */
    public void m_setMotorPosition(double position){
        m_motor.setControl(m_motor_Request.withPosition(position));
    }
    public void m_setMotorVoltage(double voltage){
        m_motor.setControl(m_motor_voltageOut_Request.withOutput(voltage));
    }
    // commands
    public Command cmd_Motor_10(){
        return runEnd(
            () -> m_setMotorPosition(0.4),
            () -> m_setMotorPosition(0)
        );
    }
    public Command cmd_Motor_neg10(){
        return startEnd(
            () -> m_setMotorPosition(-0.4),
            () -> m_setMotorPosition(0)
        );
    }
    public Command cmd_Motor_neg(){
        return run(() -> m_setMotorPosition(1));
    }
    public Command cmd_Motor_v_out(){
        return run(() -> m_setMotorVoltage(2));
    }
    public Command cmd_Motor_v_out_release(){
        return runOnce(() -> m_setMotorVoltage(0));
    }
}
