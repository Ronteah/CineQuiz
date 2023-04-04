package com.example.cinequiz.utils;

import com.example.cinequiz.R;

import java.util.ArrayList;
import java.util.List;

public class ListQuestions {
    private static List<Question> facile;
    private static List<Question> moyen;
    private static List<Question> difficile;
    private static List<Question> marvel;
    private static List<Question> dc;

    public static void init(){
        facile = new ArrayList<>();
        moyen = new ArrayList<>();
        difficile = new ArrayList<>();
        marvel = new ArrayList<>();
        dc = new ArrayList<>();


        facile.add(new Question("image", R.string.kill_bill, "https://static.hitek.fr/img/actualite/ill_m/885399190/killbillvolume1umathurman.jpg"));
        facile.add(new Question("image", R.string.le_parain, "https://www.ecranlarge.com/media/cache/1600x1200/uploads/articles/000/895/772/le-parrain-photo-marlon-brando-1173783-large.jpg"));
        facile.add(new Question("image", R.string.forrest_gump, "https://static.lpnt.fr/images/2020/09/25/20799762lpw-20799798-article-forrest-gump-cinema-jpg_7372886_1250x625.jpg"));
        facile.add(new Question("image", R.string.the_devil_wears_prada, "https://www.programme.tv/imgre/fit/~1~tls~2021~06~21~560fe26f-d593-4687-8d53-d9574215762b.jpeg/660x370/crop-from/top/quality/80/focus-point/1073,336/le-diable-s-habille-en-prada-23-novembre.jpg"));
        facile.add(new Question("image", R.string.titanic, "https://cache.marieclaire.fr/data/photo/w1000_ci/1bk/titanic.jpg"));
        facile.add(new Question("image", R.string.maleficent, "https://fr.web.img5.acsta.net/r_654_368/newsv7/21/07/23/09/48/0722936.jpg"));
        facile.add(new Question("image", R.string.ocean_eight, "https://m.media-amazon.com/images/M/MV5BNTI1YTI3Y2UtNTRmNy00OWNiLWJjOTQtMjU3ZWFlNzIzNjVkXkEyXkFqcGdeQWZlZGVyaWdh._V1_.jpg"));
        facile.add(new Question("image", R.string.ocean_eleven, "https://i2-prod.mirror.co.uk/incoming/article9388644.ece/ALTERNATES/s615b/Oceans_11_OC-7190-RGB.jpg"));
        facile.add(new Question("image", R.string.silver_linings_playbook, "https://static01.nyt.com/images/2012/11/14/arts/video-playbook-anatomy/video-playbook-anatomy-articleLarge.jpg"));
        facile.add(new Question("image", R.string.la_la_land, "https://i.guim.co.uk/img/media/723153af2444e89e56cf02c98c5d911be9bb791b/95_73_1910_1146/master/1910.jpg?width=700&quality=85&auto=format&fit=max&s=48011be6bfc0b3e96b346ad6a5247f13"));
        facile.add(new Question("image", R.string.marriage_story, "https://media.gq.com/photos/5dee891b6d3bed00084df6df/master/pass/marriage-story-yelling-scene-breakdown.jpg"));
        facile.add(new Question("image", R.string.pirates_of_caribbean, "https://www.premiere.fr/sites/default/files/styles/scale_crop_1280x720/public/2018-04/pirates5fin.jpg"));
        facile.add(new Question("image", R.string.pretty_woman, "https://media-cldnry.s-nbcnews.com/image/upload/newscms/2021_06/1673762/dey-young-pretty-woman-mc-main-210212-04.jpg"));
        facile.add(new Question("image", R.string.men_in_black, "https://img.republicworld.com/republic-prod/stories/promolarge/xhdpi/fz2fsg9ovo9kmmaw_1598680250.jpeg?tr=w-1200,h-900"));
        facile.add(new Question("image", R.string.iron_man, "https://tomorrowsociety.com/wp-content/uploads/2020/05/Iron-Man.jpg"));
        facile.add(new Question("image", R.string.star_wars, "https://www.slate.fr/sites/default/files/styles/1060x523/public/sw_3.jpg"));
        facile.add(new Question("image", R.string.jumanji, "https://blackwatchmovies.files.wordpress.com/2020/06/jumanji.jpg?w=640"));
        facile.add(new Question("image", R.string.john_wick, "https://www.thisisbarry.com/wp-content/uploads/JohnWick/dog.jpg"));
        facile.add(new Question("image", R.string.the_dark_knight, "https://compote.slate.com/images/f2076eec-6ac5-46b1-86b8-2717b1d5ddfe.jpeg"));
        facile.add(new Question("image", R.string.the_lord_of_the_rings, "https://imgsrc.cineserie.com/2020/12/le-seigneur-des-anneaux-peter-jackson-revele-sa-scene-preferee-de-la-trilogie-1.jpg?ver=1"));
        facile.add(new Question("image", R.string.toy_story, "https://cdn.vox-cdn.com/thumbor/3m0wBZJ_T60a5QgsCjzLVeMzNzQ=/0x0:2048x858/1200x800/filters:focal(1257x19:1583x345)/cdn.vox-cdn.com/uploads/chorus_image/image/64048301/p062_116c_cs.sel16.1802_RGB_FINAL.0.jpg"));
        facile.add(new Question("image", R.string.the_matrix, "https://1.bp.blogspot.com/-B7QP7yTRCU8/VAZa22MulSI/AAAAAAAAO_Q/XdlI4Jjm8BU/s1600/bestshot_thematrix.jpg"));
        facile.add(new Question("image", R.string.the_matrix, "https://www.redsharknews.com/hubfs/Unreal_Engine_5_Matrix_Awakens.jpg"));
        facile.add(new Question("image", R.string.avatar, "https://www.gry-online.pl/i/h/17/408718071.jpg"));
        facile.add(new Question("image", R.string.back_to_the_future, "https://www.melty.fr/wp-content/uploads/meltyfr/2022/12/bttf.0.0.jpeg"));
        facile.add(new Question("image", R.string.jurassic_park, "https://cdn-s-www.dna.fr/images/7B4430CD-594B-4CF6-9C71-089C6F7BB3FD/NW_raw/dans-quot-jurassic-world-quot-une-nouvelle-espece-de-dinosaures-est-creee-l-indominus-rex-photo-universal-pictures-allocine-1653555348.jpg"));
        facile.add(new Question("image", R.string.jaws, "https://cdn.aarp.net/content/dam/aarp/entertainment/movies-for-grownups/2020/06/1140-jaws.jpg"));

        //mode célébrité
        facile.add(new Question("celebrity", R.string.tom_hanks, "https://static.lpnt.fr/images/2020/09/25/20799762lpw-20799798-article-forrest-gump-cinema-jpg_7372886_1250x625.jpg"));
        facile.add(new Question("celebrity", R.string.meryl_streep, "https://www.programme.tv/imgre/fit/~1~tls~2021~06~21~560fe26f-d593-4687-8d53-d9574215762b.jpeg/660x370/crop-from/top/quality/80/focus-point/1073,336/le-diable-s-habille-en-prada-23-novembre.jpg"));
        facile.add(new Question("celebrity", R.string.leonardo_dicaprio, "https://imgr.cineserie.com/2021/10/440994-r_1920_1080-f_jpg-q_x-xxyxx-1.jpg?imgeng=/f_jpg/cmpr_0/w_660/h_370/m_cropbox&ver=1"));
        facile.add(new Question("celebrity", R.string.angelina_jolie, "https://fr.web.img5.acsta.net/r_654_368/newsv7/21/07/23/09/48/0722936.jpg"));
        facile.add(new Question("celebrity", R.string.george_clooney, "https://i2-prod.mirror.co.uk/incoming/article9388644.ece/ALTERNATES/s615b/Oceans_11_OC-7190-RGB.jpg"));
        facile.add(new Question("celebrity", R.string.jennifer_lawrence, "https://static01.nyt.com/images/2012/11/14/arts/video-playbook-anatomy/video-playbook-anatomy-articleLarge.jpg"));
        facile.add(new Question("celebrity", R.string.brad_pitt, "https://fr.web.img3.acsta.net/newsv7/19/12/13/12/05/2890246.jpg"));
        facile.add(new Question("celebrity", R.string.emma_stone, "https://medias.spotern.com/spots/w640/352/352964-1634741162.jpg"));
        facile.add(new Question("celebrity", R.string.ryan_gosling, "https://i.guim.co.uk/img/media/723153af2444e89e56cf02c98c5d911be9bb791b/95_73_1910_1146/master/1910.jpg?width=700&quality=85&auto=format&fit=max&s=48011be6bfc0b3e96b346ad6a5247f13"));
        facile.add(new Question("celebrity", R.string.scarlett_johansson, "https://www.madmoisellejulie.fr/wp-content/uploads/2018/06/Scarlett-karaoke%CC%81-Lost-in-Translation.jpg"));
        facile.add(new Question("celebrity", R.string.johnny_depp, "https://www.premiere.fr/sites/default/files/styles/scale_crop_1280x720/public/2018-04/pirates5fin.jpg"));
        facile.add(new Question("celebrity", R.string.julia_roberts, "https://media-cldnry.s-nbcnews.com/image/upload/newscms/2021_06/1673762/dey-young-pretty-woman-mc-main-210212-04.jpg"));
        facile.add(new Question("celebrity", R.string.will_smith, "https://img.republicworld.com/republic-prod/stories/promolarge/xhdpi/fz2fsg9ovo9kmmaw_1598680250.jpeg?tr=w-1200,h-900"));
        facile.add(new Question("celebrity", R.string.anne_hathaway, "https://m.media-amazon.com/images/M/MV5BNTI1YTI3Y2UtNTRmNy00OWNiLWJjOTQtMjU3ZWFlNzIzNjVkXkEyXkFqcGdeQWZlZGVyaWdh._V1_.jpg"));
        facile.add(new Question("celebrity", R.string.robert_downey_jr, "https://qph.cf2.quoracdn.net/main-qimg-baefb80d16417e8f5fff353d602f8f62-lq"));
        facile.add(new Question("celebrity", R.string.harrison_ford, "https://bucket.index.hr/b/index/b6136543-e94e-4945-a171-bb6c0841d245.jpg"));
        facile.add(new Question("celebrity", R.string.natalie_portman, "https://m.media-amazon.com/images/M/MV5BODEwOTEwMTgtMTZlNS00YThhLWFiYmMtNjY3ZDU1ZjgyMzQ4XkEyXkFqcGdeQXVyNjUxMjc1OTM@._V1_.jpg"));
        facile.add(new Question("celebrity", R.string.dwayne_johnson, "https://www.ecranlarge.com/media/cache/637x252/uploads/articles/001/411/088/jumanji-bienvenue-dans-la-jungle-photo-dwayne-johnson-1009175-large.jpg"));
        facile.add(new Question("celebrity", R.string.keanu_reeves, "https://www.thisisbarry.com/wp-content/uploads/JohnWick/dog.jpg"));

        //mode replique
        facile.add(new Question("replique", R.string.star_wars, R.string.r_force));
        facile.add(new Question("replique", R.string.casablanca, R.string.r_casablanca));
        facile.add(new Question("replique", R.string.a_few_good_men, R.string.r_truth));
        facile.add(new Question("replique", R.string.the_terminator, R.string.r_terminator));
        facile.add(new Question("replique", R.string.the_shining, R.string.r_shining));
        facile.add(new Question("replique", R.string.the_godfather, R.string.r_godfather));
        facile.add(new Question("replique", R.string.forrest_gump, R.string.r_forrest_gump));
        facile.add(new Question("replique", R.string.jaws, R.string.r_jaws));
        facile.add(new Question("replique", R.string.rocky, R.string.r_rocky));
        facile.add(new Question("replique", R.string.dirty_dancing, R.string.r_dirty_dancing));
        facile.add(new Question("replique", R.string.titanic, R.string.r_titanic));
        facile.add(new Question("replique", R.string.apocalypse_now, R.string.r_apocalypse_now));
        facile.add(new Question("replique", R.string.the_matrix, R.string.r_the_matrix));
        facile.add(new Question("replique", R.string.scarface, R.string.r_scarface));
        facile.add(new Question("replique", R.string.forrest_gump, R.string.r_forest_gump_2));
        facile.add(new Question("replique", R.string.braveheart, R.string.r_braveheart));
        facile.add(new Question("replique", R.string.pulp_fiction, R.string.r_pulp_fiction));
        facile.add(new Question("replique", R.string.the_lion_king, R.string.r_the_lion_king));


        facile.add(new Question("blindtest", R.string.the_lion_king, R.raw.anneaux));
        facile.add(new Question("blindtest", R.string.the_lion_king, R.raw.frozen));
        facile.add(new Question("blindtest", R.string.the_lion_king, R.raw.indiana));
        facile.add(new Question("blindtest", R.string.the_lion_king, R.raw.matrix));

    }

    public static List<Question> getFacile() {
        return facile;
    }

    public static List<Question> getMoyen() {
        return moyen;
    }

    public static List<Question> getDifficile() {
        return difficile;
    }

    public static List<Question> getMarvel() {
        return marvel;
    }

    public static List<Question> getDc() {
        return dc;
    }
}
