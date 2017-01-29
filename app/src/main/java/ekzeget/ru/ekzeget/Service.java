package ekzeget.ru.ekzeget;

import ekzeget.ru.ekzeget.model.gson.GsonBooks;
import rx.Observable;

public class Service {
    private API mApi;

    public Service(API api) {
        mApi = api;
    }

    public Observable<GsonBooks> getBooks() {
        return mApi.getBooks();
    }
}
