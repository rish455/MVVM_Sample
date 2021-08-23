package com.whytecreations.ecom.utils;

import androidx.fragment.app.Fragment;

public interface FragmentInteractionListener {
    void replaceFragment(Fragment fragment, String tag);
    void handleProgress(boolean isShow);
}
