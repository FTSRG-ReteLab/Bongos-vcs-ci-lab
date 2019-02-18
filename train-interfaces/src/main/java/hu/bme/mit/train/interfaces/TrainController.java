package hu.bme.mit.train.interfaces;

public interface TrainController {

	void followSpeed();

	double getReferenceSpeed();

	void setSpeedLimit(double speedLimit);

	void setJoystickPosition(int joystickPosition);

}
