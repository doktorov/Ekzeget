package ekzeget.ru.ekzeget.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ekzeget.ru.ekzeget.mvp.GithubService;

@Module(includes = {ApiModule.class})
public class GithubModule {
    @Provides
    @Singleton
    public GithubService provideGithubService(GithubApi authApi) {
        return new GithubService(authApi);
    }
}