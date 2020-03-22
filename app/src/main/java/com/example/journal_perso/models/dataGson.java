package com.example.journal_perso.models;

import android.app.Activity;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

public class dataGson {
    private Vector<espace> e;
    private Context monContext;
    private Activity monActivity;
    //private Fragment monFrag;
    private String filename = "monJson.json";

    public dataGson(Vector<espace> e, Context monContext, Activity monActivity) {
        this.e = e;
        this.monContext = monContext;
        this.monActivity = monActivity;
        // this.monFrag = monFrag;
    }

    //region getter/setter

    public Vector<espace> getE() {
        return e;
    }

    public void setE(Vector<espace> e) {
        this.e = e;
    }

    public Context getMonContext() {
        return monContext;
    }

    public void setMonContext(Context monContext) {
        this.monContext = monContext;
    }

    public Activity getMonActivity() {
        return monActivity;
    }

    public void setMonActivity(Activity monActivity) {
        this.monActivity = monActivity;
    }

    /*public Fragment getMonFrag() {
        return monFrag;
    }

    public void setMonFrag(Fragment monFrag) {
        this.monFrag = monFrag;
    }*/

    @Override
    public String toString() {
        return "dataGson{" +
                "e=" + e +
                ", monContext=" + monContext +
                ", monActivity=" + monActivity +
                ", monFrag=" + monFrag +
                '}';
    }

    //endregion


    public void ecrireFichier(espace dene, Context monContext)
    {
        final GsonBuilder builder = new GsonBuilder().serializeNulls().disableHtmlEscaping().setPrettyPrinting();
        final Gson gson = builder.create();

        String fileContents = gson.toJson(dene);  //Ne pas oublier
        FileOutputStream monFichier;

        try
        {
            monFichier = monContext.openFileOutput(filename, Context.MODE_PRIVATE);
            monFichier.write(fileContents.getBytes());
            monFichier.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void lectureFichier(){
        Gson gson = new Gson();
        String mJson="";
        try{
            InputStream f = monContext.openFileInput(filename);
            int content;

            while((content = f.read())!=-1){
                mJson = mJson + (char)content;
            }
            f.close();

           //e = (espace)gson.fromJson(content, espace.class); //erreur

        }catch (Exception e){

        }

    }

}
