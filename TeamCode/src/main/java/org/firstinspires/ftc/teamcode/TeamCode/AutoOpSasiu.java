package org.firstinspires.ftc.teamcode.TeamCode;


import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@Autonomous
@Config
public class AutoOpSasiu extends LinearOpMode {

    public static double PRELOAD_x = 14, PRELOAD_y = 0, PRELOAD_heading = 0;
    public static double SAFE_x = 25.54, SAFE_y = -13.19, SAFE_heading = 180;
    public static double SAFE2_x = 36, SAFE2_y = -23 + 2.84, SAFE2_heading = 180;
    public static double ELEM1_x = 25.54, ELEM1_y = -20, ELEM1_heading = 180;
    public static double HUMAN_x = 4, HUMAN_y = -19, HUMAN_heading = 180;
    public static double SAFE3_x = 8, SAFE3_y = -25, SAFE3_heading = 180;
    public static double SPECIMEN_x = 2.9, SPECIMEN_y = -25  , SPECIMEN_heading = 180;
    public static double SPECIMEN2_x = 3.2, SPECIMEN2_y = -27  , SPECIMEN2_heading = 180;
    public static double PRELOAD2_x = 15, PRELOAD2_y = 7, PRELOAD2_heading = 0;
    public static double ELEM2_x = 25.54, ELEM2_y = -25, ELEM2_heading = 180;
    public static double HUMAN2_x = 4, HUMAN2_y = -25, HUMAN2_heading = 180;
    public static double ELEM3_x = 18.29, ELEM3_y = -20.57, ELEM3_heading = 270;
    public static double HUMAN3_x = 7, HUMAN3_y = 7, HUMAN3_heading = 0;
    public static double PARK_x = 1.26, PARK_y = -22.24, PARK_heading = 0;
    public static double orizPosElem3 = 0.6;


