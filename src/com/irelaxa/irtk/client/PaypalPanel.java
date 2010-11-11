package com.irelaxa.irtk.client;

import com.google.gwt.user.client.ui.*;
import com.google.gwt.i18n.client.NumberFormat;

// Copyright DavesPlanet.net - all rights reserved.
// You may make use of this code if ALL of the following criteria are met:
// 1) This copyright must remain intact in the source code
// 2) There must be a visible link somewhere on the site where this is
//    used back to http://DavesPlanet.net without using re="nofollow"
// Please don't steal, these requirements are very reasonable considering the amount of work I have put into these


public class PaypalPanel extends Composite {

    // Be sure to look at the PaypalIPN.php file for a secure indication that your customer has paid you. //
    // The IPN script is called by PayPal and the script then calls the PayPal server back to verify the
    // contact was legitimate (that your customer didn't try to hack your IPN script).

    // Setting this up was a complete pain because all the samples
    // on the web seem to be the same copy/past of a non-working script.

    // The Paypal Instant Payment Notification URL can be setup in this form or
    // (I prefer) statically in your paypal options.


    // Notice how the FormPanel takes an empyt string. If you use the null constructor
    // it does an Ajax-like call in the background, and if you give it a URL it pops in a new window.
    // By experimentation I found that the empty string constructor causes the form
    // to navigate the current page to the form action, I didn't find that documented anywhere!

    //this is what you see when you get to PayPal...
//    Your Description 1 $12.34 3 $37.02
//    Your Description 2 $24.68 6 $148.08
//    Your Description 3 $37.02 9 $333.18
//     Item total: $518.28
//    Sales tax: $4.55
//    Total: $522.83 USD


    private Panel wrapper;
    private static final NumberFormat currency = NumberFormat.getCurrencyFormat();

    public PaypalPanel() {
        wrapper = new VerticalPanel();
        initWidget(wrapper);
    }

    private FormPanel createPanel(final Object cart) {
        final FormPanel form = new FormPanel("");
        form.setAction("https://www.paypal.com/us/cgi-bin/webscr");
        form.setMethod(FormPanel.METHOD_POST);

        VerticalPanel panel = new VerticalPanel();
        form.setWidget(panel);

        panel.add(new Hidden("cmd","_cart"));
        panel.add(new Hidden("business","sumi007@gmail.com"));
        panel.add(new Hidden("upload","1"));
        panel.add(new Hidden("item_name","Photography prints"));
        panel.add(new Hidden("invoice","12345"));
//        panel.add(new Hidden("amount",GWTUtils.currency.format(cart.totalCost()))); //use this instead of individual items
        panel.add(new Hidden("tax_cart",currency.format(4.55)));
        panel.add(new Hidden("currency_code","USD"));
        panel.add(new Hidden("cancel_return","http://www.irelaxa.com"));
        panel.add(new Hidden("return","http://www.irelaxa.com"));

        for (int i=1;i<=3;i++) {
            panel.add(new Hidden("item_name_" + i, "Your Description " + i));
            //$12.34 each for the first item, $24.69 each for the second item, $37.03 each for the third
            panel.add(new Hidden("amount_" + i, currency.format(i * 12.34)));
            //3 of the first item, 6 of the second, and 9 of the third item
            panel.add(new Hidden("quantity_" + i, String.valueOf(i * 3)));
        }

        panel.add(new Button("Buy Now", new ClickListener() {
            public void onClick(Widget widget) {
                form.submit();
//                cart.saveCartToDatabase(form);
//                cart.incrementCartIdAndSaveCookie();
            }
        }));
        return form;
    }


    public void update(Object cart) {
        //create your own Cart class to suit your app and call this update method to update the paypal panel
        wrapper.clear();
        wrapper.add(createPanel(cart));
    }
}
