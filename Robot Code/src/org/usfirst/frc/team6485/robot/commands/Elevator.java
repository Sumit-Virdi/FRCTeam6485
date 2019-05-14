package org.usfirst.frc.team6485.robot.subsystems;

import org.usfirst.frc.team6485.robot.Robot;
import org.usfirst.frc.team6485.robot.RobotMap;
import org.usfirst.frc.team6485.robot.commands.ManualLift;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Elevator extends PIDSubsystem{
    
	public Talon liftMotor = new Talon(RobotMap.ELEVATOR_PORT_ID);
    public Encoder liftEncoder = RobotMap.ENCODER_PORT_ID;
    public LimitSwitch bottom = new LimitSwitch(RobotMap.RELEASESUCKBUTTON);
    private PIDController upAndDownPID = new PIDController(p, i , d, liftEncoder, this);
    
	static double p = 0.01;
	static double i = 0.0;
	static double d = 0.0;
	double height;
    int level = 0;
    int setMotorDirection;
    boolean override = false;
    
    public double[] setPoints = {
 /// We'll set these later  //
    };
    
    public int[] feet = {
    		0, 1, 2, 3, 4, 5, 6
    };

    public Elevator() {
    	super("Elevator", p, i, d);
        setAbsoluteTolerance(0.005);     
        getPIDController().setContinuous(true);
        if (Robot.isReal()) {
        	liftEncoder.setDistancePerPulse(.00000579);
	    } else {
	    	liftEncoder.setDistancePerPulse((4.0/*in*/*Math.PI)/(360.0*12.0/*in/ft*/));
	    }
        liftEncoder.setDistancePerPulse(0.01);
    }
 
    public void initDefaultCommand() {
    	setDefaultCommand(new ManualLift());
    	liftEncoder.setPIDSourceParameter(PIDSource.kDistance);
    }
    
    public void switchOverride () {
    	override = !override;
    	SmartDashboard.putBoolean("Override?", override);
    }
    
    public void elevate(double input) {
	    
    	double height = liftEncoder.getDistance();
    	
		// Take the raw input if no switches are engaged.
    	if (override == true) {		
	    	liftMotor.set(input);
			return;
	    } else if (bottom.get() == true && input < 0) {
	    	Robot.elevator.getLiftEncoder().reset();  
			stop();
			return;
		} else {
			liftMotor.set(input);
		}
    }
    
    public void stop() {
    	liftMotor.set(0);
    }
    
    //---------------------------------------//
    
    protected double returnPIDInput() {
    	height = liftEncoder.getDistance();
    	return height;
    }
    
    protected void usePIDOutput(double output) {
        // Prevents lift from smacking the floor.
    	if (bottom.get() == true && output < 0) {
    		Robot.elevator.getLiftEncoder().reset();        	
    		stop();
    		return;
        } else {
        	liftMotor.set(output * setMotorDirection);
        	return;
        }
    }
    
    // Set maximum
    public void incLevel() {
    	setMotorDirection = 1;
    	this.setSetpoint(12.1);
    }
    
    // Set minimum
    public void decLevel() {
    	setMotorDirection = -1;
    	this.setSetpoint(12.1);
    }
    
    public void setLevel(int level) {
    	this.setSetpoint(setPoints[level]);
    }
    
    //---------------------------------------//
    
    public Encoder getLiftEncoder() {
    	return liftEncoder;
    }
    
    public void log() {
    	SmartDashboard.putData("Raw Lift Encoder", liftEncoder);
    	SmartDashboard.putNumber("Lift Distance", liftEncoder.getDistance());
    	SmartDashboard.putBoolean("At Bottom?", bottom.get());
    }
}
