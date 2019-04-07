package com.example.naman.firebasedatabase;

import android.content.Context;
import android.widget.Toast;
import java.util.Date;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class CallReceiver extends com.example.naman.firebasedatabase.PhonecallReceiver {

    private DatabaseReference databaseReference;

    CallReceiver()
    {
        databaseReference = FirebaseDatabase.getInstance().getReference("phonecall");
    }
    @Override
    protected void onIncomingCallStarted(Context ctx, String number, Date start) {
        Toast.makeText(ctx.getApplicationContext(), "Incoming call started", Toast.LENGTH_SHORT).show();
        notifier("Incoming call started");
    }

    @Override
    protected void onOutgoingCallStarted(Context ctx, String number, Date start) {
        Toast.makeText(ctx.getApplicationContext(), "Outgoing call started", Toast.LENGTH_SHORT).show();
        notifier("Outgoing call started");
    }

    @Override
    protected void onIncomingCallEnded(Context ctx, String number, Date start, Date end) {
        Toast.makeText(ctx.getApplicationContext(), "Incoming call ended", Toast.LENGTH_SHORT).show();
        notifier("Incoming call ended");
    }

    @Override
    protected void onOutgoingCallEnded(Context ctx, String number, Date start, Date end) {
        Toast.makeText(ctx.getApplicationContext(), "Outgoing call ended", Toast.LENGTH_SHORT).show();
        notifier("Outgoing call ended");
    }

    @Override
    protected void onMissedCall(Context ctx, String number, Date start) {
        Toast.makeText(ctx.getApplicationContext(), "Missed call", Toast.LENGTH_SHORT).show();
        notifier("Missed call");
    }


    protected void notifier(String status) {

        databaseReference.setValue(status);
    }
}