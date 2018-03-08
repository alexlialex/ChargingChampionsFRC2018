package org.usfirst.frc.team6560.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightSwitchScale extends CommandGroup {
	String gameData;

    public RightSwitchScale(String givenData) {
    	gameData = givenData;
    	char switchPos = 0;
    	char scalePos = 0;
    	try {
    		switchPos = gameData.charAt(0);
    		scalePos = gameData.charAt(1);
    	}
    	catch(NullPointerException npe) {
    		System.out.println("Game data Nonexistent!");
    	}
    }
}
