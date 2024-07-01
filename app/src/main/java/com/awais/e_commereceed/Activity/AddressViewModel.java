package com.awais.e_commereceed.Activity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.awais.e_commereceed.Utils.Resource;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import javax.inject.Inject;

public class AddressViewModel extends ViewModel {
    private final FirebaseFirestore firestore;
    private final FirebaseAuth auth;

    private final MutableLiveData<Resource<Address>> addressLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> errorLiveData = new MutableLiveData<>();

    @Inject
    public AddressViewModel(FirebaseFirestore firestore, FirebaseAuth auth) {
        this.firestore = firestore;
        this.auth = auth;
    }

    public LiveData<Resource<Address>> getAddressLiveData() {
        return addressLiveData;
    }

    public LiveData<String> getError() {
        return errorLiveData;
    }

    public void saveAddress(Address address) {
        if (validateInputs(address)) {
            firestore.collection("addresses")
                    .add(address)
                    .addOnSuccessListener(documentReference ->
                            addressLiveData.postValue(new Resource<>(Resource.Status.SUCCESS, address, null)))
                    .addOnFailureListener(e ->
                            errorLiveData.postValue(e.getMessage()));
        } else {
            errorLiveData.postValue("Invalid input");
        }
    }

    private boolean validateInputs(Address address) {
        return !address.getAddressTitle().trim().isEmpty() &&
                !address.getCity().trim().isEmpty() &&
                !address.getPhone().trim().isEmpty() &&
                !address.getState().trim().isEmpty() &&
                !address.getFullName().trim().isEmpty() &&
                !address.getStreet().trim().isEmpty();
    }
}
