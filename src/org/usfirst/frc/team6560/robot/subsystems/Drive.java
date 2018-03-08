package org.usfirst.frc.team6560.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import org.usfirst.frc.team6560.util.ADIS16448_IMU;
import org.usfirst.frc.team6560.robot.RobotMap.CAN;
import org.usfirst.frc.team6560.robot.commands.drive.TankDriveWithJoysticks;

//import edu.wpi.first.wpilibj.ADXRS450_Gyro;
//import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *
 */
public class Drive extends Subsystem {

	public static double globalDriveSpeed;

	WPI_TalonSRX frontLeftDrive;
	WPI_TalonSRX rearLeftDrive;
	WPI_TalonSRX frontRightDrive;
	WPI_TalonSRX rearRightDrive;
	SpeedControllerGroup left;
	SpeedControllerGroup right;
	DifferentialDrive drivetrain;

	// Sensors
	//public AnalogInput ultra;
	//public ADXRS450_Gyro gyro;
	public ADIS16448_IMU imu;

	public Encoder drive_enc_left;
	public Encoder drive_enc_right;

	public Drive() {
		globalDriveSpeed = 0.8;

		frontLeftDrive = new WPI_TalonSRX(CAN.DRIVE_FRONTLEFT);
		rearLeftDrive = new WPI_TalonSRX(CAN.DRIVE_REARLEFT);
		frontRightDrive = new WPI_TalonSRX(CAN.DRIVE_FRONTRIGHT);
		rearRightDrive = new WPI_TalonSRX(CAN.DRIVE_REARRIGHT);
		left = new SpeedControllerGroup(frontLeftDrive, rearLeftDrive);
		right = new SpeedControllerGroup(frontRightDrive, rearRightDrive);

		frontLeftDrive.setInverted(true);
		rearLeftDrive.setInverted(true);
		frontRightDrive.setInverted(true);
		rearRightDrive.setInverted(false);

		drivetrain = new DifferentialDrive(left, right);

		//ultra = new AnalogInput(0);
		//gyro = new ADXRS450_Gyro();
		imu = new ADIS16448_IMU();
		//gyro.calibrate();
		//gyro.reset();
		imu.calibrate();
		imu.reset();

		drive_enc_left = new Encoder(8, 9, true, Encoder.EncodingType.k2X); // TODO: Determine DIO ports
		drive_enc_right = new Encoder(6, 7, true, Encoder.EncodingType.k2X);
		initializeEncoders();
		// Should have identical initializations
		// k2x vs. k4x vs. k1x
	}

	public void tankDriveWithJoysticks(double left, double right) {
		drivetrain.tankDrive(left * globalDriveSpeed, right * globalDriveSpeed);
	}

	public void stopDrive() {
		frontLeftDrive.set(0);
		rearLeftDrive.set(0);
		frontRightDrive.set(0);
		rearRightDrive.set(0);
	}

	public void initializeEncoders() {
		// TODO: ??????????????????????????? learn this stuff
		// drive_enc_left.setMinRate(0);
		drive_enc_left.setDistancePerPulse((3 * Math.PI) / 128);
		// 1 revolution / 256 pulses * 6pi inches diameter / 1 revolution
		drive_enc_left.setReverseDirection(true);
		// drive_enc_left.setSamplesToAverage(0);
		// drive_enc_right.setMinRate(0);
		drive_enc_right.setDistancePerPulse((3 * Math.PI) / 128);
		drive_enc_right.setReverseDirection(false);
		// drive_enc_right.setSamplesToAverage(0);
	}

	public void driveStraightWithGyro(double speed) {
		//double angle = gyro.getAngle();
		double angle = getGyroAngle();
		drivetrain.arcadeDrive(-speed, -0.3 * angle);
	}

	public double getGyroAngle() {
		return imu.getAngleX();
	}

	public void arcadeDrive(double speed, double angle) {
		drivetrain.arcadeDrive(speed, angle);
	}

	public void spinClockwise(double speed) {
		speed = Math.abs(speed);
		frontLeftDrive.set(speed);
		frontRightDrive.set(speed);
		rearRightDrive.set(speed);
		rearLeftDrive.set(speed);
	}

	public void spinCounterClockwise(double speed) {
		speed = -1 * Math.abs(speed);
		frontLeftDrive.set(speed);
		frontRightDrive.set(speed);
		rearRightDrive.set(speed);
		rearLeftDrive.set(speed);
	}

	public double getSpeed() {
		return (drive_enc_left.getRate() + drive_enc_right.getRate()) / 2;
		// Divide encoder rates from both sides by 2
	}

	public void increaseDriveSpeed() {
		if (globalDriveSpeed + 0.1 <= 1.0) {
			globalDriveSpeed += 0.1;
		}
	}

	public void decreaseDriveSpeed() {
		if (globalDriveSpeed - 0.1 >= 0.1) {
			globalDriveSpeed -= 0.1;
		}
	}

	public void initDefaultCommand() {
		setDefaultCommand(new TankDriveWithJoysticks());
	}

}
