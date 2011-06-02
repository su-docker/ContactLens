package com.pongal.cl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.util.Log;

public class DataStore {

	private Context context;
	private String fileName = "repository.txt";
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

	public DataStore(Context context) {
		try {
			this.context = context;
			File dataFile = context.getFileStreamPath(fileName);
			if (!dataFile.exists()) {
				dataFile.createNewFile();
			}
		} catch (IOException e) {
			handleFailure(e);
		}
	}

	public void storeContactLensReplaceDate(Date data) {
		try {
			FileOutputStream outputStream = context.openFileOutput(fileName,
					Context.MODE_PRIVATE);
			outputStream.write(formatter.format(data).getBytes());
			outputStream.write('\n');
		} catch (IOException e) {
			handleFailure(e);
		}
	}

	public Date readContactLensReplace() {
		try {
			FileInputStream inputStream = context.openFileInput(fileName);
			byte[] buffer = new byte[64];
			inputStream.read(buffer);
			Log.d(Constant.APP_NAME, "Date read: " + new String(buffer));
			return formatter.parse(new String(buffer));
		} catch (Exception e) {
			Log.e(Constant.APP_NAME,
					"Unable to read contact lens last replace date", e);
			throw new RuntimeException(e);
		}
	}

	private void handleFailure(Exception e) {
		Log.e(Constant.APP_NAME, "Unrecoverable failure", e);
		throw new RuntimeException(e);
	}

}
