package mx.uv.fiee.iinf.tyam.API.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import mx.uv.fiee.iinf.tyam.API.ApiContants;
import mx.uv.fiee.iinf.tyam.API.SwService;
import mx.uv.fiee.iinfo.swinfo.Models.People;
import mx.uv.fiee.iinfo.swinfo.Models.Planet;
import mx.uv.fiee.iinfo.swinfo.Models.Vehicle;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import mx.uv.fiee.iinf.tyam.R;

public class Fragments extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<String> arrayList;
    Retrofit retrofit;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);
        recyclerView = view.findViewById(R.id.rvFragment);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));

        if (getArguments() != null) {
            int pos = getArguments().getInt("pos");
            switch (pos){
                case 0: getPlanets();
                    break;
                case 1: getPeople();
                    break;
                case 2: getVehicles();
                    break;
            }
        }
        return view;
    }
    private void getPlanets(){
        retrofit = new Retrofit.Builder ()
                .baseUrl (ApiContants.API_BASE_URL)
                .addConverterFactory (GsonConverterFactory.create ())
                .build ();
        SwService service = retrofit.create (SwService.class);
        Call<Resultado<Planet>> planets = service.getPlanets();
        planets.enqueue(new Callback<Resultado<Planet>>() {
            @Override
            public void onResponse(Call<Resultado<Planet>> call, Response<Resultado<Planet>> response) {
                if(response.isSuccessful()){
                    arrayList = new ArrayList<>();
                    ArrayList<Planet> results = response.body().getResults();
                    for(Planet planet: results){
                        //arrayList.add(new String(String.format("Population: %s Diameter: %s %s",planet.getPopulation(),planet.getDiameter(), planet.getName())));
                        arrayList.add(planet.getName());
                        arrayList.add(String.format("Diameter: %s",planet.getDiameter()));
                        arrayList.add(String.format("Population: %s",planet.getPopulation()));
                    }
                    Adapter adapter = new Adapter(arrayList);
                    recyclerView.setAdapter(adapter);
                }
            }
            @Override
            public void onFailure(Call<Resultado<Planet>> call, Throwable t) {
                Toast.makeText(getContext(), "Error al descargar el archivo", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getVehicles(){
        retrofit = new Retrofit.Builder ()
                .baseUrl (ApiContants.API_BASE_URL)
                .addConverterFactory (GsonConverterFactory.create ())
                .build ();
        SwService service = retrofit.create (SwService.class);
        Call<Resultado<Vehicle>> vehicles = service.getVehicles();
        vehicles.enqueue(new Callback<Resultado<Vehicle>>() {
            @Override
            public void onResponse(Call<Resultado<Vehicle>> call, Response<Resultado<Vehicle>> response) {
                if(response.isSuccessful()){
                    arrayList = new ArrayList<>();
                    ArrayList<Vehicle> results = response.body().getResults();
                    for(Vehicle vehicle: results){
                        arrayList.add(vehicle.getName());
                        arrayList.add(String.format("Manufacturer: %s",vehicle.getManufacturer()));
                        arrayList.add(String.format("Model: %s",vehicle.getModel()));
                    }
                    Adapter adapter = new Adapter(arrayList);
                    recyclerView.setAdapter(adapter);
                }
            }
            @Override
            public void onFailure(Call<Resultado<Vehicle>> call, Throwable t) {
                Toast.makeText(getContext(), "Error al descargar el archivo", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getPeople(){
        retrofit = new Retrofit.Builder ()
                .baseUrl (ApiContants.API_BASE_URL)
                .addConverterFactory (GsonConverterFactory.create ())
                .build ();
        SwService service = retrofit.create (SwService.class);
        Call<Resultado<People>> people = service.getPeople();
        people.enqueue(new Callback<Resultado<People>>() {
            @Override
            public void onResponse(Call<Resultado<People>> call, Response<Resultado<People>> response) {
                if(response.isSuccessful()){
                    arrayList = new ArrayList<>();
                    ArrayList<People> results = response.body().getResults();
                    for(People people: results){
                        arrayList.add(people.getName());
                        arrayList.add(String.format("Birth Year: %s", people.getBirth_year()));
                        arrayList.add(String.format("Height: %s",people.getHeight()));
                    }
                    Adapter adapter = new Adapter(arrayList);
                    recyclerView.setAdapter(adapter);
                }
            }
            @Override
            public void onFailure(Call<Resultado<People>> call, Throwable t) {
                Toast.makeText(getContext(), "Error al descargar el archivo", Toast.LENGTH_SHORT).show();
            }
        });
    }
}