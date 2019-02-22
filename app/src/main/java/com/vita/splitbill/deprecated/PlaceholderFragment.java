//package com.vita.splitbill.deprecated;
//
//import android.arch.lifecycle.ViewModelProviders;
//import android.content.Context;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.vita.splitbill.MenuViewModel;
//import com.vita.splitbill.deprecated.MenuViewModelFactory;
//import com.vita.splitbill.R;
//
//public class PlaceholderFragment extends Fragment {
//    /**
//     * The fragment argument representing the section number for this
//     * fragment.
//     */
//    private static final String TAG = "FRAGMENT";
//
//    private static final String ARG_SECTION_NUMBER = "section_number";
//
//    private MenuFragmentItemSelectedListener mListener;
//
//    public interface MenuFragmentItemSelectedListener{
//        void onItemSelected(int position);
//    }
//
//    public PlaceholderFragment() {
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        try {
//            mListener=(MenuFragmentItemSelectedListener) context;
//        }catch (ClassCastException e){
//            //Class cast exception
//        }
//
//    }
//
//
//
//    /**
//     * Returns a new instance of this fragment for the given section
//     * number.
//     */
//    public static PlaceholderFragment newInstance(int sectionNumber) {
//        PlaceholderFragment fragment = new PlaceholderFragment();
//        Bundle args = new Bundle();
//        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//
//        Log.d(TAG,"OnCreateView");
//        final View rootView = inflater.inflate(R.layout.fragment_menu, container, false);
//        final MenuActivity menuAct = (MenuActivity) getActivity();
//        final MenuViewModel viewModel = ViewModelProviders.of(this, new MenuViewModelFactory(menuAct.getApplication(), MenuActivity.ResID)).get(MenuViewModel.class);
//
////        viewModel.getAllMenuTitles().observe(this, new Observer<String[]>() {
////            @Override
////            public void onChanged(String[] strings) {
////                final Spinner spinner = rootView.findViewById(R.id.spinner);
////                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, strings);
////                adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
////                spinner.setAdapter(adapter);
////
////
////                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
////                    @Override
////                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
////
////                    }
////
////                    @Override
////                    public void onNothingSelected(AdapterView<?> adapterView) {
////
////                    }
////                });
////            }
////        });
//
//        return rootView;
//    }
//}