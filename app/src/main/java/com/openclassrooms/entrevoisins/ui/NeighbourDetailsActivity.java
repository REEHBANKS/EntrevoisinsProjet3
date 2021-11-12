package com.openclassrooms.entrevoisins.ui;

import android.content.Intent;
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
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.ui.neighbour_list.MyNeighbourRecyclerViewAdapter;
import com.openclassrooms.entrevoisins.ui.neighbour_list.NeighbourFragment;

import java.util.List;

public class NeighbourDetailsActivity extends AppCompatActivity {

    public static String NEIGHBOUR_KEY = "NEIGHBOUR_KEY";
    public static String FAVORITENEIGHBOUR_KEY = "FAVORITENEIGHBOUR_KEy";
    private Neighbour neighbour;
    private NeighbourApiService mApiService;


    //TODO : A FAIRE -> mimiquer ce que j'ai fait pour neighbour avec isFavorite (ok)
    private boolean isFavorite;

    FloatingActionButton favoriteButton;
    TextView prenom;
    ImageButton back;
    ImageView image;
    TextView prenom2;
    TextView tel;
    TextView mail;
    TextView location;
    TextView about;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_details);

        neighbour = (Neighbour) getIntent().getSerializableExtra(NEIGHBOUR_KEY);
        //TODO : A FAIRE -> mimiquer ce que j'ai fait pour neighbour avec isFavorite (ok)
        isFavorite = getIntent().getBooleanExtra(FAVORITENEIGHBOUR_KEY, false);


        initUi();
        updateUi();

    }

    @Override
    protected void onStop(){
        if(isFavorite) {
            mApiService.addFavoriteNeighbour(neighbour);
        } else {
            mApiService.deleteFavoriteNeighbour(neighbour);
        }
        super.onStop();

    }


    //TODO : Ici on instancie les éléments textView Image etc
    private void initUi() {
        favoriteButton = findViewById(R.id.favorite_button);
        prenom = findViewById(R.id.prenom);
        back = findViewById(R.id.back_arrow);
        image = findViewById(R.id.avatar_picture);
        prenom2 = findViewById(R.id.prenom2);
        tel = findViewById(R.id.telephone);
        mail = findViewById(R.id.facebook);
        location = findViewById(R.id.location);
        about = findViewById(R.id.aboutMe);




        //TODO : A FAIRE Mimiquer pour les autres élements de la vue
    }



    //TODO : Ici on met la valeur des texts et image en fonction de neighbour
    public void updateUi() {

        //Glide.with(image.getContext())
               // .load(neighbour.getAvatarUrl())
               // .into(image);

        prenom.setText(neighbour.getName());

        favoriteButton.setOnClickListener(v -> {
            if (isFavorite) {
                favoriteButton.setActivated(false);
                isFavorite = false;
            } else {
                favoriteButton.setActivated(true);
                isFavorite = true;
            }

        });

         back.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent ww = new Intent(getApplicationContext(), ListNeighbourActivity.class);
                 startActivity(ww);

             }
         });

          }
}
