package com.neoranga55.cleanguitestarchitecture;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class WelcomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        TextView message = (TextView) findViewById(R.id.welcome_message);
        // Get bundle from LoginActivity
        Bundle bundle = this.getIntent().getExtras();
        if (bundle!= null)
        {
            message.setText("Welcome " + bundle.getString("USER"));
        }
    }
}
