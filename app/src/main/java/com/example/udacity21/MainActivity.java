package com.example.udacity21;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

     public void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);

        quantityTextView.setText("" + number);
    }

    public void increment(View view) {
        if (quantity == 100){
            return;
        }
        quantity = quantity + 1;
            display(quantity);
    }

    public void decrement(View view) {
        if (quantity == 1) {
            return;
        }
        quantity = quantity - 1;
            display(quantity);

    }
    public void submitOrder(View view) {
        CheckBox haswhippedcream = (CheckBox) findViewById(R.id.checkbox);
        CheckBox haschocolate = (CheckBox) findViewById(R.id.choccheckbox);
        Boolean whippedcream = haswhippedcream.isChecked();
        Boolean chococream = haschocolate.isChecked();

        EditText et = (EditText) findViewById(R.id.name);
        Editable name = et.getText();
        int price = calculatePrice(whippedcream,chococream);
        String pricemessage = summary(whippedcream,chococream,price,name);
        composemail(pricemessage);



    }

    private void displaymessage(String x) {
        TextView messageTextView =  findViewById(R.id.oder_summary_text_view);
        messageTextView.setText(x);
    }

    private void displayprice(int number) {
        TextView ordersummaryTextView = (TextView) findViewById(R.id.oder_summary_text_view);
        ordersummaryTextView.setText(NumberFormat.getCurrencyInstance().format(number));

    }

    public int calculatePrice(boolean whipped, boolean choco)
    {
        int baseprice=5;
          if(whipped)
          {
              baseprice=baseprice+1;
          }
          if(choco)
          {
              baseprice+=2;
          }
        return quantity*baseprice;
    }

    public  String summary(Boolean whipped, Boolean choco, int price, Editable name){
        String pricemessage = "Name:"+ name;
        pricemessage = pricemessage + "\n Quantity"+quantity;
        pricemessage = pricemessage + "Whipped?"+whipped;
        pricemessage = pricemessage + "Chocolate? "+choco;
        pricemessage = pricemessage + "Total "+price;
        pricemessage = pricemessage + "Thanks!";
        return  pricemessage;
    }
    public  void composemail(String pricemessage){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:"));
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_TEXT,pricemessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);}
    }
}
