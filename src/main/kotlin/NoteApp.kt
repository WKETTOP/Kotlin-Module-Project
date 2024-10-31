import java.util.Scanner

class NoteApp {
    private val scanner = Scanner(System.`in`)
    private val archives = mutableListOf<Archive>()
    private val menu = Menu(scanner)

    fun start() {
        mainMenu()
    }

    private fun mainMenu() {
        val options = mutableListOf<Pair<String, () -> Unit>>(
            "Создать архив" to { createArchive() },
            "Просмотреть созданные архивы" to { viewArchive() }
        )
        menu.showMenu(options, isFirstScreen = true)
    }

    private fun createArchive() {
        println("Введите имя архива: ")
        val name = scanner.nextLine()

        if (name.isNotBlank()) {
            archives.add(Archive(name))
            println("Архив $name создан")
        } else {
            println("Имя архива не может быть пустым")
            return
        }
    }

    private fun viewArchive() {
        if (archives.isEmpty()) {
            println("Нет доступных архивов")
            return
        }

        archives.forEachIndexed { index, archive ->
            println("$index. ${archive.name}")
        }

        println("Введите номер архива для просмотра заметок или ${archives.size} для выхода назад: ")
        val input = scanner.nextLine().toIntOrNull()
        if (input == archives.size) return

        if (input != null && input < archives.size) {
            viewNotes(archives[input])
        } else {
            println("Архива под таким номером нет")
            return
        }
    }


    private fun createNote(archive: Archive) {
        println("Введите заголовок заметки: ")
        val title = scanner.nextLine()
        if (title.isBlank()) {
            println("Заголовок заметки не может быть пустым")
            return
        }

        println("Введите содержание заметки: ")
        val content = scanner.nextLine()
        if (content.isBlank()) {
            println("Содержание заметки не может быть пустым")
            return
        }

        archive.notes.add(Note(title, content))
        println("Заметка $title создана и помещена в архив ${archive.name}")
    }

    private fun viewNotes(archive: Archive) {
        val options = mutableListOf<Pair<String, () -> Unit>>(
            "Создать заметку" to { createNote(archive) },
            "Просмотреть созданные заметки" to { listNotes(archive) }
        )

        menu.showMenu(options)
    }

    private fun listNotes(archive: Archive) {
        if (archive.notes.isEmpty()) {
            println("В архиве ${archive.name} нет заметок")
            return
        }

        println("Заметки в архиве ${archive.name}: ")
        archive.notes.forEachIndexed { index, note ->
            println("$index. ${note.title}")
        }

        println("Введите номер заметки для просмотра или ${archive.notes.size} для выхода назад: ")
        val input = scanner.nextLine().toIntOrNull()
        if (input == archive.notes.size) return


        if (input != null && input in archive.notes.indices) {
            val note = archive.notes[input]
            println("Заголовок: ${note.title}")
            println("Содержание: ${note.content}")
        } else {
            println("Некорректный номер заметки")
        }
    }

}
