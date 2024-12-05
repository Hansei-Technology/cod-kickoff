package org.firstinspires.ftc.teamcode.TeamCode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.teamcode.Utils.PIDController;

@Config
public class Vert {
    public DcMotorEx left;
    public DcMotorEx right;
    public static double kp = 0.0150, kd = 0, ki = 0;
    PIDController pidController = new PIDController(kp, kd, ki);
    public static double magicPOWER = -0.4;
    public int position;
    public static int MAX_POS = 680;
    public static int HIGH_POS = 2280;
    public static int LOW_POS = 0;
    public static int MID_POS = 1500;
    public static int COS_POS = 2700;
    public boolean pidON = true;
    public static int ursu = 300;

    public enum States {
        RETRACT_PID,
        RETRACT_MAGIC,
        EXTENDED,
        RETRACTED
    }

    public States currentState = States.EXTENDED;

    ElapsedTime timer;
    public static int time_for_MAGIC = 1000;

    public Vert(HardwareMap map) {
        left = map.get(DcMotorEx.class, "m0e");
        right = map.get(DcMotorEx.class, "m1e");

        left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        right.setDirection(DcMotorSimple.Direction.REVERSE);

        left.setCurrentAlert(4, CurrentUnit.AMPS);
        right.setCurrentAlert(4, CurrentUnit.AMPS);

        pidController.targetValue = 0;

        timer = new ElapsedTime();
    }

    public void setPower(double power) {
        if(power > 0.1 || power < -0.1) {
            pidON = false;
            if(position < MAX_POS - 50 && power > 0) {
                left.setPower(power);
                right.setPower(power);
                currentState = States.EXTENDED;
            }
            if(position > 10 && power < 0) {
                left.setPower(power);
                right.setPower(power);
            }
        }
    }


    public void goDown() {
        pidON = true;
        pidController.targetValue = 0;
        currentState = States.RETRACT_PID;
    }

    public void goDownTillMotorOverCurrent() {
        timer.reset();
        left.setPower(magicPOWER);
        right.setPower(magicPOWER);
        currentState = States.RETRACT_MAGIC;
        pidON = false;
    }

    public void update() {
        if(pidON)
        {

            if (kp != pidController.p) pidController.p = kp;
            if (ki != pidController.p) pidController.i = ki;
            if (kd != pidController.p) pidController.d = kd;

            position = getPosition();


            switch (currentState)
            {
                case RETRACT_PID:
                {
                    pidON = true;
                    if(position <= 5) currentState = States.RETRACTED;
                    break;
                }
                case RETRACT_MAGIC:
                {
                    if(left.isOverCurrent() || right.isOverCurrent() || timer.milliseconds() > time_for_MAGIC)
                    {
                        ResetEncoders();
                        pidController.targetValue = 0;
                        pidON = true;
                        currentState = States.RETRACTED;
                    }
                    break;
                }
            }

            double powerExtendo = pidController.update(position);
            left.setPower(powerExtendo);
            right.setPower(powerExtendo);
        }
    }

    int getPosition() {
        return right.getCurrentPosition();
    }

    public void ResetEncoders() {
        left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void goToPoz (int poz){
        pidON = true;
        pidController.targetValue = poz;
        currentState = States.EXTENDED;
    }

    public void goToMid () {
        goToPoz(MID_POS);
    }

    public void goToCapuUrsului(){
        goToPoz(ursu);
    }

    public void goToLow () {
        goToPoz(LOW_POS);
    }

    public void goToHigh(){
        goToPoz(HIGH_POS);
    }
    public void goToCos(){
        goToPoz(COS_POS);
    }

    public void setRawPower(double power){
        left.setPower(power);
        right.setPower(power);
    }

    public void runToPos(int pos) {
        left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        left.setPower(0.4);
        right.setPower(0.4);
        left.setTargetPosition(pos);
        right.setTargetPosition(pos);
        left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
}