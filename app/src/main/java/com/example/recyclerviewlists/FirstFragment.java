package com.example.recyclerviewlists;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.recyclerviewlists.databinding.FragmentFirstBinding;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment implements WordAdapter.PassElementSelected, RecyclerItemClick {
    private static ArrayList<String> dataList = new ArrayList<>();
    static boolean yacreada = false;
    static WordAdapter adapter;
    static int itemPosition;
    private String mParam= "";
    private static final String ARG_PARAM1 = "clave2";
    private FragmentFirstBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //si hay argumentos recibo a mparam(palabra modificada)
            mParam = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new WordAdapter(getContext(), (ArrayList<String>) setData(),(WordAdapter.PassElementSelected) this, this);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setHasFixedSize(true);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //NavHostFragment.findNavController(FirstFragment.this)
                 //       .navigate(R.id.action_FirstFragment_to_SecondFragment);
                passElement(dataList.toString());
            }
        });

        binding.fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataList.add("Palabra - " + dataList.size());
                binding.recyclerView.getAdapter().notifyItemInserted(dataList.size());
                //aca se manda al final de la lista
                binding.recyclerView.smoothScrollToPosition(dataList.size());
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private List<String> setData() {

        //sino se ha creado la lista se crea
        if (!yacreada) {

            for (int i = 0; i < 20; i++) {
                dataList.add("Palabra -  " + i);

            }
            yacreada=true;
        }
        //si la palabra recibida no esta vacia se reemplazara en su posicion

        if (!mParam.equals("")) {
            dataList.set(itemPosition, mParam);
        }


        return dataList;
    }


    @Override
    public void OnItemClick(int position) {
        //esta interfaz guarda la posicion de la palabra modificada
        itemPosition = position;

    }

    @Override
    public void passElement(String element) {
        //el toast me sirve para saber la palabra seleccionada
        Toast.makeText(getContext(),element,Toast.LENGTH_SHORT).show();

        //hice un bundle para enviar la palabra seleccionada
        Bundle bundle=new Bundle();
        bundle.putString("clave1",element);
        NavController navController= Navigation.findNavController(getActivity(),R.id.recyclerView);
        navController.navigate(R.id.action_FirstFragment_to_SecondFragment,
                bundle);
    }
}