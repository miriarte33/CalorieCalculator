package manriqueiriarte.caloriecalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Switch;
import android.widget.TextView;

public class profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //set listener for button
        bmrButton = findViewById(R.id.bmrButton);
        bmrButton.setOnClickListener(bmrButtonListener);

        bmiButton = findViewById(R.id.bmiButton);
        bmiButton.setOnClickListener(bmiButtonListener);

        //get manipulated TextViews and Switch
        heightTextView = findViewById(R.id.heightTextView);
        weightTextView = findViewById(R.id.weightTextView);
        genderSwitch = (Switch) findViewById(R.id.genderSwitch);

        //set TextWatcher for ageEditText
        EditText ageEditText = findViewById(R.id.ageEditText);
        ageEditText.addTextChangedListener(ageEditTextWatcher);

        //set TextWatcher for workoutEditText
        EditText workoutEditText = (EditText) findViewById(R.id.workoutEditText);
        workoutEditText.addTextChangedListener(workoutEditTextWatcher);

        //set Listener for heightSeekBar
        SeekBar heightSeekBar = (SeekBar) findViewById(R.id.heightSeekBar);
        heightSeekBar.setOnSeekBarChangeListener(heightSeekBarListener);

        //set Listener for weightSeekBar
        SeekBar weightSeekBar = findViewById(R.id.weightSeekBar);
        weightSeekBar.setOnSeekBarChangeListener(weightSeekBarListener);



    }

    private Button bmrButton;//will launch bmr activity
    private Button bmiButton;//will launch bmi activity

    public static int height = 48;
    public static int weight = 200;
    public static int age = 0;
    public static int activeDays = 0;

    private TextView heightTextView;//show User's height
    private TextView weightTextView;//show User's weight
    public static Switch genderSwitch;//gets User's gender

    private View.OnClickListener bmrButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(profile.this, bmr.class));//launch bmr activity
        }
    };

    private View.OnClickListener bmiButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(profile.this, bmi.class));//launch bmr activity
        }
    };

    private final OnSeekBarChangeListener heightSeekBarListener = new OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            height = progress;//set the height to progress of the seekbar
            heightTextView.setText((height/12) + "\'" + (height%12) + "\"");
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private final OnSeekBarChangeListener weightSeekBarListener = new OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            weight = progress;
            weightTextView.setText(weight + "lbs");
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private final TextWatcher ageEditTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {//get age and display it
                age = Integer.parseInt(s.toString());
            } catch (NumberFormatException e){//if its not a number or empty
                age = 0;
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    private final TextWatcher workoutEditTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {//get workout days per week and display it
                activeDays = Integer.parseInt(s.toString());
            } catch (NumberFormatException e){//if its not a number or empty
                activeDays = 0;
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

}
