/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ShooterSubsystem;

public class ShootCommand extends CommandBase {
	private ShooterSubsystem m_shooter;
	private int targetRPM;


	/**
	 * Creates a new ShootCommand.
	 */
	public ShootCommand(ShooterSubsystem shooter, int RPM) {
		// Use addRequirements() here to declare subsystem dependencies.
		m_shooter = shooter;
		targetRPM = RPM;
		addRequirements(m_shooter);
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		m_shooter.setSetpoint(targetRPM);
		m_shooter.enable();
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		if(m_shooter.hasSeenBallExit()) RobotContainer.ballCount -= 1;
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		m_shooter.disable();
		m_shooter.setVoltage(Constants.ShooterConstants.IDLE_VOLTAGE);
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return RobotContainer.ballCount == 0;
	}
}
