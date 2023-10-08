package com.generator.megalottomillions;


import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.robinhood.ticker.TickerUtils;
import com.robinhood.ticker.TickerView;

import java.util.Random;



public class MainActivity extends AppCompatActivity {

    Button btngenerate;
    BottomNavigationView bottomNavigationView;
    private AdView mAdView;
    private AdRequest adRequest;
    private InterstitialAd mInterstitialAd;
    private int clickCounter = 0;

    //ID ANUNCIO ORIGINAL
    private static final String AD_UNIT_ID = "ca-app-pub-4434685305331116/1102015796";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        int layoutResourceId = 0;
        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "ad_clicked");
        mFirebaseAnalytics.logEvent("ad_clicked", bundle);

        // Obtener la densidad de píxeles de la pantalla
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float density = displayMetrics.densityDpi;

        // Asignar el layout resource id según la densidad de píxeles
        if (density <= DisplayMetrics.DENSITY_MEDIUM) {
            // Pantalla con densidad de píxeles de 160 dpi - 320x480
            //setContentView(layout_320x480);
            layoutResourceId = R.layout.activity_main;

        } else if (density <= DisplayMetrics.DENSITY_HIGH) {
            // Pantalla con densidad de píxeles de 240 dpi - 480x800
            layoutResourceId = R.layout.layout_480x800;

        } else if (density <= DisplayMetrics.DENSITY_XHIGH) {
            // Pantalla con densidad de píxeles de 320 dpi - 720x1280
            //setContentView(layout_720x1280);
            layoutResourceId = R.layout.activity_main;

        } else if (density <= DisplayMetrics.DENSITY_XXHIGH) {
            // Pantalla con densidad de píxeles de 480 dpi - 1080x1920
            layoutResourceId = R.layout.layout_1080x1920;

        } else if (density <= DisplayMetrics.DENSITY_XXXHIGH) {
            // Pantalla con densidad de píxeles de 640 dpi - 1440x2560
            layoutResourceId = R.layout.layout_1440x2560;

        } else {
            // Otras densidades de píxeles
            layoutResourceId = R.layout.activity_main;
        }
        setContentView(layoutResourceId);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {
            }
        });
        //carga Banner
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdClicked() {
                FirebaseAnalytics.getInstance(MainActivity.this).logEvent("ad_clicked", null);
            }

            @Override
            public void onAdClosed() {
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                FirebaseAnalytics.getInstance(MainActivity.this).logEvent("ad_load_failed", null);
            }

            @Override
            public void onAdImpression() {
                FirebaseAnalytics.getInstance(MainActivity.this).logEvent("ad_impression", null);
            }

            @Override
            public void onAdLoaded() {
                FirebaseAnalytics.getInstance(MainActivity.this).logEvent("ad_loaded", null);
            }

            @Override
            public void onAdOpened() {
                FirebaseAnalytics.getInstance(MainActivity.this).logEvent("ad_opened", null);
            }

        });
        // Cargar el anuncio intersticial
        InterstitialAd.load(this,AD_UNIT_ID, adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // El anuncio se cargó correctamente
                        mInterstitialAd = interstitialAd;
                        Log.d("MainActivity", "Anuncio intersticial cargado");
                    }
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Error al cargar el anuncio
                        //Log.d("MainActivity", loadAdError.toString());
                        Log.d("MainActivity", "Error al cargar anuncio intersticial");
                        mInterstitialAd = null;
                    }
                });


