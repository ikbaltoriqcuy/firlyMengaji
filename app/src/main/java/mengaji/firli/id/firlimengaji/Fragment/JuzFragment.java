package mengaji.firli.id.firlimengaji.Fragment;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

import mengaji.firli.id.firlimengaji.R;
import mengaji.firli.id.firlimengaji.adapter.JuzAdapter;
import mengaji.firli.id.firlimengaji.adapter.SurahAdapter;
import mengaji.firli.id.firlimengaji.juz.Juz1Activity;
import mengaji.firli.id.firlimengaji.model.Juz;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class JuzFragment extends Fragment {

    private RecyclerView recyclerView;
    private JuzAdapter mAdapter;
    private ArrayList<Juz> models = new ArrayList<>();
    private Context mContext;

    @SuppressLint("ValidFragment")
    public JuzFragment(Context mContext) {
        this.mContext = mContext;
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_juz, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclewViewJuz);

        prepareJuzData();

        // Inflate the layout for this fragment
        return rootView;
    }

    private void prepareJuzData(){

        models.add(new Juz(R.string.juz_1, R.mipmap.number_one, R.string.al_fathihah_ayat_1));
        models.add(new Juz(R.string.juz_2, R.mipmap.number_one, R.string.al_baqarah_ayat_142));
        models.add(new Juz(R.string.juz_3, R.mipmap.number_one, R.string.al_baqarah_ayat_253));
        models.add(new Juz(R.string.juz_4, R.mipmap.number_one, R.string.ali_imran_ayat_92));
        models.add(new Juz(R.string.juz_5, R.mipmap.number_one, R.string.an_nisa_ayat_24));
        models.add(new Juz(R.string.juz_6, R.mipmap.number_one, R.string.an_nisa_ayat_148));
        models.add(new Juz(R.string.juz_7, R.mipmap.number_one, R.string.al_maidah_ayat_83));
        models.add(new Juz(R.string.juz_8, R.mipmap.number_one, R.string.al_anam_ayat_111));
        models.add(new Juz(R.string.juz_9, R.mipmap.number_one, R.string.al_araf_ayat_88));
        models.add(new Juz(R.string.juz_10, R.mipmap.number_one, R.string.al_anfal_ayat_41));
        models.add(new Juz(R.string.juz_11, R.mipmap.number_one, R.string.at_taubah_ayat_94));
        models.add(new Juz(R.string.juz_12, R.mipmap.number_one, R.string.hud_ayat_6));
        models.add(new Juz(R.string.juz_13, R.mipmap.number_one, R.string.yusuf_ayat_53));
        models.add(new Juz(R.string.juz_14, R.mipmap.number_one, R.string.al_hijr_ayat_2));
        models.add(new Juz(R.string.juz_15, R.mipmap.number_one, R.string.al_isra_ayat_1));
        models.add(new Juz(R.string.juz_16, R.mipmap.number_one, R.string.al_kahf_ayat_75));
        models.add(new Juz(R.string.juz_17, R.mipmap.number_one, R.string.al_anbiya_ayat_1));
        models.add(new Juz(R.string.juz_18, R.mipmap.number_one, R.string.al_muminun_ayat_1));
        models.add(new Juz(R.string.juz_19, R.mipmap.number_one, R.string.al_furqan_ayat_21));
        models.add(new Juz(R.string.juz_20, R.mipmap.number_one, R.string.an_naml_ayat_60));
        models.add(new Juz(R.string.juz_21, R.mipmap.number_one, R.string.al_ankabut_ayat_45));
        models.add(new Juz(R.string.juz_22, R.mipmap.number_one, R.string.al_ahzab_ayat_31));
        models.add(new Juz(R.string.juz_23, R.mipmap.number_one, R.string.ya_sin_ayat_22));
        models.add(new Juz(R.string.juz_24, R.mipmap.number_one, R.string.az_zumar_ayat_32));
        models.add(new Juz(R.string.juz_25, R.mipmap.number_one, R.string.fussilat_ayat_47));
        models.add(new Juz(R.string.juz_26, R.mipmap.number_one, R.string.al_ahqaf_ayat_1));
        models.add(new Juz(R.string.juz_27, R.mipmap.number_one, R.string.az_zariyat_ayat_31));
        models.add(new Juz(R.string.juz_28, R.mipmap.number_one, R.string.al_mujadiilah_ayat_1));
        models.add(new Juz(R.string.juz_29, R.mipmap.number_one, R.string.al_mulk_ayat_1));
        models.add(new Juz(R.string.juz_30, R.mipmap.number_one, R.string.an_naba_ayat_1));

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        //mAdapter = new JuzAdapter(getActivity().getApplicationContext(),models,R.color.colorSemua);

        mAdapter = new JuzAdapter(getContext(), models, new JuzAdapter.ClickHandler() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(), Juz1Activity.class);
                intent.putExtra("tab" , position-1);
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(int position) {
                return false;
            }
        });

        recyclerView.setAdapter(mAdapter);
    }

}
