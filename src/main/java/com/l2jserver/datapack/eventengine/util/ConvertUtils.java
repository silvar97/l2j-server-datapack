package com.l2jserver.datapack.eventengine.util;

import com.l2jserver.datapack.eventengine.model.holder.EItemHolder;
import com.l2jserver.datapack.eventengine.model.holder.LocationHolder;
import com.l2jserver.gameserver.model.Location;
import com.l2jserver.gameserver.model.holders.ItemHolder;

import java.util.ArrayList;
import java.util.List;

public class ConvertUtils {

    public static List<Location> convertToListLocations(List<LocationHolder> list) {
        List<Location> locations = new ArrayList<>();
        for (LocationHolder loc : list) {
            locations.add(new Location(loc.getX(), loc.getY(), loc.getZ()));
        }

        return locations;
    }

    public static List<ItemHolder> convertToListItemsHolders(List<EItemHolder> list) {
        List<ItemHolder> items = new ArrayList<>();
        for (EItemHolder item : list) {
            items.add(new ItemHolder(item.getId(), item.getAmount()));
        }

        return items;
    }
}
