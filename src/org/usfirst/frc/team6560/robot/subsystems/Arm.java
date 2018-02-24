package org.usfirst.frc.team6560.robot.subsystems;

import org.usfirst.frc.team6560.robot.RobotMap.CAN;
import org.usfirst.frc.team6560.robot.commands.RotateArmWithJoystick;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class Arm extends PIDSubsystem {
	WPI_TalonSRX jointMotor1 = new WPI_TalonSRX(CAN.ARM1);
	WPI_TalonSRX jointMotor2 = new WPI_TalonSRX(CAN.ARM2);
	
    
    public Arm() {
    	super("Arm", 0.07, 0.0, 0.0);
    	jointMotor1.set(ControlMode.Follower, jointMotor2.getDeviceID());
    	jointMotor1.setSafetyEnabled(false);
    	jointMotor2.setSafetyEnabled(false);
    	jointMotor2.setInverted(true);
    	jointMotor1.setInverted(true);
    	jointMotor2.getSensorCollection().setQuadraturePosition(0, 1);
    	setAbsoluteTolerance(3000);
    	getPIDController().setContinuous(false);
    	getPIDController().disable();
    	getPIDController().setSetpoint(0);
    	
    }

    public void rotate(double speed) {
    	jointMotor2.set(speed);
    }
    
    public void stopRotate() {
    	jointMotor2.set(0);
    }
    
    public int getArmRotationRelativePosition() {
    	return jointMotor2.getSensorCollection().getQuadraturePosition();
    }
    
    public void initDefaultCommand() {
    	setDefaultCommand(new RotateArmWithJoystick());
    }
    
    protected double returnPIDInput() {
    	return jointMotor2.getSensorCollection().getQuadraturePosition();
    }
    
   
	protected void usePIDOutput(double output) {
		jointMotor2.pidWrite(output);
		
	}
}

