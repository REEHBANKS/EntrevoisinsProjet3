package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.ClickFavoriteNeighbourEvent;
import com.openclassrooms.entrevoisins.events.ClickNeighbourEvent;
import com.openclassrooms.entrevoisins.events.DeleteFavoriteNeighbourEvent;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.NeighbourDetailsActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;


public class NeighbourFragment extends Fragment {

    private NeighbourApiService mApiService;
    private List<Neighbour> mNeighbours;
    private RecyclerView mRecyclerView;
    //TODO : J'ai ajouté une variable favori pour qu'on sache si le fragment est celui des voisins ou des favoris
    private boolean isFavorite;

    public static String FAVORITE_KEY = "FAVORITE_KEY";

    /**
     * Create and return a new instance
     * @return @{@link NeighbourFragment}
     */
    public static NeighbourFragment newInstance(boolean isFavorite) {
        NeighbourFragment fragment = new NeighbourFragment();
        //TODO J'ai ajouté ce code pour qu'on puisse instancier le fragment avec l'info de favori
        Bundle bundle = new Bundle();
        bundle.putBoolean(FAVORITE_KEY, isFavorite);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getNeighbourApiService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_neighbour_list, container, false);
        Context context = view.getContext();
        //TODO : C'est ici qu'on récupère l'information favoris
        assert getArguments() != null;
        isFavorite = getArguments().getBoolean(FAVORITE_KEY, false);
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        return view;
    }

    /**
     * Init the List of neighbours
     */
    private void initList() {
        if(isFavorite){
            mNeighbours = mApiService.getFavoriteNeighbours();
        }else {
            mNeighbours = mApiService.getNeighbours();
        }
        mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(mNeighbours, isFavorite));
    }

    @Override
    public void onResume() {
        super.onResume();
        initList();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    /**
     * Fired if the user clicks on a delete button
     * @param event : neighbour
     */
    @Subscribe
    public void onDeleteNeighbour(DeleteNeighbourEvent event) {
        mApiService.deleteNeighbour(event.neighbour);
        mApiService.deleteFavoriteNeighbour(event.neighbour);
        initList();
    }

    //TODO : Là on mimique la réception du click sur un neighbour
    /**
     * Fired if the user clicks on cell
     * @param event : neighbour
     */
    @Subscribe
    public void onClickNeighbour(ClickNeighbourEvent event) {
        launchDetailActivity(event.neighbour);
    }

    //TODO : A FAIRE -> Faire la mimique de la réception des delete pour un favorite neighbour (ok)

    /**
     * In the page favorite Fired if the user clicks on a delete button
     * @param event : favorite neighbour
     */
    @Subscribe
    public void onDeletefavoriteNeighbour(DeleteFavoriteNeighbourEvent event) {
    //TODO : A FAIRE -> Dans la fonction delete, appeler la fonction de l'apiService pour supprimer le favorite neighbour (ok)

            mApiService.deleteFavoriteNeighbour(event.neighbour);
            initList();

    }

    //TODO : A FAIRE -> Faire la mimique de la réception des clicks  pour un favorite neighbour (ok)
    /**
     * In the page favorite  Fired if the user clicks on cell
     * @param event : favorite neighbour
     */
    @Subscribe
    public void onClickFavoriteNeighbour(ClickFavoriteNeighbourEvent event) {
    //TODO : A FAIRE -> Dans la fonction click, lancer l'activité avec la valeur favorite true(ok)

            launchDetailActivity(event.neighbour);

    }

    private void launchDetailActivity(Neighbour neighbour) {
        Intent intent = new Intent(getActivity(), NeighbourDetailsActivity.class);
        intent.putExtra(NeighbourDetailsActivity.NEIGHBOUR_KEY, neighbour);
        intent.putExtra(NeighbourDetailsActivity.FAVORITENEIGHBOUR_KEY, mApiService.isNeighbourFavorite(neighbour));
        startActivity(intent);
    }


}
