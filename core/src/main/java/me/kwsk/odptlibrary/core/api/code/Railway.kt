package me.kwsk.odptlibrary.core.api.code

import me.kwsk.odptlibrary.core.api.common.OdptRailway

/**
 * 路線ID
 */
interface Railway {
    /**
     * OdbtRailway型に変換する
     */
    fun toOdbtRailway(): OdptRailway
}

/**
 * JR東日本路線ID
 */
enum class RailwayJREast(val id: String) : Railway {
    ATERAZAWA("odpt.Railway:JR-East.Aterazawa"),
    UETSU("odpt.Railway:JR-East.Uetsu"),
    OFUNATO("odpt.Railway:JR-East.Ofunato"),
    KITAKAMI("odpt.Railway:JR-East.Kitakami"),
    JOBAN("odpt.Railway:JR-East.Joban"),
    SUIGUN("odpt.Railway:JR-East.Suigun"),
    TOHOKU("odpt.Railway:JR-East.Tohoku"),
    /**
     * 陸羽西線
     */
    RIKU_WEST("odpt.Railway:JR-East.RikuWest"),
    ITSUKAICHI("odpt.Railway:JR-East.Itsukaichi"),
    UCHIBO("odpt.Railway:JR-East.Uchibo"),
    KAWAGOE("odpt.Railway:JR-East.Kawagoe"),
    KEIYO("odpt.Railway:JR-East.Keiyo"),
    SAGAMI("odpt.Railway:JR-East.Sagami"),
    SHONAN_SHINJUKU("odpt.Railway:JR-East.ShonanShinjuku"),
    SHINETSU("odpt.Railway:JR-East.Shinetsu"),
    /**
     * 中央本線
     */
    CHUO("odpt.Railway:JR-East.Chuo"),
    HACHIKO("odpt.Railway:JR-East.Hachiko"),
    MITO("odpt.Railway:JR-East.Mito"),
    MUSASHINO("odpt.Railway:JR-East.Musashino"),
    YOKOSUKA("odpt.Railway:JR-East.Yokosuka"),
    YOKOHAMA("odpt.Railway:JR-East.Yokohama"),
    SHINONOI("odpt.Railway:JR-East.Shinonoi"),
    YAHIKO("odpt.Railway:JR-East.Yahiko"),
    HOKURIKU_SHINKANSEN("odpt.Railway:JR-East.HokurikuShinkansen"),
    UENO_TOKYO("odpt.Railway:JR-East.UenoTokyo"),
    YAMAGATA_SHINKANSEN("odpt.Railway:JR-East.YamagataShinkansen"),
    SENSEKI_TOHOKU("odpt.Railway:JR-East.SensekiTohoku"),
    ISHINOMAKI("odpt.Railway:JR-East.Ishinomaki"),
    IWAIZUMI("odpt.Railway:JR-East.Iwaizumi"),
    /**
     * 奥羽本線
     */
    OU("odpt.Railway:JR-East.Ou"),
    /**
     * 奥羽本線（山形線）
     */
    OU_YAMAGATA("odpt.Railway:JR-East.OuYamagata"),
    OMINATO("odpt.Railway:JR-East.Ominato"),
    OGA("odpt.Railway:JR-East.Oga"),
    KAMAISHI("odpt.Railway:JR-East.Kamaishi"),
    KESENNUMA("odpt.Railway:JR-East.Kesennuma"),
    GONO("odpt.Railway:JR-East.Gono"),
    SENZAN("odpt.Railway:JR-East.Senzan"),
    SENSEKI("odpt.Railway:JR-East.Senseki"),
    TAZAWAKO("odpt.Railway:JR-East.Tazawako"),
    TADAMI("odpt.Railway:JR-East.Tadami"),
    TSUGARU("odpt.Railway:JR-East.Tsugaru"),
    HACHINOHE("odpt.Railway:JR-East.Hachinohe"),
    HANAWA("odpt.Railway:JR-East.Hanawa"),
    /**
     * 磐越西線
     */
    BANETSU_WEST("odpt..Railway:JR-East.BanetsuWest"),
    /**
     * 磐越東線
     */
    BANETSU_EAST("odpt..Railway:JR-East.BanetsuEast"),
    YAMADA("odpt.Railway:JR-East.Yamada"),
    YONESAKA("odpt.Railway:JR-East.Yonesaka"),
    /**
     * 陸羽東線
     */
    RIKU_EAST("odpt.Railway:JR-East.RikuEast"),
    AGATSUMA("odpt.Railway:JR-East.Agatsuma"),
    ITO("odpt.Railway:JR-East.Ito"),
    UTSUNOMIYA("odpt.Railway:JR-East.Utsunomiya"),
    OME("odpt.Railway:JR-East.Ome"),
    KASHIMA("odpt.Railway:JR-East.Kashima"),
    KARASUYAMA("odpt.Railway:JR-East.Karasuyama"),
    KURURI("odpt.Railway:JR-East.Kururi"),
    KEIHIN_TOHOKU("odpt.Railway:JR-East.KeihinTohoku"),
    KOUMI("odpt.Railway:JR-East.Koumi"),
    SAIKYO("odpt.Railway:JR-East.Saikyo"),
    JOETSU("odpt.Railway:JR-East.Joetsu"),
    /**
     * 常磐線快速電車
     */
    JOBAN_RAPID("odpt.Railway:JR-East.JobanRapid"),
    /**
     * 常磐線各駅停車：いわゆる常磐緩行線
     */
    JOBAN_LOCAL("odpt.Railway:JR-East.JobanLocal"),
    /**
     * 総武快速線
     */
    SOBU_RAPID("odpt.Railway:JR-East.SobuRapid"),
    /**
     * 総武本線
     */
    SOBU("odpt.Railway:JR-East.Sobu"),
    SOTOBO("odpt.Railway:JR-East.Sotobo"),
    TAKASAKI("odpt.Railway:JR-East.Takasaki"),
    /**
     * 中央・総武各駅停車：いわゆる総武緩行線
     */
    CHUO_SOBU_LOCAL("odpt.Railway:JR-East.ChuoSobuLocal"),
    /**
     * 中央線快速電車
     */
    CHUO_RAPID("odpt.Railway:JR-East.ChuoRapid"),
    TSURUMI("odpt.Railway:JR-East.Tsurumi"),
    TOKAIDO("odpt.Railway:JR-East.Tokaido"),
    TOGANE("odpt.Railway:JR-East.Togane"),
    NARITA("odpt.Railway:JR-East.Narita"),
    NAMBU("odpt.Railway:JR-East.Nambu"),
    NIKKO("odpt.Railway:JR-East.Nikko"),
    YAMANOTE("odpt.Railway:JR-East.Yamanote"),
    RYOMO("odpt.Railway:JR-East.Ryomo"),
    IIYAMA("odpt.Railway:JR-East.Iiyama"),
    ECHIGO("odpt.Railway:JR-East.Echigo"),
    OITO("odpt.Railway:JR-East.Oito"),
    HAKUSHIN("odpt.Railway:JR-East.Hakushin"),
    AKITA_SHINKANSEN("odpt.Railway:JR-East.AkitaShinkansen"),
    JOETSU_SHINKANSEN("odpt.Railway:JR-East.JoetsuShinkansen"),
    TOHOKU_SHINKANSEN("odpt.Railway:JR-East.TohokuShinkansen");

