package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
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
        sensor = new TrainSensorImpl(controller, user);
    }

    @Test
    public void WithinMargins() {
        when(controller.getReferenceSpeed()).thenReturn((double)100);
        when(user.getAlarmState()).thenReturn(false);
        sensor.overrideSpeedLimit(100);
        verify(user,times(0)).setAlarmState(true);
    }
    
    
    @Test
    public void OutOfAbsoluteMargins() {
        when(controller.getReferenceSpeed()).thenReturn((double)400);
        when(user.getAlarmState()).thenReturn(true);
        sensor.overrideSpeedLimit(600);
        verify(user,times(1)).setAlarmState(true);
    }
    
    
    @Test
    public void OutOfRelativeMargins() {
        when(controller.getReferenceSpeed()).thenReturn((double)150);
        when(user.getAlarmState()).thenReturn(true);
        sensor.overrideSpeedLimit(50);
        verify(user,times(1)).setAlarmState(true);
    }
    
    
    @Test
    public void OutOfBothMargins() {
        when(controller.getReferenceSpeed()).thenReturn((double)100);
        when(user.getAlarmState()).thenReturn(true);
        sensor.overrideSpeedLimit(-500);
        verify(user,times(1)).setAlarmState(true);
    }
}
