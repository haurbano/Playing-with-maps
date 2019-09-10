package com.example.googlemapstest.di

import com.example.data.datasources.RouteDataSourceImpl
import com.example.domain.datasources.RouteDataSource
import com.example.googlemapstest.mvp.MapActivityContract
import com.example.googlemapstest.mvp.MapModel
import com.example.googlemapstest.mvp.MapPresenter
import org.koin.dsl.module

val appModule = module {

    // data
    factory<RouteDataSource> { RouteDataSourceImpl() }

    // App
    factory<MapActivityContract.Presenter> { MapPresenter(get()) }
    factory<MapActivityContract.Model> { MapModel(get()) }

    // Domain

}