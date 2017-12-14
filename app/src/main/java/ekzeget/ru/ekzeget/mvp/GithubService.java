package ekzeget.ru.ekzeget.mvp;

import ekzeget.ru.ekzeget.di.modules.GithubApi;
import rx.Observable;

public class GithubService {

    private GithubApi mGithubApi;

    public GithubService(GithubApi githubApi) {
        mGithubApi = githubApi;
    }


    public Observable<String> signIn(String token) {
        return mGithubApi.signIn(token);
    }
}
