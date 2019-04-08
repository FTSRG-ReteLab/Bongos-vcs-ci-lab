package hu.bme.mit.train.sensor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class TrainSensorTest {
    
    TrainController controller;
    TrainUser user;
    TrainSensor sensor;
    
    @Before
    public void before() {
        controller = mock(TrainController.class);
        user = mock(TrainUser.class);
        sensor = new TrainSensor(controller, user);
    }

    @Test
    public void WithinMargins() {
        when(controller.getReferencespeed()).thenReturn(100);
        when(user.getAlarmState()).thenReturn(false);
        sensor.overrideSpeedLimit(100);
        verify(user,times(0)).setAlarmState(true);
    }
    
    
    @Test
    public void OutOfAbsoluteMargins() {
        when(controller.getReferencespeed()).thenReturn(400);
        when(user.getAlarmState()).thenReturn(true);
        sensor.overrideSpeedLimit(400);
        verify(user,times(1)).setAlarmState(true);
    }
    
    
    @Test
    public void OutOfRelativeMargins() {
        when(controller.getReferencespeed()).thenReturn(100);
        when(user.getAlarmState()).thenReturn(true);
        sensor.overrideSpeedLimit(50);
        verify(user,times(1)).setAlarmState(true);
    }
    
    
    @Test
    public void OutOfBothMargins() {
        when(controller.getReferencespeed()).thenReturn(100);
        when(user.getAlarmState()).thenReturn(true);
        sensor.overrideSpeedLimit(-500)
        verify(user,times(1)).setAlarmState(true);
    }
}
