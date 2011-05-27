package com.pongal.cl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.util.Log;

public class DataStore {

	private Context context;
	private String fileName = "repository.txt";

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

	public void store(Object data) {
		try {
			FileOutputStream outputStream = context.openFileOutput(fileName,
					Context.MODE_PRIVATE);
			outputStream.write(data.toString().getBytes());
			outputStream.write('\n');
		} catch (IOException e) {
			handleFailure(e);
		}
	}

	private void handleFailure(Exception e) {
		Log.e(Constant.APP_NAME, "Unrecoverable failure", e);
		throw new RuntimeException(e);
	}

}
