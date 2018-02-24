package org.usfirst.frc.team6560.robot.commands;

import org.usfirst.frc.team6560.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StartIntake extends Command {

    public StartIntake() {
        requires(Robot.cubeIntake);
        requires(Robot.pneumatics);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.pneumatics.openArm();
    	Timer.delay(0.2);
    	Robot.cubeIntake.intakeCube(0.4);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	for (int i = 10; i > 0; i--) {
    		Timer.delay(0.1);
    		Robot.cubeIntake.intakeCube(0.2+((i/10)*0.2));
    	}
    }
}