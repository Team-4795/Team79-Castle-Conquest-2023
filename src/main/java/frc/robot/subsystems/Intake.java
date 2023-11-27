package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase{
    private PWMVictorSPX coolspin = new PWMVictorSPX(5);
  
    public Intake (){
        this.nocoolspin();
    }

    public void spinin(){
        coolspin.set(.8);
    }
    public void spinout(){
        coolspin.set(-.8);
    }
    public void nocoolspin(){
        coolspin.set(0);
    }
}