package org.firstinspires.ftc.teamcode.TeamCode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class Oriz {

    Servo motor;
    public static double max = 0.9;
    public static double min = 0.3;
    public static double viteza = 0.001;

    public Oriz(HardwareMap map){
        motor = map.get(Servo.class, "s1");
    }

    public void goToPoz(double poz) {
        motor.setPosition(poz);
    }

    public void open(){
        motor.setPosition(max);
    }

    public void close(){
        motor.setPosition(min);
    }

}
