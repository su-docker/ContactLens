package com.pongal.cl;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

public class Main extends Activity {
	private DataStore dataStore;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		dataStore = new DataStore(this);
		Button doneBtn = (Button) findViewById(R.id.doneBtn);
		doneBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				DatePicker p = (DatePicker) findViewById(R.id.replaceDatePicker);
				String date = p.getDayOfMonth() + "-" + p.getMonth() + "-"
						+ p.getYear();
				dataStore.store(date);
				finish();
			}
		});
	}
}