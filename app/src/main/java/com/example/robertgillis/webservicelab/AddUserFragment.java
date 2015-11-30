package com.example.robertgillis.webservicelab;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddUserFragment extends Fragment
{

    private String url = "http://cssgate.insttech.washington.edu/~robear/addUser.php";


    public AddUserFragment()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_user, container, false);
        final EditText mEmailText = (EditText) v.findViewById(R.id.email_text);
        final EditText mPwdText = (EditText) v.findViewById(R.id.password_text);
        Button addButton = (Button) v.findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (mEmailText.getText().length() != 0 && mPwdText.getText().length() != 0)
                {
                    url += "?email=" + mEmailText.getText().toString() + "&password=" + mPwdText.getText().toString();
                    new AddUserWebTask().execute(url);
                }
            }
        });
        return v;
    }

    private class AddUserWebTask extends AsyncTask<String, Void, String>
    {
        private static final String TAG = "AddUserWebTask";

        @Override
        protected String doInBackground(String...urls)
        {
            try
            {
                return downloadUrl(urls[0]);
            }
            catch (IOException e)
            {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }

        private String downloadUrl(String myurl) throws IOException
        {
            InputStream is = null;
            int len = 500;

            try
            {
                URL url = new URL(myurl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.connect();
                int response = conn.getResponseCode();
                Log.d(TAG, "The response is: " + response);
                is = conn.getInputStream();
                String contentAsString = readIt(is, len);
                Log.d(TAG, "The string is: " + contentAsString);
                return contentAsString;
            } catch (Exception e)
            {
                Log.d(TAG, "Something happened. " + e.getMessage());
            } finally
            {
                if (is != null)
                {
                    is.close();
                }
            }
            return null;
        }

        public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException
        {
            Reader reader = null;
            reader = new InputStreamReader(stream, "UTF-8");
            char[] buffer = new char[len];
            reader.read(buffer);
            return new String(buffer);
        }

        @Override
        protected void onPostExecute(String s)
        {
            super.onPostExecute(s);
            try
            {
                JSONObject jsonObject = new JSONObject(s);
                String status = jsonObject.getString("result");
                if (status.equalsIgnoreCase("success"))
                {
                    Toast.makeText(getActivity(), "User successfully inserted", Toast.LENGTH_LONG).show();
                }
                getFragmentManager().popBackStackImmediate();
            }
            catch(Exception e)
            {
                Log.d(TAG, "Parsing JSON Exception " + e.getMessage());
            }
        }

    }

}
