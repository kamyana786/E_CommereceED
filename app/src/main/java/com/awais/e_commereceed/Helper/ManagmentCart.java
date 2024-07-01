package com.awais.e_commereceed.Helper;

import android.content.Context;
import android.widget.Toast;

import com.awais.e_commereceed.Domain.ItemsDomain;

import java.util.ArrayList;

public class ManagmentCart {

    private Context context;
    private TinyDB tinyDB;

    public ManagmentCart(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }

    public void insertItem(ItemsDomain item) {
        ArrayList<ItemsDomain> listitem = getListCart();
        boolean existAlready = false;
        int n = 0;
        for (int y = 0; y < listitem.size(); y++) {
            if (listitem.get(y).getTitle().equals(item.getTitle())) {
                existAlready = true;
                n = y;
                break;
            }
        }
        if (existAlready) {
            listitem.get(n).setNumberinCart(item.getNumberinCart());
        } else {
            listitem.add(item);
        }
        tinyDB.putListObject("CartList", listitem);
        Toast.makeText(context, "Added to your Cart", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<ItemsDomain> getListCart() {
        return tinyDB.getListObject("CartList");
    }

    public void minusItem(ArrayList<ItemsDomain> listitem, int position, ChangeNumberItemsListener changeNumberItemsListener) {
        if (listitem.get(position).getNumberinCart() == 1) {
            listitem.remove(position);
        } else {
            listitem.get(position).setNumberinCart(listitem.get(position).getNumberinCart() - 1);
        }
        tinyDB.putListObject("CartList", listitem);
        changeNumberItemsListener.changed();
    }

    public void plusItem(ArrayList<ItemsDomain> listitem, int position, ChangeNumberItemsListener changeNumberItemsListener) {
        listitem.get(position).setNumberinCart(listitem.get(position).getNumberinCart() + 1);
        tinyDB.putListObject("CartList", listitem);
        changeNumberItemsListener.changed();
    }

    public Double getTotalFee() {
        ArrayList<ItemsDomain> listitem2 = getListCart();
        double fee = 0;
        for (int i = 0; i < listitem2.size(); i++) {
            fee = fee + (listitem2.get(i).getPrice() * listitem2.get(i).getNumberinCart());
        }
        return fee;
    }
}
