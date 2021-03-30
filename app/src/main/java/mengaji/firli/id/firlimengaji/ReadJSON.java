package mengaji.firli.id.firlimengaji;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import mengaji.firli.id.firlimengaji.model.SurahFormat;

public class ReadJSON {

    private static int[] musicRaw ={
            R.raw.al_fatihah_ayat_1,
            R.raw.al_fatihah_ayat_2,
            R.raw.al_fatihah_ayat_3,
            R.raw.al_fatihah_ayat_4,
            R.raw.al_fatihah_ayat_5,
            R.raw.al_fatihah_ayat_6,
            R.raw.al_fatihah_ayat_7
    };


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public  List<SurahFormat> queryJSON(Context context,int no){
        List<SurahFormat> data = new ArrayList<>();
        StringBuffer sb = new StringBuffer();
        BufferedReader br;
        try {
            br = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(R.raw.quran)));
            String temp;
            while ((temp = br.readLine()) != null) {
                sb.append(temp);
            }

            JSONArray jsonArray = new JSONArray(sb.toString());
            for(int n=0; n<jsonArray.length() ; n++) {
                JSONObject jsonObject = jsonArray.getJSONObject(n);

                int id = Integer.valueOf(jsonObject.getString("id"));
                int surahId = Integer.valueOf(jsonObject.getString("suraId"));
                int verseID = Integer.valueOf(jsonObject.getString("verseID"));
                String ayahText = jsonObject.getString("ayahText");
                String indoText  = jsonObject.getString("indoText");

                if(surahId == no) {
                    if(surahId ==1)
                        data.add(new SurahFormat(id, surahId, verseID, ayahText, indoText,musicRaw[verseID-1]));
                    else
                        data.add(new SurahFormat(id, surahId, verseID, ayahText, indoText,null));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Log.d("json : ", sb.toString());


        return data;
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public  List<SurahFormat> queryJuzJSON(Context context,int nojuz){
       RangeJuz rangeJuz = null;
       boolean isStart = false, isEnd = false;

        List<SurahFormat> data = new ArrayList<>();
        StringBuffer sb = new StringBuffer();
        BufferedReader br;
        try {
            br = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(R.raw.juz)));
            String temp;
            while ((temp = br.readLine()) != null) {
                sb.append(temp);
            }

            JSONArray jsonArrayJuz = new JSONArray(sb.toString());
            for(int n=0; n<jsonArrayJuz.length() ; n++) {
                JSONObject jsonObject = jsonArrayJuz.getJSONObject(n);

                int getNumJuz = Integer.valueOf(jsonObject.getString("index"));

                if(nojuz == getNumJuz) {
                    JSONObject startObject = jsonObject.getJSONObject("start");
                    JSONObject endObject = jsonObject.getJSONObject("end");

                    rangeJuz= new RangeJuz(
                            Integer.valueOf(startObject.getString("verse").split("_")[1]),
                            Integer.valueOf(startObject.getString("nosurah")),
                            Integer.valueOf(endObject.getString("verse").split("_")[1]),
                            Integer.valueOf(endObject.getString("nosurah"))
                    );

                }

            }

            Log.d("RangeJuz : " , rangeJuz.toString());

            StringBuffer sbJuz = new StringBuffer();
            BufferedReader brJuz;
            String tempJuz;


            brJuz = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(R.raw.quran)));
            while ((tempJuz = brJuz.readLine()) != null) {
                sbJuz.append(tempJuz);
            }


            JSONArray jsonArray = new JSONArray(sbJuz.toString());
            for(int n=0; n<jsonArray.length() ; n++) {
                Log.d("JSON : " ,"gk ketemu sayang");

                JSONObject jsonObject = jsonArray.getJSONObject(n);

                int id = Integer.valueOf(jsonObject.getString("id"));
                int surahId = Integer.valueOf(jsonObject.getString("suraId"));
                int verseID = Integer.valueOf(jsonObject.getString("verseID"));
                String ayahText = jsonObject.getString("ayahText");
                String indoText  = jsonObject.getString("indoText");

                if( surahId == rangeJuz.startNumSurah && verseID == rangeJuz.startNumAyah) {
                    isStart = true;
                }


                if(isStart && !isEnd) {
                    if(surahId ==1)
                        data.add(new SurahFormat(id, surahId, verseID, ayahText, indoText,musicRaw[verseID-1]));
                    else
                        data.add(new SurahFormat(id, surahId, verseID, ayahText, indoText,null));

                    Log.d("JSON : " ,data.get(data.size()-1).toString());
                }

                if( surahId == rangeJuz.endNumSurah && verseID == rangeJuz.endNumAyah) {
                    isEnd = true;
                    break;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }


    class RangeJuz{
        int startNumAyah;
        int startNumSurah;

        int endNumAyah;
        int endNumSurah;

        public RangeJuz(int startNumAyah, int startNumSurah, int endNumAyah, int endNumSurah) {
            this.startNumAyah = startNumAyah;
            this.startNumSurah = startNumSurah;
            this.endNumAyah = endNumAyah;
            this.endNumSurah = endNumSurah;
        }

        public int getStartNumAyah() {
            return startNumAyah;
        }

        public void setStartNumAyah(int startNumAyah) {
            this.startNumAyah = startNumAyah;
        }

        public int getStartNumSurah() {
            return startNumSurah;
        }

        public void setStartNumSurah(int startNumSurah) {
            this.startNumSurah = startNumSurah;
        }

        public int getEndNumAyah() {
            return endNumAyah;
        }

        public void setEndNumAyah(int endNumAyah) {
            this.endNumAyah = endNumAyah;
        }

        public int getEndNumSurah() {
            return endNumSurah;
        }

        public void setEndNumSurah(int endNumSurah) {
            this.endNumSurah = endNumSurah;
        }

        @Override
        public String toString() {
            return "RangeJuz{" +
                    "startNumAyah=" + startNumAyah +
                    ", startNumSurah=" + startNumSurah +
                    ", endNumAyah=" + endNumAyah +
                    ", endNumSurah=" + endNumSurah +
                    '}';
        }
    }
}
