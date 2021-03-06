package smart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.ac.sbg.icts.spacebrew.client.SpacebrewClient;
import at.ac.sbg.icts.spacebrew.client.SpacebrewClientCallback;
import at.ac.sbg.icts.spacebrew.client.SpacebrewMessage;
import at.ac.sbg.icts.spacebrew.client.publisher.BooleanPublisher;
import at.ac.sbg.icts.spacebrew.client.publisher.RangePublisher;
import at.ac.sbg.icts.spacebrew.client.publisher.StringPublisher;
import at.ac.sbg.icts.spacebrew.test.MyBooleanSubscriber;
import at.ac.sbg.icts.spacebrew.test.MyRangeSubscriber;
import at.ac.sbg.icts.spacebrew.test.MyStringSubscriber;

public class Example implements SpacebrewClientCallback
{
	private final Logger			log	= LoggerFactory.getLogger(SpacebrewClient.class);

	//Cilent 收发数据
	private final SpacebrewClient	client;

	// Some publishers demonstrating the use of abstract publishers
	BooleanPublisher				myBooleanPublisher;
	RangePublisher					myRangePublisher;
	StringPublisher					myStringPublisher;

	/**
	 * Starts the Spacebrew client.
	 * 
	 * @param args Unused
	 */
	public static void main(String args[])
	{
		new Example();
	}

	/**
	 * Starts a SpacebrewClient, adds exemplary subscribers and publishers and
	 * connects the client to the server.
	 */
	public Example()
	{
		 String serverUri = "ws://spacebrew.icts.sbg.ac.at:9000";
		client = new SpacebrewClient(this, serverUri, "Example Java Spacebrew Client",
				"An example client with fake subscribers and simple publishers.");

		// add some subscribers 
		//("name"，type"，"function")
		client.addSubscriber("switch", SpacebrewMessage.TYPE_BOOLEAN, "switchInput");
		client.addSubscriber("counter", SpacebrewMessage.TYPE_RANGE, "counterInput");
		client.addSubscriber("time", SpacebrewMessage.TYPE_STRING, "timeInput");

		/**
		 * This subscriber demonstrates that subscribers can have the same name
		 * but differing types.
		 */
		client.addSubscriber("counter", SpacebrewMessage.TYPE_STRING, "counterStringInput");

		client.addPublisher("switch", false);
		client.addPublisher("counter", 0);
		client.addPublisher("time", "");

		/**
		 * This publisher demonstrates that publishers can have the same name
		 * but differing types.
		 */
		client.addPublisher("counter", "");

		/**
		 * Actually connect to the server and start publishing and subscribing
		 * for data.
		 */
		client.connect();
	}

	Object	test	= null;

	/**
	 * Called by SpacebrewClient as a callback method for the "switch"
	 * subscriber.
	 * 
	 * @param input The received boolean message
	 */
	public void switchInput(boolean input)
	{
		log.info("The switch turns: " + (input ? "on" : "off"));
		log.info("" + test.getClass());
	}

	/**
	 * Called by <code>SpacebrewClient</code> as a callback method for the
	 * "counter" subscriber with type range.
	 * 
	 * @param input The received range message
	 */
	public void counterInput(int input)
	{
		log.info("The counter counts to: " + input);
	}

	/**
	 * Called by <code>SpacebrewClient</code> as a callback method for the
	 * "counter" subscriber with type string.
	 * 
	 * @param input The received string message
	 */
	public void counterStringInput(String input)
	{
		log.info("The counter says: " + input);
	}

	/**
	 * Called by <code>SpacebrewClient</code> as a callback method for the
	 * "time" subscriber.
	 * 
	 * @param input The received string message
	 */
	public void timeInput(String input)
	{
		log.info("The time is now: " + input);
	}

