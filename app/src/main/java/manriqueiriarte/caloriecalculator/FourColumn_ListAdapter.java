package manriqueiriarte.caloriecalculator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Button;

import java.util.ArrayList;


public class FourColumn_ListAdapter extends ArrayAdapter<User> {

    private LayoutInflater mInflater;
    private ArrayList<User> users;
    private int mViewResourceId;

    public FourColumn_ListAdapter(Context context, int textViewResourceId, ArrayList<User> users) {
        super(context, textViewResourceId, users);
        this.users = users;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(mViewResourceId, null);

        User user = users.get(position);

        if (user != null) {
            TextView name = (TextView) convertView.findViewById(R.id.textName);
            TextView weight = (TextView) convertView.findViewById(R.id.textWeight);
            TextView dailyCaloricIntake = (TextView) convertView.findViewById(R.id.textDailyCaloricIntake);
            TextView bmi = (TextView) convertView.findViewById(R.id.textBmi);

            if (name != null) {
                name.setText(user.getName());
            }

            if (weight != null) {
                weight.setText(Integer.toString(user.getWeight()));
            }

            if (dailyCaloricIntake != null) {
                dailyCaloricIntake.setText(Integer.toString(user.getDailyCaloricIntake()));
            }

            if(bmi != null) {
                bmi.setText(String.format("%.2f", user.getBmi()));
            }
        }

        return convertView;
    }
}
