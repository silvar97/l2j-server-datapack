package com.l2jserver.datapack.eventengine.model.holder;

import com.l2jserver.gameserver.model.Location;
import com.l2jserver.gameserver.model.interfaces.IPositionable;

public class LocationHolder {

    private int x;
    private int y;
    private int z;
    private int heading;
    private int instanceId;

    public LocationHolder(Location location) {
        x = location.getX();
        y = location.getY();
        z = location.getZ();
        heading = location.getHeading();
        instanceId = location.getInstanceId();
    }
    public LocationHolder(IPositionable location) {
        x = location.getX();
        y = location.getY();
        z = location.getZ();
        heading = location.getHeading();
        instanceId = location.getInstanceId();
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public int getHeading() {
        return heading;
    }

    public int getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(int instanceId) {
        this.instanceId = instanceId;
    }

    public Location getLocation() {
        Location loc = new Location(x, y, z, heading);
        loc.setInstanceId(instanceId);
        return loc;
    }
}
