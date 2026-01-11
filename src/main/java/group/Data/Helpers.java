package group.Data;

import group.Types.TileIds;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Helpers {
    public static Path GetPersistantPath() {
        String userHome = System.getProperty("user.home");
        return Paths.get(userHome, ".openinvasion");
    }

    public static group.Types.Rect TileIdToRect(TileIds id)
    {
        switch (id)
        {

            case zombie:
                return new group.Types.Rect(438, 525 , 3, 5);
            case wooden_floor:
                return new group.Types.Rect(3037, 2137, 128, 128);
            case whitecrackedwall:
                return new group.Types.Rect(2619, 1142, 64, 64);
            case vendingmachine:
                break;
            case tutorial:
                break;
            case therock:
                break;
            case table:
                break;
            case suicide_victim:
                break;
            case suicidewhite:
                break;
            case suicide:
                break;
            case stairs:
                break;
            case shelf:
                break;
            case player:
                break;
            case playeralt:
                break;
            case doorsimple:
                break;
            case bullet:
                break;
            case glock:
                break;
            case glockfire:
                break;
            case box:
                break;
            case wall1:
                break;
            case wall2:
                break;
            case wall3:
                break;
            case wall4:
                break;
            case wall5:
                break;
            case wall6:
                break;
            case wall7:
                break;
            case wall8:
                break;
            case wall9:
                break;
            case spawnlabel:
                break;
            case snowflakeparticle:
                break;
            case shotgunshoot:
                break;
            case shotgun:
                break;
            case shoplabel:
                break;
            case shoes:
                break;
            case roundedui:
                break;
            case postaldudeface:
                break;
            case postal1cover:
                break;
            case poolofblood:
                break;
            case placeholdertexture:
                break;
            case newspaperblank:
                break;
            case newspaper5:
                break;
            case newspaper4:
                break;
            case newspaper3:
                break;
            case newspaper2:
                break;
            case newspaper1:
                break;
            case newspaperpickup:
                break;
            case mainmenubg:
                break;
            case magicdoorlabel:
                break;
            case lockedico:
                break;
            case lamp:
                break;
            case key:
                break;
            case katana:
                break;
            case fskatana:
                break;
            case fridge:
                break;
            case doubledoors:
                break;
            case bigdoor:
                break;
            case defwhite:
                break;
            case defcursor:
                break;
            case hqcrate:
                break;
            case crackedwall:
                break;
            case cahir:
                break;
            case chains:
                break;
            case aimcursor:
                break;
            case cashdesk:
                break;
            case carpet:
                break;
            case brokenvendingmashine:
                break;
            case bloodyoverlay:
                break;
            case barrel:
                break;
            case valuebar:
                break;
            case arshoot:
                break;
            case ar:
                break;
            case ammo:
                break;
            case hqfloor:
                break;
            case defblack:
                break;
            case corner1:
                break;
            case corner2:
                break;
            case corner3:
                break;
            case corner4:
                break;
            case stair1:
                break;
            case stair2:
                break;
            case stair3:
                break;
            case stair4:
                break;
            case stair5:
                break;
            case stair6:
                break;
            case stair7:
                break;
            case stair8:
                break;
            case stair9:
                break;
            case semtranswall1:
                break;
            case semtranswall2:
                break;
            case semtranswall3:
                break;
            case semtranswall4:
                break;
            case semtranswall5:
                break;
            case semtranswall6:
                break;
            case semtranswall7:
                break;
            case semtranswall8:
                break;
            case semtranswall9:
                break;
            case hqparticle:
                break;
            case uicheckmark:
                break;
            case uirankunranked:
                break;
            case uiranksilver:
                break;
            case uiranksapphire:
                break;
            case uirankiron:
                break;
            case uirankgold:
                break;
            case uirankdiamond:
                break;
            case uirankbronze:
                break;
            case mystery_crate_open3:
                break;
            case mystery_crate_open2:
                break;
            case mystery_crate_open1:
                break;
            case mystery_crate_idle:
                break;
            case talking:
                break;
            case talking1:
                break;
            case nottalking:
                break;
            case nottalking1:
                break;
            case Suicidevictim_1979:
                break;
            case readallaboutit:
                break;
            case noise:
                break;
            case lockedach:
                break;
            case hangmanach:
                break;
            case goodendingach:
                break;
            case endlesscompletedach:
                break;
            case bestrankach:
                break;
            case badendach:
                break;
            default:
                return new group.Types.Rect(0, 0, 0, 0);
        }
        return new group.Types.Rect(0, 0, 0, 0);
    }
}
