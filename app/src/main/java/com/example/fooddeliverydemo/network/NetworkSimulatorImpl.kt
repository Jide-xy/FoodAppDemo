package com.example.fooddeliverydemo.network

import com.example.fooddeliverydemo.model.Category
import com.example.fooddeliverydemo.model.Food
import com.example.fooddeliverydemo.model.Promo
import com.example.fooddeliverydemo.model.relation.CategoryWithFood
import com.google.gson.Gson
import io.reactivex.Observable
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.SocketPolicy
import javax.inject.Inject
import kotlin.random.Random
import kotlin.random.nextInt

class NetworkSimulatorImpl @Inject constructor(
        private val foodService: FoodService,
        private val mockWebServer: MockWebServer) : NetworkSimulator {

    override fun getAllFood(): Observable<List<CategoryWithFood>> {
        enqueueResponse(generateCategoriesWithFoods())
        return foodService.getAllFood()
    }

    override fun getPromos(): Observable<List<Promo>> {
        enqueueResponse(generatePromos())
        return foodService.getPromos()
    }

    private fun <T> enqueueResponse(
            response: T,
            headers: Map<String, String> = emptyMap(),
            socketPolicy: SocketPolicy = SocketPolicy.KEEP_OPEN
    ) {
        val mockResponse = MockResponse().apply {
            this.socketPolicy = socketPolicy
            for ((key, value) in headers) {
                addHeader(key, value)
            }
        }

        mockWebServer.enqueue(
                mockResponse
                        .setBody(Gson().toJson(response))
        )
    }

    private fun generatePromos(): List<Promo> {
        return listOf(
                Promo(1, "https://picsum.photos/720/1240?random=${Random.Default.nextInt(1..100)}", "https://picsum.photos/720/1240"),
                Promo(2, "https://picsum.photos/720/1240?random=${Random.Default.nextInt(1..100)}", "https://picsum.photos/720/1240"),
                Promo(3, "https://picsum.photos/720/1240?random=${Random.Default.nextInt(1..100)}", "https://picsum.photos/720/1240"),
                Promo(4, "https://picsum.photos/720/1240?random=${Random.Default.nextInt(1..100)}", "https://picsum.photos/720/1240"),
        )
    }

    private fun generateCategoriesWithFoods(): List<CategoryWithFood> {
        return listOf(
                CategoryWithFood(
                        Category(1, "Pizza"),
                        listOf(
                                Food(1, "Pepperoni", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer tincidunt leo vel metus fermentum, sed tincidunt sem pellentesque. Aenean id lorem vulputate, molestie enim nec, blandit lorem. Morbi congue laoreet est, eget feugiat mi imperdiet porta. Pellentesque porttitor, justo ac accumsan pellentesque, massa massa tempus elit, eu cursus diam nunc vitae purus. ",
                                        Random.Default.nextInt(10..10000).toDouble(), Random.Default.nextInt(10..30), Random.Default.nextInt(30..100).toDouble(), "https://picsum.photos/720/1240?random=${Random.Default.nextInt(1..100)}", 1),
                                Food(2, "Meatpie", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer tincidunt leo vel metus fermentum, sed tincidunt sem pellentesque. Aenean id lorem vulputate, molestie enim nec, blandit lorem. Morbi congue laoreet est, eget feugiat mi imperdiet porta. Pellentesque porttitor, justo ac accumsan pellentesque, massa massa tempus elit, eu cursus diam nunc vitae purus. ",
                                        Random.Default.nextInt(10..10000).toDouble(), Random.Default.nextInt(10..30), Random.Default.nextInt(30..100).toDouble(), "https://picsum.photos/720/1240?random=${Random.Default.nextInt(1..100)}", 1),
                                Food(3, "Chicken Pizza", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer tincidunt leo vel metus fermentum, sed tincidunt sem pellentesque. Aenean id lorem vulputate, molestie enim nec, blandit lorem. Morbi congue laoreet est, eget feugiat mi imperdiet porta. Pellentesque porttitor, justo ac accumsan pellentesque, massa massa tempus elit, eu cursus diam nunc vitae purus. ",
                                        Random.Default.nextInt(10..10000).toDouble(), Random.Default.nextInt(10..30), Random.Default.nextInt(30..100).toDouble(), "https://picsum.photos/720/1240?random=${Random.Default.nextInt(1..100)}", 1),
                                Food(4, "Suya Pizza", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer tincidunt leo vel metus fermentum, sed tincidunt sem pellentesque. Aenean id lorem vulputate, molestie enim nec, blandit lorem. Morbi congue laoreet est, eget feugiat mi imperdiet porta. Pellentesque porttitor, justo ac accumsan pellentesque, massa massa tempus elit, eu cursus diam nunc vitae purus. ",
                                        Random.Default.nextInt(10..10000).toDouble(), Random.Default.nextInt(10..30), Random.Default.nextInt(30..100).toDouble(), "https://picsum.photos/720/1240?random=${Random.Default.nextInt(1..100)}", 1),
                        )
                ),
                CategoryWithFood(
                        Category(2, "Sushi"),
                        listOf(
                                Food(5, "Egusi", "Nam ligula dolor, auctor consequat ipsum at, eleifend mollis tellus. Nam vitae lectus in mi consequat dictum. Nam tempus, nibh quis fermentum tempor, lorem leo molestie sem, nec suscipit orci sapien id est. Integer laoreet imperdiet mi et fringilla. Nullam sollicitudin nunc vel felis ultricies posuere. Phasellus finibus non leo a aliquam.",
                                        Random.Default.nextInt(10..10000).toDouble(), Random.Default.nextInt(10..30), Random.Default.nextInt(30..100).toDouble(), "https://picsum.photos/720/1240?random=${Random.Default.nextInt(1..100)}", 2),
                                Food(6, "Afang", "Nam ligula dolor, auctor consequat ipsum at, eleifend mollis tellus. Nam vitae lectus in mi consequat dictum. Nam tempus, nibh quis fermentum tempor, lorem leo molestie sem, nec suscipit orci sapien id est. Integer laoreet imperdiet mi et fringilla. Nullam sollicitudin nunc vel felis ultricies posuere. Phasellus finibus non leo a aliquam.",
                                        Random.Default.nextInt(10..10000).toDouble(), Random.Default.nextInt(10..30), Random.Default.nextInt(30..100).toDouble(), "https://picsum.photos/720/1240?random=${Random.Default.nextInt(1..100)}", 2),
                                Food(7, "Edikaikong", "",
                                        Random.Default.nextInt(10..10000).toDouble(), Random.Default.nextInt(10..30), Random.Default.nextInt(30..100).toDouble(), "https://picsum.photos/720/1240?random=${Random.Default.nextInt(1..100)}", 2),
                                Food(8, "Ewedu", "Nam ligula dolor, auctor consequat ipsum at, eleifend mollis tellus. Nam vitae lectus in mi consequat dictum. Nam tempus, nibh quis fermentum tempor, lorem leo molestie sem, nec suscipit orci sapien id est. Integer laoreet imperdiet mi et fringilla. Nullam sollicitudin nunc vel felis ultricies posuere. Phasellus finibus non leo a aliquam",
                                        Random.Default.nextInt(10..10000).toDouble(), Random.Default.nextInt(10..30), Random.Default.nextInt(30..100).toDouble(), "https://picsum.photos/720/1240?random=${Random.Default.nextInt(1..100)}", 2),
                        )
                ),
                CategoryWithFood(
                        Category(3, "Drinks"),
                        listOf(
                                Food(9, "Coke", "Donec eu commodo leo, egestas mollis sapien. Sed non nibh vel velit feugiat tincidunt et sit amet eros. Donec tristique elementum dolor, eget sollicitudin nisi pellentesque in. Ut sodales arcu in justo convallis convallis. Phasellus convallis magna nec dui imperdiet posuere. Donec blandit nibh dapibus, accumsan urna nec, imperdiet tellus. Vivamus vestibulum odio lacinia nulla cursus, ut gravida libero condimentum",
                                        Random.Default.nextInt(10..10000).toDouble(), Random.Default.nextInt(10..30), Random.Default.nextInt(30..100).toDouble(), "https://picsum.photos/720/1240?random=${Random.Default.nextInt(1..100)}", 3),
                                Food(10, "Fanta", "Donec eu commodo leo, egestas mollis sapien. Sed non nibh vel velit feugiat tincidunt et sit amet eros. Donec tristique elementum dolor, eget sollicitudin nisi pellentesque in. Ut sodales arcu in justo convallis convallis. Phasellus convallis magna nec dui imperdiet posuere. Donec blandit nibh dapibus, accumsan urna nec, imperdiet tellus. Vivamus vestibulum odio lacinia nulla cursus, ut gravida libero condimentum",
                                        Random.Default.nextInt(10..10000).toDouble(), Random.Default.nextInt(10..30), Random.Default.nextInt(30..100).toDouble(), "https://picsum.photos/720/1240?random=${Random.Default.nextInt(1..100)}", 3),
                                Food(11, "Sprite", "Donec eu commodo leo, egestas mollis sapien. Sed non nibh vel velit feugiat tincidunt et sit amet eros. Donec tristique elementum dolor, eget sollicitudin nisi pellentesque in. Ut sodales arcu in justo convallis convallis. Phasellus convallis magna nec dui imperdiet posuere. Donec blandit nibh dapibus, accumsan urna nec, imperdiet tellus. Vivamus vestibulum odio lacinia nulla cursus, ut gravida libero condimentum",
                                        Random.Default.nextInt(10..10000).toDouble(), Random.Default.nextInt(10..30), Random.Default.nextInt(30..100).toDouble(), "https://picsum.photos/720/1240?random=${Random.Default.nextInt(1..100)}", 3),
                                Food(12, "Pepsi", "Donec eu commodo leo, egestas mollis sapien. Sed non nibh vel velit feugiat tincidunt et sit amet eros. Donec tristique elementum dolor, eget sollicitudin nisi pellentesque in. Ut sodales arcu in justo convallis convallis. Phasellus convallis magna nec dui imperdiet posuere. Donec blandit nibh dapibus, accumsan urna nec, imperdiet tellus. Vivamus vestibulum odio lacinia nulla cursus, ut gravida libero condimentum",
                                        Random.Default.nextInt(10..10000).toDouble(), Random.Default.nextInt(10..30), Random.Default.nextInt(30..100).toDouble(), "https://picsum.photos/720/1240?random=${Random.Default.nextInt(1..100)}", 3),
                        )
                )
        )
    }
}