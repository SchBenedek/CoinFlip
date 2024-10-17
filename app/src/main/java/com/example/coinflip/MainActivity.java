package com.example.coinflip;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView dobasok;
    private TextView gyozelem;
    private TextView vereseg;
    private Button fej_button;
    private Button iras_button;
    private ImageView kep;
    private Integer dobasok_szama=0;
    private Integer gyozelem_szama=0;
    private Integer vereseg_szama=0;
    private Random random;
    private Integer gepSzam=0;
    private Integer emberSzam=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        fej_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kep.setImageResource(R.drawable.heads);
                emberSzam=0;
                GepRandom();
                Jatek();
            }
        });
        iras_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kep.setImageResource(R.drawable.tails);
                emberSzam=1;
                GepRandom();
                Jatek();
            }
        });
    };

    public int GepRandom(){
        gepSzam=random.nextInt(2);
        if (gepSzam == 0) {
            Toast.makeText(MainActivity.this, "Fej", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "Írás", Toast.LENGTH_SHORT).show();
        }
        return gepSzam;
    };
    public void Jatek() {
        if(dobasok_szama<5)
        {
            if (emberSzam == gepSzam) {
                dobasok_szama++;
                dobasok.setText(String.format("Dobások: %s", dobasok_szama));
                gyozelem_szama++;
                gyozelem.setText(String.format("Győzelem: %s", gyozelem_szama));
            }
            if(emberSzam!=gepSzam){
                dobasok_szama++;
                dobasok.setText(String.format("Dobások: %s", dobasok_szama));
                vereseg_szama++;
                vereseg.setText(String.format("Vereség: %s", vereseg_szama));
            }
        }
        else {
            JatekVege();
        }
    };
    void JatekVege(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Szeretne új játékot kezdeni?");
        if(gyozelem_szama>vereseg_szama)
        {
            builder.setTitle("Győzelem");
        }
        else {
            builder.setTitle("Vereség");
        }
        builder.setPositiveButton("Nem", (DialogInterface.OnClickListener) (dialog, which) -> {
            // When the user click yes button then app will close
            finish();
        });
        builder.setNegativeButton("Igen", (DialogInterface.OnClickListener) (dialog, which) -> {
            // If user click no then dialog box is canceled.
            Ujjatek();
            dialog.cancel();
        });
        builder.show();
    };
    public void Ujjatek(){
        dobasok_szama=0;
        gyozelem_szama=0;
        vereseg_szama=0;
    }

    public void init(){
        dobasok=findViewById(R.id.dobasok);
        gyozelem=findViewById(R.id.gyozelem);
        vereseg=findViewById(R.id.vereseg);
        fej_button=findViewById(R.id.fej_button);
        iras_button=findViewById(R.id.iras_button);
        kep=findViewById(R.id.kep);
        random=new Random();
    }
}