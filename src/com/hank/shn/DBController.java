package com.hank.shn;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBController extends SQLiteOpenHelper {

	public DBController(Context applicationcontext) {
		super(applicationcontext, "shn_app.db", null, 1);

	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		String query;
		query = "CREATE TABLE words ( wordId INTEGER PRIMARY KEY, wordEng TEXT,meaningEng TEXT,sentenceEng TEXT,wordNep TEXT,meaningNep TEXT,sentenceNep TEXT)";
		database.execSQL(query);

	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int version_old,
			int current_version) {
		String query;
		query = "DROP TABLE IF EXISTS words";
		database.execSQL(query);
		onCreate(database);
	}

	public ArrayList<HashMap<String, String>> getAllWords() {
		ArrayList<HashMap<String, String>> wordList;
		wordList = new ArrayList<HashMap<String, String>>();
		String selectQuery = "SELECT  wordId,wordEng,meaningEng FROM words";
		SQLiteDatabase database = this.getWritableDatabase();
		Cursor cursor = database.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("wordId", cursor.getString(0));
				map.put("wordEng", cursor.getString(1));
				map.put("meaningEng", cursor.getString(2));
				wordList.add(map);
			} while (cursor.moveToNext());
		}

		return wordList;
	}

	public HashMap<String, String> getWordDetail(String id) {

		HashMap<String, String> wordDetail = new HashMap<String, String>();
		SQLiteDatabase database = this.getReadableDatabase();
		String selectQuery = "SELECT * FROM words where wordId='" + id + "'";
		Cursor cursor = database.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {

				wordDetail.put("wordId", cursor.getString(0));
				wordDetail.put("wordEng", cursor.getString(1));
				wordDetail.put("meaningEng", cursor.getString(2));
				wordDetail.put("sentenceEng", cursor.getString(3));
				wordDetail.put("wordNep", cursor.getString(4));
				wordDetail.put("meaningNep", cursor.getString(5));
				wordDetail.put("sentenceNep", cursor.getString(6));

			} while (cursor.moveToNext());
		}
		return wordDetail;
	}
}
