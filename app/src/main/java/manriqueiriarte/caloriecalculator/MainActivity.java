package manriqueiriarte.caloriecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        profileButton = (Button) findViewById(R.id.profileButton);
        profileButton.setOnClickListener(profileButtonListener);
    }

    private Button profileButton;//will launch profile activity

    private View.OnClickListener profileButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(MainActivity.this, profile.class));
        }
    };
}