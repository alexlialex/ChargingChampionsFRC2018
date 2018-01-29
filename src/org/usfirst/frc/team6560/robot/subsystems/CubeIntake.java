package org.usfirst.frc.team6560.robot.subsystems;

import org.usfirst.frc.team6560.robot.RobotMap.CAN;
import org.usfirst.frc.team6560.robot.commands.IntakeCubeWithJoystick;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CubeIntake extends Subsystem {

	WPI_TalonSRX intakeMotor1 = new WPI_TalonSRX(CAN.GRABBER_LEFT);
	WPI_TalonSRX intakeMotor2 = new WPI_TalonSRX(CAN.GRABBER_RIGHT);
	WPI_TalonSRX rotationMotor = new WPI_TalonSRX(CAN.GRABBER_ROTATION);
	Solenoid solenoid0 = new Solenoid(CAN.SOLENOID_0);
	Compressor compressor_0 = new Compressor(CAN.COMPRESSOR_ID);
	
    public CubeIntake() {
    	intakeMotor1.setSafetyEnabled(false);
    	intakeMotor2.setSafetyEnabled(false);
    	compressor_0.setClosedLoopControl(true);
    	compressor_0.start();
    }

    public void intakeCube(double speed) {
    	intakeMotor1.set(speed);
    	intakeMotor2.set(speed);
    }
    
    public void shootCube(double speed) {
    	intakeMotor1.set(-1 * speed);
    	intakeMotor2.set(-1 * speed);
    }
    
    public void stopIntake() {
    	intakeMotor1.set(0);
    	intakeMotor2.set(0);
    }
    
    public void rotateGrabber(double speed) {
    	rotationMotor.set(speed);
    }
    
    public void openArm() {
    	solenoid0.set(true);
    }
    
    public void closeArm() {
    	solenoid0.set(false);
    }
    
    
    public void initDefaultCommand() {
    	compressor_0.start();
    	setDefaultCommand(new IntakeCubeWithJoystick());
    }
}

