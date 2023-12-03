package frc.robot.subsystems;

import javax.management.loading.PrivateClassLoader;

import edu.wpi.first.wpilibj.motorcontrol.PWMTalonSRX;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmSubsystem extends SubsystemBase {
    private PWMTalonSRX RightArmMotor = new PWMTalonSRX(4);
    private PWMTalonSRX LeftArmMotor = new PWMTalonSRX(5);

    public ArmSubsystem (){
        RightArmMotor.setInverted(true);
        this.STOP();
    }

    public void RotateForward(){
        RightArmMotor.set(0.7);
        LeftArmMotor.set(0.7);
    }
    public void RotateBackward(){
        RightArmMotor.set(-0.7);
        LeftArmMotor.set(-0.7);
    }
    public void STOP(){
        RightArmMotor.set(0);
        LeftArmMotor.set(0);
    } 
}
