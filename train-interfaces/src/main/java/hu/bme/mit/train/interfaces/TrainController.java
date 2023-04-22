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

	void timerMethod();

	 class Timer implements Runnable{
		 TrainController controller;

		 public Timer(TrainController controller){
			 this.controller = controller;
		 }
		 private long currTime = 0;
		 @Override
		 public void run() {
			int i = 0;
			 while(i < 7){
				 try {
					 Thread.sleep(1000);
				 } catch (InterruptedException e) {
					 throw new RuntimeException(e);
				 }
				 controller.followSpeed();
				 System.out.println(controller.getReferenceSpeed());
				 currTime++;
				 i++;
			 }
		 }

		 public long getCurrTime(){
			 return currTime;
		 }
	 }
}
