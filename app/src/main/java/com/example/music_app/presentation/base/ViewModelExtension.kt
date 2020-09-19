package com.example.music_app.presentation.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel

inline fun <reified T : ViewModel> Fragment.hiltNavViewModels(/*@IdRes navGraphIdRes: Int*/) =
    viewModels<T>(
//        ownerProducer = { findNavController().getBackStackEntry(navGraphIdRes) },
        factoryProducer = { defaultViewModelProviderFactory }
    )