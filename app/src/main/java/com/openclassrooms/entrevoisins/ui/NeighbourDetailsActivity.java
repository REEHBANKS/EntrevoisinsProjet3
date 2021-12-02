package com.openclassrooms.entrevoisins.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.ui.neighbour_list.MyNeighbourRecyclerViewAdapter;
import com.openclassrooms.entrevoisins.ui.neighbour_list.NeighbourFragment;

import java.util.List;
import java.util.Objects;

public class NeighbourDetailsActivity extends AppCompatActivity {

    public static String NEIGHBOUR_KEY = "NEIGHBOUR_KEY";
    public static String FAVORITENEIGHBOUR_KEY = "FAVORITENEIGHBOUR_KEy";
    private Neighbour neighbour;
    private NeighbourApiService mApiService;


    //TODO : A FAIRE -> mimiquer ce que j'ai fait pour neighbour avec isFavorite (ok)
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
       Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        mApiService = DI.getNeighbourApiService();


        neighbour = (Neighbour) getIntent().getSerializableExtra(NEIGHBOUR_KEY);
        //TODO : A FAIRE -> mimiquer ce que j'ai fait pour neighbour avec isFavorite (ok)
        isFavorite = getIntent().getBooleanExtra(FAVORITENEIGHBOUR_KEY, false);


        initUi();
        updateUi();

    }

    // TODO je veux verifier que Neighbourg n'est pas favori, si ce n'est pas le cas, je l'ajoute




    //TODO : Ici on instancie les éléments textView Image etc
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


                back.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent otherActivity2 = new Intent(getApplicationContext(),ListNeighbourActivity.class);
                startActivity(otherActivity2);

            }

        });




        //TODO : A FAIRE Mimiquer pour les autres élements de la vue
    }


    //TODO : Ici on met la valeur des texts et image en fonction de neighbour
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
            if (isFavorite) {
                favoriteButton.setActivated(false);
                isFavorite = false;
                mApiService.deleteFavoriteNeighbour(neighbour);


            } else {
                favoriteButton.setActivated(true);
                isFavorite = true;
                mApiService.addFavoriteNeighbour(neighbour);
            }
        });



    }




}
