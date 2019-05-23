package com.example.drcreeper.refereeapp.interfaces;

import androidx.fragment.app.Fragment;

public interface FragmentWorker {
    void switchFragment(Fragment newFragment);
    void setHeader(String title);
    void setHeader(int title);
}
