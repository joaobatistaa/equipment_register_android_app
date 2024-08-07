package com.android.inventariocmrm;

import android.app.Activity;
import android.app.Application;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class RealmController {
    private static RealmController instance;
    private final Realm realm;

    public RealmController(Application application) {
        realm = Realm.getDefaultInstance();
    }

    public static RealmController with(Fragment fragment) {
        if (instance == null) instance = new RealmController(fragment.getActivity().getApplication());
        return instance;
    }
    public static RealmController with(Activity activity) {
        if (instance == null) instance = new RealmController(activity.getApplication());
        return instance;
    }
    public static RealmController with(Application application) {
        if (instance == null) instance = new RealmController(application);
        return instance;
    }

    public static RealmController getInstance() {
        return instance;
    }

    public Realm getRealm() {
        return realm;
    }

    public List<Equips> getEquipamentos() {
        RealmResults<EquipsRealm> realmResults = realm.where(EquipsRealm.class).findAll();
        realmResults = realmResults.sort("added_date", Sort.DESCENDING);
        List<Equips> newList = new ArrayList<>();
        for (EquipsRealm c : realmResults){
            newList.add(c.getOriginal());
        }
        return newList;
    }

    public Equips saveEquipamentos(Equips obj) {
        realm.beginTransaction();
        EquipsRealm newObj = obj.getObjectRealm();
        newObj.added_date = System.currentTimeMillis();
        newObj = realm.copyToRealmOrUpdate(newObj);
        realm.commitTransaction();
        return newObj != null ? newObj.getOriginal() : null;
    }

    public Equips getEquipamentos(long id) {
        EquipsRealm postRealm = realm.where(EquipsRealm.class).equalTo("id", id).findFirst();
        return postRealm != null ? postRealm.getOriginal() : null;
    }

    public void deleteEquipamentos(long id) {
        realm.beginTransaction();
        realm.where(EquipsRealm.class).equalTo("id", id).findFirst().deleteFromRealm();
        realm.commitTransaction();
    }

    public int getEquipamentosSize() {
        return realm.where(EquipsRealm.class).findAll().size();
    }
}
