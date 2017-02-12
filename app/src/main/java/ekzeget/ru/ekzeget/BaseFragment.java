package ekzeget.ru.ekzeget;

import android.support.v4.app.Fragment;

import ekzeget.ru.ekzeget.fabrevealmenu.view.FABRevealMenu;

public class BaseFragment extends Fragment {

    public boolean onBackPressed() {
        if (fabMenu != null) {
            if (fabMenu.isShowing()) {
                fabMenu.closeMenu();
                return false;
            }
        }
        return true;
    }

    private FABRevealMenu fabMenu;

    public FABRevealMenu getFabMenu() {
        return fabMenu;
    }

    public void setFabMenu(FABRevealMenu fabMenu) {
        this.fabMenu = fabMenu;
    }
}