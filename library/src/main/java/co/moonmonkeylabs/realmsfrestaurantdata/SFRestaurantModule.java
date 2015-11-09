package co.moonmonkeylabs.realmsfrestaurantdata;

import co.moonmonkeylabs.realmsfrestaurantdata.model.Business;
import io.realm.annotations.RealmModule;

@RealmModule(library = true, classes = { Business.class })
public class SFRestaurantModule {
}
