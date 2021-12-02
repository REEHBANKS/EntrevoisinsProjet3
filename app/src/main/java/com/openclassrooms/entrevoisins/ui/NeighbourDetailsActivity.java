package com.openclassrooms.entrevoisins.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

public class NeighbourDetailsActivity extends AppCompatActivity {

    public static String NEIGHBOUR_KEY = "NEIGHBOUR_KEY";
    public static String FAVORITE_NEIGHBOUR_KEY = "FAVORITENEIGHBOUR_KEY";
    private Neighbour neighbour;
    private NeighbourApiService mApiService;
    private boolean isFavorite;

    FloatingActionButton favoriteButton;
    TextView prenom;
    ImageView image;
    TextView prenom2;
    TextView tel;
    TextView mail;
    TextView location;
    TextView about;
    ImageButton back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_details);
        mApiService = DI.getNeighbourApiService();
        neighbour = (Neighbour) getIntent().getSerializableExtra(NEIGHBOUR_KEY);
        isFavorite = getIntent().getBooleanExtra(FAVORITE_NEIGHBOUR_KEY, false);
        initUi();
        updateUi();
    }

    private void initUi() {
        favoriteButton = findViewById(R.id.favorite_button);
        prenom = findViewById(R.id.prenom);
        image = findViewById(R.id.avatar_picture);
        prenom2 = findViewById(R.id.prenom2);
        tel = findViewById(R.id.telephone);
        mail = findViewById(R.id.facebook);
        location = findViewById(R.id.location);
        about = findViewById(R.id.aboutMe);
        back = findViewById(R.id.back_button);
    }

    public void updateUi() {
        Glide.with(image.getContext())
                .load(neighbour.getAvatarUrl())
                .into(image);

        prenom.setText(neighbour.getName());
        prenom2.setText((neighbour.getName()));
        tel.setText(neighbour.getPhoneNumber());
        location.setText(neighbour.getAddress());
        about.setText(neighbour.getAboutMe());
        mail.setText(neighbour.getMail());
        favoriteButton.setActivated(isFavorite);

        favoriteButton.setOnClickListener(v -> {
            favoriteButton.setActivated(!favoriteButton.isActivated());
            if (favoriteButton.isActivated()) {
                mApiService.addFavoriteNeighbour(neighbour);
            } else {
                mApiService.deleteFavoriteNeighbour(neighbour);
            }
        });

        back.setOnClickListener(view -> finish());
    }
}
