package com.example.android.romaniandrivinglicencequiz;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /**
     * Variables's declaration.
     */

    int score;
    RadioButton a3;
    CheckBox a4;
    CheckBox a5;
    CheckBox a6;
    CheckBox a7;
    RadioButton a9;
    EditText nameField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameField = findViewById(R.id.name_field);

        a3 = findViewById(R.id.A3_radioButton);
        a4 = findViewById(R.id.A4_checkBox);
        a5 = findViewById(R.id.A5_checkBox);
        a6 = findViewById(R.id.A6_checkBox);
        a7 = findViewById(R.id.A7_checkBox);
        a9 = findViewById(R.id.A9_radioButton);

    }

    /**
     * This method calculates score.
     */
    private void calculateScore() {

        /**
         *  Correct RadioButtons and CheckBoxes answers are pressed and stored in a boolean variable.
         */

        boolean isA3 = a3.isChecked();
        boolean isA4 = a4.isChecked();
        boolean isA5 = a5.isChecked();
        boolean isA6 = a6.isChecked();
        boolean isA7 = a7.isChecked();
        boolean isA9 = a9.isChecked();

        /**
         * Evaluates if the correct RadioButtons pressed ==> true then adds 1 point to the score
         */
        score = 0;

        if (isA3) {
            score += 1;
        } else {
            score += 0;
        }

        if (isA9) {
            score += 1;
        } else {
            score += 0;
        }

        /**
         * Checks if both CheckBoxes are correct and adds 1 points to the score, else adds 0 points.
         */

        if (isA4 && isA5) {
            score += 1;
        } else {
            score += 0;
        }

        if (isA6 && isA7) {
            score += 1;
        } else {
            score += 0;
        }

    }

    /**
     * This method is called when the Submit button is pressed.
     *
     * @param view
     */

    public void submitResult(View view) {

        /**
         * This method is called to update the latest value stored in the variable score.
         */

        calculateScore();

        /**
         *  Question 1 is not evaluated as true or false, just requests an string input as a name.
         */

        String name_field = nameField.getText().toString();

        /**
         * Show an message with the score as a toast.
         */

        Toast toast = Toast.makeText(this, name_field + getString(R.string.toast_string1) + score + getString(R.string.toast_string2), Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

        /**
         * Send an intent with the score to an e-mail address.
         */

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.subject_name)  + name_field);
        intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.result_text) + score);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
