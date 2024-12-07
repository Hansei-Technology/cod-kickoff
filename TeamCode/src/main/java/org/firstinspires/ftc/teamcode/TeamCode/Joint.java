package org.firstinspires.ftc.teamcode.TeamCode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Joint {

    public static double downPos = 0.6;
    public static double midPos = 0.7;
    public static double levelPos = 0.9;
    Servo servo;

    public Joint(HardwareMap map){
        servo = map.get(Servo.class, "s4");
        servo.setDirection(Servo.Direction.REVERSE);
    }

    public enum Status{
        LEVEL, DOWN, MID
    }

    Status status = Status.LEVEL;

    public void toggle(){
        if(status == Status.LEVEL){
            status = Status.DOWN;
            servo.setPosition(downPos);
        }
        else if(status == Status.DOWN || status == Status.MID){
            status = Status.LEVEL;
            servo.setPosition(levelPos);
        }
    }

    public void goDown(){
        servo.setPosition(downPos);
        status = Status.DOWN;
    }

    public void goToLevel(){
        servo.setPosition(levelPos);
        status = Status.LEVEL;
    }

    public void goToMid(){
        servo.setPosition(midPos);
        status = Status.MID;
    }
}
