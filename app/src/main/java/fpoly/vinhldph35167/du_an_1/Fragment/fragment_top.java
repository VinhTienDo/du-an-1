package fpoly.vinhldph35167.du_an_1.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import fpoly.vinhldph35167.du_an_1.Adapter.TopAdapter;
import fpoly.vinhldph35167.du_an_1.Dao.DonHangDao;
import fpoly.vinhldph35167.du_an_1.Dao.ThongKeDao;
import fpoly.vinhldph35167.du_an_1.Model.Top;
import fpoly.vinhldph35167.du_an_1.R;


public class fragment_top extends Fragment {
    ListView lvTop;
    ArrayList<Top> list;
    TopAdapter adapter;
    ThongKeDao dao;


    public fragment_top() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_top, container, false);
        lvTop = v.findViewById(R.id.lvTop);
        DonHangDao donHangDao = new DonHangDao(getActivity());
        list = (ArrayList<Top>) donHangDao.getTop();
        adapter = new TopAdapter(getActivity(),this,list);
        lvTop.setAdapter(adapter);
        return v;
    }
}