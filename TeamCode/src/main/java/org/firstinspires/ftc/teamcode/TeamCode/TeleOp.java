package org.firstinspires.ftc.teamcode.TeamCode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Utils.StickyGamepad;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class TeleOp extends LinearOpMode {
    Cleste cleste;
    Joint joint;
    Sasiu sasiu;
    Oriz oriz;
    Vert vert;
    StickyGamepad sticky1;
    Rotation rotatie;
    Intake intake;




    @Override
    public void runOpMode() throws InterruptedException {
        cleste = new Cleste(hardwareMap);
        joint = new Joint(hardwareMap);
        sasiu = new Sasiu(hardwareMap);
        oriz = new Oriz(hardwareMap);
        vert = new Vert(hardwareMap);
        sticky1 = new StickyGamepad(gamepad1, this);
        rotatie = new Rotation(hardwareMap);
        intake = new Intake(hardwareMap, vert);




        waitForStart();

        cleste.close();
        joint.goToLevel();
        rotatie.paralel();
        oriz.close();
        vert.goDown();

        while(opModeIsActive()){



            sasiu.move(gamepad1);
            vert.update();
            sticky1.update();

            if(gamepad1.a){
                vert.goDown();
                oriz.close();
                joint.goToLevel();
            }

            if(gamepad1.b){
                vert.goToMid();
                oriz.close();
                joint.goToLevel();
            }

            if(gamepad1.y){
                vert.goToHigh();
                oriz.close();
                joint.goToLevel();
            }

            if(gamepad1.x){
                intake.collect();
            }

            if(gamepad1.right_stick_button){
                intake.resetCollect();
            }

            if(gamepad1.dpad_right){
                vert.goToCapuUrsului();
                oriz.close();
                joint.goToLevel();
            }

            if(gamepad1.dpad_down){
                joint.goToMid();
            }

            if(gamepad1.right_trigger > 0.1){
                oriz.open();
            }

            if(gamepad1.left_trigger > 0.1){
                oriz.close();
            }

            if(sticky1.right_bumper){
                joint.toggle();
            }

            if(sticky1.left_bumper){
                cleste.toggle();
            }

            if(sticky1.dpad_up){
                rotatie.toggle();
            }

            intake.update();
            vert.update();
        }
    }
}
