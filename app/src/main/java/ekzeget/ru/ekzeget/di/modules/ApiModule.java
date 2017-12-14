package ekzeget.ru.ekzeget.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module(includes = {RetrofitModule.class})
public class ApiModule {
    @Provides
    @Singleton
    public GithubApi provideAuthApi(Retrofit retrofit) {
        return retrofit.create(GithubApi.class);
    }
}