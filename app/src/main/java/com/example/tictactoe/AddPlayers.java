package com.example.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddPlayers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_players);

        EditText playerone = findViewById(R.id.player1);
        EditText playertwo = findViewById(R.id.player2);
        Button startgamebutton= findViewById(R.id.startgamebutton);

        startgamebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String getplayeroneName = playerone.getText().toString();
                String getplayertwoName = playertwo.getText().toString();

                if(getplayeroneName.isEmpty() || getplayertwoName.isEmpty())
                {
                    Toast.makeText(AddPlayers.this,"please enter player name",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Intent intent = new Intent(AddPlayers.this,MainActivity.class);
                    intent.putExtra("PlayerOne",getplayeroneName); // Ensure keys match
                    intent.putExtra("PlayerTwo",getplayertwoName); // Ensure keys match
                    startActivity(intent);
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
