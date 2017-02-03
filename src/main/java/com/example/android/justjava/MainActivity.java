/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 */

package com.example.android.justjava;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */

public class MainActivity extends AppCompatActivity {

    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view){

        CheckBox whippedCream = (CheckBox)findViewById(R.id.whipped_cream_checkbox);
        CheckBox chocolate = (CheckBox)findViewById(R.id.chocolate_checkbox);

        boolean isWhippedCream = whippedCream.isChecked();
        boolean isChocolate = chocolate.isChecked();

        int price = calculatePrice(isWhippedCream, isChocolate);

        //retrieves name for order
        EditText editText= (EditText) findViewById(R.id.name_editText);
        String name = editText.getText().toString();

        //creates order summary string
        String order = createOrderSummary(price, name, isWhippedCream, isChocolate);

        //create subject line string
        String subject = getApplicationName(this) + " order for " + name;

        //creates intent for email
        composeEmail(subject, order);
    }

    /**
     * This method is called from from submitOrder to calculate price
     * @param addWhippedCream adds 1 to costPerCoffee if true
     * @param addChocolate adds 2 to costPerCoff if true
     * @return total price
     */
    private int calculatePrice(boolean addWhippedCream, boolean addChocolate){
        //cost per coffee
        int costPerCoffee = 5;

        //adds $1 if coffee is whipped
        if(addWhippedCream){
            costPerCoffee += 1;
        }

        //adds $2 if coffee has chocolate
        if(addChocolate){
            costPerCoffee += 2;
        }

        //returns total cost
        return quantity * costPerCoffee;
    }

    /**
     * Creates String for Order summary
     *
     * @param price total order
     * @param name customers name
     * @param whippedCream determine if whipped
     * @param chocolate determine if added chocolates
     * @return complete order summary in String form
     */
    private String createOrderSummary(int price, String name, boolean whippedCream, boolean chocolate){
        //create string with multiple concatenations
        String order = "Name: " + name;
        order += "\nAdd Whipped cream? " + whippedCream;
        order += "\nAdd Chocolate? " + chocolate;
        order += "\nQuantity: " + quantity;
        order += "\nTotal: $"+ price + "\nThank You!";
        return order;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number){
        TextView quantityView = (TextView) findViewById(R.id.quantity_text_view);
        quantityView.setText("" + number);
        //old code below
        //quantityView.setText(NumberFormat.getInstance().format(number));
    }

    /**
     * @param view affects the Quantity display on screen
     */
    public void increment(View view){
        //ensure quantity doesn't go over 100 orders
        if(quantity < 100) {
            quantity++;
        } else if(quantity == 100){
            //setup toast message to notify user
            CharSequence text = "Cannot order more than 100 cups.";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(this, text, duration);
            toast.show();
        }

        display(quantity);
    }

    /**
     * @param view affects the Quantity display on screen
     */
    public void decrement(View view){
        //ensure quantity doesn't go negative
        if(quantity > 0){
            quantity--;
        } else if(quantity == 0){
            //setup toast message to notify user
            CharSequence text = "Cannot order negative cups.";
            int duration = Toast.LENGTH_SHORT;

            //display error message
            Toast.makeText(this, text, duration).show();
        }
        display(quantity);
    }

    /**
     * Sends Order Summary via Email client
     *@param order text of email
     * @param subject text for subject order
     */
    public void composeEmail(String subject, String order) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); //only email apps should handle this
        intent.putExtra(Intent.EXTRA_TEXT, order);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.v("composeEmail", "Failed to resolveActivity()");
        }

    }

    public String getApplicationName(Context context) {
        String appName = context.getApplicationInfo().loadLabel(getPackageManager()).toString();
        return appName;
    }
}


