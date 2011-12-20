package com.solidstategroup.web.pages;

import org.apache.wicket.protocol.http.WebApplication;


public class RadarApplication extends WebApplication
{    	
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<BasePage> getHomePage()
	{
		return BasePage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		super.init();

		// add your configuration here
	}
}
