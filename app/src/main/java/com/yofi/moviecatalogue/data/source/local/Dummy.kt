package com.yofi.moviecatalogue.data.source.local

import com.yofi.moviecatalogue.data.source.response.ItemMovie
import com.yofi.moviecatalogue.data.source.response.ItemTvShow

object Dummy {
    fun getDataMovie(): List<ItemMovie> {
        val list = ArrayList<ItemMovie>()

        list.add(
            ItemMovie(
                1,
                "Godzilla vs. Kong",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                "2021",
                6.5,
                "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages."
            )
        )

        list.add(
            ItemMovie(
                2,
                "The Marksman",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6vcDalR50RWa309vBH1NLmG2rjQ.jpg",
                "2021",
                5.6,
                "im Hanson’s quiet life is suddenly disturbed by two people crossing the US/Mexico border – a woman and her young son – desperate to flee a Mexican cartel. After a shootout leaves the mother dead, Jim becomes the boy’s reluctant defender. He embraces his role as Miguel’s protector and will stop at nothing to get him to safety, as they go on the run from the relentless assassins."
            )
        )


        list.add(
            ItemMovie(
                3,
                "The Unholy",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/4ryVgyGJzdBq8ejVIB0snxBqnyM.jpg",
                "2021",
                5.0,
                "Alice, a young hearing-impaired girl who, after a supposed visitation from the Virgin Mary, is inexplicably able to hear, speak and heal the sick. As word spreads and people from near and far flock to witness her miracles, a disgraced journalist hoping to revive his career visits the small New England town to investigate. When terrifying events begin to happen all around, he starts to question if these phenomena are the works of the Virgin Mary or something much more sinister."
            )
        )

        list.add(
            ItemMovie(
                4,
                "I Am All Girls",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/m6bUeV4mczG3z2YXXr5XDKPsQzv.jpg",
                "2021",
                5.7,
                "A special crimes investigator forms an unlikely bond with a serial killer to bring down a global child sex trafficking syndic"
            )
        )

        list.add(
            ItemMovie(
                5,
                "Army of the Dead",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/z8CExJekGrEThbpMXAmCFvvgoJR.jpg",
                "2021",
                5.9,
                "Following a zombie outbreak in Las Vegas, a group of mercenaries take the ultimate gamble: venturing into the quarantine zone to pull off the greatest heist ever attempted."

            )
        )

        list.add(
            ItemMovie(
                6,
                "The Virtuoso",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/vXHzO26mJaOt4VO7ZFiM6No5ScT.jpg",
                "2021",
                4.9,
                "A lonesome stranger with nerves of steel must track down and kill a rogue hitman to satisfy an outstanding debt. But the only information he's been given is a time and location where to find his quarry. No name. No description. Nothing."
            )
        )

        list.add(
            ItemMovie(
                7,
                "Monster Hunter",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/1UCOF11QCw8kcqvce8LKOO6pimh.jpg",
                "2020",
                5.3,
                "A portal transports Cpt. Artemis and an elite unit of soldiers to a strange world where powerful monsters rule with deadly ferocity. Faced with relentless danger, the team encounters a mysterious hunter who may be their only hope to find a way home."
            )
        )

        list.add(
            ItemMovie(
                8,
                "Wonder Woman 1984",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
                "2020",
                5.4,
                "A botched store robbery places Wonder Woman in a global battle against a powerful and mysterious ancient force that puts her powers in jeopardy."
            )
        )

        list.add(
            ItemMovie(
                9,
                "Tom & Jerry",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6KErczPBROQty7QoIsaa6wJYXZi.jpg",
                "2021",
                5.2,
                "Tom the cat and Jerry the mouse get kicked out of their home and relocate to a fancy New York hotel, where a scrappy employee named Kayla will lose her job if she can’t evict Jerry before a high-class wedding at the hotel. Her solution? Hiring Tom to get rid of the pesky mouse."
            )
        )

        list.add(
            ItemMovie(
                10,
                "Nobody",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/oBgWY00bEFeZ9N25wWVyuQddbAo.jpg",
                "2021",
                7.4,
                "Hutch Mansell, a suburban dad, overlooked husband, nothing neighbor — a \"nobody.\" When two thieves break into his home one night, Hutch's unknown long-simmering rage is ignited and propels him on a brutal path that will uncover dark secrets he fought to leave behind."
            )
        )
        return list
    }

    fun getDataTvShow(): ArrayList<ItemTvShow> {
        val list = ArrayList<ItemTvShow>()

        list.add(
            ItemTvShow(
                1,
                "Lucifer",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg",
                "2016",
                8.1,
                "Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape."
            )
        )

        list.add(
            ItemTvShow(
                2,
                "The Good Doctor",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
                "2017",
                8.2,
                "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives"
            )
        )

        list.add(
            ItemTvShow(
                3,
                "The Flash",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                "2014",
                7.7,
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent."
            )
        )

        list.add(
            ItemTvShow(
                4,
                "Cobra Kai",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/4CkQvdUAtoLW3qkjWi7t1MTnW7R.jpg",
                "2018",
                8.6,
                "This Karate Kid sequel series picks up 30 years after the events of the 1984 All Valley Karate Tournament and finds Johnny Lawrence on the hunt for redemption by reopening the infamous Cobra Kai karate dojo. This reignites his old rivalry with the successful Daniel LaRusso, who has been working to maintain the balance in his life without mentor Mr. Miyagi."
            )
        )

        list.add(
            ItemTvShow(
                5,
                "Riverdale",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/wRbjVBdDo5qHAEOVYoMWpM58FSA.jpg",
                "2017",
                6.9,
                "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade."
            )
        )

        list.add(
            ItemTvShow(
                6,
                "Grey's Anatomy",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg",
                "2005",
                7.6,
                "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital."
            )
        )

        list.add(
            ItemTvShow(
                7,
                "Haunted: Latin America",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/7au3qp7xw4fQ8eHEsrzWkFMVNm4.jpg",
                "2021",
                5.3,
                "Real people's terrifying tales of the chilling, unexplained and paranormal come to life with dramatic reenactments in this reality series."
            )
        )

        list.add(
            ItemTvShow(
                8,
                "Lucifer",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg",
                "2016",
                8.1,
                "Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape."
            )
        )

        list.add(
            ItemTvShow(
                9,
                "Elite",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/3NTAbAiao4JLzFQw6YxP1YZppM8.jpg",
                "2018",
                7.5,
                "When three working class kids enroll in the most exclusive school in Spain, the clash between the wealthy and the poor students leads to tragedy."
            )
        )

        list.add(
            ItemTvShow(
                10,
                "WandaVision",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/glKDfE6btIRcVB5zrjspRIs4r52.jpg",
                "2021",
                8.1,
                "Wanda Maximoff and Vision—two super-powered beings living idealized suburban lives—begin to suspect that everything is not as it seems."
            )
        )

        return list
    }
}