//GENERADOR DE LOTO PARA MEGAMILLIONS -- 5/70 + 1/25
        Button btgenerar = (Button) findViewById(R.id.btngenerate);
        int[] megamill = {R.id.txtmegam41, R.id.txtmegam42, R.id.txtmegam43, R.id.txtmegam44, R.id.txtmegam45, R.id.txtmegam46, R.id.txtmegam51, R.id.txtmegam52, R.id.txtmegam53, R.id.txtmegam54, R.id.txtmegam55, R.id.txtmegam56, R.id.txtmegam5, R.id.txtmegam4, R.id.txtmegam3, R.id.txtmegam2, R.id.txtmegam, R.id.txtmegam1, R.id.txtmm5, R.id.txtmm4, R.id.txtmm3, R.id.txtmm2, R.id.txtmm, R.id.txtmm21, R.id.txtmegam10, R.id.txtmegam9, R.id.txtmegam8, R.id.txtmegam7, R.id.txtmegam6, R.id.txtmegam31};

        btgenerar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickCounter++;
                // Generar número aleatorio entre 2 y 7
                int randomNum = new Random().nextInt(5) + 2;

                if (clickCounter >= randomNum) {
                    if (mInterstitialAd != null) {
                        mInterstitialAd.show(MainActivity.this);
                        mInterstitialAd = null;
                        InterstitialAd.load(getApplicationContext(), AD_UNIT_ID, adRequest, new InterstitialAdLoadCallback() {
                            @Override
                            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                                super.onAdLoaded(interstitialAd);
                                mInterstitialAd = interstitialAd;
                            }
                            @Override
                            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                super.onAdFailedToLoad(loadAdError);
                                mInterstitialAd = null;
                            }
                        });
                        clickCounter = 0;
                    }
                }
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                int[] results = new int[30];
                                Random r = new Random();
                                results[0] = r.nextInt(15) + 1; // 1 - 15
                                results[1] = r.nextInt(15) + 16; // 16-30
                                results[2] = r.nextInt(15) + 31; // 31-45
                                results[3] = r.nextInt(15) + 46; // 46-60
                                results[4] = r.nextInt(10) + 61; // 61-71
                                results[5] = r.nextInt(25) + 1; // 1-25

                                results[6] = r.nextInt(15) + 1; // 1 - 15
                                results[7] = r.nextInt(15) + 16; // 16-30
                                results[8] = r.nextInt(15) + 31; // 31-45
                                results[9] = r.nextInt(15) + 46; // 46-60
                                results[10] = r.nextInt(10) + 61; // 61-71
                                results[11] = r.nextInt(25) + 1; // 1-25

                                results[12] = r.nextInt(15) + 1; // 1 - 15
                                results[13] = r.nextInt(15) + 16; // 16-30
                                results[14] = r.nextInt(15) + 31; // 31-45
                                results[15] = r.nextInt(15) + 46; // 46-60
                                results[16] = r.nextInt(10) + 61; // 61-71
                                results[17] = r.nextInt(25) + 1; // 1-25

                                results[18] = r.nextInt(15) + 1; // 1 - 15
                                results[19] = r.nextInt(15) + 16; // 16-30
                                results[20] = r.nextInt(15) + 31; // 31-45
                                results[21] = r.nextInt(15) + 46; // 46-60
                                results[22] = r.nextInt(10) + 61; // 61-71
                                results[23] = r.nextInt(25) + 1; // 1-25

                                results[24] = r.nextInt(15) + 1; // 1 - 15
                                results[25] = r.nextInt(15) + 16; // 16-30
                                results[26] = r.nextInt(15) + 31; // 31-45
                                results[27] = r.nextInt(15) + 46; // 46-60
                                results[28] = r.nextInt(10) + 61; // 61-71
                                results[29] = r.nextInt(25) + 1; // 1-25
                                for (int i = 0; i < 30; i++) {
                                    TickerView tickerView = (TickerView) findViewById(megamill[i]);
                                        tickerView.setCharacterList(TickerUtils.getDefaultNumberList());
                                    tickerView.setText("0"); // Añade esta línea para inicializar el TickerView en 0
                                    tickerView.setText(String.valueOf(results[i]));
                                }
                                handler.postDelayed(this, 1000);
                                handler.removeCallbacks(this);
                            }
                        });
                    }
                }, 100);

            }
        });
    }
    // Método para mostrar el anuncio intersticial
    public void showInterstitial() {
        if (mInterstitialAd != null) {
            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdClicked() {
                    FirebaseAnalytics.getInstance(MainActivity.this).logEvent("ad_clicked", null);
                    Log.d(TAG, "El anuncio se clickeo.");
                }

                @Override
                public void onAdDismissedFullScreenContent() {
                    // Se llama cuando se cierra el anuncio
                    Log.d("MainActivity", "El anuncio se cerró.");
                    mInterstitialAd = null;
                    // Recargamos el anuncio para que esté listo para el siguiente botón
                }

                @Override
                public void onAdFailedToShowFullScreenContent(AdError adError) {
                    // Se llama si hay un error al mostrar el anuncio
                    Log.d("MainActivity", "No se pudo mostrar el anuncio.");
                    mInterstitialAd = null;
                }

                @Override
                public void onAdImpression() {
                    FirebaseAnalytics.getInstance(MainActivity.this).logEvent("ad_impression", null);
                    Log.d(TAG, "Ad recorded an impression.");
                }

                @Override
                public void onAdShowedFullScreenContent() {
                    // Se llama cuando el anuncio se muestra correctamente
                    Log.d("MainActivity", "El anuncio se mostró.");
                }
            });
            mInterstitialAd.show(this);
        } else {
            Log.d("MainActivity", "El anuncio no está listo todavía.");
        }

    }

}




