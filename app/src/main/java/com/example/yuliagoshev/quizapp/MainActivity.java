package com.example.yuliagoshev.quizapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    int Score=0;
    Spinner sp;
    String [] question4={"Barack Obama", "Theodore Roosevelt", "Abraham Lincoln", "Woodrow Wilson"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner sp= findViewById(R.id.spinner);
        sp.setOnItemSelectedListener(this);

        ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, question4);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);
    }

    public void submitQuiz(View view) {
        int Score = findScore();
        LayoutInflater myInflator = getLayoutInflater();
        View myLayout = myInflator.inflate(R.layout.custom_spinner, (ViewGroup) findViewById(R.id.spinnerlayout));
        Toast myToast = new Toast(getApplicationContext());
        myToast.setDuration(Toast.LENGTH_LONG);
        myToast.setView(myLayout);
        myToast.show();
        TextView myMessage = myLayout.findViewById(R.id.txtdisplay);
        if (Score == 0) {
            //show a message for a score of zero
            myMessage.setText("So sorry! Your score is: " + Score);


        } else if (Score >= 1 && Score <= 3) {
            //show a message for a score >= 1 and <= 3
            myMessage.setText("Just keep trying! Your score is: " + Score);

        } else if (Score >= 4 && Score <= 6) {
            //show a message for a score of greater than or equal to 5 and less than or equal to 8
            myMessage.setText("Nice try! Your score is: " + Score);

        } else {
            //show a message for a score of 8
            myMessage.setText("Excellent!!! Your score is " + Score + " out of 8");

        }
        Score=0;

    }

    public int findScore() {

        RadioGroup radioGroup1 = findViewById(R.id.radioGroup1);
        RadioGroup radioGroup2 = findViewById(R.id.radioGroup2);
        RadioGroup radioGroup3= findViewById(R.id.radioGroup3);
        CheckBox checkBoxPresident1= findViewById(R.id.checkbox_president1);
        CheckBox checkBoxPresident2= findViewById(R.id.checkbox_president2);
        CheckBox checkBoxPresident3= findViewById(R.id.checkbox_president3);
        CheckBox checkBoxPresident4= findViewById(R.id.checkbox_president4);
        CheckBox checkBoxPresident5= findViewById(R.id.checkbox_president5);
        CheckBox checkBoxPresident6= findViewById(R.id.checkbox_president6);
        CheckBox checkBoxOption1= findViewById(R.id.checkbox_option1);
        CheckBox checkBoxOption2= findViewById(R.id.checkbox_option2);
        CheckBox checkBoxOption3= findViewById(R.id.checkbox_option3);
        CheckBox checkBoxOption4= findViewById(R.id.checkbox_option4);
        EditText tallPresident= findViewById (R.id.edit_text1);
        String question3=tallPresident.getText().toString ();
        EditText resignedPresident= findViewById (R.id.edit_text2);
        String question7=resignedPresident.getText().toString ();

        //Question1
        if (radioGroup1.getCheckedRadioButtonId () == R.id.radio_button3) {
            Score += 1;
        }
        //Question2
        if (!checkBoxPresident1.isChecked () && checkBoxPresident2.isChecked () && checkBoxPresident3.isChecked () && checkBoxPresident4.isChecked () && !checkBoxPresident5.isChecked () && checkBoxPresident6.isChecked ()) {
            Score += 1;
        } else {
            Score += 0;
        }
        //Question3
        if (question3.equals("Abraham Lincoln") || question3.equals("Abraham Lincoln ") || question3.equals("abraham lincoln")|| question3.equals("abraham lincoln ")){
            Score += 1;
        }
        //Question5
        if (radioGroup2.getCheckedRadioButtonId () == R.id.radio_button6) {
            Score += 1;
        }
        //Question 6
        if (checkBoxOption1.isChecked () && !checkBoxOption2.isChecked () && !checkBoxOption3.isChecked() && !checkBoxOption4.isChecked ()) {
            Score += 1;
        } else {
            Score += 0;
        }
        //Question7
        if (question7.equals("Richard Nixon") || question7.equals("Richard Nixon ") || question7.equals("richard nixon")|| question7.equals("richard nixon ")){
            Score += 1;
        }
        //Question8
        if (radioGroup3.getCheckedRadioButtonId () == R.id.radio_button12) {
            Score += 1;
        }
        return Score;
    }

        public void shareScore (View view){
            EditText editTextUserName= findViewById (R.id.userName);
            String userName=editTextUserName.getText().toString();
            int Score=findScore ();
            String message="";
            if (Score > 0){
                message= "Congratulations!\n Your friend " + userName + " got a score of " + Score + " out of 8 in President Trivia Quiz!\n Download the app from the playstore and try it now!!!";
            } else {
                message="Your friend " + userName + " got a score of " + Score + " in President Trivia Quiz!\n App is availabe in playstore!";
            }
            Intent intent= new Intent (Intent.ACTION_SENDTO);
            intent.setData (Uri.parse ("mailto:"));
            intent.putExtra(Intent.EXTRA_SUBJECT, userName + " got a score of " + Score + " in President Trivia Quiz App!");
            intent.putExtra(Intent.EXTRA_TEXT, message);
            if (intent.resolveActivity(getPackageManager()) !=null){
                startActivity (intent);
            }
    }

    public void resetQuiz (View view) {
        Score=0;
        RadioGroup radioGroup1 = findViewById(R.id.radioGroup1);
        radioGroup1.clearCheck();
        RadioGroup radioGroup2 = findViewById(R.id.radioGroup2);
        radioGroup2.clearCheck();
        RadioGroup radioGroup3 = findViewById(R.id.radioGroup3);
        radioGroup3.clearCheck();
        EditText userName= findViewById (R.id.userName);
        userName.getText().clear();
        EditText tallPresident= findViewById (R.id.edit_text1);
        tallPresident.getText().clear();
        EditText resignedPresident= findViewById (R.id.edit_text2);
        resignedPresident.getText().clear();
        CheckBox checkBoxPresident1 = findViewById(R.id.checkbox_president1);
        checkBoxPresident1.setChecked(false);
        CheckBox checkBoxPresident2 = findViewById(R.id.checkbox_president2);
        checkBoxPresident2.setChecked(false);
        CheckBox checkBoxPresident3 = findViewById(R.id.checkbox_president3);
        checkBoxPresident3.setChecked(false);
        CheckBox checkBoxPresident4 = findViewById (R.id.checkbox_president4);
        checkBoxPresident4.setChecked(false);
        CheckBox checkBoxPresident5 = findViewById (R.id.checkbox_president5);
        checkBoxPresident5.setChecked(false);
        CheckBox checkBoxPresident6 = findViewById (R.id.checkbox_president6);
        checkBoxPresident6.setChecked(false);
        CheckBox checkBoxOption1 = findViewById (R.id.checkbox_option1);
        checkBoxOption1.setChecked(false);
        CheckBox checkBoxOption2 = findViewById(R.id.checkbox_option2);
        checkBoxOption2.setChecked(false);
        CheckBox checkBoxOption3 = findViewById(R.id.checkbox_option3);
        checkBoxOption3.setChecked(false);
        CheckBox checkBoxOption4 = findViewById(R.id.checkbox_option4);
        checkBoxOption4.setChecked(false);
        LayoutInflater myInflator=getLayoutInflater();
        View myLayout=myInflator.inflate(R.layout.custom_spinner,(ViewGroup)findViewById(R.id.spinnerlayout) );
        Toast myToast=new Toast(getApplicationContext());
        myToast.setDuration(Toast.LENGTH_LONG);
        myToast.setView(myLayout);
        myToast.show();
        TextView myMessage= myLayout.findViewById(R.id.txtdisplay);
        myMessage.setText ("Quiz was reset.Try again!");
        sp = findViewById(R.id.spinner);
        sp.setSelection(0);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(position == 0) {
            Score +=0;
        }
        if(position == 1) {
            Score +=1;
        }
        if(position == 2) {
            Score +=0;
        }
        if(position == 3) {
            Score +=0;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
