package com.example.naman.firebasedatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {


    private static EditText workspace;
    private static DatabaseReference databaseReference;

    public void updateWorkspace (DataSnapshot dataSnapshot)
    {
        Toast.makeText(this.getApplicationContext(),dataSnapshot.getValue().toString(),Toast.LENGTH_SHORT).show();
        WorkSpace work = dataSnapshot.getValue(WorkSpace.class);
        workspace.setText(work.getWork());
        //getActivity().getApplicationContext()
         Toast.makeText(this.getApplicationContext(),"byee",Toast.LENGTH_SHORT).show();
    }

    public static void updateFirebase (String s){

            WorkSpace work = new WorkSpace(s);
            databaseReference.child("-LboEi2euNhbVw20HOXJ").setValue(work);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseReference = FirebaseDatabase.getInstance().getReference("workspace");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Toast.makeText(getApplicationContext(),"enter",Toast.LENGTH_SHORT).show();
                updateWorkspace(dataSnapshot);
                Toast.makeText(getApplicationContext(),"exit",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        workspace = (EditText) findViewById(R.id.workspace);
        workspace.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateFirebase(workspace.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });




    }

}





