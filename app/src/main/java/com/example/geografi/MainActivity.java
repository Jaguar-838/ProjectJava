package com.example.geografi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<View> allEds;
    public static final String APP_PREFERENCES = "mysetting";
    public static final String APP_PREFERENCES_COUNTER = "counter";
    private SharedPreferences mSettings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        shablon("karta","title","tema",23);
    }

    private void shablon(String karta, String title, String tema, int kol)
    {
        LinearLayout sps = findViewById(R.id.spisok1);

        allEds = new ArrayList<View>();

        final LinearLayout linear = (LinearLayout) findViewById(R.id.spisok1);

        //берем наш кастомный лейаут находим через него все наши кнопки и едит тексты, задаем нужные данные

        for(int i=1; i<=kol; i++) {
            final View view = getLayoutInflater().inflate(R.layout.cartochka, null);
            ImageView img = view.findViewById(R.id.img);
            TextView tt = view.findViewById(R.id.titletxt);
            TextView dt = view.findViewById(R.id.datetxt);

            String mDrawableName ="m"+karta + i;
            int resID = getResources().getIdentifier(mDrawableName, "drawable", getPackageName());
            img.setImageResource(resID);

            String mStringName1 = "@string/"+title+ i;
            int resID2 = getResources().getIdentifier(mStringName1, "strings", getPackageName());
            tt.setText(resID2);

            String mStringName = "@string/"+ tema + i;
            int resID1 = getResources().getIdentifier(mStringName, "strings", getPackageName());
            dt.setText(resID1);

            CardView btn = view.findViewById(R.id.cardvi);
            int finalI = i;
            btn.setOnClickListener(new View.OnClickListener() {
                String q =Integer.toString(finalI);
                @Override
                public void onClick(View view) {
                    mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = mSettings.edit();
                    editor.putString("kartinka", karta+q);
                    editor.putString("title","@string/"+title+q);
                    editor.putString("taxt","@string/"+tema+q);
                    editor.apply();
                    Intent tmm1 = new Intent(MainActivity.this,ShablonStr.class);
                    tmm1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(tmm1);
                }
            });

            //добавляем все что создаем в массив
            allEds.add(view);

            //добавляем елементы в linearlayout
            linear.addView(view);
        }
    }
}