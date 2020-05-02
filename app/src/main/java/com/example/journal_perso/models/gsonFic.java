package com.example.journal_perso.models;

import android.app.Activity;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Vector;

public class gsonFic {
    private Vector<espace> e;
    private Context monContext;
    private Activity monActivity;
    private String filename = "monJson.json";
    private java.lang.Object Object;

    public gsonFic() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        gsonFic gsonFic = (gsonFic) o;
        return Objects.equals(e, gsonFic.e) &&
                Objects.equals(monContext, gsonFic.monContext) &&
                Objects.equals(monActivity, gsonFic.monActivity) &&
                Objects.equals(filename, gsonFic.filename);
    }

    @Override
    public int hashCode() {
        return Objects.hash(e, monContext, monActivity, filename);
    }

    @Override
    public String toString() {
        return "dataGson{" +
                "e=" + e +
                ", monContext=" + monContext +
                ", monActivity=" + monActivity +
                '}';
    }

    //endregion

    public void ecrireFichier(Object obj, Context monContext, String FILENAME) //public void ecrireFichier(maData maDatas, Context monContext)
    {
        final GsonBuilder builder = new GsonBuilder().serializeNulls().disableHtmlEscaping().setPrettyPrinting();
        final Gson gson = builder.create();
        FileOutputStream monFichier;
        String fileContents = null;

        if (obj instanceof structData) {
            structData maDatas = (structData) obj;
            fileContents = gson.toJson(maDatas);  //Ne pas oublier
        } else if (obj instanceof dateData) {
            dateData maDataLoc = (dateData) obj;
            fileContents = gson.toJson(maDataLoc);
        }
        try
        {
            monFichier = monContext.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            monFichier.write(fileContents.getBytes());
            monFichier.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Object LireFichier(Context context, String FILENAME) {
        Object obj = null;

        final Gson gson = new GsonBuilder()
                .serializeNulls()
                .disableHtmlEscaping()
                .setPrettyPrinting()
                .create();

        String resultFromJson = "";

        try {
            InputStream inputStream = context.openFileInput(FILENAME);
            int content;
            while ((content = inputStream.read()) != -1) {
                resultFromJson += (char) content;
            }
            inputStream.close();
            if (FILENAME.equals("monJson.json")) {
                obj = gson.fromJson(resultFromJson, structData.class);
            } else if (FILENAME.equals("dataJson.json")) {
                obj = gson.fromJson(resultFromJson, dateData.class);
            }
            return obj;
        } catch (Exception e) {
            return null;
        }
    }
}
