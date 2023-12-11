package com.example.elso_teszt.fragment;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.elso_teszt.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FirebaseAuth mAuth;

    public UserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UserFragment newInstance(String param1, String param2) {
        UserFragment fragment = new UserFragment();
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
        mAuth = FirebaseAuth.getInstance(); // Firebase Auth inicializálása
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        SwitchCompat engedelySwitch = view.findViewById(R.id.engedely);
        SwitchCompat aszfSwitch = view.findViewById(R.id.aszf);
        SwitchCompat adatvedelemSwitch = view.findViewById(R.id.adatvedelem);

        // Csúszkák hátterének színének beállítása
        int csuszkaHatterSzine = ContextCompat.getColor(requireContext(), R.color.zold);
        engedelySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    engedelySwitch.setThumbTintList(ColorStateList.valueOf(csuszkaHatterSzine));
                } else {
                    engedelySwitch.setThumbTintList(ColorStateList.valueOf(Color.WHITE));
                }
            }
        });

        aszfSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    aszfSwitch.setThumbTintList(ColorStateList.valueOf(csuszkaHatterSzine));
                } else {
                    aszfSwitch.setThumbTintList(ColorStateList.valueOf(Color.WHITE));
                }
            }
        });

        adatvedelemSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    adatvedelemSwitch.setThumbTintList(ColorStateList.valueOf(csuszkaHatterSzine));
                } else {
                    adatvedelemSwitch.setThumbTintList(ColorStateList.valueOf(Color.WHITE));
                }
            }
        });

        Button regisztracioGomb = view.findViewById(R.id.regisztracio_gomb);
        regisztracioGomb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText emailEditText = view.findViewById(R.id.email);
                EditText passwordEditText = view.findViewById(R.id.jelszo);

                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                // Regisztráció
                registerUser(email, password);
            }
        });

        return view;
    }
    // Regisztrációs függvény hozzáadása
    private void registerUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sikeres regisztráció
                            Toast.makeText(requireContext(), "Sikeres regisztráció", Toast.LENGTH_SHORT).show();

                            // Ide adhatsz hozzá kódot, amit a sikeres regisztráció esetén szeretnél végrehajtani
                        } else {
                            // Sikertelen regisztráció
                            Toast.makeText(requireContext(), "Sikertelen regisztráció: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }


}