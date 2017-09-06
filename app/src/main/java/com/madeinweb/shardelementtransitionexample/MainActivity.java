package com.madeinweb.shardelementtransitionexample;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity
{
//    example retirado do link abaixo
//    http://mikescamell.com/shared-element-transitions-part-2/
    private SimpleFragmentA simpleFragmentA;
    private SimpleFragmentB simpleFragmentB;
    private boolean fgFramentA;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        fgFramentA = true;
        simpleFragmentA = SimpleFragmentA.newInstance();
        simpleFragmentB = SimpleFragmentB.newInstance();

        getSupportFragmentManager()
        .beginTransaction()
        .add(R.id.content, simpleFragmentA)
        .commit();

        Button button = (Button) findViewById(R.id.fragment_a_btn);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                {
                    if(fgFramentA) {
                        fgFramentA = false;
                        ImageView imageView = simpleFragmentA.getmImageView();
                        getSupportFragmentManager()
                                .beginTransaction()
                                .addSharedElement(imageView, ViewCompat.getTransitionName(imageView))
                                .addToBackStack(TAG)
                                .replace(R.id.content, simpleFragmentB)
                                .commit();
                    }
                    else
                    {
                        fgFramentA = true;
                        ImageView imageView = simpleFragmentB.getmImageView();
                        getSupportFragmentManager()
                                .beginTransaction()
                                .addSharedElement(imageView, ViewCompat.getTransitionName(imageView))
                                .addToBackStack(TAG)
                                .replace(R.id.content, simpleFragmentA)
                                .commit();
                    }
                }
            }
        });
    }
}