    override fun toOdbtRailway(): OdptRailway {
        return OdptRailway(id)
    }
}

/**
 * 京急電鉄路線ID
 */
enum class RailwayKeikyu(val id: String) : Railway {
    AIRPORT("odpt.Railway:Keikyu.Airport"),
    KURIHAMA("odpt.Railway:Keikyu.Kurihama"),
    KEIKYU_MAIN("odpt.Railway:Keikyu.Main"),
    KEIKYU_DAISHI("odpt.Railway:Keikyu.Daishi"),
    ZUSHI("odpt.Railway:Keikyu.Zushi");

    override fun toOdbtRailway(): OdptRailway {
        return OdptRailway(id)
    }
}

/**
 * 京王電鉄路線ID
 */
enum class RailwayKeio(val id: String) : Railway {
    KEIO("odpt.Railway:Keio.Keio"),
    KEIO_NEW("odpt.Railway:Keio.KeioNew"),
    KEIBAJO("odpt.Railway:Keio.Keibajo"),
    SAGAMIHARA("odpt.Railway:Keio.Sagamihara"),
    TAKAO("odpt.Railway:Keio.Takao"),
    DOBUTSUEN("odpt.Railway:Keio.Dobutsuen"),
    INOKASHIRA("odpt.Railway:Keio.Inokashira");

    override fun toOdbtRailway(): OdptRailway {
        return OdptRailway(id)
    }
}

/**
 * 京成電鉄路線ID
 */
enum class RailwayKeisei(val id: String) : Railway {
    KANAMACHI("odpt.Railway:Keisei.Kanamachi"),
    KEISEI_MAIN("odpt.Railway:Keisei.Main"),
    HIGASHI_NARITA("odpt.Railway:Keisei.Higashi"),
    OSHIAGE("odpt.Railway:Keisei.Oshiage"),
    CHIBA("odpt.Railway:Keisei.Chiba"),
    CHIHARA("odpt.Railway:Keisei.Chihara"),
    NARITA_SKYACCESS("odpt.Railway:Keisei.NaritaSKYACCESS");

    override fun toOdbtRailway(): OdptRailway {
        return OdptRailway(id)
    }
}

/**
 * 小田急電鉄路線ID
 */
enum class RailwayOdakyu(val id: String) : Railway {
    ODAWARA("odpt.Railway:Odakyu.Odawara"),
    ENOSHIMA("odpt.Railway:Odakyu.Enoshima"),
    TAMA("odpt.Railway:Odakyu.Tama");

    override fun toOdbtRailway(): OdptRailway {
        return OdptRailway(id)
    }
}

/**
 * 西武鉄道路線ID
 */
