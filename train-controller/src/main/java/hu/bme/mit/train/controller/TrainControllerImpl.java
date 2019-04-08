package hu.bme.mit.train.controller;

import hu.bme.mit.train.interfaces.TrainController;

public class TrainControllerImpl implements TrainController, Runnable {

	private double step = 1;
	private double referenceSpeed = 0;
	private double speedLimit = 0;


	public void run(){
		followSpeed();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e){
			e.printStackTrace();
		}
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

		enforceSpeedLimit();
	}

	@Override
	public double getReferenceSpeed() {
		return referenceSpeed;
	}

	@Override
	public void setSpeedLimit(double speedLimit) {
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

}