	/**
	 * Callback method for the <code>SpacebrewClient</code> object. Will be
	 * called when the connection to the server has been established
	 * successfully.
	 */
//	@Override
	public void onOpen()
	{
		log.info("Connection opened.");

		/**
		 * These subscribers demonstrate, that subscribers can be added even
		 * after the connection has been established.
		 */
		MyBooleanSubscriber myBooleanSubscriber = new MyBooleanSubscriber();
		client.addSubscriber("myBooleanSubscriber", myBooleanSubscriber);

		MyRangeSubscriber myRangeSubscriber = new MyRangeSubscriber();
		client.addSubscriber("myRangeSubscriber", myRangeSubscriber);

		MyStringSubscriber myStringSubscriber = new MyStringSubscriber();
		client.addSubscriber("myStringSubscriber", myStringSubscriber);

		/**
		 * The next publishers demonstrate, that publishers can be added even
		 * after the connection has been established.
		 */

		// will repeat the output from the "switch" publisher
		myBooleanPublisher = new BooleanPublisher("myBooleanPublisher", false, client);

		/**
		 * This publisher demonstrates the use of the linear transformation and
		 * low-pass filter in <code>RangePublisher</code>
		 */
		myRangePublisher = new RangePublisher("myRangePublisher", 0, client);
		myRangePublisher.setMinValue(0);
		myRangePublisher.setMaxValue(100);
		myRangePublisher.activateLowPassFilter();

		/*
		 * A small alpha value for the low-pass filter creates high smoothing.
		 * Because we smooth a random value, the output value will always try to
		 * approach the middle of the range interval [0,1023], which is 512.
		 */
		myRangePublisher.setLowPassAlpha(0.2f);

		// will repeat the output from the "time" publisher
		myStringPublisher = new StringPublisher("myStringPublisher", "", client);

		// TODO add examples for SpacebrewSensorManager and SpacebrewSensor

		// Do your stuff in a different thread to not block the main thread
		// (with a never ending loop like in this example).
		Thread thread = new Thread()
		{
			@Override
			public void run()
			{
				loop();
			}
		};
		thread.start();
	}

	/**
	 * Callback method for the <code>SpacebrewClient</code> object. Will be
	 * called when the connection to the server has been closed.
	 */
//	@Override
	public void onClose()
	{
		log.info("Connection closed.");
	}

	/**
	 * Callback method for the <code>SpacebrewClient</code> object. Will be
	 * called when an exception force closed the connection to the server.
	 */
//	@Override
	public void onError()
	{
		log.info("An error occured.");
	}

	/**
	 * When the connection has been established, we send some values until the
	 * program terminates.
	 */
	private void loop()
	{
		// used for the "switch" publisher
		boolean booleanValue = false;

		// used for the "counter" publisher
		int rangeValue = 0;

		/**
		 * Loop modeled after http://www.java-gaming.org/index.php?topic=24220.0
		 */
		final double hertz = 1;
		final double NANO_SECOND = 1000000000;

		final double TIME_BETWEEN_UPDATES = NANO_SECOND / hertz;

		double now = System.nanoTime();
		double lastUpdateTime;

		while (true)
		{
			lastUpdateTime = System.nanoTime();

			if (client.isConnected())
			{
				client.publish("switch", booleanValue);
				myBooleanPublisher.publish(booleanValue);
				booleanValue = !booleanValue;

				client.publish("counter", rangeValue);
				client.publish("counter", Integer.toBinaryString(rangeValue));

				rangeValue++;
				if (rangeValue > 1023)
				{
					rangeValue = 0;
				}

				String time = System.currentTimeMillis() + "";
				client.publish("time", time);
				myStringPublisher.publish(time);

				// create a random value in the interval [0,100]
				int randomValue = (int) (Math.random() * 100);

				// the random value will be linear transformed to the interval
				// [0,1023] and a low-pass filter is used to smooth the output
				myRangePublisher.publish(randomValue);
			}

			// Yield until it has been at least the target time between updates
			// This saves the CPU from hogging.
			while (now - lastUpdateTime < TIME_BETWEEN_UPDATES)
			{
				Thread.yield();

				// This stops the app from consuming all your CPU. It makes this
				// slightly less accurate, but is worth it. You can remove this
				// line and it will still work (better),
				// your CPU just climbs on certain OSes. FYI on some OS's this
				// can cause pretty bad stuttering.
				try
				{
					Thread.sleep(1);
				}
				catch (Exception e)
				{
					// ignore
				}

				now = System.nanoTime();
			}
		}
	}
}
