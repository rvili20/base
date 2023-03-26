package hu.bme.mit.train.interfaces;

import com.google.common.collect.Table;

import java.time.LocalTime;

public interface TrainSensor {

	int getSpeedLimit();

	void overrideSpeedLimit(int speedLimit);

	void tachographRecordAdd();

	Table<LocalTime, Integer, Integer> getTachograph();
}
