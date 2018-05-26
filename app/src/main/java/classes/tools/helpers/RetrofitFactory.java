package classes.tools.helpers;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by A.Mohammadi on 7/23/2017.
 */

public class RetrofitFactory {


    private static final String URL =
//            "http://192.168.101.33:7080/";
            "http://79.175.138.95:1010/";

    private static Retrofit retrofit;

    private static Retrofit getRetrofit(){
        if(retrofit == null){
            Retrofit.Builder reBuilder = new Retrofit.Builder();
            reBuilder
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create());
            retrofit = reBuilder.build();
        }
        return retrofit;
    }

    public static IRetrofit getRetrofitClient(){
        IRetrofit retroClient = RetrofitFactory.getRetrofit().create(IRetrofit.class);
        return retroClient;
    }
}
