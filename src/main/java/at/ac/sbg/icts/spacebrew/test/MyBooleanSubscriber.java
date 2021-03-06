package at.ac.sbg.icts.spacebrew.test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.ac.sbg.icts.spacebrew.client.BooleanSubscriber;

/**
 * A boolean subscriber that simple logs the incoming boolean value.
 * 
 * @author Ben Li
 */
public class MyBooleanSubscriber implements BooleanSubscriber
{
	Logger	log	= LoggerFactory.getLogger(MyBooleanSubscriber.class);

	public MyBooleanSubscriber()
	{

	}

//	@Override
	public void receive(boolean value)
	{
		log.info("MyBooleanSubscriber receives: {} ", value);
	}
}
