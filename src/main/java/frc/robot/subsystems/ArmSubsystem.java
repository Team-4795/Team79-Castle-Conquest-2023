package frc.robot.subsystems;

import javax.management.loading.PrivateClassLoader;

import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmSubsystem extends SubsystemBase {
    private PWMVictorSPX RightArmMotor = new PWMVictorSPX(3);
    private PWMVictorSPX LeftArmMotor = new PWMVictorSPX(4);

    public ArmSubsystem (){
        RightArmMotor.setInverted(true);
        this.STOP();
    }

    public void RotateForward(){
        RightArmMotor.set(0.8);
        LeftArmMotor.set(0.8);
    }
    public void RotateBackward(){
        RightArmMotor.set(-0.8);
        LeftArmMotor.set(-0.8);
    }
    public void STOP(){
        RightArmMotor.set(0);
        LeftArmMotor.set(0);
    } 
}
