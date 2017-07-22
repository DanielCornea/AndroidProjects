package com.example.android.guradiannewsapp;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by danok on 7/14/2017.
 */

public class QuerryUtilities {
    private static final String LOG_TAG = QuerryUtilities.class.getSimpleName();

    private QuerryUtilities() {
    }

    public static List<Article> getArticleData(String sourceURL) {
        URL url = createURL(sourceURL);
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);

        }
        List<Article> articles = extractFeatureFromJson(jsonResponse);
        return articles;
    }

    private static URL createURL(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jResponse = "";
        if (url == null) {
            return jResponse;

        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error  code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving  JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {

                inputStream.close();
            }
        }
        return jResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private static List<Article> extractFeatureFromJson(String ArticleJSON) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(ArticleJSON)) {
            return null;
        }
        // Create an empty ArrayList that we can start adding articles to
        List<Article> articles = new ArrayList<>();
        // Try to parse the JSON response string. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {
            // Create a JSONObject from the JSON response string
            JSONObject baseJsonResponse = new JSONObject(ArticleJSON);

            // Extract the JSONArray associated with the key called "items",
            // which represents a list of features (or items).
            JSONObject rawArticles = baseJsonResponse.getJSONObject("response");
            //Log.v("Raw Article array ", String.valueOf(rawArticleArray.length()));

            JSONArray articlesRawArray = rawArticles.getJSONArray("results");
            // For each Article in the ArticleArray, create an {@link Article} object
            for (int i = 0; i < articlesRawArray.length(); i++) {

                // Get a single Article at position i within the list of articles
                JSONObject currentArticle = articlesRawArray.getJSONObject(i);


                String title = currentArticle.getString("webTitle");


                String section;
                if (currentArticle.has("sectionName")) {
                    // parse the section field
                    section = currentArticle.getString("sectionName");
//
                } else {
                    section = "Section N/A";
                }
                String url = currentArticle.getString("webUrl");

                // Create a new {@link Article} object with the title section and url,
                // and url from the JSON response.
                Article Article = new Article(title, section, url);

                // Add the new {@link Article} to the list of articles.
                articles.add(Article);
            }
        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the articles JSON results", e);
        }
        // Return the list of articles
        return articles;
    }
}





