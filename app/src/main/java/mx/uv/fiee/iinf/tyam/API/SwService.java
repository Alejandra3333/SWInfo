package mx.uv.fiee.iinf.tyam.API;

import mx.uv.fiee.iinf.tyam.API.Fragments.Resultado;
import mx.uv.fiee.iinfo.swinfo.Models.People;
import mx.uv.fiee.iinfo.swinfo.Models.Planet;
import mx.uv.fiee.iinfo.swinfo.Models.Vehicle;
import retrofit2.Call;
import retrofit2.http.GET;

public interface SwService {
    @GET("planets")
    Call<Resultado<Planet>> getPlanets();

    @GET("vehicles")
    Call<Resultado<Vehicle>> getVehicles();

    @GET("people")
    Call<Resultado<People>> getPeople();
}