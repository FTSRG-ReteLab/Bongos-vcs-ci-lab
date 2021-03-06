package hu.bme.mit.train.system;

import com.google.common.collect.Table;
import hu.bme.mit.train.sensor.TrainTachograph;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import hu.bme.mit.train.system.TrainSystem;

public class TrainSystemTest {

	TrainController controller;
	TrainSensor sensor;
	TrainUser user;
	TrainTachograph tachograph;
	
	@Before
	public void before() {
		TrainSystem system = new TrainSystem();
		controller = system.getController();
		sensor = system.getSensor();
		user = system.getUser();
		tachograph = new TrainTachograph(controller, user);

		sensor.overrideSpeedLimit(50);
	}
	
	@Test
	public void OverridingJoystickPosition_IncreasesReferenceSpeed() {
		sensor.overrideSpeedLimit(10);
		Assert.assertTrue(Math.abs(controller.getReferenceSpeed()-0) == 0);
		
		user.overrideJoystickPosition(5);

		controller.followSpeed();
		Assert.assertTrue(Math.abs(controller.getReferenceSpeed()-5) == 0);
		controller.followSpeed();
		Assert.assertTrue(Math.abs(controller.getReferenceSpeed()-10) == 0);
		controller.followSpeed();
		Assert.assertTrue(Math.abs(controller.getReferenceSpeed()-10) == 0);
	}

	@Test
	public void OverridingJoystickPositionToNegative_SetsReferenceSpeedToZero() {
		user.overrideJoystickPosition(4);
		controller.followSpeed();
		user.overrideJoystickPosition(-5);
		controller.followSpeed();
		Assert.assertTrue(Math.abs(controller.getReferenceSpeed()-0) == 0);
	}

	@Test
	public void OverrideJoystickPositionToNegative_SetReferenceSpeedToNegative() {
		user.overrideJoystickPosition(-5);
		controller.followSpeed();
		Assert.assertFalse(Math.abs(controller.getReferenceSpeed()+5) == 0);
	}

	@Test
	public void checkDatas() {
		Table datas = tachograph.getDatas();
		Assert.assertTrue((int)datas.get(0,1) == user.getJoystickPosition());
	}

	
}
