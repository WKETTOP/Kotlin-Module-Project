data class Note(
    val title: String,
    val content: String
)

class ManagerNote {
    fun addNoteToArchive(archive: Archive, title: String, content: String) {
        archive.notes.add(Note(title, content))
    }
}
