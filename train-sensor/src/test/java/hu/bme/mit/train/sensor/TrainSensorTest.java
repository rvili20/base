package hu.bme.mit.train.sensor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;

import static org.mockito.Mockito.*;

public class TrainSensorTest {

    TrainUser mockUser;
    TrainController mockController;
    TrainSensor sensor;
    
    @Before
    public void before() {
        mockController = mock(TrainController.class);
        mockUser = mock(TrainUser.class);
       sensor = new TrainSensorImpl(mockController, mockUser);
    }

    @Test
    public void sensorOverrideSpeedLimitTest() {
        sensor.overrideSpeedLimit(501);
        
        verify(mockUser, times(1)).setAlarmState(true);

    }
    @Test
    public void sensorOverrideSpeedLimitTest2(){
        sensor.overrideSpeedLimit(499);
        
        verify(mockUser, times(1)).setAlarmState(false);
    }

    @Test
    public void sensorOverrideSpeedLimitRelative1(){
        when(mockController.getReferenceSpeed()).thenReturn(151);
        sensor.overrideSpeedLimit(100);
        verify(mockUser, times(1)).setAlarmState(true);
    }

    @Test
    public void maxCoverageTest(){
        
        mockController.followSpeed();
        sensor.tachographRecordAdd();
		Assert.assertFalse(sensor.getTachograph().isEmpty());
        mockController.setSpeedLimit(50);
        Assert.assertEquals(5, sensor.getSpeedLimit());
    }

    @Test
    public void sensorOverrideSpeedLimitRelative2(){
        when(mockController.getReferenceSpeed()).thenReturn(90);
        sensor.overrideSpeedLimit(100);
        verify(mockUser, times(1)).setAlarmState(false);
    }
}
