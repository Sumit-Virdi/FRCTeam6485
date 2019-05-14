package org.usfirst.frc.team6485.robot.commands;

import org.usfirst.frc.team6485.robot.Robot;
import org.usfirst.frc.team6485.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class ManualLift extends Command{
	public ManualLift() {
		requires(Robot.elevator);
	}

	@Override
	protected void initialize() {
		Robot.m_oi.getJoystick();
	}

	@Override
	protected void execute() {
		double JoyStick = Robot.m_oi.getJoystickControllerAxis(RobotMap.STICKCONTROLLERJOYSTICK);
		Robot.elevator.elevate(JoyStick);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.elevator.stop();
	}

	@Override
	protected void interrupted() {
		this.end();
	}

}

