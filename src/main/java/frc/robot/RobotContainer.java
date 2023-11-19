// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.RomiDrivetrain;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final RomiDrivetrain m_romiDrivetrain = new RomiDrivetrain();
  
  private final ArmSubsystem m_Arm = new ArmSubsystem();

  private final Intake m_Intake = new Intake();

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_romiDrivetrain);

  private CommandXboxController m_controller = new CommandXboxController(0);

  private Command auto = new RunCommand(() -> m_romiDrivetrain.arcadeDrive(1,0), m_romiDrivetrain).withTimeout(5).alongWith(new RunCommand(m_Arm::RotateForward).withTimeout(1)).andThen(new RunCommand(m_Intake::spinout)).withTimeout(2).andThen(new RunCommand(()-> m_romiDrivetrain.arcadeDrive(-1, 0))).withTimeout(2).alongWith(new RunCommand(m_Arm::RotateBackward).withTimeout(1)).andThen(new RunCommand(()-> m_romiDrivetrain.arcadeDrive(0, 1))).withTimeout(.5).andThen(new RunCommand(()-> m_romiDrivetrain.arcadeDrive(1, 0))).withTimeout(3);

//All time in seconds MUST be changed after testing, they are just place holders


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    m_romiDrivetrain.setDefaultCommand(
        new RunCommand(() -> m_romiDrivetrain.arcadeDrive(m_controller.getLeftY(), m_controller.getLeftX()), m_romiDrivetrain)
    );

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link edu.wpi.first.wpilibj.GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    m_controller.a().onTrue(Commands.runOnce(() -> {m_Arm.RotateForward();}, m_Arm));
    m_controller.y().onTrue(Commands.runOnce(()->{m_Arm.RotateBackward();}, m_Arm));
    m_controller.x().onTrue(Commands.runOnce(()->{m_Intake.spinin();}, m_Intake));
    m_controller.b().onTrue(Commands.runOnce(()->{m_Intake.spinout();}, m_Intake));
    m_controller.y().onFalse(Commands.runOnce(()->{m_Arm.STOP();}, m_Arm));
    m_controller.a().onFalse(Commands.runOnce(() -> {m_Arm.STOP();}, m_Arm));
    m_controller.x().onFalse(Commands.runOnce(()->{m_Intake.nocoolspin();}, m_Intake));
    m_controller.b().onFalse(Commands.runOnce(()->{m_Intake.nocoolspin();}, m_Intake));


  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    
    return auto;

  
  }
}
