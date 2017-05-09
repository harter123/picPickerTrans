package picPicker;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.Label;
import java.awt.MediaTracker;
import java.awt.Rectangle;
import java.awt.Robot;

public class ScreenImage {
	public static Image getScreenImage(int x, int y, int w, int h) throws AWTException, InterruptedException {
		Robot robot = new Robot();
		Image screen = robot.createScreenCapture(new Rectangle(x, y, w, h)).getScaledInstance(w, h, Image.SCALE_SMOOTH);
		MediaTracker tracker = new MediaTracker(new Label());
		tracker.addImage(screen, 1);
		tracker.waitForID(0);
		return screen;
	}
}
