package com.pongal.cl;

import java.util.Date;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
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
				Date date = new Date(p.getYear() - 1900, p.getMonth(), p
						.getDayOfMonth());
				dataStore.storeContactLensReplaceDate(date);
				// Uri data = Uri.parse("contactlens://widget/id/");
				// Intent intent = new Intent(Constant.REFRESH_APPWIDGET);
				// intent.setData(data);
				// sendBroadcast(intent);
				updateAllWidgets();
				finish();
			}
		});
	}

	private void updateAllWidgets() {
		AppWidgetManager appWidgetManager = AppWidgetManager
				.getInstance(getApplicationContext());
		int[] appWidgetIds = appWidgetManager
				.getAppWidgetIds(new ComponentName(this,
						CLAppWidgetProvider.class));
		if (appWidgetIds.length > 0) {
			new CLAppWidgetProvider().onUpdate(this, appWidgetManager,
					appWidgetIds);
		}
	}
}