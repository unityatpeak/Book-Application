

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log4JPropertyFileDemo {
	static Logger myLogger=Logger.getLogger(Log4JPropertyFileDemo.class.getName() );
	Object x;
	public void do_something(int a,float b)
	{
		myLogger.debug("Operation performed successfully");
		myLogger.info("do_something are:"+a+" "+b);
		if(x==null)
		{
			myLogger.error("Value of x is null");
		}
	}
	public static void main(String[] args) {
		PropertyConfigurator.configure("resources/log4j.properties");
		Log4JPropertyFileDemo m=new Log4JPropertyFileDemo();
		m.do_something(1,(float)1.2);
	}
               
}

