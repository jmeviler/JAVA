package at.ac.sbg.icts.spacebrew.test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.ac.sbg.icts.spacebrew.client.RangeSubscriber;

/**
 * A simple range subscriber that logs the incoming boolean value.
 * 
 * @author Axel Baumgartner
 */
public class MyRangeSubscriber implements RangeSubscriber
{
	Logger	log	= LoggerFactory.getLogger(MyRangeSubscriber.class);

	public MyRangeSubscriber()
	{

	}

//	@Override
	public void receive(int value)
	{
		log.info("MyRangeSubscriber receives: {} ", value);
	}
}
