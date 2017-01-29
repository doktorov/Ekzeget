package ekzeget.ru.ekzeget;

import ekzeget.ru.ekzeget.model.gson.GsonBooks;
import retrofit2.http.GET;
import rx.Observable;

public interface API {
    @GET("api.php?books=nz,vz&name&short&parts")
    Observable<GsonBooks> getBooks();
}
