package com.example.wordfragmentapplication;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WordFragment extends Fragment {
    //인터페이스 생성
    public interface OnWordSelectListener{
        public void onWordSelected(int position);
    }
    //변수 생성
    OnWordSelectListener mCallback;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public WordFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WordFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WordFragment newInstance(String param1, String param2) {
        WordFragment fragment = new WordFragment();
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
        //인플래터를 ViewGroup 객체에 넣어서 리턴
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_word, container, false);

        ListView listView = rootView.findViewById(R.id.listView1);
        //리스트뷰는 데이터로 리턴
        //컬렉션(배열, 동적배열)을 담는 것이 어댑터
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                /*호출하는 액티비티, 데이터를 어떻게 표시, 실제로 표시할 데이터*/
                getActivity(),android.R.layout.simple_list_item_1,Data.words
        );
        //리스트뷰에 어댑터 내용 표시
        listView.setAdapter(adapter);
        //리스트뷰의 아이템 클릭할때
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position /*리스트 뷰의 위치*/, long id) {
                if(mCallback != null){
                    //현재 내가 선텍한 워드의 포지션을 전송
                    mCallback.onWordSelected(position);
                }
            }
        });
        return rootView;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        //내가 액티비티에 붙을때
        super.onAttach(context);
        //OnWordSelectListener의 context이면
        if(context instanceof OnWordSelectListener){
            //타입캐스팅한 context를 mCallback에 넣어라
            mCallback= (OnWordSelectListener) context;
        }
    }
}