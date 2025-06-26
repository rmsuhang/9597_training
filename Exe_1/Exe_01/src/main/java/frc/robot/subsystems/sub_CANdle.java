//frc package
package frc.robot.subsystems;

// my pakcage
import frc.robot.Configs;

// ctre package
import com.ctre.phoenix.led.*;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class sub_CANdle extends SubsystemBase {
    private final CANdle m_candle = new CANdle(Configs.subsys.CANDLE.CAN_ID,Configs.subsys.CANDLE.CAN_BUS);
    private final int LedCount = Configs.subsys.CANDLE.LED_COUNTS;
    private Animation curAnimation = null;

    public void setFire(){ 
        m_candle.setLEDs(0,0,255,0,0,LedCount);
        curAnimation = null;
    }

    public void setRainbow(){ curAnimation = new RainbowAnimation(1,0.1,LedCount);}

    public void setAll(){ 
        m_candle.setLEDs(0,0,0);
        curAnimation = null;
    }

    @Override
    public void periodic(){
        m_candle.animate(curAnimation);
    }
}
