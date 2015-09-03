package com.wego.airline;


import com.wego.airline.resources.AirlineResource;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;


public class WegoApp extends Application<WegoConfig> {
    public static void main(String[] args) throws Exception {
        new WegoApp().run(args);
    }

    @Override
    public void run(WegoConfig configuration, Environment environment) {
    	environment.jersey().register(new AirlineResource());
    }

}
