/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 */

package com.example.android.justjava;

import android.content.Context;
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

        EditText editText= (EditText) findViewById(R.id.name_editText);
        String name = editText.getText().toString();

        displayMessage(createOrderSummary(price, name, isWhippedCream, isChocolate));
    }

    /**
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
        //quantityView.setText(NumberFormat.getInstance().format(number));
    }

    /**
     * @param coffeeOrder String of complete order details
     */
    private void displayMessage(String coffeeOrder){
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(coffeeOrder);
    }

    /**
     * @param view affects the Quantity display on screen
     */
    public void increment(View view){
        if(quantity < 100) {
            quantity++;
        } else if(quantity == 100){
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
        if(quantity > 0){
            quantity--;
        } else if(quantity == 0){
            CharSequence text = "Cannot order negative cups.";
            int duration = Toast.LENGTH_SHORT;

            //display error message
            Toast.makeText(this, text, duration).show();
        }
        display(quantity);
    }
}


