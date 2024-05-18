package com.example.myapplication5.repository;

import android.content.Context;

public interface FilesRepository {
    void writeIntoAppSpecDS(String inputContent);
    boolean writeIntoCommonFilesDS(String inputContent);

    String readFromAppSpecDS();
    String readFromCommonFilesDS();

}
