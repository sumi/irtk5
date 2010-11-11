<?php
require ("setupdatabase.php");

$req = 'cmd=_notify-validate';

foreach ($_POST as $key => $value) {
	$value = stripslashes($value);//urlencode(stripslashes($value));
	$req .= "&$key=$value";
	$$key = $value; //create local variables...
}

// post back to PayPal system to validate
$header .= "POST /cgi-bin/webscr HTTP/1.0\r\n";
$header .= "Content-Type: application/x-www-form-urlencoded\r\n";
$header .= "Content-Length: " . strlen($req) . "\r\n\r\n";
$fp = fsockopen ("www.paypal.com", 80, $errno, $errstr, 30);
$result = "UNKNOWN"; //cant think of a valid situation where this won't get replaced...
if (!$fp) {
	mail("Sales@Nowhere.com", "PAYPAL SOCKET FAILURE", "Invoice number is $invoice\n\nfor $payment_gross\n\nfrom $first_name $last_name $payer_email\n\nwas not validated against paypal because of socket error $errno $errstr");
} else {
	fputs ($fp, $header . $req);
	$res = "";
	while (!feof($fp)) {
		$res .= fgets ($fp, 1024);
	}
	$separator = strpos ($res, "\r\n\r\n");
	$result = substr($res, $separator + 4);
	if ($result == "UNVERIFIED" || $result == "INVALID" || $result == "UNKNOWN") {
		mail("Sales@Nowhere.com", "INVALID Order Received - hack attack?", "Invoice $invoice\n\nfor $payment_gross\n\nfrom $first_name $last_name $payer_email\n\n$result");
	}
	else {

	// check the payment_status is Completed
	// check that txn_id has not been previously processed
	// check that receiver_email is your Primary PayPal email
	// check that payment_amount/payment_currency are correct
	// process payment

            if (strcmp ($payment_status, "Completed") == 0) {
                $message .= "Dear Customer,\n Thank you for your order.\n\nYour invoice number is $invoice, and your order will be processed right away.\n\nIf you have any problems, please contact us: \n\nsales\@Nowhere.com";
                mail($payer_email, "Your photo order", $message, "From: Sales@Nowhere.com\nReply-To: Sales@Nowhere.com");
                mail("Sales@Nowhere.com", "Order Received", "Order number $item_number\n\nfor $payment_gross\n\nfrom $first_name $last_name $payer_email\n\nwas received\n\n$result");
            } else {
                $message .= "Dear Customer,\n Thank you for your order.\n\nYour invoice number is $invoice, and your order will be processed shortly.\n\nThank you \n\nsales\@Nowhere.com";
                mail($payer_email, "Your photo order", $message, "From: Sales@Nowhere.com\nReply-To: Sales@Nowhere.com");
                mail("Sales@Nowhere.com", "INCOMPLETE Order Received", "Invoice number is $invoice\n\nfor $payment_gross\n\nfrom $first_name $last_name $payer_email\n\nwas received with status $payment_status\n\n$result");
            }
	}
	$qry = "INSERT into sales (invoice, receiver_email, payment_status, pending_reason, payment_date, payment_gross, payment_fee, txn_id, txn_type, first_name, last_name, address_street, address_city, address_state, address_zip, address_country, address_status, payer_email, payer_status, payment_type, notify_version , verify_sign, result)
	VALUES( \"$invoice\", \"$receiver_email\", \"$payment_status\", \"$pending_reason\", \"$payment_date\", \"$payment_gross\", \"$payment_fee\", \"$txn_id\", \"$txn_type\", \"$first_name\", \"$last_na me\", \"$address_street\", \"$address_city\", \"$address_state\", \"$address_zip \", \"$address_country\", \"$address_status\", \"$payer_email\", \"$payer_status \", \"$payment_type\", \"$notify_version\", \"$verify_sign\" , \"$result\")";
	$result = mysql_query($qry,$db);
	mysql_close($db);
}

fclose ($fp);
?>