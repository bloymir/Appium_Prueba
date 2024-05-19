package w3c;


import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import io.appium.java_client.AppiumDriver;


import java.time.Duration;

import static java.lang.String.format;
import static java.util.Collections.singletonList;


public class FingerGestureUtils {
    AppiumDriver driver;
    private static final String FINGER_1 = "Finger 1";
    private static final String FINGER_2 = "Finger 2";

    public FingerGestureUtils(AppiumDriver driver){
        this.driver = driver;
    }


    public void tap(final WebElement element){
        final var start = getElementCenter(element);
        final var sequence = singleFingerSwipe(FINGER_1, 0, start, null);
        this.driver.perform (singletonList(sequence));
    }
    public void dragTo(final WebElement source, final WebElement target){
        final var start = getElementCenter(source);
        final var end = getElementCenter(target);
        System.out.println("Moviendo...");
        printPoint("Start ", start);
        printPoint("End ", end);

        final var sequence = singleFingerSwipe(FINGER_1, 0, start, end);
        this.driver.perform(singletonList(sequence));
    }

    private void printPoint (final String type, final Point point) {
        System.out.println (format ("{0}: [x: {1}, y: {2}]", type, point.getX (), point.getY ()));
    }
    private Point getElementCenter(final WebElement element){
        final var location = element.getLocation();
        final var size = element.getSize();
        final var x = (size.getWidth() / 2) + location.getX();
        final var y = (size.getHeight() / 2) + location.getY();
        return getCorrectedCoordinates(element, new Point(x,y));
    }

    private Point getCorrectedCoordinates(final WebElement element, final Point point){
        final var screenSize = getScreenSize();
        var x = point.getX();
        var y = point.getY();
        var w = screenSize.getWidth();
        var h = screenSize.getHeight();

        if (element != null){
            final var size = element.getSize();
            final var location = element.getLocation();
            w = size.getWidth() + location.getX();
            h = size.getHeight() + location.getY();
        }

        if(x >= w){
            x = w - 5;
        }
        if(y >= h){
            y = h - 5;
        }
        if(x < 0){
            x = 5;
        }
        if(y<0){
            y = 5;
        }
        return new Point(x, y);
    }
    private Dimension getScreenSize(){
        return driver.manage().window().getSize();
        //return this.driver.manage()
          //      .window().getSize();
    }

    private Sequence singleFingerSwipe(final String fingerName, final int index, final Point start, final Point end){
        final var finger = new PointerInput(PointerInput.Kind.TOUCH, fingerName);
        final var sequence = new org.openqa.selenium.interactions.Sequence(finger, index);

        sequence.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), start.getX(), start.getY()));
        sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

        if(end != null){
            sequence.addAction(new Pause(finger, Duration.ofMillis(500)));
            sequence.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), end.getX(), end.getY()));
        }

        sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        return sequence;
    }
}
