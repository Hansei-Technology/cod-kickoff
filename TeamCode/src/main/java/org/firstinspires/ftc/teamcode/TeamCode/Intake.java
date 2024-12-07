package org.firstinspires.ftc.teamcode.TeamCode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
@Config
public class Intake {

    Cleste cleste;
    Vert vert;
    Joint joint;
    Rotation rotatie;
    ElapsedTime time;
    public boolean needToGoBack = false;

    public Intake(HardwareMap map, Vert verticala){
        cleste = new Cleste(map);
        vert = verticala;
        joint = new Joint(map);
        rotatie = new Rotation(map);
        time = new ElapsedTime();
    }

    public void resetCollect(){
        vert.goDown();
        rotatie.paralel();
        joint.goToLevel();
    }

    public void collect(){
        time.reset();
        vert.goToLow();
        cleste.close();
        needToGoBack = true;
    }


    public void update(){
        if(time.milliseconds() > 300 && needToGoBack == true){
            needToGoBack = false;
            resetCollect();
        }
    }
}
