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
        facile.add(new Question("image", R.string.jumanji, "https://focus.telerama.fr/967x550/100/2022/12/20/01d02761116546d8e1fc1a8191cb6c4273287507.jpg"));
        facile.add(new Question("image", R.string.john_wick, "https://static1.moviewebimages.com/wordpress/wp-content/uploads/2022/11/Bill-Skarsgard-John-Wick-4.jpg"));
        facile.add(new Question("image", R.string.the_dark_knight, "https://www.leblogducinema.com/wp-content/uploads//2017/09/The-Dark-Knight-1.jpg"));
        facile.add(new Question("image", R.string.the_lord_of_the_rings, "https://www.slate.fr/sites/default/files/styles/1060x523/public/f_1.jpg"));
        facile.add(new Question("image", R.string.toy_story, "https://www.lexpress.fr/resizer/7aNZA1AMtN1C61N754kh-UXaEg4=/970x548/cloudfront-eu-central-1.images.arcpublishing.com/lexpress/ZKXTRZGLP5ATVJ3DBJHHS3STIM.jpg"));
        facile.add(new Question("image", R.string.the_matrix, "https://www.redsharknews.com/hubfs/Unreal_Engine_5_Matrix_Awakens.jpg"));
        facile.add(new Question("image", R.string.avatar, "https://www.gry-online.pl/i/h/17/408718071.jpg"));
        facile.add(new Question("image", R.string.back_to_the_future, "https://www.melty.fr/wp-content/uploads/meltyfr/2022/12/bttf.0.0.jpeg"));
        facile.add(new Question("image", R.string.jurassic_park, "https://cdn-s-www.dna.fr/images/7B4430CD-594B-4CF6-9C71-089C6F7BB3FD/NW_raw/dans-quot-jurassic-world-quot-une-nouvelle-espece-de-dinosaures-est-creee-l-indominus-rex-photo-universal-pictures-allocine-1653555348.jpg"));
        facile.add(new Question("image", R.string.jaws, "https://cdn.aarp.net/content/dam/aarp/entertainment/movies-for-grownups/2020/06/1140-jaws.jpg"));
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
