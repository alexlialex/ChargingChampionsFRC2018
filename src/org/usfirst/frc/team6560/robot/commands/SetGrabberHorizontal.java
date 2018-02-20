package org.usfirst.frc.team6560.robot.commands;

import org.usfirst.frc.team6560.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetGrabberHorizontal extends Command {

    public SetGrabberHorizontal() {
        requires(Robot.cubeIntake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.cubeIntake.setSetpoint(325);
    	Robot.cubeIntake.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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
    	Robot.cubeIntake.disable();
    }
}
