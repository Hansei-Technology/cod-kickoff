package org.firstinspires.ftc.teamcode.util;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.TeamCode.Joint;
import org.firstinspires.ftc.teamcode.TeamCode.Oriz;

@TeleOp
@Config
public class OrizTest extends LinearOpMode {

    Oriz oriz;
    Joint joint;
    public static double pos = 0;
    @Override
    public void runOpMode() throws InterruptedException {
        oriz = new Oriz(hardwareMap);
        joint = new Joint(hardwareMap);

        waitForStart();

        joint.goToLevel();

        while(opModeIsActive()){
            oriz.goToPoz(pos);
        }
    }
}
