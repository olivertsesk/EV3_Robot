package graspTest;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class graspTest {

	public static void main(String[] args) {
		EV3LargeRegulatedMotor armMotor = new EV3LargeRegulatedMotor(LocalEV3
				.get().getPort("A"));
		EV3LargeRegulatedMotor armMotor2 = new EV3LargeRegulatedMotor(LocalEV3
				.get().getPort("C"));
		EV3LargeRegulatedMotor graspMotor = new EV3LargeRegulatedMotor(LocalEV3
				.get().getPort("B"));
		SensorModes colorSensor = new EV3ColorSensor(LocalEV3.get().getPort(
				"S1"));
		SampleProvider colorValue = colorSensor.getMode("Red");
		float[] colorData = new float[colorValue.sampleSize()];
		armMotor.setAcceleration(90);
		armMotor.setSpeed(50);
		armMotor2.setAcceleration(90);
		armMotor2.setSpeed(50);

		graspMotor.setSpeed(20);

		while (true) {
			armMotor.rotate(115, true);
			armMotor2.rotate(115, false);

			Delay.msDelay(2000);

			colorValue.fetchSample(colorData, 0);
			float color = colorData[0];
			if (color >= 0.05) { // color of ball is red
				graspMotor.rotate(-80);
				Delay.msDelay(2000);
				armMotor.rotate(-115, true);
				armMotor2.rotate(-115, false);
				graspMotor.rotate(80);
			} else {
				armMotor.rotate(-115, true);
				armMotor2.rotate(-115, false);
			}
		}
	}

}
