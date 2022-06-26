//Classusedisintent
//java-mainactivity-new-activity-empty
//agrkoicheezsirfdisplaykrnihtohuskiclassobjectidmtbnao
package com.example.prakharsharma.threepages;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=(Button)findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(MainActivity.this,Second.class);
                startActivity(i1);
                finish();
            }
        });
    }
}
