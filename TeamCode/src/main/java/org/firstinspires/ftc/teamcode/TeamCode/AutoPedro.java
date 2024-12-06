package org.firstinspires.ftc.teamcode.TeamCode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.pedroPathing.follower.Follower;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.BezierLine;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.Path;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.PathChain;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.Point;

@Config
@Autonomous
public class AutoPedro extends OpMode {

    Cleste cleste;
    Joint joint;
    Vert vert;
    Oriz oriz;
    Sasiu sasiu;
    private Telemetry telemetry;
    private Follower follower;
    private Path goToPreload;
    public static double PRELOAD_x = 14, PRELOAD_y = 0 + 2.84, PRELOAD_heading = 0;
    public static double SAFE_x = 23, SAFE_y = -18 + 2.84, SAFE_heading = 180;
    public static double SAFE2_x = 36, SAFE2_y = -23 + 2.84, SAFE2_heading = 180;
    public static double ELEM1_x = 23.7, ELEM1_y = -20.65 + 2.84, ELEM1_heading = 180;
    public static double HUMAN_x = 3.33, HUMAN_y = -20.65 + 2.84, HUMAN_heading = 180;
    public static double SAFE3_x = 8.8, SAFE3_y = -19.75 + 2.84, SAFE3_heading = 180;
    public static double SPECIMEN_x = 2.94, SPECIMIEN_y = -19.75 + 2.84  , SPECIMEN_heading = 180;
    public static double PRELOAD2_x = 14, PRELOAD2_y = 0 + 2.84, PRELOAD2_heading = 0;
    public static double ELEM2_x = 23.7, ELEM2_y = -25.8 + 2.84, ELEM2_heading = 180;
    public static double HUMAN2_x = 3.33, HUMAN2_y = -25.8 + 2.84, HUMAN2_heading = 180;
    public static double ELEM3_x = 23.7, ELEM3_y = -27.4 + 2.84, ELEM3_heading = 270;
    public static double HUMAN3_x = 3.33, HUMAN3_y = -27.4 + 2.84, HUMAN3_heading = 270;

    PathChain goTo1Sample;
    PathChain goTo2Sample;
    PathChain goTo3Sample;
    PathChain goTo1Human;
    PathChain goTo2Human;
    PathChain goTo3Human;
    PathChain goToPark;

    private enum STATES {
        PRELOAD,
        MOVING,
        
    }

    @Override
    public void init() {
        follower = new Follower(hardwareMap);

    }

    @Override
    public void loop() {



    }
}

