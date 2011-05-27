package com.pongal.cl;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

public class CLAppWidgetProvider extends AppWidgetProvider {

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		for (int widgetId : appWidgetIds) {
			RemoteViews views = new RemoteViews(context.getPackageName(),
					R.layout.homescreenwidget);
			views.setTextViewText(R.id.daysLeftTxt, "7");
			appWidgetManager.updateAppWidget(widgetId, views);
		}
	}
}
