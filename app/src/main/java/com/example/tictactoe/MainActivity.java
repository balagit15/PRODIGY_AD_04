package com.example.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tictactoe.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private final List<int[]> combinationList = new ArrayList<>();
    private int[] boxPosition = {0,0,0,0,0,0,0,0,0};
    private int playerTurn=1;
    private int totalSelectedBoxes = 0; // Start with 0

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());

        combinationList.add(new int[] {0,1,2});
        combinationList.add(new int[] {3,4,5});
        combinationList.add(new int[] {6,7,8});
        combinationList.add(new int[] {0,3,6});
        combinationList.add(new int[] {1,4,7});
        combinationList.add(new int[] {2,5,8});
        combinationList.add(new int[] {2,4,6});
        combinationList.add(new int[] {0,4,8});

        String getPlayerOneName = getIntent().getStringExtra("PlayerOne");
        String getPlayerTwoName = getIntent().getStringExtra("PlayerTwo");

        binding.playeronename.setText(getPlayerOneName);
        binding.playertwoname.setText(getPlayerTwoName);

        binding.image1.setOnClickListener(v -> performAction((ImageView) v, 0));
        binding.image2.setOnClickListener(v -> performAction((ImageView) v, 1));
        binding.image3.setOnClickListener(v -> performAction((ImageView) v, 2));
        binding.image4.setOnClickListener(v -> performAction((ImageView) v, 3));
        binding.image5.setOnClickListener(v -> performAction((ImageView) v, 4));
        binding.image6.setOnClickListener(v -> performAction((ImageView) v, 5));
        binding.image7.setOnClickListener(v -> performAction((ImageView) v, 6));
        binding.image8.setOnClickListener(v -> performAction((ImageView) v, 7));
        binding.image9.setOnClickListener(v -> performAction((ImageView) v, 8));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void performAction(ImageView imageView, int selectedBoxPosition) {
        if (boxPosition[selectedBoxPosition] != 0) {
            return;
        }

        boxPosition[selectedBoxPosition] = playerTurn;
        totalSelectedBoxes++;

        if (playerTurn == 1) {
            imageView.setImageResource(R.drawable.xicon2);
        } else {
            imageView.setImageResource(R.drawable.oicon2);
        }

        if (checkResults()) {
            ResultDialog resultDialog = new ResultDialog(MainActivity.this, (playerTurn == 1 ? binding.playeronename.getText().toString() : binding.playertwoname.getText().toString()) + " is the Winner!!!", MainActivity.this);
            resultDialog.setCancelable(false);
            resultDialog.show();
        } else if (totalSelectedBoxes == 9) {
            ResultDialog resultDialog = new ResultDialog(MainActivity.this, "Match Draw!!", MainActivity.this);
            resultDialog.setCancelable(false);
            resultDialog.show();
        } else {
            changePlayerTurn();
        }
    }

    private void changePlayerTurn() {
        playerTurn = playerTurn == 1 ? 2 : 1;

        if (playerTurn == 1) {
            binding.playeronelayout.setBackgroundResource(R.drawable.black_border);
            binding.playertwolayout.setBackgroundResource(R.drawable.white_box);
        } else {
            binding.playertwolayout.setBackgroundResource(R.drawable.black_border);
            binding.playeronelayout.setBackgroundResource(R.drawable.white_box);
        }
    }

    private boolean checkResults() {
        boolean response = false;
        for (int[] combination : combinationList) {
            if (boxPosition[combination[0]] == playerTurn && boxPosition[combination[1]] == playerTurn && boxPosition[combination[2]] == playerTurn) {
                response = true;
                break;
            }
        }
        return response;
    }

    public void restartMatch() {
        boxPosition = new int[] {0,0,0,0,0,0,0,0,0};
        playerTurn = 1;
        totalSelectedBoxes = 0;

        binding.image1.setImageResource(R.drawable.white_box);
        binding.image2.setImageResource(R.drawable.white_box);
        binding.image3.setImageResource(R.drawable.white_box);
        binding.image4.setImageResource(R.drawable.white_box);
        binding.image5.setImageResource(R.drawable.white_box);
        binding.image6.setImageResource(R.drawable.white_box);
        binding.image7.setImageResource(R.drawable.white_box);
        binding.image8.setImageResource(R.drawable.white_box);
        binding.image9.setImageResource(R.drawable.white_box);
    }
}
