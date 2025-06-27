package frc.robot.subsystems;

//import com.ctre.phoenix6.SignalLogger;
//import com.ctre.phoenix6.configs.CANcoderConfiguration;
//import com.ctre.phoenix6.configs.CANrangeConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
//import com.ctre.phoenix6.controls.VelocityTorqueCurrentFOC;
//import com.ctre.phoenix6.controls.VoltageOut;
//import com.ctre.phoenix6.hardware.CANcoder;
//import com.ctre.phoenix6.hardware.CANrange;
import com.ctre.phoenix6.hardware.TalonFX;
//import com.ctre.phoenix6.signals.FeedbackSensorSourceValue;
//import com.ctre.phoenix6.signals.GravityTypeValue;
//import com.ctre.phoenix6.signals.InvertedValue;
//import com.ctre.phoenix6.signals.SensorDirectionValue;

//import static edu.wpi.first.units.Units.Second;
//import static edu.wpi.first.units.Units.Volts;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
//import edu.wpi.first.wpilibj2.command.WaitCommand;
//import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine;
import frc.robot.Constants;

public class ExampleSubsystem extends SubsystemBase {
    private final TalonFX m_motor_1 = new TalonFX(Constants.MOTOR.MOTOR_1_ID, "rio");
    private final TalonFX m_motor_2 = new TalonFX(Constants.MOTOR.MOTOR_2_ID, "rio");
    // set control mode for each motor
    private final MotionMagicVoltage m_testMotorPositionRequest = new MotionMagicVoltage(0.0)
            .withSlot(0);
     
 //  private SysIdRoutine m_sysIdRoutineToApply = m_sysIdRoutineClawPitch;

    public ExampleSubsystem() {
        var m_test_motorConfigs = new TalonFXConfiguration();

        m_test_motorConfigs.Slot0.kS = 0.2;
        m_test_motorConfigs.Slot0.kV = 0.0;
        m_test_motorConfigs.Slot0.kA = 0;
        m_test_motorConfigs.Slot0.kP = 3;
        m_test_motorConfigs.Slot0.kI = 0;
        m_test_motorConfigs.Slot0.kD = 0;
        m_test_motorConfigs.MotionMagic.MotionMagicAcceleration = 100; // Acceleration is around 40 rps/s
        m_test_motorConfigs.MotionMagic.MotionMagicCruiseVelocity = 200; // Unlimited cruise velocity
        m_test_motorConfigs.MotionMagic.MotionMagicExpo_kV = 0.12; // kV is around 0.12 V/rps
        m_test_motorConfigs.MotionMagic.MotionMagicExpo_kA = 0.1; // Use a slower kA of 0.1 V/(rps/s)
        m_test_motorConfigs.MotionMagic.MotionMagicJerk = 0; // Jerk is around 0

        m_motor_1.getConfigurator().apply(m_test_motorConfigs);
        m_motor_2.getConfigurator().apply(m_test_motorConfigs);
    }

    /** 
     * Sets the claw pitch position.
     * 
     * @param position
     */
    public void m_setMotorPosition(double position) {
        m_motor_1.setControl(m_testMotorPositionRequest.withPosition(position));
        m_motor_2.setControl(m_testMotorPositionRequest.withPosition(position));
    }

    /**
     * Set the motor to target position
     * 
     * @return
     */
    public Command motorSetPosition() {
        return startEnd(
            () -> { m_setMotorPosition(50); },
            () -> { m_setMotorPosition(0);}
        );

    }

    public Command cmd_motor_SetPositon(){
        return run(
            () -> {
                m_setMotorPosition(Constants.MOTOR.MOTOR_POSITION_1);
            }).until(
            ()->{
                return (Math.abs(getMotorPosition()-Constants.MOTOR.MOTOR_POSITION_1) < Constants.ACCEPTED_ERROR.TEST_MOTOR);
            }
        );
    }
    public double getMotorPosition(){
        return m_motor_1.getPosition().getValueAsDouble();
    }
   }