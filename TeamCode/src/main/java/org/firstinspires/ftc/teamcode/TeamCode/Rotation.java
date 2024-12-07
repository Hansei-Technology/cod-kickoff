package org.firstinspires.ftc.teamcode.TeamCode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Rotation {

    Servo servo;

    public static double paralel = 0.47;
    public static double perpendicular = 0.77;

    public Rotation(HardwareMap map){
        servo = map.get(Servo.class, "s0");
    }

    public void paralel(){
        servo.setPosition(paralel);
    }

    public void perpendicular(){
        servo.setPosition(perpendicular);
    }

    public enum States{
        PAR,
        PERP
    }

    States state = States.PAR;

    public void toggle(){
        if(state == States.PAR){
            state = States.PERP;
            servo.setPosition(perpendicular);
        }
        else{
            state = States.PAR;
            servo.setPosition(paralel);
        }
    }


}
