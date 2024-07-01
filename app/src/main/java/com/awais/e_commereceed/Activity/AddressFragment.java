package com.awais.e_commereceed.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.awais.e_commereceed.databinding.FragmentAddressBinding;

public class AddressFragment extends Fragment {
    public static final String EXTRA_FROM_CART = "com.awais.e_commereceed.EXTRA_FROM_CART";

    private FragmentAddressBinding binding;
    private AddressViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(AddressViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAddressBinding.inflate(inflater, container, false);
        boolean fromCart = getActivity().getIntent().getBooleanExtra(EXTRA_FROM_CART, false);
        if (fromCart) {
            // Handle navigation from the cart activity
            // For example, update UI elements specific to cart checkout
        }
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonSave.setOnClickListener(v -> {
            String addressTitle = binding.edAddressTitle.getText().toString();
            String fullName = binding.edFullName.getText().toString();
            String street = binding.edStreet.getText().toString();
            String phone = binding.edPhone.getText().toString();
            String city = binding.edCity.getText().toString();
            String state = binding.edState.getText().toString();

            Address address = new Address(addressTitle, fullName, street, phone, city, state);
            viewModel.saveAddress(address);
        });

        observeViewModel();
    }

    private void observeViewModel() {
        viewModel.getAddressLiveData().observe(getViewLifecycleOwner(), resource -> {
            switch (resource.getStatus()) {
                case LOADING:
                    binding.progressbarAddress.setVisibility(View.VISIBLE);
                    break;
                case SUCCESS:
                    binding.progressbarAddress.setVisibility(View.INVISIBLE);
                    NavHostFragment.findNavController(AddressFragment.this).navigateUp();
                    break;
                case ERROR:
                    binding.progressbarAddress.setVisibility(View.INVISIBLE);
                    String errorMessage = resource.getMessage() != null ? resource.getMessage() : "Unknown error occurred";
                    Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        });
    }
}
