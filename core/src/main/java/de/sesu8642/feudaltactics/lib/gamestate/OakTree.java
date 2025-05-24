// SPDX-License-Identifier: GPL-3.0-or-later

package de.sesu8642.feudaltactics.lib.gamestate;

/**
 * Map object representing a tree. A tree prevent the tile it stands on from
 * generating income. Trees have a chance to spread to neighboring tiles.
 **/
public class OakTree extends Tree {

    public static final String SPRITE_NAME = "tree";
    @Override
    public String getSpriteName() {
        return SPRITE_NAME;
    }

    @Override
    public OakTree getCopy() {
        return new OakTree();
    }

    @Override
    public String toString() {
        return getClass().getName();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        return getClass() == obj.getClass();
    }

}
