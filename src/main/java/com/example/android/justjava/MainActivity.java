/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 */

package com.example.android.justjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */

public class MainActivity extends AppCompatActivity {

    int quantity = 0;
    int costPerCoffee = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view){
        int price = calculatePrice();
        Log.v("MainActivity", "The price is " + price);

        EditText editText= (EditText) findViewById(R.id.name_editText);
        String name = editText.getText().toString();
        Log.v("MainActivity","The name entered is " + name);

        displayMessage(createOrderSummary(price, name));
    }

    /**
     * @return total price
     */
    private int calculatePrice(){
        return quantity * costPerCoffee;
    }

    /**
     * Creates String for Order summary
     *
     * @param price of the order
     * @return complete order summary in String form
     */
    private String createOrderSummary(int price, String string){
        String order = "Name: " + string;
        order += "\nAdd Whipped cream? " + isChecked((CheckBox)findViewById(R.id.whipped_cream_checkbox));
        order += "\nAdd Chocolate? " + isChecked((CheckBox)findViewById(R.id.chocolate_checkbox));
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
        quantity++;
        display(quantity);
    }

    /**
     * @param view affects the Quantity display on screen
     */
    public void decrement(View view){
        if(quantity > 0){
            quantity--;}
        display(quantity);
    }

    private boolean isChecked(CheckBox checkBox){
        return checkBox.isChecked();
    }
}


