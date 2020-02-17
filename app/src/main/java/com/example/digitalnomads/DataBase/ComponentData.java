package com.example.digitalnomads.DataBase;

import dagger.Component;

@Component(modules = {DataBaseModule.class})
public interface ComponentData {

    DataBaseListNews getDataBaseListNews();
}
