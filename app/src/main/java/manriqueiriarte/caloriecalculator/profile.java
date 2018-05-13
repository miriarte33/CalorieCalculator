package manriqueiriarte.caloriecalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        myDB = new DataBaseHelper(this);

        //set listener for button
        bmrButton = findViewById(R.id.bmrButton);
        bmrButton.setOnClickListener(bmrButtonListener);

        bmiButton = findViewById(R.id.bmiButton);
        bmiButton.setOnClickListener(bmiButtonListener);

        addValues = findViewById(R.id.addEntryButton);
        addValues.setOnClickListener(addEntryButtonListener);

        viewButton = findViewById(R.id.viewEntriesButton);
        viewButton.setOnClickListener(viewButtonListener);

        viewHealthyEntriesButton = findViewById(R.id.healthyEntriesButton);
        viewHealthyEntriesButton.setOnClickListener(viewHealthyEntriesButtonListener);

        //get manipulated TextViews and Switch
        heightTextView = findViewById(R.id.heightTextView);
        weightTextView = findViewById(R.id.weightTextView);
        genderSwitch = (Switch) findViewById(R.id.genderSwitch);

        //set TextWatcher for ageEditText
        ageEditText = findViewById(R.id.ageEditText);
        ageEditText.addTextChangedListener(ageEditTextWatcher);

        //set TextWatcher for name
        enterNameEditText = findViewById(R.id.enterNameEditText);
        enterNameEditText.addTextChangedListener(enterNameEditTextWatcher);

        //set Listener for heightSeekBar
        heightSeekBar = (SeekBar) findViewById(R.id.heightSeekBar);
        heightSeekBar.setOnSeekBarChangeListener(heightSeekBarListener);

        //set Listener for weightSeekBar
        weightSeekBar = findViewById(R.id.weightSeekBar);
        weightSeekBar.setOnSeekBarChangeListener(weightSeekBarListener);

        //get radio buttons
        zero = (RadioButton) findViewById(R.id.zeroButton);
        oneToTwo = (RadioButton) findViewById(R.id.oneToTwoButton);
        threeToFive = (RadioButton) findViewById(R.id.threeToFiveButton);
        sixToSeven = (RadioButton) findViewById(R.id.sixToSevenButton);
    }

    DataBaseHelper myDB;
    private Button bmrButton;//will launch bmr activity
    private Button bmiButton;//will launch bmi activity
    private Button addValues;
    private Button viewButton;
    private Button viewHealthyEntriesButton;
    private EditText enterNameEditText;
    private EditText ageEditText;
    private SeekBar weightSeekBar;
    private SeekBar heightSeekBar;

    public static int height = 48;
    public static int weight = 200;
    public static int age = 0;
    public static String name = "";

    private TextView heightTextView;//show User's height
    private TextView weightTextView;//show User's weight
    public static Switch genderSwitch;//gets User's gender
    //Radio buttons
    public static RadioButton zero;
    public static RadioButton oneToTwo;
    public static RadioButton threeToFive;
    public static RadioButton sixToSeven;

    private View.OnClickListener addEntryButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String name1 = name;
            int weight1 = weight;
            double bmi = CalculateBmi();
            int dailyCaloricIntake = CalculateDailyCaloricIntake();

            if (name1.length() != 0 ) {
                AddData(name1, weight1, dailyCaloricIntake, bmi);
                enterNameEditText.setText("");
                ageEditText.setText("");
                weightSeekBar.setProgress(200);
                heightSeekBar.setProgress(48);
            } else {
              Toast.makeText(profile.this,"You must put something in the \"Name\" text field.",Toast.LENGTH_LONG).show();
            }
        }
    };

    private View.OnClickListener viewHealthyEntriesButtonListener = new View.OnClickListener() {
        @Override
        public void onClick (View view) {
            startActivity(new Intent(profile.this, ViewHealthyBmiContents.class));
        }
    };

    private View.OnClickListener viewButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(profile.this, ViewListContents.class);
            startActivity(intent);
        }
    };

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

    private final TextWatcher enterNameEditTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            name = s.toString();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    public void AddData(String name, int weight, int dailyCaloricIntake, double bmi) {
        boolean insert = myDB.addData(name, weight, dailyCaloricIntake, bmi);
        Log.d("MI", insert + "");
        if (insert == true) {
            Toast.makeText(profile.this, "Entered Data", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(profile.this, "Something Went Wrong", Toast.LENGTH_LONG).show();
        }
    }

    private double CalculateBmi() {
        double bmi = ( (double) weight / ( (double) height * (double) height)) * 703;
        return bmi;
    }

    private int CalculateDailyCaloricIntake() {
        double heightCm = height*2.54;
        double weightKg = weight/2.2;
        double bmr = 0.0;
        int dailyCaloricIntake = 0;

        if (genderSwitch.isChecked() == false) { //if the selection is female
            //Mifflin-St.Jeor equation for females
            bmr = (heightCm * 6.25) + (weightKg * 9.99) - (profile.age * 4.92) - 161;
        } else {//if the selection is male
            //Mifflin-St.Jeor equation for males
            bmr = (heightCm * 6.25) + (weightKg * 9.99) - (profile.age * 4.92) + 5;
        }

        //setting caloricTextView
        if (zero.isChecked()) {
            dailyCaloricIntake = (int) (bmr * 1.2);
        } else if (oneToTwo.isChecked()) {
            dailyCaloricIntake = (int) (bmr * 1.375);
        } else if (threeToFive.isChecked()) {
            dailyCaloricIntake = (int) (bmr * 1.55);
        } else if (sixToSeven.isChecked()) {
            dailyCaloricIntake = (int) (bmr * 1.725);
        }

        return dailyCaloricIntake;
    }

}
