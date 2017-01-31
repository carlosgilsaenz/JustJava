/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 */

package com.example.android.justjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

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
        displayPrice(calculatePrice());
    }

    private int calculatePrice(){
        return quantity * costPerCoffee;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number){
        TextView quantityView = (TextView) findViewById(R.id.quantity_text_view);
        quantityView.setText("" + number);
        //quantityView.setText(NumberFormat.getInstance().format(number));
    }

    private void displayPrice(int number){
        TextView priceView = (TextView) findViewById(R.id.price_text_view);
        priceView.setText("Total: $" + number + "\nThank you!");
    }

    public void increment(View view){
        quantity++;
        display(quantity);
    }

    public void decrement(View view){
        if(quantity > 0){
            quantity--;}
        display(quantity);
    }
}


