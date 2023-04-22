package hu.bme.mit.train.interfaces;

public interface TrainController {

	void followSpeed();

	int getReferenceSpeed();

	void setSpeedLimit(int speedLimit);

	void setJoystickPosition(int joystickPosition);

	void setEmergencyBrake(boolean isEmergencyBrake);

	boolean getEmergencyBrake();

	int getStep();

	void setSensor(TrainSensor sensor);

	void weAreChecking();
}
