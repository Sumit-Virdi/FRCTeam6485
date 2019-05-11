
package org.usfirst.frc.team6485.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;

public class OI {
	public XboxController xbox = new XboxController(RobotMap.XBOX_PORT);
	public Joystick joystick = new Joystick(RobotMap.JOYSTICK_PORT);

	public Joystick getJoystick() {
		return joystick;
	}
	
	public XboxController getXboxController() {
		return xbox;
	}
	
	public double getXboxControllerAxis(int axis) {
		return xbox.getRawAxis(axis);
	}
	public double getJoystickControllerAxis(int axis) {
		return joystick.getRawAxis(axis);
	}
}
