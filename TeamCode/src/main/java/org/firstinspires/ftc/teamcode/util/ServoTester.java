package org.firstinspires.ftc.teamcode.util;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.TeamCode.Joint;

@Config
@TeleOp
public class ServoTester extends LinearOpMode {

    Servo servo;
    public static String port = "";
    public static double pos = 0.5;
    public static boolean reverse = false;

    Joint joint;

    @Override
    public void runOpMode() throws InterruptedException {
        servo = hardwareMap.get(Servo.class, port);
        if(reverse == true){
            servo.setDirection(Servo.Direction.REVERSE);
        }
        waitForStart();
        while (opModeIsActive()){
            servo.setPosition(pos);
        }
    }
}