enum class RailwaySeibu(val id: String) : Railway {
    SEIBU_CHICHIBU("odpt.Railway:Seibu.SeibuChichibu"),
    KOKUBUNJI("odpt.Railway:Seibu.Kokubunji"),
    IKEBUKURO("odpt.Railway:Seibu.Ikebukuro"),
    TOSHIMA("odpt.Railway:Seibu.Toshima"),
    SAYAMA("odpt.Railway:Seibu.Sayama"),
    SHINJUKU("odpt.Railway:Seibu.Shinjuku"),
    TAMAKO("odpt.Railway:Seibu.Tamako"),
    SEIBU_YURAKUCHO("odpt.Railway:Seibu.Seibu-yurakucho"),
    SEIBUEN("odpt.Railway:Seibu.Seibuen"),
    TAMAGAWA("odpt.Railway:Seibu.Tamagawa"),
    YAMAGUCHI("odpt.Railway:Seibu.Yamaguchi");

    override fun toOdbtRailway(): OdptRailway {
        return OdptRailway(id)
    }
}

/**
 * 東武鉄道路線ID
 */
enum class RailwayTobu(val id: String) : Railway {
    TOBU_SKYTREE("odpt.Railway:Tobu.TobuSkytree"),
    KAMEIDO("odpt.Railway:Tobu.Kameido"),
    TOBU_DAISHI("odpt.Railway:Tobu.Daishi"),
    ISESAKI("odpt.Railway:Tobu.Isesaki"),
    SANO("odpt.Railway:Tobu.Sano"),
    KOIZUMI("odpt.Railway:Tobu.Koizumi"),
    KIRYU("odpt.Railway:Tobu.Kiryu"),
    NIKKO("odpt.Railway:Tobu.Kiryu"),
    UTSUNOMIYA("odpt.Railway:Tobu.Utsunomiya"),
    KINUGAWA("odpt.Railway:Tobu.Kinugawa"),
    TOBUURBANPARK("odpt.Railway:Tobu.TobuUrbanPark"),
    TOJO("odpt.Railway:Tobu.Tojo"),
    OGOSE("odpt.Railway:Tobu.Ogose");

    override fun toOdbtRailway(): OdptRailway {
        return OdptRailway(id)
    }
}

/**
 * 東京都交通局路線ID
 */
enum class RailwayToei(val id: String) : Railway {
    MITA("odpt.Railway:Toei.Mita"),
    OEDO("odpt.Railway:Toei.Oedo"),
    ARAKAWA("odpt.Railway:Toei.Arakawa"),
    NIPPORI_TONERI("odpt.Railway:Toei.NipporiToneri"),
    ASAKUSA("odpt.Railway:Toei.Asakusa"),
    SHINJUKU("odpt.Railway:Toei.Shinjuku");

    override fun toOdbtRailway(): OdptRailway {
        return OdptRailway(id)
    }
}

/**
 * 東京メトロ路線ID
 */
enum class RailwayTokyoMetro(val id: String) : Railway {
    GINZA("odpt.Railway:TokyoMetro.Ginza"),
    MARUNOUCHI("odpt.Railway:TokyoMetro.Marunouchi"),
    MARUNOUCHI_BRANCH("odpt.Railway:TokyoMetro.MarunouchiBranch"),
    HIBIYA("odpt.Railway:TokyoMetro.Hibiya"),
    TOZAI("odpt.Railway:TokyoMetro.Tozai"),
    CHIYODA("odpt.Railway:TokyoMetro.Chiyoda"),
    YURAKUCHO("odpt.Railway:TokyoMetro.Yurakucho"),
    HANZOMON("odpt.Railway:TokyoMetro.Hanzomon"),
    NAMBOKU("odpt.Railway:TokyoMetro.Namboku"),
    FUKUTOSHIN("odpt.Railway:TokyoMetro.Fukutoshin");

    override fun toOdbtRailway(): OdptRailway {
        return OdptRailway(id)
    }
}

/**
 * 東急路線ID
 */
enum class RailwayTokyu(val id: String) : Railway {
    TOYOKO("odpt.Railway:Tokyu.Toyoko"),
    MEGURO("odpt.Railway:Tokyu.Meguro"),
    DEN_EN_TOSHI("odpt.Railway:Tokyu.DenEnToshi"),
    OIMACHI("odpt.Railway:Tokyu.Oimachi"),
    IKEGAMI("odpt.Railway:Tokyu.Ikegami"),
    TOKYU_TAMAGAWA("odpt.Railway:Tokyu.TokyuTamagawa"),
    SETAGAYA("odpt.Railway:Tokyu.Setagaya"),
    KODOMONOKUNI("odpt.Railway:Tokyu.Kodomonokuni");

    override fun toOdbtRailway(): OdptRailway {
        return OdptRailway(id)
    }
}

/**
 * 東京臨海高速鉄道路線ID
 */
enum class RailwayTWR(val id: String) : Railway {
    RINKAI("odpt.Railway:TWR.Rinkai");

    override fun toOdbtRailway(): OdptRailway {
        return OdptRailway(id)
    }
}

/**
 * ゆりかもめ路線ID
 */
enum class RailwayYurikamome(val id: String) : Railway {
    YURIKAMOME("odpt.Railway:Yurikamome.Yurikamome");

    override fun toOdbtRailway(): OdptRailway {
        return OdptRailway(id)
    }
}
