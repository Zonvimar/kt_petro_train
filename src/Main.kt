import kotlin.random.Random

fun main() {
    val cities = listOf(
        "Санкт-Петербург", "Москва", "Самара", "Новосибирск", "Казань",
        "Нижний Новгород", "Челябинск", "Омск", "Ростов-на-Дону"
    )

    while (true) {
        println("Введите 'EXIT' для выхода или 'START' для составления плана поезда:")
        when (readlnOrNull()?.uppercase()) {
            "EXIT" -> break
            "START" -> {
                val direction = createDirection(cities)
                println("Создано направление: $direction")

                var passengers = sellTickets()
                println("Количество пассажиров, купивших билеты: $passengers")

                val train = createTrain(passengers)
                println("Сформирован поезд: ${train.size} вагонов.")
                train.forEachIndexed { index, sits ->
                    println("Вагон ${index + 1}: Вместимость - $sits, Пассажиров - ${minOf(passengers, sits)}")
                    passengers -= minOf(passengers, sits)
                }

                println("Поезд $direction, состоящий из ${train.size} вагонов, отправлен.")
            }
            else -> println("Неверный ввод, пожалуйста, попробуйте снова.")
        }
    }
}

fun createDirection(cities: List<String>): String {
    val startCity = cities.random()
    var endCity: String
    do {
        endCity = cities.random()
    } while (endCity == startCity)
    return "$startCity - $endCity"
}

fun sellTickets(): Int {
    return Random.nextInt(5, 201)
}

fun createTrain(passengers: Int): List<Int> {
    val train = mutableListOf<Int>()
    var remainingPass = passengers
    while (remainingPass > 0) {
        val sits = Random.nextInt(5, 25)
        train.add(sits)
        remainingPass -= sits
    }
    return train
}
