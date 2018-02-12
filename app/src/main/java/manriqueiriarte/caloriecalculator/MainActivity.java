package manriqueiriarte.caloriecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;//for displaying text
import android.widget.Switch;
import android.widget.CompoundButton.OnCheckedChangeListener;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get manipulated TextViews and Switch
        ageTextView = findViewById(R.id.ageTextView);
        bmrTextView = findViewById(R.id.bmrTextView);
        workoutTextView = findViewById(R.id.workoutTextView);
        caloricTextView = findViewById(R.id.caloricTextView);
        heightTextView = findViewById(R.id.heightTextView);
        weightTextView = findViewById(R.id.weightTextView);
        genderSwitch = (Switch) findViewById(R.id.genderSwitch);

        bmrTextView.setText(0 + " Calories");//set original bmr value
        caloricTextView.setText(0 + " Calories");//set original daily caloric intake value

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

        //set Listener for genderSwitch
        genderSwitch.setOnCheckedChangeListener(genderSwitchListener);
    }

    private int height = 48;
    private int weight = 200;
    private int age = 0;
    private int activeDays = 0;

    private TextView ageTextView;//show User's age
    private TextView bmrTextView;//show the calculated BMR
    private TextView workoutTextView;//show User's workouts per week
    private TextView caloricTextView;//show the calculated recommended daily caloric intake
    private TextView heightTextView;//show User's height
    private TextView weightTextView;//show User's weight
    private Switch genderSwitch;//gets User's gender

    private void calculate() {
        heightTextView.setText((height/12) + "\'" + (height%12) + "\"");
        weightTextView.setText(weight + "lbs");

        double heightCm = height*2.54;
        double weightKg = weight/2.2;
        double bmr = 0.0;
        double dailyCaloricIntake = 0.0;

        if (genderSwitch.isChecked()==false) { //if the selection is female
            //Mifflin-St.Jeor equation for females
            bmr = (heightCm * 6.25) + (weightKg * 9.99) - (age * 4.92) - 161;
        } else {//if the selection is male
            //Mifflin-St.Jeor equation for males
            bmr = (heightCm * 6.25) + (weightKg * 9.99) - (age * 4.92) + 5;
        }
        bmrTextView.setText((int)bmr + " Calories");

        //setting caloricTextView
        if (activeDays==0) {
            dailyCaloricIntake = bmr * 1.2;
        } else if (activeDays >= 1 && activeDays < 3) {
            dailyCaloricIntake = bmr * 1.375;
        } else if (activeDays >= 3 && activeDays <= 5) {
            dailyCaloricIntake = bmr * 1.55;
        } else {
            dailyCaloricIntake = bmr * 1.725;
        }
        caloricTextView.setText((int)dailyCaloricIntake + " Calories");
    }

    private final OnSeekBarChangeListener heightSeekBarListener = new OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            height = progress;//set the height to progress of the seekbar
            calculate();
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
            calculate();
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
                ageTextView.setText(Integer.toString(age));
            } catch (NumberFormatException e){//if its not a number or empty
                ageTextView.setText("");
                age = 0;
            }
            calculate();
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
                workoutTextView.setText(Integer.toString(activeDays));
            } catch (NumberFormatException e){//if its not a number or empty
                workoutTextView.setText("");
                activeDays = 0;
            }
            calculate();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    private final OnCheckedChangeListener genderSwitchListener = new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            calculate();
        }
    };
}
