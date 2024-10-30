import java.util.Scanner

class NoteApp {
    private val scanner = Scanner(System.`in`)
    private val managerArchive = ManagerArchive()
    private val managerNote = ManagerNote()
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
            managerArchive.addArchive(name)
            println("Архив $name создан")
        } else {
            println("Имя архива не может быть пустым")
            return
        }
    }

    private fun viewArchive() {
        val archives = managerArchive.getArchive()
        if (archives.isEmpty()) {
            println("Нет доступных архивов")
            return
        }

        archives.forEachIndexed { index, archive ->
            println("$index, ${archive.name}")
        }
        println("Введите номер архива для просмотра заметок: ")
        val input = scanner.nextLine().toIntOrNull()
        if (input == archives.size) return

        val selectedArchive = managerArchive.getArchive(input ?: -1)
        if (selectedArchive != null) {
            viewNotes(selectedArchive)
        } else {
            println("Некорректный номер архива.")
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

        managerNote.addNoteToArchive(archive, title, content)
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

        println("Введите номер заметки для просмотра: ")
        val input = scanner.nextLine().toIntOrNull()
        if (input == archive.notes.size) return

        if (input != null && input in archive.notes.indices) {
            val note = archive.notes[input]
            println("Заголовок: ${note.title}")
            println("Содержание: ${note.content}")
        } else {
            println("Некорректный номер заметки.")
        }
    }

}
