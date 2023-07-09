package com.example.recyclerviewlists;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.recyclerviewlists.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    private String mParam1;
    private static final String ARG_PARAM1 = "clave1";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        binding.editText.setText(mParam1);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passElement(binding.editText.getText().toString());
                //NavHostFragment.findNavController(SecondFragment.this)
                    //    .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }

    private void passElement(String element) {
        Bundle bundle=new Bundle();
        bundle.putString("clave2",element);
        NavController navController= Navigation.findNavController(getActivity(),R.id.layout_second);
        navController.navigate(R.id.action_SecondFragment_to_FirstFragment,
                bundle);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}