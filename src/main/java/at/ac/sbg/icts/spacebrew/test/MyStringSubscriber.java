package at.ac.sbg.icts.spacebrew.test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.ac.sbg.icts.spacebrew.client.StringSubscriber;

/**
 * A simple string subscriber that logs the incoming boolean value.
 * 
 * @author Ben Li
 */
public class MyStringSubscriber implements StringSubscriber
{
	Logger	log	= LoggerFactory.getLogger(MyRangeSubscriber.class);

	public MyStringSubscriber()
	{

	}

//	@Override
	public void receive(String value)
	{
		log.info("MyStringSubscriber receives: {} ", value);
	}
}
