package ekzeget.ru.ekzeget.di.modules;

import retrofit2.http.GET;
import retrofit2.http.Header;
import rx.Observable;

public interface GithubApi {
    Integer PAGE_SIZE = 50;

    @GET("/user")
    Observable<String> signIn(@Header("Authorization") String token);
}