package hu.bme.mit.train.interfaces;

public interface TrainSensor {

	double getSpeedLimit();

	void overrideSpeedLimit(double speedLimit);

}
