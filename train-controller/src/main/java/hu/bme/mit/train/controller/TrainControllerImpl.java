package hu.bme.mit.train.controller;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;

import java.util.Timer;
import java.util.TimerTask;

public class TrainControllerImpl implements TrainController {

	private int step = 0;
	private int referenceSpeed = 0;
	private int speedLimit = 0;

	private boolean isEmergencyBrake = false;

	private TrainSensor sensor;

	Timer timer = new Timer();
	TimerTask checking = new TimerTask() {
		@Override
		public void run() {
			followSpeed();
		}
	};
	@Override
	public void weAreChecking(){
		timer.scheduleAtFixedRate(checking, 0, 1000);
	}

	@Override
	public void setSensor(TrainSensor sensor){
		this.sensor = sensor;
	}

	@Override
	public void followSpeed() {
		if (referenceSpeed < 0) {
			referenceSpeed = 0;
		} else {
		    if(referenceSpeed+step > 0) {
                referenceSpeed += step;
            } else {
		        referenceSpeed = 0;
            }
		}
		if(isEmergencyBrake){
			speedLimit = 0;
		}

		enforceSpeedLimit();
		sensor.tachographRecordAdd();
	}



	@Override
	public void setEmergencyBrake(boolean isEmergencyBrake){
		this.isEmergencyBrake = isEmergencyBrake;
	}

	@Override
	public int getReferenceSpeed() {
		return referenceSpeed;
	}

	@Override
	public void setSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
		enforceSpeedLimit();
		
	}

	private void enforceSpeedLimit() {
		if (referenceSpeed > speedLimit) {
			referenceSpeed = speedLimit;
		}
	}

	@Override
	public void setJoystickPosition(int joystickPosition) {
		this.step = joystickPosition;		
	}

	@Override
	public boolean getEmergencyBrake(){
		return this.isEmergencyBrake;
	}

	@Override
	public int getStep(){
		return this.step;
	}

}
