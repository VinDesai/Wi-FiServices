package com.felight.wifidemo;

import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends Activity {
	private WifiManager wifiManager;
	TextView tvResult;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tvResult = (TextView) findViewById(R.id.tvResult);
		wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);

		if (wifiManager.isWifiEnabled()) {
			((ToggleButton) findViewById(R.id.tbWifi)).setChecked(true);
		} else {
			((ToggleButton) findViewById(R.id.tbWifi)).setChecked(false);
		}

		tvResult.setText(getWifiName() + " " + isConnectedToInternet(this));
	}

	public void wifiEnableDisable(View view) {
		if (wifiManager.isWifiEnabled()) {
			wifiManager.setWifiEnabled(false);
		} else {
			wifiManager.setWifiEnabled(true);
		}
	}

	public String getWifiName() {
		String ssid = "none";
		WifiInfo wifiInfo = wifiManager.getConnectionInfo();
		
		/*
		 * if (WifiInfo.getDetailedStateOf( wifiInfo.getSupplicantState()) ==
		 * NetworkInfo.DetailedState.CONNECTED) { ssid = wifiInfo.getSSID(); }
		 */
		ssid = wifiInfo.getSSID();
		return ssid;
	}
	
	

	public boolean isConnectedToInternet(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) {
			NetworkInfo info = connectivity
					.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			if (info != null) {
				if (info.isConnected()) {
					return true;
				}
			}
		}
		return false;
	}

	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
