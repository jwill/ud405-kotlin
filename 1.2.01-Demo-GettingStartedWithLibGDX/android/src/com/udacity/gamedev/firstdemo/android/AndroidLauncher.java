package com.udacity.gamedev.firstdemo.android;

import android.os.Bundle;
import com.udacity.gamedev.firstdemo.FirstDemoGame;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new FirstDemoGame(), config);
	}
}
