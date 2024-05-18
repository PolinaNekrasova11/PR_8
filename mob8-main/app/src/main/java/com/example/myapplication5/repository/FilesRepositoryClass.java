package com.example.myapplication5.repository;

import android.content.Context;

import com.example.myapplication5.data_source.Files.AppSpecificDataSource;
import com.example.myapplication5.data_source.Files.CommonFilesDataSource;

public class FilesRepositoryClass implements FilesRepository{
    private final AppSpecificDataSource appSpecificDataSource;
    private final CommonFilesDataSource commonFilesDataSource;


    public FilesRepositoryClass(Context context, String appSpecDSFileName, String commonFilesDSFileName) {
        appSpecificDataSource = new AppSpecificDataSource(context, appSpecDSFileName);
        commonFilesDataSource = new CommonFilesDataSource(context, commonFilesDSFileName);

    }
    @Override
    public void writeIntoAppSpecDS(String inputContent) {
        appSpecificDataSource.writeAppSpecificDS("\n" + inputContent);
    }

    @Override
    public String readFromAppSpecDS() {
        return appSpecificDataSource.readAppSpecificDS();
    }

    @Override
    public boolean writeIntoCommonFilesDS(String inputContent) {
        return commonFilesDataSource.writeContent("\n" + inputContent);
    }

    @Override
    public String readFromCommonFilesDS() {
        return commonFilesDataSource.readFile();
    }

}
