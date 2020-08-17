package com.example.wordfragmentapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DefinitionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DefinitionFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    final static String ARG_POSITION="position"; //포지션이라는 변수를 상수로 만듦

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    int mCurrentPostion=-1; //현재 선택된 포지션

    public DefinitionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DefinitionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DefinitionFragment newInstance(String param1, String param2) {
        DefinitionFragment fragment = new DefinitionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(savedInstanceState != null){ //현재 부착된 상태인지
            mCurrentPostion = savedInstanceState.getInt(ARG_POSITION); //부착된 상태일때 ARG_POSTION을 mCurrentPostion에 넣어라
        }
        return inflater.inflate(R.layout.fragment_definition, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        //화면에 보일때 argument를 받도록
        Bundle agrs = getArguments();
        if(agrs!=null){ //전달된 데이터가 있을때
            updateDefinitionView(agrs.getInt(ARG_POSITION));
        }else if(mCurrentPostion != -1){//포지션을 받았을때
            updateDefinitionView(mCurrentPostion);
        }
    }
    public void updateDefinitionView(int position){
        TextView defTv = getActivity().findViewById(R.id.definition);
        defTv.setText(Data.definitions[position]);
        mCurrentPostion=position;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ARG_POSITION,mCurrentPostion);
    }
}