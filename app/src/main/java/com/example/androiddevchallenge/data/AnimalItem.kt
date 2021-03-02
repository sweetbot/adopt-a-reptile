/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.data

import androidx.compose.runtime.Immutable

@Immutable // Tell Compose runtime that this object will not change so it can perform optimizations
data class AnimalItem(
    val id: Long,
    val name: String,
    val category: String,
    val thumbUrl: String,
    val thumbContentDesc: String,
    val description: String = "",
    val email: String,
    val likes: Int,
    val like: Int,
    val owner: String = "https://i.pravatar.cc/112?$id"
)

object AnimalItemRepository {
    fun getItem(itemId: Long): AnimalItem = animalItems.find { it.id == itemId }!!
    fun getRelated(@Suppress("UNUSED_PARAMETER") itemId: Long): List<AnimalItem> = animalItems
}

val animalItems = listOf(
    AnimalItem(
        id = 0,
        name = "Gecko 4 new home",
        category = "\uD83E\uDD8E",
        thumbUrl = "https://i.ytimg.com/vi/ZhatTfAlAt0/maxresdefault.jpg",
        thumbContentDesc = "This giant day gecko is gorgeous. Day Geckos are diurnal, typically brightly colored lizards that are known for their tropical aura. These lizards love nectar and prepared sweet diets as well as insects. Day geckos require both UVB and heat lighting and make great pets, as long as you are willing to not handle your pets. Day geckos also make great inhabitants for naturalistic vivarium.",
        email = "fancygiantcroissant@gmail.com",
        likes = 7,
        like = 1
    ),
    AnimalItem(
        id = 1,
        name = "Captive-born baby ball python",
        category = "\uD83D\uDC0D",
        thumbUrl = "https://external-preview.redd.it/Q-H1mCyct8k3w-eAPj3eiWQmAhdrAC9zAdVPSr5O_9A.jpg?width=960&crop=smart&auto=webp&s=46f6e6678c1894ad86222096c533c48aa102955f",
        thumbContentDesc = "This captive-born baby ball python has pieball gene and is absolutely stunning looking. He is unfortunately not suitable for a breeding project due to a kink on his tail. Looking for people who truly love snake as pets.",
        email = "fancygiantcroissant@gmail.com",
        likes = 12,
        like = 1
    ),
    AnimalItem(
        id = 2,
        name = "Disabled tortoise looking for temp care taker",
        category = "\uD83D\uDC22",
        thumbUrl = "https://images.swns.com/wp-content/uploads/2015/06/SWNS_TORTOISE_WHEELS_02.jpg",
        thumbContentDesc = "This is Touche, and his new set of wheels. The disabled tortoise has been fitted with the wheels from a Hotwheels toy car to help him scoot around after he lost a leg.\n" +
            "Eight-year-old Touche had a back leg amputated after an accident at home last week which left him limping. But a creative vet at Highcroft Vetinary Hospital in Whitchurch, Bristol decided to use a special resin and fix the base of a little toy car to the underside of Touche’s shell to help him get back to his old self. The surgery took an hour and a half to complete and was a complete success, with little Touche seemingly un-phased by his new prosthetic.",
        email = "fancygiantcroissant@gmail.com",
        likes = 218,
        like = 1
    ),
    AnimalItem(
        id = 3,
        name = "Cute leopard gecko",
        category = "\uD83E\uDD8E",
        thumbUrl = "https://media1.tenor.com/images/eeaf053bba9d0c1e1aad0e3979e1ccb8/tenor.gif",
        thumbContentDesc = "He is cute when he winks.",
        email = "fancygiantcroissant@gmail.com",
        likes = 9,
        like = 1
    ),
    AnimalItem(
        id = 4,
        name = "Western hognose",
        category = "\uD83D\uDC0D",
        thumbUrl = "https://i.pinimg.com/736x/b0/88/f8/b088f830bc6f120e1d5627673275c498.jpg",
        thumbContentDesc = "A Western hognose does require a secure cage, even though it is not as adept at escape as other snakes. A plastic, 5-gallon reptile terrarium with a secure lid is ideal for a hatchling. These are inexpensive and widely available at most pet stores or reptile shops online that sell reptile supplies. An adult Western hognose can be kept in a 20-gallon aquarium set up. The Western hognose is a ground-dwelling species, so opt for an enclosure with a greater amount of floor space; height is not as important.",
        email = "fancygiantcroissant@gmail.com",
        likes = 19,
        like = 1
    ),
    AnimalItem(
        id = 5,
        name = "Baby bearded dragon looking for loving family",
        category = "\uD83D\uDC0A",
        thumbUrl = "https://oddlycutepets.com/wp-content/uploads/2019/08/canva-photo-editor-8-1024x768.png",
        thumbContentDesc = "These gentle beasts are from Australia but are now readily\n" +
            "available due to their willingness to breed in captivity.\n" +
            "Bearded Dragons make a wonderful pet for both beginners\n" +
            "and advanced reptile keepers. Due to their docile nature and\n" +
            "relative small size (usually 16-20 inches) they have become\n" +
            "quite popular in recent years. These beautiful creatures are\n" +
            "highly recommended for families with small children also due\n" +
            "to their seeming love for attention.",
        email = "fancygiantcroissant@gmail.com",
        likes = 14,
        like = 1
    ),
    AnimalItem(
        id = 6,
        name = "Friendly tokay gecko",
        category = "\uD83E\uDD8E",
        thumbUrl = "https://morphmarket-media.s3.amazonaws.com/media/cache/21/b8/21b8a9b8a030e5274b786d1b7958ee12.jpg",
        thumbContentDesc = "Normally tokays have lousy bites but this one is friendly. My girlfriend is not allowing me to have this gecko.",
        email = "fancygiantcroissant@gmail.com",
        likes = 17,
        like = 1
    ),
    AnimalItem(
        id = 7,
        name = "Pet alligator for pros with legal license only",
        category = "\uD83D\uDC0A",
        thumbUrl = "https://external-content.duckduckgo.com/iu/?u=http%3A%2F%2Fdehayf5mhw1h7.cloudfront.net%2Fwp-content%2Fuploads%2Fsites%2F86%2F2017%2F03%2F09170810%2F3DE84C2400000578-4278440-image-a-176_1488546559349.jpg&f=1&nofb=1",
        thumbContentDesc = "The first thing that is important to do if you are considering keeping an alligator as a pet is to contact your state’s wildlife authority to find out whether or not you can do so legally in that specific area.  It is forbidden to own a pet alligator in most jurisdictions, and exotic pet permits are often only granted to experience reptile handlers that have existing facilities to keep the creatures in.\n" +
            "\n" +
            "If you are granted permission to keep an alligator legally, the next most important thing is to design a proper enclosure for the animal that will not only keep it from escaping, but will also prohibit entry to intruders.  The enclosure should also be sturdy enough to resist damage, as alligators are giant, powerful and aggressive.",
        email = "fancygiantcroissant@gmail.com",
        likes = 22,
        like = 1
    ),
    AnimalItem(
        id = 8,
        name = "Woodie The Green Iguana",
        category = "\uD83E\uDD8E",
        thumbUrl = "https://www.hartz.com/wp-content/uploads/2016/12/Handling_Your_Pet_Reptile_2000x786.jpg",
        thumbContentDesc = "Iguanas are among the most popular pet reptiles. They love to bask in the sun or under an ultraviolet light, and they enjoy a diet of leafy greens and vegetables. Many people don’t realize that iguanas can grow to be quite large, exceeding 6 ft (1.8 m) in length. Iguanas should be properly socialized when they are young to ensure that they can be handled as adults. They require specialized housing and regular veterinary care and may not be a suitable pet for everyone.",
        email = "fancygiantcroissant@gmail.com",
        likes = 14,
        like = 1
    ),
    AnimalItem(
        id = 9,
        name = "Baby Captive-born Madagascar Giant Day Gecko",
        category = "\uD83E\uDD8E",
        thumbUrl = "https://lh3.googleusercontent.com/XGjcAs_M27wRJEvvKasvM67a30PzqA76Lx8aYT8eTFC0mMjaVLvl3Jr5LxMTEo6l5eYs7uCfZFXSROB95bcRMSMpw8-gD0ZORYGjlkd55CekhFXF2kWc7suVs9X-k6edJZUYq0M",
        thumbContentDesc = "Baby Captive-born Madagascar Giant Day Gecko looking for a new home. I am not asking for money.",
        email = "fancygiantcroissant@gmail.com",
        likes = 7,
        like = 1
    ),
    AnimalItem(
        id = 10,
        name = "Mike the corn snake",
        category = "\uD83D\uDC0D",
        thumbUrl = "https://i.pinimg.com/originals/8f/8a/48/8f8a48fe7a7327a65ca687f4f27228b4.jpg",
        thumbContentDesc = "You can change his name. He is pretty cool about that.",
        email = "fancygiantcroissant@gmail.com",
        likes = 6,
        like = 1
    ),
    AnimalItem(
        id = 11,
        name = "Colorful leopard geckos",
        category = "\uD83E\uDD8E",
        thumbUrl = "https://lh4.googleusercontent.com/proxy/NgTW-Qqg6xhaB5eev47zcWyB6rp12xCSX8bm_5fkcITM6wqyeOCK_80TwethyFnQIV4z7ueWPUpHuUQhX53muDen6uU2hw=w1200-h630-p-k-no-nu",
        thumbContentDesc = "Use reptile carpet, tile, or an appropriate semi-arid bioactive soil/substrate. For geckos shorter than 6 inches (15 cm), use reptile carpet—these guys are so small they might accidentally swallow the bedding material, which is bad for them. Scoop the waste when it is noticed and change all their bedding at least once a month.",
        email = "fancygiantcroissant@gmail.com",
        likes = 4,
        like = 1
    ),
    AnimalItem(
        id = 12,
        name = "Lily the Knob tailed gecko",
        category = "\uD83E\uDD8E",
        thumbUrl = "https://c2.staticflickr.com/6/5600/15373826537_ccb5b8817a_b.jpg",
        thumbContentDesc = "knob-tailed gecko is commonly seen in captivity, but unlike other lizard species, this reptile does not like to be handled because they get threatened easily. It is commonly maintained in captivity because it will thrive in any condition as long as the humidity level is controlled. This species may have hardy characteristics, but you still need to provide its need for it to grow healthily.",
        email = "fancygiantcroissant@gmail.com",
        likes = 198,
        like = 1
    )
)
