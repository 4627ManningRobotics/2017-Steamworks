package org.usfirst.frc.team4627.robot.commands;

import org.usfirst.frc.team4627.robot.Robot;
import org.usfirst.frc.team4627.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoPointAtPegGyro extends Command {
		double targetHeading;
		double angle;
    public AutoPointAtPegGyro() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.driveTrain);
        }

        // Called just before this Command runs the first time
        protected void initialize() {
        	angle = -Robot.distFromCamCenter/5;
        	System.out.println(angle);
        	double setpoint =  (Robot.sensors.getFused()  +  angle)    %360  ;
        	if (setpoint <0) 
        		setpoint+=360;
        	double startHeading= Robot.sensors.getFused();
        	targetHeading = (startHeading+angle)%360;
        	if (targetHeading<0)
        		targetHeading+=360;
        	

        	if (angle < 0) {
        		Robot.driveTrain.setLeftMotors(-RobotMap.AUTO_TURN_SPEED);
        		Robot.driveTrain.setRightMotors(RobotMap.AUTO_TURN_SPEED);
        	}
        	else {
        		Robot.driveTrain.setLeftMotors(RobotMap.AUTO_TURN_SPEED);
        		Robot.driveTrain.setRightMotors(-RobotMap.AUTO_TURN_SPEED);
        	}
        }

        // Called repeatedly when this Command is scheduled to run
        protected void execute() {
        	
        	
        	
 
        }

        // Make this return true when this Command no longer needs to run execute()
        protected boolean isFinished() {
        	if (Math.abs(Robot.sensors.getFused() - targetHeading)  < 3 )
        		return true;
        	else return false;
        	
        }

        // Called once after isFinished returns true
        protected void end() {
        	Robot.driveTrain.setRightMotors(0);
        	Robot.driveTrain.setLeftMotors(0);
      
        }

        // Called when another command which requires one or more of the same
        // subsystems is scheduled to run
        protected void interrupted() {
        	end();
        }
}