    public void runOpMode() throws InterruptedException{

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        telemetry = new MultipleTelemetry(FtcDashboard.getInstance().getTelemetry(), telemetry);

        Pose2d PRELOAD = new Pose2d(PRELOAD_x, PRELOAD_y, Math.toRadians(PRELOAD_heading));
        Pose2d SAFE = new Pose2d(SAFE_x, SAFE_y, Math.toRadians(SAFE_heading));
        Pose2d SAFE2 = new Pose2d(SAFE2_x, SAFE2_y, Math.toRadians(SAFE2_heading));
        Pose2d ELEM1 = new Pose2d(ELEM1_x, ELEM1_y, Math.toRadians(ELEM1_heading));
        Pose2d HUMAN = new Pose2d(HUMAN_x, HUMAN_y, Math.toRadians(HUMAN_heading));
        Pose2d SAFE3 = new Pose2d(SAFE3_x, SAFE3_y, Math.toRadians(SAFE3_heading));
        Pose2d SPECIMEN = new Pose2d(SPECIMEN_x, SPECIMEN_y, Math.toRadians(SPECIMEN_heading));
        Pose2d PRELOAD2 = new Pose2d(PRELOAD2_x, PRELOAD2_y, Math.toRadians(PRELOAD2_heading));
        Pose2d ELEM2 = new Pose2d(ELEM2_x, ELEM2_y, Math.toRadians(ELEM2_heading));
        Pose2d HUMAN2 = new Pose2d(HUMAN2_x, HUMAN2_y, Math.toRadians(HUMAN2_heading));
        Pose2d ELEM3 = new Pose2d(ELEM3_x, ELEM3_y, Math.toRadians(ELEM3_heading));
        Pose2d HUMAN3 = new Pose2d(HUMAN3_x, HUMAN3_y, Math.toRadians(HUMAN3_heading));
        Pose2d PARK = new Pose2d(PARK_x, PARK_y, Math.toRadians(PARK_heading));
        Pose2d SPECIMEN2 = new Pose2d(SPECIMEN2_x, SPECIMEN2_y, Math.toRadians(SPECIMEN2_heading));

        Cleste cleste = new Cleste(hardwareMap);
        Joint joint = new Joint(hardwareMap);
        Vert vert = new Vert(hardwareMap);
        Oriz oriz = new Oriz(hardwareMap);
        Rotation rotatie = new Rotation(hardwareMap);
        Intake intake = new Intake(hardwareMap, vert);



        waitForStart();



        drive.followTrajectorySequenceAsync(drive.trajectorySequenceBuilder(new Pose2d(0, 0, Math.toRadians(0)))
                        .addTemporalMarker( () -> {
                            vert.goToHigh();
                        })
                        .waitSeconds(0.2)
                        .addTemporalMarker( () -> {
                            oriz.close();
                            joint.goToLevel();
                            cleste.close();
                            rotatie.paralel();
                        })
                        .waitSeconds(0.8)
                .lineToLinearHeading(PRELOAD)
                .addTemporalMarker( () -> {
                    vert.goToPoz(1175);
                })
                .waitSeconds(0.5)
                .addTemporalMarker( () -> {
                    cleste.open();
                })
                .waitSeconds(0.2)
                .addTemporalMarker( () -> {
                    vert.goDown();
                })
                        .waitSeconds(0)
                        .setReversed(true)
                .splineToLinearHeading(SAFE, 270)
                .lineToLinearHeading(ELEM1)
                .lineToLinearHeading(HUMAN)
                        .lineToLinearHeading(ELEM1)
                        .lineToLinearHeading(ELEM2)
//                        .lineToLinearHeading(HUMAN2)
//                        .lineToLinearHeading(ELEM2)
//                        .lineToLinearHeading(ELEM3)
//                        .addTemporalMarker( () -> {
//                            oriz.open();
//                            joint.goToMid();
//                            rotatie.perpendicular();
//                        })
//                        .waitSeconds(0.2)
//                        .addTemporalMarker( () -> {
//                            intake.collect();
//                        })
//                        .waitSeconds(0.2)
//                        .addTemporalMarker( () -> {
//                            oriz.close();
//                        })
//                        .lineToLinearHeading(HUMAN)
//                        .addTemporalMarker( () -> {
//                            cleste.open();
//                        })
                        .lineToLinearHeading(SAFE3)
                        .waitSeconds(0.5)
                .lineToLinearHeading(SPECIMEN)
                        .addTemporalMarker( () -> {
                            cleste.close();
                            vert.goToMid();
                        })
                        .waitSeconds(0.3)
                        .lineToLinearHeading(HUMAN3)
                        .addTemporalMarker(() -> {
                            vert.goToHigh();
                        })
                        .waitSeconds(1)
                .lineToLinearHeading(PRELOAD2)
                .addTemporalMarker( () -> {
                    vert.goToPoz(1175);
                })
                .waitSeconds(0.63)
                .addTemporalMarker( () -> {
                    cleste.open();
                })
                .waitSeconds(0.2)
                .addTemporalMarker( () -> {
                    vert.goDown();
                })
                        .lineToLinearHeading(SAFE3)
                        .waitSeconds(0.5)
                .lineToLinearHeading(SPECIMEN)
                        .addTemporalMarker( () -> {
                            cleste.close();
                            vert.goToMid();
                        })
                        .waitSeconds(0.3)
                        .lineToLinearHeading(HUMAN3)
                        .addTemporalMarker( () -> {
                            vert.goToHigh();
                        })
                        .waitSeconds(1)
                .lineToLinearHeading(PRELOAD2)
                .addTemporalMarker( () -> {
                    vert.goToPoz(1175);
                })
                .waitSeconds(0.63)
                .addTemporalMarker( () -> {
                    cleste.open();
                })
                .waitSeconds(0.2)
                .addTemporalMarker( () -> {
                    vert.goDown();
                })
//                .lineToLinearHeading(SPECIMEN)
//                .addTemporalMarker( () -> {
//                    cleste.close();
//                    vert.goToHigh();
//                })
//                .lineToLinearHeading(PRELOAD2)
//                .addTemporalMarker( () -> {
//                    vert.goToMid();
//                })
//                .waitSeconds(0.5)
//                .addTemporalMarker( () -> {
//                    cleste.open();
//                })
//                .waitSeconds(0.2)
//                .addTemporalMarker( () -> {
//                    vert.goDown();
//                })
                        .lineToLinearHeading(PARK)
                .build());

        while(opModeIsActive() && gamepad1.a == false) {
            drive.update();
            vert.update();
            intake.update();
            Pose2d poseEstimate = drive.getPoseEstimate();

            telemetry.addData("x", poseEstimate.getX());
            telemetry.addData("y", poseEstimate.getY());
            telemetry.addData("heading", poseEstimate.getHeading());
            telemetry.addData("vert pos", vert.getPosition());
            telemetry.update();
        }

    }
}

