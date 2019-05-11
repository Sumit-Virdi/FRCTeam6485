package org.usfirst.frc.team6485.robot.commands;

import org.usfirst.frc.team6485.robot.Robot;
import org.usfirst.frc.team6485.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class ArcadeDrive extends Command{
	public ArcadeDrive() {
		requires(Robot.driveTrain);
	}

	@Override
	protected void initialize() {
		Robot.m_oi.getXboxController();
		Robot.m_oi.getJoystick();
	}

	@Override
	protected void execute() {
		double Stick = Robot.m_oi.getXboxControllerAxis(RobotMap.STICKCONTROLLERXBOX);
		Robot.driveTrain.SetMotorsSpeed(Stick);
		
		double JoyStick = Robot.m_oi.getJoystickControllerAxis(RobotMap.STICKCONTROLLERJOYSTICK);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.driveTrain.SetMotorsSpeed(0);
	}

	@Override
	protected void interrupted() {
		this.end();
	}

}
