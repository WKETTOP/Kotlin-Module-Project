import java.util.Scanner

class Menu(private val scanner: Scanner) {

    fun showMenu(options: List<Pair<String, () -> Unit>>, isFirstScreen: Boolean = false) {
        while (true) {
            println("Выберите пункт меню:")
            options.forEachIndexed { index, option ->
                println("${index}. ${option.first}")
            }
            if (isFirstScreen == true) {
                println("${options.size}. Выход")
            } else {
                println("${options.size}. Назад")
            }
            println("Введите номер пункта:")

            val enter = scanner.nextLine().toIntOrNull()
            if (isFirstScreen && enter == options.size) {
                println("Выход из программы.")
                break
            } else if (!isFirstScreen && enter == options.size) {
                return
            }
            if (enter != null && enter in options.indices) {
                options[enter].second.invoke()
            } else {
                println("Некорректный ввод. Пожалуйста, введите цифру")
            }
        }
    }
}
