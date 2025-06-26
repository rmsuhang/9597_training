package frc.robot;

public class Configs {
    public static class Sys{
        public static final String RIO_CAN_BUS = "rio";

    }
    public static class subsys{
        public static class MOTOR_1{
            public static int CAN_ID = 1;
            public static String CAN_BUS = Sys.RIO_CAN_BUS;
        }
        public static class CANDLE {
            public static int CAN_ID = 3;
            public static String CAN_BUS = Sys.RIO_CAN_BUS;
            public static int LED_COUNTS = 16;
        }
    }
    
}
