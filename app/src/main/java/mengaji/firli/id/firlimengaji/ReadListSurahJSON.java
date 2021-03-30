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

import mengaji.firli.id.firlimengaji.model.ListSurah;
import mengaji.firli.id.firlimengaji.model.SurahFormat;

public class ReadListSurahJSON {


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static List<ListSurah> queryJSON(Context context){
        List<ListSurah> data = new ArrayList<>();
        StringBuffer sb = new StringBuffer();
        BufferedReader br;
        try {
            br = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(R.raw.surat)));
            String temp;
            while ((temp = br.readLine()) != null) {
                sb.append(temp);
            }


            JSONObject jsonObject = new JSONObject(sb.toString());
            JSONArray jsonArray = jsonObject.getJSONArray("surat");

            for(int n=0; n<jsonArray.length() ; n++) {
                JSONObject jsonObjectData = jsonArray.getJSONObject(n);

                String namaIndo = jsonObjectData.getString("namaSurah") ;
                String namaArab  = jsonObjectData.getString("name");
                String diturunkan  = jsonObjectData.getString("diturunkan");
                String jumlahayat  = jsonObjectData.getString("jumlahAyat");
                String nomersurat = jsonObjectData.getString("surah") ;



                data.add(new ListSurah(namaIndo,namaArab,diturunkan,jumlahayat, nomersurat));

                Log.d("jaon",data.get(n).toString());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Log.d("json : ", sb.toString());


        return data;
    }
}
