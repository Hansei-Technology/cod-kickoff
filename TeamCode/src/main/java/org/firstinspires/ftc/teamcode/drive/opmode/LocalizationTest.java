package org.firstinspires.ftc.teamcode.drive.opmode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.TeamCode.Cleste;
import org.firstinspires.ftc.teamcode.TeamCode.Intake;
import org.firstinspires.ftc.teamcode.TeamCode.Joint;
import org.firstinspires.ftc.teamcode.TeamCode.Oriz;
import org.firstinspires.ftc.teamcode.TeamCode.Rotation;
import org.firstinspires.ftc.teamcode.TeamCode.Sasiu;
import org.firstinspires.ftc.teamcode.TeamCode.Vert;
import org.firstinspires.ftc.teamcode.Utils.StickyGamepad;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

/**
 * This is a simple teleop routine for testing localization. Drive the robot around like a normal
 * teleop routine and make sure the robot's estimated pose matches the robot's actual pose (slight
 * errors are not out of the ordinary, especially with sudden drive motions). The goal of this
 * exercise is to ascertain whether the localizer has been configured properly (note: the pure
 * encoder localizer heading may be significantly off if the track width has not been tuned).
 */
@TeleOp(group = "drive")
public class LocalizationTest extends LinearOpMode {

    Joint joint;
    Cleste cleste;
    Vert vert;
    Oriz oriz;
    Rotation rotatie;
    StickyGamepad sticky1;
    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        cleste = new Cleste(hardwareMap);
        joint = new Joint(hardwareMap);
        oriz = new Oriz(hardwareMap);
        vert = new Vert(hardwareMap);
        rotatie = new Rotation(hardwareMap);
        sticky1 = new StickyGamepad(gamepad1, this);

        drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        cleste.close();
        joint.goToLevel();
        rotatie.paralel();
        oriz.close();
        vert.goDown();

        waitForStart();


        while (!isStopRequested()) {
            vert.update();
            sticky1.update();

            drive.setWeightedDrivePower(
                    new Pose2d(
                            -gamepad1.left_stick_y,
                            -gamepad1.left_stick_x,
                            -gamepad1.right_stick_x
                    )
            );

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

            vert.update();

            drive.update();

            Pose2d poseEstimate = drive.getPoseEstimate();
            telemetry.addData("x", poseEstimate.getX());
            telemetry.addData("y", poseEstimate.getY());
            telemetry.addData("heading", poseEstimate.getHeading());
            telemetry.update();
        }
    }
}
