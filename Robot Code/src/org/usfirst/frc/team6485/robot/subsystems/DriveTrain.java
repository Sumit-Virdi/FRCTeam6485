package org.usfirst.frc.team6485.robot.subsystems;

import org.usfirst.frc.team6485.robot.RobotMap;
import org.usfirst.frc.team6485.robot.commands.ArcadeDrive;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem{
	
	// Referencing The DriveTrain Motors //
	private Talon motorleftfront = new Talon(RobotMap.MOTOR_LEFT_FRONT_ID);
	private Talon motorrightfront = new Talon(RobotMap.MOTOR_RIGHT_FRONT_ID);
	private Talon motorleftback = new Talon(RobotMap.MOTOR_LEFT_BACK_ID);
	private Talon motorrightback = new Talon(RobotMap.MOTOR_RIGHT_BACK_ID);
	
	public void SetMotorsSpeed(double speed) {
		motorleftfront.set(ControlMode.PercentOutput, -speed);
		motorrightfront.set(ControlMode.PercentOutput, speed);
		motorleftback.set(ControlMode.PercentOutput, -speed);
		motorrightback.set(ControlMode.PercentOutput, speed);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ArcadeDrive());
	}

}
