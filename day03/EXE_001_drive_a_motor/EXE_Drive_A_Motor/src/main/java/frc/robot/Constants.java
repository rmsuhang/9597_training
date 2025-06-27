// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }
  public static class ACCEPTED_ERROR{
    public static final double TEST_MOTOR = 2;
  }
  public static class MOTOR{
      public static final int MOTOR_1_ID = 1;
      public static final int MOTOR_2_ID = 2;
      public static final double MOTOR_POSITION_1 = 50.0;
  }
  public static class CANDLE{
      public static final int CANdleID = 3;
      public static final int JoystickId = 0;
      public static final int IncrementAnimButton = XboxController.Button.kRightBumper.value;
      public static final int DecrementAnimButton = XboxController.Button.kLeftBumper.value;
      public static final int BlockButton = XboxController.Button.kStart.value;
      public static final int MaxBrightnessAngle = 90;
      public static final int MidBrightnessAngle = 180;
      public static final int ZeroBrightnessAngle = 270;
      public static final int VbatButton = XboxController.Button.kA.value;
      public static final int V5Button = XboxController.Button.kB.value;
      public static final int CurrentButton = XboxController.Button.kX.value;
      public static final int TemperatureButton = XboxController.Button.kY.value;
  }
}
