package group.Data;

import com.raylib.java.shapes.*;
import group.Types.TileIds;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Helpers {
    public static Path GetPersistantPath() {
        String userHome = System.getProperty("user.home");
        return Paths.get(userHome, ".openinvasion");
    }

    private static Rectangle R(float x, float y, float w, float h) {
        Rectangle r = new Rectangle();
        r.x = x;
        r.y = y;
        r.width = w;
        r.height = h;
        return r;
    }

    public static Rectangle TileIdToRect(TileIds id) {
        switch (id) {

            case zombie: return R(438, 525, 3, 5);
            case wooden_floor: return R(3037, 2137, 128, 128);
            case whitecrackedwall: return R(2619, 1142, 64, 64);
            case vendingmachine: return R(1915, 1496, 50, 93);
            case tutorial: return R(1937, 930, 304, 208);
            case therock: return R(2565, 1541, 638, 527);
            case table: return R(1832, 348, 196, 105);

            case suicide_victim: return R(3398, 1532, 641, 857);
            case suicidewhite: return R(931, 859, 36, 175);
            case suicide: return R(927, 670, 35, 170);

            case stairs: return R(249, 547, 64, 16);
            case shelf: return R(97, 561, 11, 7);

            case player: return R(60, 554, 3, 5);
            case playeralt: return R(70, 554, 3, 5);
            case doorsimple: return R(74, 562, 5, 8);
            case bullet: return R(56, 561, 6, 5);

            case glock: return R(71, 491, 23, 18);
            case glockfire: return R(71, 512, 24, 18);
            case box: return R(55, 491, 6, 6);

            case wall1: return R(55, 509, 3, 3);
            case wall2: return R(58, 509, 3, 3);
            case wall3: return R(61, 509, 3, 3);
            case wall4: return R(55, 512, 3, 3);
            case wall5: return R(58, 512, 3, 3);
            case wall6: return R(61, 512, 3, 3);
            case wall7: return R(55, 515, 3, 3);
            case wall8: return R(58, 515, 3, 3);
            case wall9: return R(61, 515, 3, 3);

            case spawnlabel: return R(554, 463, 87, 24);
            case snowflakeparticle: return R(578, 280, 9, 9);
            case shotgunshoot: return R(497, 343, 33, 9);
            case shotgun: return R(494, 324, 32, 9);
            case shoplabel: return R(165, 292, 283, 199);
            case shoes: return R(2535, 1039, 156, 67);

            case roundedui1: return R(2464, 729, 64, 64);
            case roundedui2: return R(2528, 729, 64, 64);
            case roundedui3: return R(2464, 793, 64, 64);
            case roundedui4: return R(2528, 793, 64, 64);

            case plainuibg: return R(2502, 788, 64, 64);
            case postaldudeface: return R(2439, 398, 269, 278);
            case postal1cover: return R(2470, 36, 252, 312);
            case poolofblood: return R(1851, 161, 236, 175);

            case newspaperblank: return R(1034, 2227, 867, 803);
            case newspaper5: return R(146, 2226, 887, 793);
            case newspaper4: return R(999, 1441, 887, 793);
            case newspaper3: return R(62, 1409, 899, 825);
            case newspaper2: return R(970, 581, 881, 846);
            case newspaper1: return R(38, 609, 874, 801);

            case newspaperpickup: return R(1471, 287, 175, 175);
            case mainmenubg: return R(2812, 767, 1280, 720);

            case magicdoorlabel: return R(1205, 260, 184, 226);
            case lockedico: return R(1175, 262, 22, 26);
            case lamp: return R(1100, 265, 56, 154);
            case key: return R(1036, 331, 17, 6);

            case katana: return R(730, 350, 159, 58);
            case fskatana: return R(0, 0, 0, 0);
            case fridge: return R(5, 292, 92, 148);

            case doubledoors: return R(2030, 8, 48, 34);
            case bigdoor: return R(1922, 8, 61, 102);

            case defwhite: return R(1862, 56, 32, 32);
            case defcursor: return R(1874, 30, 2, 2);
            case hqcrate: return R(1545, 1, 237, 228);
            case crackedwall: return R(1441, 76, 63, 55);
            case cahir: return R(1443, 16, 66, 37);
            case chains: return R(1385, 18, 21, 172);
            case aimcursor: return R(1041, 17, 8, 8);
            case cashdesk: return R(915, 38, 452, 188);
            case carpet: return R(647, 155, 208, 107);
            case brokenvendingmashine: return R(531, 158, 50, 93);
            case bloodyoverlay: return R(2801, 15, 1280, 720);
            case barrel: return R(465, 158, 45, 55);
            case valuebar: return R(703, 9, 110, 107);

            case arshoot: return R(478, 85, 198, 64);
            case ar: return R(474, 14, 196, 64);
            case ammo: return R(368, 14, 34, 56);

            case hqfloor: return R(317, 95, 128, 128);
            case defblack: return R(293, 6, 2, 2);

            case corner1: return R(283, 19, 3, 3);
            case corner2: return R(286, 19, 3, 3);
            case corner3: return R(283, 22, 3, 3);
            case corner4: return R(286, 22, 3, 3);

            case stair1: return R(291, 26, 3, 3);
            case stair2: return R(294, 26, 3, 3);
            case stair3: return R(297, 26, 3, 3);
            case stair4: return R(291, 29, 3, 3);
            case stair5: return R(294, 29, 3, 3);
            case stair6: return R(297, 29, 3, 3);
            case stair7: return R(291, 32, 3, 3);
            case stair8: return R(294, 32, 3, 3);
            case stair9: return R(297, 32, 3, 3);

            case semtranswall1: return R(306, 18, 3, 3);
            case semtranswall2: return R(309, 18, 3, 3);
            case semtranswall3: return R(312, 18, 3, 3);
            case semtranswall4: return R(306, 21, 3, 3);
            case semtranswall5: return R(309, 21, 3, 3);
            case semtranswall6: return R(312, 21, 3, 3);
            case semtranswall7: return R(306, 24, 3, 3);
            case semtranswall8: return R(309, 24, 3, 3);
            case semtranswall9: return R(312, 24, 3, 3);

            case hqparticle: return R(0, 0, 270, 270);

            case uicheckmark: return R(849, 663, 697, 777);
            case uirankunranked: return R(1953, 12, 34, 55);
            case uiranksilver: return R(2141, 19, 30, 54);
            case uiranksapphire: return R(2336, 18, 30, 54);
            case uirankiron: return R(2272, 18, 30, 54);
            case uirankgold: return R(2208, 16, 30, 54);
            case uirankdiamond: return R(2087, 22, 30, 54);
            case uirankbronze: return R(2029, 12, 30, 54);

            case mystery_crate_open3: return R(1663, 138, 206, 320);
            case mystery_crate_open2: return R(1385, 146, 206, 320);
            case mystery_crate_open1: return R(1131, 151, 206, 320);
            case mystery_crate_idle: return R(891, 135, 206, 320);

            case talking: return R(37, 2015, 495, 414);
            case talking1: return R(87, 1552, 470, 396);
            case nottalking: return R(76, 1130, 414, 394);
            case nottalking1: return R(62, 694, 431, 392);

            case Suicidevictim_1979: return R(2, 100, 512, 512);

            case readallaboutit: return R(231, 0, 33, 33);
            case noise: return R(198, 0, 33, 33);
            case lockedach: return R(165, 0, 33, 33);
            case hangmanach: return R(132, 0, 33, 33);
            case goodendingach: return R(99, 0, 33, 33);
            case endlesscompletedach: return R(66, 0, 33, 33);
            case bestrankach: return R(33, 0, 33, 33);
            case badendach: return R(0, 0, 33, 33);

            case placeholdertexture:
            default:
                return R(1929, 1263, 128, 128);
        }
    }
}
