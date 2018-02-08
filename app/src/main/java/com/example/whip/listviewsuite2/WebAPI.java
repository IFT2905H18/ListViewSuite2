package com.example.whip.listviewsuite2;

import com.example.whip.listviewsuite2.toutv.Lineups;
import com.example.whip.listviewsuite2.toutv.Root;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WebAPI {
    public String url;
    public String type;

    public WebAPI(String type){
        url = "https://www.webdepot.umontreal.ca/Usagers/p1141140/MonDepotPublic/Demo_Modified2.txt";
        this.type = type;
    }

    public Lineups run() throws IOException {

        OkHttpClient client = new OkHttpClient(); // Va récupérer contenu html d'un URL
        Request request = new Request.Builder().url(url).build(); // On crée la requête à l'URL (GET..)
        Response response = client.newCall(request).execute(); // Exécute la requête et obtient la réponse
        String json = response.body().string(); //Transforme le JSON du site en long String


        Moshi moshi = new Moshi.Builder().build();  //On utilise Moshi pour parser le json

        JsonAdapter<Root> jsonAdapter = moshi.adapter(Root.class);

        Root root = jsonAdapter.fromJson(json); //Appel adapter, à partir du json et okhttp, met tout dans la racine
        Lineups films;

        for (int i = 0; i<root.Lineups.size(); i++){
            if (root.Lineups.get(i).Title.equals(type)){
                films = root.Lineups.get(i);
                return films;
            }
        }
        return null;

    }

}
