package ekzeget.ru.ekzeget.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import ekzeget.ru.ekzeget.di.modules.ContextModule;
import ekzeget.ru.ekzeget.di.modules.GithubModule;
import ekzeget.ru.ekzeget.mvp.GithubService;

@Singleton
@Component(modules = {ContextModule.class, GithubModule.class})
public interface AppComponent {
    Context getContext();
    GithubService getAuthService();
}
