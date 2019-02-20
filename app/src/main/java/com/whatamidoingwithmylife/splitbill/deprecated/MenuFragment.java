//package com.whatamidoingwithmylife.splitbill.deprecated;
//
//import android.app.Activity;
//import android.arch.lifecycle.Observer;
//import android.arch.lifecycle.ViewModelProviders;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.Spinner;
//
//import com.whatamidoingwithmylife.splitbill.MenuViewModel;
//import com.whatamidoingwithmylife.splitbill.deprecated.MenuViewModelFactory;
//import com.whatamidoingwithmylife.splitbill.R;
//
//public class MenuFragment extends Fragment {
//    /**
//     * The fragment argument representing the section number for this
//     * fragment.
//     */
//    private static final String TAG = "SplitBill/Fragment";
//    private static final String ARG_SECTION_NUMBER = "section_number";
//    private final Fragment f = this;
//
//    MenuTitles mCallback;
//
//
//    public MenuFragment() {
//    }
//
//    public interface MenuTitles {
//        void onStringReceived(String[] strings);
//    }
//
//    /**
//     * Returns a new instance of this fragment for the given section
//     * number.
//     */
//    public static MenuFragment newInstance(int sectionNumber) {
//        MenuFragment fragment = new MenuFragment();
//        Bundle args = new Bundle();
//        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//
//        // This makes sure that the container activity has implemented
//        // the callback interface. If not, it throws an exception
//        try {
//            mCallback = (MenuTitles) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnHeadlineSelectedListener");
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//
//        final View rootView = inflater.inflate(R.layout.fragment_menu, container, false);
//        final MenuActivity menuAct = (MenuActivity) getActivity();
//        final MenuViewModel viewModel = ViewModelProviders.of(this, new MenuViewModelFactory(menuAct.getApplication(), MenuActivity.ResID)).get(MenuViewModel.class);
//
//        viewModel.getAllMenuTitles().observe(this, new Observer<String[]>() {
//            @Override
//            public void onChanged(String[] strings) {
//                final Spinner spinner = rootView.findViewById(R.id.spinner);
////                final SubMenuViewModel subMenuViewModel = ViewModelProviders.of(f, new SubMenuViewModelFactory(menuAct.getApplication(), spinner.getSelectedItemPosition() + 1)).get(SubMenuViewModel.class);
//
//                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, strings);
//                adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
//                spinner.setAdapter(adapter);
//
//                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                    @Override
//                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                        int MenuID = spinner.getSelectedItemPosition() + 1;
//                        menuAct.doTheWigWam(MenuID);
//                    }
//
//                    @Override
//                    public void onNothingSelected(AdapterView<?> adapterView) {
//
//                    }
//                });
//            }
//        });
//
//        return rootView;
//    }
//
//
//
//}