// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import edu.wpi.first.wpilibj.DataLogManager;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

public class AlgaeIntake extends SubsystemBase {
  /** Creates a new AlgaeIntake. */
  private SparkMax algaeIntakeMotor =
      new SparkMax(IntakeConstants.algaeIntakeMotorID, MotorType.kBrushless);

  private RelativeEncoder AlgaeIntake = algaeIntakeMotor.getEncoder();

  public AlgaeIntake() {
    DataLogManager.log("Configuring Intake");
    SparkMaxConfig algaeConfig = new SparkMaxConfig();
    algaeConfig
        .inverted(true)
        .smartCurrentLimit(IntakeConstants.intakeCurrentLimit)
        .secondaryCurrentLimit(IntakeConstants.algaeIntakeShutoffCurrentLimit)
        .idleMode(IdleMode.kBrake);
  }

  public void feedtoproccessor() {
    algaeIntakeMotor.set(1.0);
  }

  public void stopintakealgae() {
    algaeIntakeMotor.set(0);
  }

  //   @Override
  //   public Command getPrematchCheckCommand(
  //     VirtualXboxController controller, virtualJoystick joystick) {
  //       return Commands.sequence(
  //         Commands.runOnce(
  //           () -> {
  //             REVLibError error = algaeIntakeMotor.getLastError();
  //             if (error != REVLibError.kOk) {
  //               addError("Intake motor error: " + error.name());
  //             } else {
  //               addInfo("Intake motor contains no errors");
  //             }
  //           });
  //           Commands.runOnce(
  //             () -> {
  //               joystick.setButton(OperatorConstants.intakeNoteButton, true);
  //             }),
  //             Commands.waitSeconds(prematchDelay),
  //             Commands.runOnce(
  //               () -> {
  //                 if (Math.abs(intakeEncoder.getVelocity()) <= 1e-4) {
  //                   addError("Intake Motor is not moving");
  //                 } else {
  //                   addInfo("Intake Motor is moving");
  //                 }
  //                 joystick.clearVirtualButtons();
  //               }),
  //               Commands.runOnce(
  //                 () -> {
  //                   joystick.clearVirtualButtons();
  //                 }));
  //     }
  // }

  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Intake/Temperature", algaeIntakeMotor.getMotorTemperature());
  }
}
