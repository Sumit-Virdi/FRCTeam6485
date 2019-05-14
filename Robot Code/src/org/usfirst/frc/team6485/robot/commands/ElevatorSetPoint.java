package org.usfirst.frc.team6485.robot.commands;

import org.usfirst.frc.team6485.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ElevatorSetPoint extends Command{
	  
		private int level;
		
	    public ElevatorSetPoint(int setpoint) {
	        requires(Robot.elevator);
	        level = setpoint;
	    }

	    // Called just before this Command runs the first time
	    protected void initialize() {
	        Robot.elevator.enable();
	        Robot.elevator.setLevel(level);
	    }

	    // Called repeatedly when this Command is scheduled to run
	    protected void execute() {
	    }

	    // Make this return true when this Command no longer needs to run execute()
	    protected boolean isFinished() {
	        return Robot.elevator.onTarget();
	    }

	    // Called once after isFinished returns true
	    protected void end() {
	    	Robot.elevator.disable();
	    }

	    // Called when another command which requires one or more of the same
	    // subsystems is scheduled to run
	    protected void interrupted() {
	    }
	}