package com.pongal.cl;

import java.util.Calendar;
import java.util.Date;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

public class CLAppWidgetProvider extends AppWidgetProvider {

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		Log.d(Constant.APP_NAME, "Inside the on update method");
		for (int widgetId : appWidgetIds) {
			RemoteViews views = new RemoteViews(context.getPackageName(),
					R.layout.homescreenwidget);
			views.setTextViewText(R.id.daysLeftTxt, calculateDaysLeft(context));
			appWidgetManager.updateAppWidget(widgetId, views);
		}
	}
	
	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d(Constant.APP_NAME,"Into home screen widget " + intent.getAction());
		//TODO: Try removing the below code
		if(Constant.REFRESH_APPWIDGET.equals(intent.getAction())) {
			intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
		}
		super.onReceive(context, intent);
	}

	private String calculateDaysLeft(Context context) {
		DataStore dataStore = new DataStore(context);
		Date lastDate = dataStore.readContactLensReplace();
		Date currentDate = Calendar.getInstance().getTime();
		currentDate.setMinutes(0);
		currentDate.setHours(0);
		currentDate.setSeconds(0);
		Log.d(Constant.APP_NAME, "Dates: " + lastDate + ": " + currentDate);
		int days = (int) (currentDate.getTime() - lastDate.getTime())
				/ (1000 * 60 * 60 * 24);
		return (Constant.CONTACT_LENS_REPLACE_CYCLE - days) + "";
	}
}
